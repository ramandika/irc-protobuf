package if4031.tugas.protobuf.Client;


import if4031.tugas.protobuf.ChatApplicationGrpc;
import if4031.tugas.protobuf.MessageProtos;
import if4031.tugas.protobuf.StringGenerator;
import io.grpc.ChannelImpl;
import io.grpc.netty.NegotiationType;
import io.grpc.netty.NettyChannelBuilder;

import java.util.*;

public class IRCClient {
    private static ChatApplicationGrpc.ChatApplicationBlockingStub blockingStub;
    private static int currentUId = -1;
    private static String currentUser = null;
    private static List<MessageProtos.MessageRecv> myMessages = new ArrayList<MessageProtos.MessageRecv>();
    private static boolean isExit=false;

    public static void main(String[] args) {
        try {
            ChannelImpl channel = NettyChannelBuilder.forAddress("127.0.0.1",9090).
                   negotiationType(NegotiationType.PLAINTEXT).build();
            blockingStub = ChatApplicationGrpc.newBlockingStub(channel);
            IRCClient client = new IRCClient();
            client.startClient();
            channel.shutdown();
        } catch (Exception x) {
            x.printStackTrace();
        }

    }


    private void startClient(){
        Thread inputHandler = perform();
        Thread messageReceiver = messageReceiver();
        inputHandler.start();
        messageReceiver.start();
        try {
            messageReceiver.join();
            inputHandler.join();
        } catch (InterruptedException e) {
            throw new IllegalStateException(e);
        }
    }

    private Thread messageReceiver(){
        return new Thread(){
            @Override
            public void run() {
                final Timer timer=new Timer();
                final TimerTask asyncTask= new TimerTask() {
                    @Override
                    public void run() {
                        try {
                            if(!isExit){
                                if (currentUId > -1 && currentUser != null) {
                                    synchronized (blockingStub) {
                                        MessageProtos.TypeNative.Builder request = MessageProtos.TypeNative.newBuilder();
                                        request.setResponseType("int");
                                        request.setValue(String.valueOf(currentUId));
                                        myMessages = blockingStub.pullMessage(request.build()).getBunchChatList();
                                    }
                                    if (!myMessages.isEmpty()) {
                                        for (MessageProtos.MessageRecv m : myMessages) {
                                            System.out.println("[" + m.getChname() + "] (" + m.getNickname() + "):" + m.getMessage() + " (" + m.getTime() + ")");
                                        }
                                    }
                                }
                            }else{
                                timer.cancel();
                                timer.purge();
                                System.out.println("Bye");

                            }

                        } catch (Exception x) {
                            x.printStackTrace();
                        }
                    }
                };
                timer.schedule(asyncTask, 0, 100);
            }
        };
    }

    private Thread perform() {
        return new Thread() {
            @Override
            public void run() {
                try {

                    Scanner client_input = new Scanner(System.in);
                    String buffer, content;
                    boolean exit=false;
                    while (!exit) {
                        String input = client_input.nextLine();
                        if (!input.isEmpty()) {
                            if (input.contains(" ")) {
                                buffer = input.substring(0, input.indexOf(' '));
                                content = input.substring(input.indexOf(' ') + 1, input.length());
                            } else {
                                buffer = input;
                                content = input;
                            }
                            if (buffer.length() > 0) {
                                switch (buffer) {
                                    case "/NICK":
                                        if (!content.isEmpty()) {
                                            if (currentUser == null) {
                                                synchronized (blockingStub) {
                                                    MessageProtos.TypeNative.Builder request = MessageProtos.TypeNative.newBuilder();
                                                    request.setResponseType("String");
                                                    request.setValue(content);
                                                    currentUId = new Integer(blockingStub.createNick(request.build()).getValue());
                                                }
                                                if (currentUId > -1) {
                                                    currentUser = content;
                                                } else {
                                                    System.out.println("Username exist ! Please use another nickname");
                                                }
                                            } else {
                                                System.out.println("You are logged in as " + currentUser + ". Reopen the client to use another nickname");
                                            }
                                        } else {
                                            StringGenerator tempNick = new StringGenerator(8);
                                            MessageProtos.TypeNative.Builder request = MessageProtos.TypeNative.newBuilder();
                                            request.setResponseType("String");
                                            request.setValue(tempNick.nextString());
                                            currentUser = tempNick.nextString();
                                            synchronized (blockingStub) {
                                                currentUId = new Integer(blockingStub.createNick(request.build()).getValue());
                                            }
                                        }
                                        break;
                                    case "/JOIN":
                                        if (!content.isEmpty()) {
                                            synchronized (blockingStub) {
                                                MessageProtos.UserRequest.Builder request = MessageProtos.UserRequest.newBuilder();
                                                request.setUserid(currentUId);
                                                request.setData(content);
                                                blockingStub.joinChannel(request.build());
                                                System.out.println("You have joined " + content + " channel");
                                            }
                                        } else {
                                            synchronized (blockingStub) {
                                                MessageProtos.UserRequest.Builder request = MessageProtos.UserRequest.newBuilder();
                                                request.setUserid(currentUId);
                                                request.setData("general");
                                                blockingStub.joinChannel(request.build());
                                                System.out.println("You have joined general channel");
                                            }
                                        }
                                        break;
                                    case "/LEAVE":
                                        if (!content.isEmpty()) {
                                            synchronized (blockingStub) {
                                                MessageProtos.UserRequest.Builder request = MessageProtos.UserRequest.newBuilder();;
                                                request.setUserid(currentUId);
                                                request.setData(content);
                                                if (!Boolean.valueOf(blockingStub.leaveChannel(request.build()).getValue())) {
                                                    System.out.println("channel not found");
                                                }
                                            }
                                        }
                                        break;
                                    case "/EXIT":
                                        synchronized (blockingStub) {
                                            MessageProtos.TypeNative.Builder request = MessageProtos.TypeNative.newBuilder();
                                            request.setResponseType("int");
                                            request.setValue(String.valueOf(currentUId));
                                            blockingStub.exit(request.build());
                                            exit=true;
                                            break;
                                        }
                                    default:
                                        MessageProtos.MessageSend.Builder M = MessageProtos.MessageSend.newBuilder();
                                        M.setUserid(currentUId);
                                        synchronized (blockingStub) {
                                            M.setTime(blockingStub.getServerTime(MessageProtos.UserRequest.getDefaultInstance()).getValue());
                                        }
                                        if (buffer.contains("@")) {
                                            M.setChname(buffer.substring(1));
                                            M.setMessage(content);
                                        } else {
                                            M.setChname("");
                                            M.setMessage(input);
                                        }
                                        synchronized (blockingStub) {
                                            String boo=blockingStub.sendMessage(M.build()).getValue();
                                            if (!Boolean.valueOf(boo)) {
                                                System.out.println("channel unknown, please join a channel or recheck your message");
                                            }
                                        }
                                }
                            }
                        } else {
                            System.out.println("format input wrong");
                        }

                    }
                } catch (Exception x) {
                    x.printStackTrace();
                }
                isExit=true;
            }
        };
    }

}

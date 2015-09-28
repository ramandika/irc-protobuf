package if4031.tugas.protobuf;

import io.grpc.stub.StreamObserver;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Stanley on 9/28/2015.
 */
public class ChatHandler implements ChatApplicationGrpc.ChatApplication{

    private Integer lastUId = 0;
    private static Map<Integer,List<MessageProtos.MessageSend>> chatbox; //<<usrid,list of message>>
    private static Map<Integer,String> activeUsers; //<usrid,nickname>>
    private static Map<String,ArrayList<Integer>> channelActive; //<<chname,lisf of userid>>
    private static Map<Integer,ArrayList<String>> userChannels; //<<usrid,list of channel>>
    private Object lockChannel=new Object();
    private Map<String,Object> lockusers;
    private Map<Integer,Object> lockchatbox;

    public ChatHandler() {
        chatbox = new HashMap<Integer, List<MessageProtos.MessageSend>>();
        activeUsers = new HashMap<Integer, String>();
        channelActive = new HashMap<String, ArrayList<Integer>>();
        userChannels = new HashMap<Integer, ArrayList<String>>();
        lockChannel = new ArrayList<Object>();
        lockusers = new HashMap<>();
        lockchatbox = new HashMap<>();
    }
    @Override
    public void sendMessage(MessageProtos.MessageSend request, StreamObserver<MessageProtos.TypeNative> responseObserver) {
        try {
            int UId = request.getUserid();
            String chname = request.getChname();
            if (chname == null) {//Send to all channel user belongs to
                ArrayList<String> channels = userChannels.get(request.getUserid());

                if(channels.size()>0) {
                    for (String channel : channels) {
                        synchronized (lockusers.get(channel)) {
                            ArrayList<Integer> users = channelActive.get(channel);
                            request.newBuilderForType().setChname(channel);
                            if (users.contains(UId)) {
                                for (Integer user : users) {
                                    synchronized (lockchatbox.get(user)) {
                                        List<MessageProtos.MessageSend> messages = chatbox.get(user);
                                        MessageProtos.MessageSend r =  MessageProtos.MessageSend.newBuilder().
                                                setChname(request.getChname()).
                                                setMessage(request.getMessage()).
                                                setUserid(request.getUserid()).
                                                setTime(request.getTime()).build();
                                        messages.add(UId,r);
                                    }
                                }
                            } else {
                                throw new Exception("a");
                            }
                        }
                    }
                }
                else{
                    throw new Exception("b");
                }
            } else {
                synchronized (lockusers.get(chname)) {
                    ArrayList<Integer> users = channelActive.get(chname);
                    if (users.contains(UId)) {
                        for (Integer user : users) {
                            synchronized (lockchatbox.get(user)) {
                                List<MessageProtos.MessageSend> messages = chatbox.get(user);
                                MessageProtos.MessageSend r =  MessageProtos.MessageSend.newBuilder().
                                        setChname(request.getChname()).
                                        setMessage(request.getMessage()).
                                        setUserid(request.getUserid()).
                                        setTime(request.getTime()).build();
                                messages.add(UId,r);
                            }
                        }
                    } else {
                        throw new Exception("c");
                    }
                }
            }
        } catch(Exception x){
            System.out.println(x.getMessage());
            System.out.println(request.getChname() +" channel not found");
            responseObserver.onValue(MessageProtos.TypeNative.newBuilder().setResponseType("boolean").setValue(String.valueOf(false)).build());
        }
        responseObserver.onValue(MessageProtos.TypeNative.newBuilder().setResponseType("boolean").setValue(String.valueOf(true)).build());
        responseObserver.onCompleted();
    }

    @Override
    public void joinChannel(MessageProtos.UserRequest request, StreamObserver<MessageProtos.TypeNative> responseObserver) {
        String chname = request.getData();
        int uId = request.getUserid();
        synchronized (lockChannel) {
            if (channelActive.get(chname) != null) {//channel found
                ArrayList<Integer> users = channelActive.get(chname);
                users.add(uId);
                if (userChannels.get(uId) != null) {
                    ArrayList<String> channels = userChannels.get(uId);
                    channels.add(chname);
                } else {
                    ArrayList<String> channels = new ArrayList<String>();
                    channels.add(chname);
                    userChannels.put(uId, channels);
                }
            } else {//channel not found
                ArrayList<Integer> users = new ArrayList<Integer>();
                users.add(uId);
                channelActive.put(chname, users);
                System.out.println("channel " + chname + " created");
                if (userChannels.get(uId) != null) {
                    ArrayList<String> channels = userChannels.get(uId);
                    channels.add(chname);
                } else {
                    ArrayList<String> channels = new ArrayList<String>();
                    channels.add(chname);
                    userChannels.put(uId, channels);
                }
                lockusers.put(chname,new Object());
            }
            System.out.println(activeUsers.get(uId) + " join " + chname);
            responseObserver.onValue(MessageProtos.TypeNative.newBuilder().setResponseType("boolean").setValue(String.valueOf(true)).build());
            responseObserver.onCompleted();
        }
    }

    @Override
    public void leaveChannel(MessageProtos.UserRequest request, StreamObserver<MessageProtos.TypeNative> responseObserver) {
        String chname = request.getData();
        int uId = request.getUserid();
        ArrayList<Integer> users=channelActive.get(chname);
        ArrayList<String> channels=userChannels.get(uId);
        try{
            if(users!=null && channels!=null) {
                users.remove(new Integer(uId));
                channels.remove(chname);
                System.out.println(activeUsers.get(uId) + " leave " + chname);
                responseObserver.onValue(MessageProtos.TypeNative.newBuilder().setResponseType("boolean").setValue(String.valueOf(true)).build());
                responseObserver.onCompleted();
            }
        } catch(Exception x){
            x.printStackTrace();
            responseObserver.onValue(MessageProtos.TypeNative.newBuilder().setResponseType("boolean").setValue(String.valueOf(false)).build());
            responseObserver.onCompleted();
        }
    }

    @Override
    public void pullMessage(MessageProtos.TypeNative request, StreamObserver<MessageProtos.ChatBunch> responseObserver) {
        int userId = new Integer(request.getValue());
        MessageProtos.ChatBunch.Builder l= MessageProtos.ChatBunch.newBuilder();
        if(chatbox.get(userId)!=null) {
            synchronized (lockchatbox.get(userId)) {
                for (MessageProtos.MessageSend m : chatbox.get(userId)) {
                    SimpleDateFormat sdf = new SimpleDateFormat("MMM dd,yyyy HH:mm");
                    Date resultdate = new Date(m.getTime());
                    MessageProtos.MessageRecv temp = MessageProtos.MessageRecv.newBuilder().setNickname(activeUsers.get(m.getUserid())).
                            setTime(sdf.format(resultdate)).
                            setMessage(m.getMessage()).
                            setChname(m.getChname())
                            .build();
                    l.addBunchChat(temp);
                }
                chatbox.get(userId).clear();
                responseObserver.onValue(l.build());
                responseObserver.onCompleted();
            }
        }
    }

    @Override
    public void createNick(MessageProtos.TypeNative request, StreamObserver<MessageProtos.TypeNative> responseObserver) {
        String nickname = request.getValue();
        synchronized (this) {
            for (Map.Entry<Integer, String> entry : activeUsers.entrySet()) {
                if (nickname.equals(entry.getValue())) {
                    responseObserver.onValue(MessageProtos.TypeNative.newBuilder().setResponseType("int").setValue(String.valueOf(-1)).build()); //username exist
                }
            }
            List<MessageProtos.MessageSend> messages = new ArrayList<MessageProtos.MessageSend>();
            activeUsers.put(lastUId, nickname);
            chatbox.put(lastUId, messages);
            lockchatbox.put(lastUId,new Object());
            System.out.println("created new User : " + nickname + " " + lastUId);
            lastUId++;
            responseObserver.onValue(MessageProtos.TypeNative.newBuilder().setResponseType("int").setValue(String.valueOf(lastUId - 1)).build());
            responseObserver.onCompleted();
        }
    }

    @Override
    public void exit(MessageProtos.TypeNative request, StreamObserver<MessageProtos.TypeNative> responseObserver) {
        int uId = new Integer(request.getValue());
        userChannels.remove(uId);
        activeUsers.remove(uId);
        chatbox.remove(uId);
        lockchatbox.remove(uId);
        responseObserver.onValue(MessageProtos.TypeNative.newBuilder().setResponseType("boolean").setValue(String.valueOf(true)).build());
        responseObserver.onCompleted();
    }

    @Override
    public void getServerTime(MessageProtos.UserRequest request, StreamObserver<MessageProtos.TypeNative> responseObserver) {
        responseObserver.onValue(MessageProtos.TypeNative.newBuilder().setResponseType("String").setValue(String.valueOf(System.currentTimeMillis())).build());
        responseObserver.onCompleted();
    }
}

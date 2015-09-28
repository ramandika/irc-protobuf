package if4031.tugas.protobuf.Server;

import if4031.tugas.protobuf.ChatApplicationGrpc;
import if4031.tugas.protobuf.ChatHandler;
import io.grpc.ServerImpl;
import io.grpc.netty.NettyServerBuilder;

/**
 * Created by Stanley on 9/28/2015.
 */
public class IRCServer {
    final static int PORT = 9090;
    public static void main(String[] args){
        Runnable simple = IRCServer::simple;
        new Thread(simple).start();
    }

    public static void simple(){
        try{
            ServerImpl gRpcServer = NettyServerBuilder.forPort(PORT)
                    .addService(ChatApplicationGrpc.bindService(new ChatHandler()))
                    .build().start();
            System.out.println("Server started on port " + PORT);
            gRpcServer.awaitTermination();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}

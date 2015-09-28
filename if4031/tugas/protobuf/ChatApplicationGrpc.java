package if4031.tugas.protobuf;

import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;

@javax.annotation.Generated("by gRPC proto compiler")
public class ChatApplicationGrpc {

  // Static method descriptors that strictly reflect the proto.
  public static final io.grpc.MethodDescriptor<MessageProtos.MessageSend,
      MessageProtos.TypeNative> METHOD_SEND_MESSAGE =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          "irc.ChatApplication", "sendMessage",
          io.grpc.protobuf.ProtoUtils.marshaller(MessageProtos.MessageSend.parser()),
          io.grpc.protobuf.ProtoUtils.marshaller(MessageProtos.TypeNative.parser()));
  public static final io.grpc.MethodDescriptor<MessageProtos.UserRequest,
      MessageProtos.TypeNative> METHOD_JOIN_CHANNEL =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          "irc.ChatApplication", "joinChannel",
          io.grpc.protobuf.ProtoUtils.marshaller(MessageProtos.UserRequest.parser()),
          io.grpc.protobuf.ProtoUtils.marshaller(MessageProtos.TypeNative.parser()));
  public static final io.grpc.MethodDescriptor<MessageProtos.UserRequest,
      MessageProtos.TypeNative> METHOD_LEAVE_CHANNEL =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          "irc.ChatApplication", "leaveChannel",
          io.grpc.protobuf.ProtoUtils.marshaller(MessageProtos.UserRequest.parser()),
          io.grpc.protobuf.ProtoUtils.marshaller(MessageProtos.TypeNative.parser()));
  public static final io.grpc.MethodDescriptor<MessageProtos.TypeNative,
      MessageProtos.ChatBunch> METHOD_PULL_MESSAGE =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          "irc.ChatApplication", "pullMessage",
          io.grpc.protobuf.ProtoUtils.marshaller(MessageProtos.TypeNative.parser()),
          io.grpc.protobuf.ProtoUtils.marshaller(MessageProtos.ChatBunch.parser()));
  public static final io.grpc.MethodDescriptor<MessageProtos.TypeNative,
      MessageProtos.TypeNative> METHOD_CREATE_NICK =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          "irc.ChatApplication", "createNick",
          io.grpc.protobuf.ProtoUtils.marshaller(MessageProtos.TypeNative.parser()),
          io.grpc.protobuf.ProtoUtils.marshaller(MessageProtos.TypeNative.parser()));
  public static final io.grpc.MethodDescriptor<MessageProtos.TypeNative,
      MessageProtos.TypeNative> METHOD_EXIT =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          "irc.ChatApplication", "exit",
          io.grpc.protobuf.ProtoUtils.marshaller(MessageProtos.TypeNative.parser()),
          io.grpc.protobuf.ProtoUtils.marshaller(MessageProtos.TypeNative.parser()));
  public static final io.grpc.MethodDescriptor<MessageProtos.UserRequest,
      MessageProtos.TypeNative> METHOD_GET_SERVER_TIME =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          "irc.ChatApplication", "getServerTime",
          io.grpc.protobuf.ProtoUtils.marshaller(MessageProtos.UserRequest.parser()),
          io.grpc.protobuf.ProtoUtils.marshaller(MessageProtos.TypeNative.parser()));

  public static ChatApplicationStub newStub(io.grpc.Channel channel) {
    return new ChatApplicationStub(channel);
  }

  public static ChatApplicationBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new ChatApplicationBlockingStub(channel);
  }

  public static ChatApplicationFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new ChatApplicationFutureStub(channel);
  }

  public static interface ChatApplication {

    public void sendMessage(MessageProtos.MessageSend request,
                            io.grpc.stub.StreamObserver<MessageProtos.TypeNative> responseObserver);

    public void joinChannel(MessageProtos.UserRequest request,
                            io.grpc.stub.StreamObserver<MessageProtos.TypeNative> responseObserver);

    public void leaveChannel(MessageProtos.UserRequest request,
                             io.grpc.stub.StreamObserver<MessageProtos.TypeNative> responseObserver);

    public void pullMessage(MessageProtos.TypeNative request,
                            io.grpc.stub.StreamObserver<MessageProtos.ChatBunch> responseObserver);

    public void createNick(MessageProtos.TypeNative request,
                           io.grpc.stub.StreamObserver<MessageProtos.TypeNative> responseObserver);

    public void exit(MessageProtos.TypeNative request,
                     io.grpc.stub.StreamObserver<MessageProtos.TypeNative> responseObserver);

    public void getServerTime(MessageProtos.UserRequest request,
                              io.grpc.stub.StreamObserver<MessageProtos.TypeNative> responseObserver);
  }

  public static interface ChatApplicationBlockingClient {

    public MessageProtos.TypeNative sendMessage(MessageProtos.MessageSend request);

    public MessageProtos.TypeNative joinChannel(MessageProtos.UserRequest request);

    public MessageProtos.TypeNative leaveChannel(MessageProtos.UserRequest request);

    public MessageProtos.ChatBunch pullMessage(MessageProtos.TypeNative request);

    public MessageProtos.TypeNative createNick(MessageProtos.TypeNative request);

    public MessageProtos.TypeNative exit(MessageProtos.TypeNative request);

    public MessageProtos.TypeNative getServerTime(MessageProtos.UserRequest request);
  }

  public static interface ChatApplicationFutureClient {

    public com.google.common.util.concurrent.ListenableFuture<MessageProtos.TypeNative> sendMessage(
            MessageProtos.MessageSend request);

    public com.google.common.util.concurrent.ListenableFuture<MessageProtos.TypeNative> joinChannel(
            MessageProtos.UserRequest request);

    public com.google.common.util.concurrent.ListenableFuture<MessageProtos.TypeNative> leaveChannel(
            MessageProtos.UserRequest request);

    public com.google.common.util.concurrent.ListenableFuture<MessageProtos.ChatBunch> pullMessage(
            MessageProtos.TypeNative request);

    public com.google.common.util.concurrent.ListenableFuture<MessageProtos.TypeNative> createNick(
            MessageProtos.TypeNative request);

    public com.google.common.util.concurrent.ListenableFuture<MessageProtos.TypeNative> exit(
            MessageProtos.TypeNative request);

    public com.google.common.util.concurrent.ListenableFuture<MessageProtos.TypeNative> getServerTime(
            MessageProtos.UserRequest request);
  }

  public static class ChatApplicationStub extends io.grpc.stub.AbstractStub<ChatApplicationStub>
      implements ChatApplication {
    private ChatApplicationStub(io.grpc.Channel channel) {
      super(channel);
    }

    private ChatApplicationStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected ChatApplicationStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new ChatApplicationStub(channel, callOptions);
    }

    @Override
    public void sendMessage(MessageProtos.MessageSend request,
        io.grpc.stub.StreamObserver<MessageProtos.TypeNative> responseObserver) {
      asyncUnaryCall(
          channel.newCall(METHOD_SEND_MESSAGE, callOptions), request, responseObserver);
    }

    @Override
    public void joinChannel(MessageProtos.UserRequest request,
        io.grpc.stub.StreamObserver<MessageProtos.TypeNative> responseObserver) {
      asyncUnaryCall(
          channel.newCall(METHOD_JOIN_CHANNEL, callOptions), request, responseObserver);
    }

    @Override
    public void leaveChannel(MessageProtos.UserRequest request,
        io.grpc.stub.StreamObserver<MessageProtos.TypeNative> responseObserver) {
      asyncUnaryCall(
          channel.newCall(METHOD_LEAVE_CHANNEL, callOptions), request, responseObserver);
    }

    @Override
    public void pullMessage(MessageProtos.TypeNative request,
        io.grpc.stub.StreamObserver<MessageProtos.ChatBunch> responseObserver) {
      asyncUnaryCall(
          channel.newCall(METHOD_PULL_MESSAGE, callOptions), request, responseObserver);
    }

    @Override
    public void createNick(MessageProtos.TypeNative request,
        io.grpc.stub.StreamObserver<MessageProtos.TypeNative> responseObserver) {
      asyncUnaryCall(
          channel.newCall(METHOD_CREATE_NICK, callOptions), request, responseObserver);
    }

    @Override
    public void exit(MessageProtos.TypeNative request,
        io.grpc.stub.StreamObserver<MessageProtos.TypeNative> responseObserver) {
      asyncUnaryCall(
          channel.newCall(METHOD_EXIT, callOptions), request, responseObserver);
    }

    @Override
    public void getServerTime(MessageProtos.UserRequest request,
        io.grpc.stub.StreamObserver<MessageProtos.TypeNative> responseObserver) {
      asyncUnaryCall(
          channel.newCall(METHOD_GET_SERVER_TIME, callOptions), request, responseObserver);
    }
  }

  public static class ChatApplicationBlockingStub extends io.grpc.stub.AbstractStub<ChatApplicationBlockingStub>
      implements ChatApplicationBlockingClient {
    private ChatApplicationBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private ChatApplicationBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected ChatApplicationBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new ChatApplicationBlockingStub(channel, callOptions);
    }

    @Override
    public MessageProtos.TypeNative sendMessage(MessageProtos.MessageSend request) {
      return blockingUnaryCall(
          channel.newCall(METHOD_SEND_MESSAGE, callOptions), request);
    }

    @Override
    public MessageProtos.TypeNative joinChannel(MessageProtos.UserRequest request) {
      return blockingUnaryCall(
          channel.newCall(METHOD_JOIN_CHANNEL, callOptions), request);
    }

    @Override
    public MessageProtos.TypeNative leaveChannel(MessageProtos.UserRequest request) {
      return blockingUnaryCall(
          channel.newCall(METHOD_LEAVE_CHANNEL, callOptions), request);
    }

    @Override
    public MessageProtos.ChatBunch pullMessage(MessageProtos.TypeNative request) {
      return blockingUnaryCall(
          channel.newCall(METHOD_PULL_MESSAGE, callOptions), request);
    }

    @Override
    public MessageProtos.TypeNative createNick(MessageProtos.TypeNative request) {
      return blockingUnaryCall(
          channel.newCall(METHOD_CREATE_NICK, callOptions), request);
    }

    @Override
    public MessageProtos.TypeNative exit(MessageProtos.TypeNative request) {
      return blockingUnaryCall(
          channel.newCall(METHOD_EXIT, callOptions), request);
    }

    @Override
    public MessageProtos.TypeNative getServerTime(MessageProtos.UserRequest request) {
      return blockingUnaryCall(
          channel.newCall(METHOD_GET_SERVER_TIME, callOptions), request);
    }
  }

  public static class ChatApplicationFutureStub extends io.grpc.stub.AbstractStub<ChatApplicationFutureStub>
      implements ChatApplicationFutureClient {
    private ChatApplicationFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private ChatApplicationFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected ChatApplicationFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new ChatApplicationFutureStub(channel, callOptions);
    }

    @Override
    public com.google.common.util.concurrent.ListenableFuture<MessageProtos.TypeNative> sendMessage(
        MessageProtos.MessageSend request) {
      return futureUnaryCall(
          channel.newCall(METHOD_SEND_MESSAGE, callOptions), request);
    }

    @Override
    public com.google.common.util.concurrent.ListenableFuture<MessageProtos.TypeNative> joinChannel(
        MessageProtos.UserRequest request) {
      return futureUnaryCall(
          channel.newCall(METHOD_JOIN_CHANNEL, callOptions), request);
    }

    @Override
    public com.google.common.util.concurrent.ListenableFuture<MessageProtos.TypeNative> leaveChannel(
        MessageProtos.UserRequest request) {
      return futureUnaryCall(
          channel.newCall(METHOD_LEAVE_CHANNEL, callOptions), request);
    }

    @Override
    public com.google.common.util.concurrent.ListenableFuture<MessageProtos.ChatBunch> pullMessage(
        MessageProtos.TypeNative request) {
      return futureUnaryCall(
          channel.newCall(METHOD_PULL_MESSAGE, callOptions), request);
    }

    @Override
    public com.google.common.util.concurrent.ListenableFuture<MessageProtos.TypeNative> createNick(
        MessageProtos.TypeNative request) {
      return futureUnaryCall(
          channel.newCall(METHOD_CREATE_NICK, callOptions), request);
    }

    @Override
    public com.google.common.util.concurrent.ListenableFuture<MessageProtos.TypeNative> exit(
        MessageProtos.TypeNative request) {
      return futureUnaryCall(
          channel.newCall(METHOD_EXIT, callOptions), request);
    }

    @Override
    public com.google.common.util.concurrent.ListenableFuture<MessageProtos.TypeNative> getServerTime(
        MessageProtos.UserRequest request) {
      return futureUnaryCall(
          channel.newCall(METHOD_GET_SERVER_TIME, callOptions), request);
    }
  }

  public static io.grpc.ServerServiceDefinition bindService(
      final ChatApplication serviceImpl) {
    return io.grpc.ServerServiceDefinition.builder("irc.ChatApplication")
      .addMethod(io.grpc.ServerMethodDefinition.create(
          METHOD_SEND_MESSAGE,
          asyncUnaryCall(
            new io.grpc.stub.ServerCalls.UnaryMethod<
                MessageProtos.MessageSend,
                MessageProtos.TypeNative>() {
              @Override
              public void invoke(
                  MessageProtos.MessageSend request,
                  io.grpc.stub.StreamObserver<MessageProtos.TypeNative> responseObserver) {
                serviceImpl.sendMessage(request, responseObserver);
              }
            })))
      .addMethod(io.grpc.ServerMethodDefinition.create(
          METHOD_JOIN_CHANNEL,
          asyncUnaryCall(
            new io.grpc.stub.ServerCalls.UnaryMethod<
                MessageProtos.UserRequest,
                MessageProtos.TypeNative>() {
              @Override
              public void invoke(
                  MessageProtos.UserRequest request,
                  io.grpc.stub.StreamObserver<MessageProtos.TypeNative> responseObserver) {
                serviceImpl.joinChannel(request, responseObserver);
              }
            })))
      .addMethod(io.grpc.ServerMethodDefinition.create(
          METHOD_LEAVE_CHANNEL,
          asyncUnaryCall(
            new io.grpc.stub.ServerCalls.UnaryMethod<
                MessageProtos.UserRequest,
                MessageProtos.TypeNative>() {
              @Override
              public void invoke(
                  MessageProtos.UserRequest request,
                  io.grpc.stub.StreamObserver<MessageProtos.TypeNative> responseObserver) {
                serviceImpl.leaveChannel(request, responseObserver);
              }
            })))
      .addMethod(io.grpc.ServerMethodDefinition.create(
          METHOD_PULL_MESSAGE,
          asyncUnaryCall(
            new io.grpc.stub.ServerCalls.UnaryMethod<
                MessageProtos.TypeNative,
                MessageProtos.ChatBunch>() {
              @Override
              public void invoke(
                  MessageProtos.TypeNative request,
                  io.grpc.stub.StreamObserver<MessageProtos.ChatBunch> responseObserver) {
                serviceImpl.pullMessage(request, responseObserver);
              }
            })))
      .addMethod(io.grpc.ServerMethodDefinition.create(
          METHOD_CREATE_NICK,
          asyncUnaryCall(
            new io.grpc.stub.ServerCalls.UnaryMethod<
                MessageProtos.TypeNative,
                MessageProtos.TypeNative>() {
              @Override
              public void invoke(
                  MessageProtos.TypeNative request,
                  io.grpc.stub.StreamObserver<MessageProtos.TypeNative> responseObserver) {
                serviceImpl.createNick(request, responseObserver);
              }
            })))
      .addMethod(io.grpc.ServerMethodDefinition.create(
          METHOD_EXIT,
          asyncUnaryCall(
            new io.grpc.stub.ServerCalls.UnaryMethod<
                MessageProtos.TypeNative,
                MessageProtos.TypeNative>() {
              @Override
              public void invoke(
                  MessageProtos.TypeNative request,
                  io.grpc.stub.StreamObserver<MessageProtos.TypeNative> responseObserver) {
                serviceImpl.exit(request, responseObserver);
              }
            })))
      .addMethod(io.grpc.ServerMethodDefinition.create(
          METHOD_GET_SERVER_TIME,
          asyncUnaryCall(
            new io.grpc.stub.ServerCalls.UnaryMethod<
                MessageProtos.UserRequest,
                MessageProtos.TypeNative>() {
              @Override
              public void invoke(
                  MessageProtos.UserRequest request,
                  io.grpc.stub.StreamObserver<MessageProtos.TypeNative> responseObserver) {
                serviceImpl.getServerTime(request, responseObserver);
              }
            }))).build();
  }
}

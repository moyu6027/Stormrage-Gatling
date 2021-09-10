package cn.qe.gatling.example;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

public class DemoServer {
    public static void main(String[] args) throws Exception {
        DemoServiceGrpc.DemoServiceImplBase service = new DemoServiceGrpc.DemoServiceImplBase() {
            @Override
            public void pingPong(Ping request, StreamObserver<Pong> responseObserver) {
                responseObserver.onNext(
                        Pong.newBuilder()
                                .setData(request.getData())
                                .build()
                );
                responseObserver.onCompleted();
            }
        };

        Server server = ServerBuilder.forPort(7777)
                .directExecutor()
                .addService(service)
                .build()
                .start();

        System.out.println("Listening on port 7777.");
        server.awaitTermination();
    }
}

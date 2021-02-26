package org.zalex;

import io.grpc.stub.StreamObserver;
import org.zalex.grpc.GreetingServiceGrpc;
import org.zalex.grpc.GreetingServiceOuterClass;

public class GreetingServiceImpl extends GreetingServiceGrpc.GreetingServiceImplBase {

    @Override
    public void greeting(GreetingServiceOuterClass.HelloRequest request,
                         StreamObserver<GreetingServiceOuterClass.HelloResponse> responceObserver) {

        for (int i = 0; i < 10000; i++) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            GreetingServiceOuterClass.HelloResponse response = GreetingServiceOuterClass.HelloResponse
                    .newBuilder().setGreeting("Hellow from server, " + request.getName())
                    .build();

            responceObserver.onNext(response);
        }


        responceObserver.onCompleted();

    }
}

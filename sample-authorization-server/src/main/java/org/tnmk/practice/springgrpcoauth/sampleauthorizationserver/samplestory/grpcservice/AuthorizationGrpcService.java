package org.tnmk.practice.springgrpcoauth.sampleauthorizationserver.samplestory.grpcservice;

import io.grpc.stub.StreamObserver;
import org.lognet.springboot.grpc.GRpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tnmk.common.grpc.global.GlobalGrpcInterceptor;
import org.tnmk.practice.springgrpcoauth.authorizationserver.proto.AuthorizationGrpcServiceGrpc;
import org.tnmk.practice.springgrpcoauth.authorizationserver.proto.JwtProto;
import org.tnmk.practice.springgrpcoauth.authorizationserver.proto.PasswordAuthenticationRequestProto;

@GRpcService
public class AuthorizationGrpcService extends AuthorizationGrpcServiceGrpc.AuthorizationGrpcServiceImplBase{
    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalGrpcInterceptor.class);

    @Override
    public void authenticate(PasswordAuthenticationRequestProto request, StreamObserver<JwtProto> responseObserver) {
        JwtProto jwtProto = JwtProto.newBuilder()
            .setValue("xxx")
            .build();
        responseObserver.onNext(jwtProto);
        responseObserver.onCompleted();
    }

}

package org.tnmk.practice.springgrpcoauth.sampleresourceserver.samplestory.grpcservice;

import io.grpc.stub.StreamObserver;
import org.lognet.springboot.grpc.GRpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.tnmk.common.grpc.global.GlobalGrpcInterceptor;
import org.tnmk.practice.springgrpcoauth.sampleresourceserver.proto.ItemIdProto;
import org.tnmk.practice.springgrpcoauth.sampleresourceserver.proto.ItemProto;
import org.tnmk.practice.springgrpcoauth.sampleresourceserver.proto.SampleItemGrpcServiceGrpc;
import org.tnmk.practice.springgrpcoauth.sampleresourceserver.samplestory.service.SampleItemService;
import java.util.Optional;

@GRpcService
public class SampleItemGrpcService extends SampleItemGrpcServiceGrpc.SampleItemGrpcServiceImplBase {
    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalGrpcInterceptor.class);

    private final SampleItemService sampleItemService;

    @Autowired
    public SampleItemGrpcService(SampleItemService sampleItemService) {
        this.sampleItemService = sampleItemService;
    }

    public void getItem(ItemIdProto request, StreamObserver<ItemProto> responseObserver) {
        LOGGER.info("RESOURCE: getItem: " + request);
        Optional<ItemProto> itemProto = sampleItemService.getItem(request.getId());
        responseObserver.onNext(itemProto.orElse(null));
        responseObserver.onCompleted();
    }

}

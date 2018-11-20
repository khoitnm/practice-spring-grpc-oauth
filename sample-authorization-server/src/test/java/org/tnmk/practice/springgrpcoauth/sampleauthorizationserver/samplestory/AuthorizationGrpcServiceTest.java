package org.tnmk.practice.springgrpcoauth.sampleauthorizationserver.samplestory;

import io.grpc.testing.GrpcServerRule;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.tnmk.practice.springgrpcoauth.authorizationserver.proto.AuthorizationGrpcServiceGrpc;
import org.tnmk.practice.springgrpcoauth.authorizationserver.proto.JwtProto;
import org.tnmk.practice.springgrpcoauth.authorizationserver.proto.PasswordAuthenticationRequestProto;
import org.tnmk.practice.springgrpcoauth.sampleauthorizationserver.samplestory.grpcservice.AuthorizationGrpcService;

/**
 * More additional reference links:
 * <li>
 * This link use new grpc version with many use cases:
 * https://github.com/grpc/grpc-java/blob/master/testing/src/test/java/io/grpc/testing/GrpcServerRuleTest.java
 * </li>
 *
 * <li>
 * This link use old grpc version but has some clear explanations:
 * https://github.com/grpc/grpc-java/blob/master/examples/src/test/java/io/grpc/examples/helloworld/HelloWorldServerTest.java
 * </li>
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AuthorizationGrpcServiceTest {
    /**
     * This rule manages automatic graceful start and shutdown for the registered servers and channels at the end of test.
     */
    @Rule
    public final GrpcServerRule grpcServerRule = new GrpcServerRule();

    @Autowired
    private AuthorizationGrpcService sampleItemGrpcService;

    private AuthorizationGrpcServiceGrpc.AuthorizationGrpcServiceBlockingStub stub;

    @Before
    public void setUp() {
        // Create a server, add grpc grpcservice, start, and register for automatic graceful shutdown.
        grpcServerRule.getServiceRegistry().addService(sampleItemGrpcService);

        //Connect client stub to the server via channel (the combination of address and port)
        stub = AuthorizationGrpcServiceGrpc.newBlockingStub(grpcServerRule.getChannel());
    }

    @Test
    public void test_GetItem_NotEmpty_Success() {
        PasswordAuthenticationRequestProto passwordAuthenticationRequestProto = PasswordAuthenticationRequestProto.newBuilder()
            .setUsername("aaa")
            .setPassword("ppp")
            .build();
        JwtProto jwtProto = stub.authenticate(passwordAuthenticationRequestProto);
        Assert.assertNotNull(jwtProto.getValue());
    }
}

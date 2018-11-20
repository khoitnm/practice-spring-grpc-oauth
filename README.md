# Introduction
This is the demo to apply OAuth with gRPC and Spring framework.

Revinate has a demo like that: https://github.com/revinate/grpc-spring-security-demo
However, people found a concurrency bug with that demo which is hard to reproduce.

So, I want to find another way to do it.
Besides, I also want to apply the key distribution standard of OpenID in this project.

# Key distribution via JWKs URI
## Overview
https://stackoverflow.com/questions/30064795/how-to-share-a-public-key-for-oauth2-jwt-validation 
"There's no solution that is standardized as part of the OAuth 2.0 protocol suite
But OpenID Connect is a cross-domain SSO protocol that was built on top of OAuth 2.0, which also defined a more standardized option of dealing with key distribution in the form of JWKs URIs as part of the Discover"

Some example JWKs URIs:
* Google provides an API for certificates: https://www.googleapis.com/oauth2/v3/certs
* Pivotal also has a similar API: https://uaa.run.pivotal.io/token_keys 
The result of those APIs is explained here: https://auth0.com/docs/jwks

## Some thought:
Benefits of using JWKs URI:
It follows the OpenID standard, may be useful in the future.
We don't need to configure the public key in every microservices. We just need to configure it in the Authorization Server (in our Mona List, it's the Authentication service). So when there's anything need to be changed, we just need to change it in the Authorization Server.

The drawback: 
Resource Servers (our microservices) need to connect the Authorization Server to get public key whenever it needs to validate JWT tokens. We may need some cache mechanism to minimize the number of requests to JWKs URI.

## Other reference links:
https://auth0.com/docs/jwks
https://docs.spring.io/spring-security-oauth2-boot/docs/current/reference/html/boot-features-security-oauth2-resource-server.html

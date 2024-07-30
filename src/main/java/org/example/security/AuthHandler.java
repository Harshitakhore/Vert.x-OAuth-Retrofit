package org.example.security;

import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;

public class AuthHandler {

    private final JwtTokenGenerator jwtTokenGenerator;

    public AuthHandler(JwtTokenGenerator jwtTokenGenerator) {
        this.jwtTokenGenerator = jwtTokenGenerator;
    }

    public void login(RoutingContext context) {
        JsonObject body = context.getBodyAsJson();
        String name = body.getString("name");
        String email = body.getString("email");

        boolean authenticated = authenticateUser(name, email);

        if (authenticated) {

            String token = jwtTokenGenerator.generateToken(email);
            context.response().putHeader("content-type", "application/json")
                    .end(new JsonObject().put("token", token).encode());
        } else {
            context.response().setStatusCode(401).end("Invalid credentials");
        }
    }

    private boolean authenticateUser(String name, String email) {

        return name != null && !name.isEmpty() && email != null && !email.isEmpty();
    }
}

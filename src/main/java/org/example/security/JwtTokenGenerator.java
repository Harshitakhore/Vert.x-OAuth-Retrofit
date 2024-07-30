package org.example.security;

import io.vertx.core.json.JsonObject;
import io.vertx.ext.auth.jwt.JWTAuth;
import io.vertx.ext.auth.JWTOptions;

public class JwtTokenGenerator {

    private final JWTAuth jwtAuthProvider;

    public JwtTokenGenerator(JWTAuth jwtAuthProvider) {
        this.jwtAuthProvider = jwtAuthProvider;
    }

    public String generateToken(String userId) {
        JsonObject claims = new JsonObject().put("sub", userId);
        JWTOptions options = new JWTOptions().setExpiresInMinutes(60); // Token expiry time in minutes
        return jwtAuthProvider.generateToken(claims, options);
    }
}

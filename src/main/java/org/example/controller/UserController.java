package org.example.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.vertx.ext.auth.jwt.JWTAuth;
import io.vertx.ext.web.Router;
import org.example.handler.UserHandler;
import org.example.repository.UserRepository;

public class UserController {

    private final ObjectMapper mapper = new ObjectMapper();
    private final UserRepository userRepository;
    private final JWTAuth jwtAuthProvider;

    public UserController(UserRepository userRepository, JWTAuth jwtAuthProvider) {
        this.userRepository = userRepository;
        this.jwtAuthProvider = jwtAuthProvider;
    }


    public void registerRoutes(Router router) {

        UserHandler userHandler = new UserHandler(userRepository, mapper, jwtAuthProvider);



        router.post("/users").handler(userHandler::createUser);
        router.get("/users/:id").handler(userHandler::getUser);
        router.put("/users/:id").handler(userHandler::updateUser);
        router.delete("/users/:id").handler(userHandler::deleteUser);
       // router.get("/users").handler(userHandler::getAllUsers);
    }
}

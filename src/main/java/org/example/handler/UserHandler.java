package org.example.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.auth.JWTOptions;
import io.vertx.ext.auth.jwt.JWTAuth;
import io.vertx.ext.web.RoutingContext;
import org.example.entity.User;
import org.example.repository.UserRepository;
import org.example.security.JwtTokenGenerator;

import java.util.List;

public class UserHandler {

    private final UserRepository userRepository;
    private final ObjectMapper mapper;
    private final JWTAuth jwtAuthProvider;


    public UserHandler(UserRepository userRepository, ObjectMapper mapper, JWTAuth jwtAuthProvider) {
        this.userRepository = userRepository;
        this.mapper = mapper;
        this.jwtAuthProvider = jwtAuthProvider;
    }
    public void createUser(RoutingContext routingContext) {
        try {
            String requestBody = routingContext.getBodyAsString();
            System.out.println("Request Body: " + requestBody); // Log request body

            if (requestBody == null || requestBody.isEmpty()) {
                routingContext.response()
                        .setStatusCode(400)
                        .end("Request body is empty");
                return;
            }

            User user = mapper.readValue(requestBody, User.class);
            userRepository.save(user);
            respondWithJson(routingContext.response(), user);
        } catch (Exception e) {
            e.printStackTrace();
            routingContext.response().setStatusCode(500).end(e.getMessage());
        }
    }



    public void getUser(RoutingContext routingContext) {
        Long id = Long.valueOf(routingContext.pathParam("id"));
        User user = userRepository.findById(id);
        if (user != null) {
            respondWithJson(routingContext.response(), user);
        } else {
            routingContext.response().setStatusCode(404).end("User not found");
        }
    }

    public void updateUser(RoutingContext routingContext) {
        Long id = Long.valueOf(routingContext.pathParam("id"));
        User existingUser = userRepository.findById(id);
        if (existingUser != null) {
            try {
                User updatedUser = mapper.readValue(routingContext.getBodyAsString(), User.class);
                existingUser.setName(updatedUser.getName());
                existingUser.setEmail(updatedUser.getEmail());
                userRepository.update(existingUser);
                respondWithJson(routingContext.response(), existingUser);
            } catch (Exception e) {
                e.printStackTrace();
                routingContext.response().setStatusCode(500).end(e.getMessage());
            }
        } else {
            routingContext.response().setStatusCode(404).end("User not found");
        }
    }

    public void deleteUser(RoutingContext routingContext) {
        Long id = Long.valueOf(routingContext.pathParam("id"));
        User user = userRepository.findById(id);
        if (user != null) {
            userRepository.delete(user);
            routingContext.response().setStatusCode(204).end();
        } else {
            routingContext.response().setStatusCode(404).end("User not found");
        }
    }

    public void getAllUsers(RoutingContext routingContext) {
        List<User> users = userRepository.findAll();
        respondWithJson(routingContext.response(), users);
    }

    private void respondWithJson(HttpServerResponse response, Object data) {
        response.putHeader("content-type", "application/json");
        try {
            response.end(mapper.writeValueAsString(data));
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatusCode(500).end(e.getMessage());

        }
    }





}
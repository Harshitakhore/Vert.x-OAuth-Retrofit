package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.ext.auth.jwt.JWTAuth;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;
import io.vertx.core.Handler;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.ext.web.handler.JWTAuthHandler;
import org.example.controller.UserController;
import org.example.repository.UserRepository;
import org.example.repository.UserRepositoryImpl;
import org.example.security.AuthHandler;
import org.example.security.AuthProvider;
import org.example.security.JwtTokenGenerator;
import org.example.service.ApiClient;
import io.ebean.EbeanServer;
import org.example.config.EbeanConfig;
import org.example.entity.Post;

public class Main extends AbstractVerticle {

    @Override
    public void start() {
        EbeanServer ebeanServer = EbeanConfig.initializeEbeanServer();
        UserRepository userRepository = new UserRepositoryImpl(ebeanServer);


        // Create Vert.x instance
        //Vertx vertx = Vertx.vertx();
        ObjectMapper mapper = new ObjectMapper();

        // Create JWTAuth provider
        JWTAuth jwtAuth = AuthProvider.createJwtAuthProvider(vertx);
        JwtTokenGenerator jwtTokenGenerator = new JwtTokenGenerator(jwtAuth);
        AuthHandler authHandler = new AuthHandler(jwtTokenGenerator);
        UserController userController = new UserController(userRepository, jwtAuth);

        ApiClient apiClient = new ApiClient(ebeanServer);

        Router router = Router.router(vertx);


        router.route().handler(BodyHandler.create());

        // GET endpoint to fetch a post by ID
        router.get("/getpost/:id").handler(ctx -> {
            int id = Integer.parseInt(ctx.pathParam("id"));
            apiClient.getPost(id, res -> {
                if (res.succeeded()) {
                    ctx.response().putHeader("content-type", "application/json").end(res.result());
                } else {
                    ctx.response().setStatusCode(404).end("Post not found");
                }
            });
        });

        // POST endpoint to create a new post
        router.post("/create-post").handler(ctx -> {
            Post post = ctx.getBodyAsJson().mapTo(Post.class);
            apiClient.createPost(post, res -> {
                if (res.succeeded()) {
                    ctx.response().putHeader("content-type", "application/json").end(res.result());
                } else {
                    ctx.response().setStatusCode(500).end("Failed to create post");
                }
            });
        });
        router.get("/getallposts").handler(ctx -> {
            apiClient.getAllPosts(res -> {
                if (res.succeeded()) {
                    ctx.response().putHeader("content-type", "application/json").end(res.result());
                } else {
                    ctx.response().setStatusCode(500).end("Failed to fetch posts");
                }
            });
        });
        // Public login route
        router.post("/login").handler(authHandler::login);

        // Protected user routes
        router.route("/users/*").handler(JWTAuthHandler.create(jwtAuth));
        userController.registerRoutes(router);


        vertx.createHttpServer().requestHandler(router).listen(8888, result -> {
            if (result.succeeded()) {
                System.out.println("Server started on port 8888");
            } else {
                System.err.println("Failed to start server: " + result.cause());
            }
        });

    }



    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(new Main());


    }
}

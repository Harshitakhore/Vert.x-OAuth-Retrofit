package org.example.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.ebean.EbeanServer;
import org.example.entity.Post;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Future;

import java.util.List;

public class ApiClient {
    private static final String BASE_URL = "https://api.restful-api.dev/";
    private ApiService apiService;
    private Gson gson;

    public ApiClient(EbeanServer ebeanServer) {
        this.gson = new GsonBuilder().setPrettyPrinting().create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        apiService = retrofit.create(ApiService.class);
    }

    public void getPost(int id, Handler<AsyncResult<String>> resultHandler) {
        Call<Post> call = apiService.getPost(id);
        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if (response.isSuccessful()) {
                    Post fetchedPost = response.body();
                    String jsonResponse = gson.toJson(fetchedPost);
                    System.out.println("Post fetched: " + jsonResponse);
                    resultHandler.handle(Future.succeededFuture(jsonResponse));
                } else {
                    System.out.println("Request failed with code: " + response.code());
                    try {
                        System.out.println("Response body: " + response.errorBody().string());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    resultHandler.handle(Future.failedFuture("Failed to fetch post. Code: " + response.code()));
                }
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                t.printStackTrace();
                resultHandler.handle(Future.failedFuture(t));
            }
        });
    }
    public void getAllPosts(Handler<AsyncResult<String>> resultHandler) {
        Call<List<Post>> call = apiService.getAllPosts();
        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if (response.isSuccessful()) {
                    List<Post> posts = response.body();
                    String jsonResponse = gson.toJson(posts);
                    System.out.println("Posts fetched: " + jsonResponse);
                    resultHandler.handle(Future.succeededFuture(jsonResponse));
                } else {
                    System.out.println("Request failed with code: " + response.code());
                    try {
                        System.out.println("Response body: " + response.errorBody().string());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    resultHandler.handle(Future.failedFuture("Failed to fetch posts. Code: " + response.code()));
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                t.printStackTrace();
                resultHandler.handle(Future.failedFuture(t));
            }
        });
    }

    public void createPost(Post post, Handler<AsyncResult<String>> resultHandler) {
        Call<Post> call = apiService.createPost(post);
        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if (response.isSuccessful()) {
                    Post createdPost = response.body();
                    String jsonResponse = gson.toJson(createdPost);
                    System.out.println("Post created: " + jsonResponse);
                    resultHandler.handle(Future.succeededFuture(jsonResponse));
                } else {
                    System.out.println("Request failed with code: " + response.code());
                    try {
                        System.out.println("Response body: " + response.errorBody().string());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    resultHandler.handle(Future.failedFuture("Failed to create post. Code: " + response.code()));
                }
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                t.printStackTrace();
                resultHandler.handle(Future.failedFuture(t));
            }
        });
    }
}

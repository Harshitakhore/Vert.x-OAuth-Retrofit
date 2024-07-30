package org.example.service;

import org.example.entity.Post;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;


import retrofit2.http.Path;

import java.util.List;
import java.util.Map;


public interface ApiService {
    @POST("objects")
    Call<Post> createPost(@Body Post post);

    @GET("objects/{id}")
    Call<Post> getPost(@Path("id") int id);

    @GET("objects")
    Call<List<Post>> getAllPosts();
}





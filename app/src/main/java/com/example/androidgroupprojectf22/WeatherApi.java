package com.example.androidgroupprojectf22;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
public interface WeatherApi {

    @GET("posts")
    Call<List> getPosts();

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .build();
}

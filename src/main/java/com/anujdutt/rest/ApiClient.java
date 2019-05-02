package com.anujdutt.rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    public static Retrofit getBingRetrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.cognitive.microsoft.com/bing/v7.0")
                .build();
        return retrofit;
    }

    public static Retrofit getRedditRetrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.reddit.com")
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().create()))
                .build();
        return retrofit;
    }

    public static Retrofit getTwitterRetrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.cognitive.microsoft.com/bing/v7.0")
                .build();
        return retrofit;
    }

    public static Retrofit getNewsApiRetrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://newsapi.org")
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().create()))
                .build();
        return retrofit;
    }
}

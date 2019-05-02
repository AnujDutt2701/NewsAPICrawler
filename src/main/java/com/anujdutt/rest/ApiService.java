package com.anujdutt.rest;

import com.anujdutt.models.reddit.RedditModel;
import com.anujdutt.models.newsapi.NewsApiOrgModel;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    @GET("/search.json")
    Call<RedditModel> searchReddit(@Query("q") String query, @Query("limit") String limit, @Query("sort") String sort, @Query("t") String t, @Query("before") String before, @Query("after") String after);

    @GET("/v2/everything")
    Call<NewsApiOrgModel> searchNewsApiOrg(@Query("apiKey") String apiKey, @Query("q") String query, @Query("page") String page, @Query("pageSize") String pageSize, @Query("sortBy") String sortBy, @Query("from") String from);

}

package com.anujdutt.fetcher;

import com.anujdutt.Constants;
import com.anujdutt.models.newsapi.NewsApiOrgModel;
import com.anujdutt.models.newsapi.Article;
import com.anujdutt.rest.ApiService;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class NewsApiOrgFetcher {

    public List<Article> getAllPostsForAQuery(Retrofit retrofit, String query) {
        System.out.println("getAllPostsForAQuery");
        boolean fetch = true;
        int page = 1;
        List<Article> list = new ArrayList<>();

        while (fetch) {
            try {
                NewsApiOrgModel newsApiOrgModel = queryNewsApiOrg(retrofit, query, String.valueOf(page));
                page++;
                if (newsApiOrgModel == null || newsApiOrgModel.articles == null || newsApiOrgModel.articles.size() == 0) {
                    fetch = false;
                } else {
                    list.addAll(newsApiOrgModel.articles);
                }
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } catch (IOException e) {
                fetch = false;
            }
        }
        return list;
    }

    private NewsApiOrgModel queryNewsApiOrg(Retrofit retrofit, String query, String page) throws IOException {
        System.out.println("queryNewsApiOrg");
        ApiService service = retrofit.create(ApiService.class);
        Call<NewsApiOrgModel> NewsApiOrgModelCall = service.searchNewsApiOrg(Constants.NEWS_API_KEY_RAMAN, query, page, "100", "relevancy", "2019-02-28");
        Response<NewsApiOrgModel> response = NewsApiOrgModelCall.execute();
        return response.body();
    }
}

package com.anujdutt.fetcher;

import com.anujdutt.models.reddit.RedditModel;
import com.anujdutt.rest.ApiService;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RedditFetcher {

    public List<RedditModel.Data_> getAllPostsForAQuery(Retrofit retrofit, String query) {
        System.out.println("getAllPostsForAQuery");
        long jan1st2019 = 1546300800L;
        boolean fetch = true;
        String after = null;
        List<RedditModel.Data_> list = new ArrayList<>();

        while (fetch) {
            try {
                RedditModel redditModel = queryReddit(retrofit, query, after);
                after = redditModel.data.after;
                if (after == null) {
                    fetch = false;
                }
                for (RedditModel.Child child : redditModel.data.children) {
                    if(child.data.created >= jan1st2019)
                        list.add(child.data);
                }
            } catch (IOException e) {
                fetch = false;
            }
        }
        return list;
    }

    private RedditModel queryReddit(Retrofit retrofit, String query, String after) throws IOException {
        System.out.println("queryReddit");
        ApiService service = retrofit.create(ApiService.class);
        Call<RedditModel> redditModelCall = service.searchReddit(query, "100", "relevance", "month", "", after);
        Response<RedditModel> response = redditModelCall.execute();
        return response.body();
    }
}

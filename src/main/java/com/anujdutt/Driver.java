package com.anujdutt;

import com.anujdutt.fetcher.NewsApiOrgFetcher;
import com.anujdutt.fetcher.RedditFetcher;
import com.anujdutt.json.Article;
import com.anujdutt.json.NewsApiOrg;
import com.anujdutt.json.RedditPosts;
import com.anujdutt.models.query.Query;
import com.anujdutt.models.query.Response;
import com.anujdutt.models.reddit.RedditModel;
import com.anujdutt.rest.ApiClient;
import com.anujdutt.sql.ConnectionUtil;
import com.anujdutt.sql.DMLUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class Driver {

    public static Connection connection;

    public static List<Query> queries;

    public static void main(String[] args) {
        try {
            queries = new ArrayList<>();
            try {
//                URL url = Driver.class.getClassLoader().getResource("/query_topics.json");
                FileReader fileReader = new FileReader(new File("src/main/resources/query_topics.json"));
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                StringBuilder stringBuilder = new StringBuilder();
                String s;
                while ((s = bufferedReader.readLine()) != null) {
                    stringBuilder.append(s);
                }

                Gson gson = new GsonBuilder().create();
                Type type = new TypeToken<Response>() {
                }.getType();
                Response response = gson.fromJson(stringBuilder.toString(), type);
                queries.addAll(response.queryToTopics);
            } catch (IOException e) {
                e.printStackTrace();
            }
            connection = ConnectionUtil.getConnection();
//            fetchRedditPosts();
            fetchNewsApiOrg();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private static void fetchRedditPosts() {
        Pattern youtubePattern = Pattern.compile(".*(youtube|youtu.be).*");
        Pattern imgurPattern = Pattern.compile(".*(imgur).*");
        RedditFetcher redditFetcher = new RedditFetcher();
        for (Query query : queries) {
            List<RedditModel.Data_> list = redditFetcher.getAllPostsForAQuery(ApiClient.getRedditRetrofit(), query.query + " India");
            System.out.println("Size of list: " + list.size());
            for (RedditModel.Data_ data_ : list) {
                if (data_.url != null && !data_.isRedditMediaDomain && !data_.isSelf) {
                    if (youtubePattern.matcher(data_.url).matches()) {
                        System.out.println("Youtube url");
                        continue;
                    }
                    if (imgurPattern.matcher(data_.url).matches()) {
                        System.out.println("Imgur url");
                        continue;
                    }

                    RedditPosts redditPosts = new RedditPosts();
                    redditPosts.id = data_.id;
                    redditPosts.created = data_.created;
                    redditPosts.subreddit = data_.subreddit;
                    redditPosts.subreddit_id = data_.subredditId;
                    redditPosts.title = data_.title;
                    redditPosts.query = query.query;
                    redditPosts.topic = query.topic.equals("protests/riots") ? 1 : 2;
                    Article article = new Article();
                    article.url = data_.url;
                    article.is_scraped = false;
                    article.reddit_id = redditPosts.id;
                    article.news_api_org_id = null;
                    article.tweet_id = null;
                    article.scraped_data = null;

                    DMLUtil.insertARedditPost(connection, redditPosts, article);
                }
            }
        }

    }

    private static void fetchNewsApiOrg() {
        NewsApiOrgFetcher newsApiOrgFetcher = new NewsApiOrgFetcher();
        for (Query query : queries) {
            List<com.anujdutt.models.newsapi.Article> list = newsApiOrgFetcher.getAllPostsForAQuery(ApiClient.getNewsApiRetrofit(), query.query + " India");
            System.out.println("Size of list: " + list.size());
            for (com.anujdutt.models.newsapi.Article data_ : list) {
                if (data_.url != null) {

                    NewsApiOrg newsApiOrg = new NewsApiOrg();
                    newsApiOrg.created = data_.publishedAt;
                    newsApiOrg.title = data_.title;
                    newsApiOrg.url = data_.url;
                    newsApiOrg._description = data_.description;
                    newsApiOrg._source = data_.source.name;
                    newsApiOrg.query = query.query;
                    newsApiOrg.topic = query.topic.equals("protests/riots") ? 1 : 2;
                    Article article = new Article();
                    article.url = data_.url;
                    article.is_scraped = false;
                    article.reddit_id = null;
                    article.news_api_org_id = null;
                    article.tweet_id = null;
                    article.scraped_data = null;

                    DMLUtil.insertANewsApiPost(connection, newsApiOrg, article);
                }
            }
        }

    }
}

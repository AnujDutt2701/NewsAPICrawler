package com.anujdutt.sql;

import com.anujdutt.json.Article;
import com.anujdutt.json.NewsApiOrg;
import com.anujdutt.json.RedditPosts;
import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;

import java.sql.*;

public class DMLUtil {

    public static void insertARedditPost(Connection connection, RedditPosts redditPosts, Article article) {

        System.out.println("insertARedditPost()");
        PreparedStatement insertRedditPosts = null;
        PreparedStatement insertArticles = null;

        String insertRedditPostsStr =
                "INSERT INTO advanced_ir.RedditPosts " +
                        "VALUES( ?, ?, ?, ?, ?, ?, ?);";

        String insertArticlesStr =
                "INSERT INTO advanced_ir.Articles  (reddit_id, url, is_scraped)" +
                        "VALUES(?, ?, ?);";

        try {
            connection.setAutoCommit(false);
            insertRedditPosts = connection.prepareStatement(insertRedditPostsStr);
            insertArticles = connection.prepareStatement(insertArticlesStr);


            insertRedditPosts.setString(1, redditPosts.id);
            insertRedditPosts.setLong(2, redditPosts.created);
            insertRedditPosts.setString(3, redditPosts.subreddit);
            insertRedditPosts.setString(4, redditPosts.subreddit_id);
            insertRedditPosts.setString(5, redditPosts.title);
            insertRedditPosts.setString(6, redditPosts.query);
            insertRedditPosts.setInt(7, redditPosts.topic);
            insertRedditPosts.executeUpdate();

            System.out.println("Inserted reddit post");
            insertArticles.setString(1, article.reddit_id);
            insertArticles.setString(2, article.url);
            insertArticles.setBoolean(3, article.is_scraped);

            System.out.println(insertArticles);
            insertArticles.executeUpdate();


            System.out.println("Inserted article");
            connection.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (connection != null) {
                try {
                    System.err.print("Transaction is being rolled back");
                    connection.rollback();
                } catch (SQLException excep) {
                    e.printStackTrace();
                }
            }
        } finally {
            System.out.println("finally()");
            if (insertRedditPosts != null) {
                try {
                    insertRedditPosts.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (insertArticles != null) {
                try {
                    insertArticles.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void insertANewsApiPost(Connection connection, NewsApiOrg newsApiOrg, Article article) {

        System.out.println("insertANewsApiPost()");
        PreparedStatement insertNewsApiPosts = null;
        PreparedStatement insertArticles = null;

        String insertNewsApiPostsStr =
                "INSERT INTO advanced_ir.NewsApiOrgPosts (created, title, _description, _source, url, _query, _topic)" +
                        "VALUES( ?, ?, ?, ?, ?, ?, ?);";

        String insertArticlesStr =
                "INSERT INTO advanced_ir.Articles  (news_api_org_id, url, is_scraped)" +
                        "VALUES(?, ?, ?);";

        try {
            connection.setAutoCommit(false);
            insertNewsApiPosts = connection.prepareStatement(insertNewsApiPostsStr,
                    Statement.RETURN_GENERATED_KEYS);
            insertArticles = connection.prepareStatement(insertArticlesStr);


            insertNewsApiPosts.setString(1, newsApiOrg.created);
            insertNewsApiPosts.setString(2, newsApiOrg.title);
            insertNewsApiPosts.setString(3, newsApiOrg._description);
            insertNewsApiPosts.setString(4, newsApiOrg._source);
            insertNewsApiPosts.setString(5, newsApiOrg.url);
            insertNewsApiPosts.setString(6, newsApiOrg.query);
            insertNewsApiPosts.setInt(7, newsApiOrg.topic);

            int affectedRows = insertNewsApiPosts.executeUpdate();

            if (affectedRows == 0) {
                return;
            }

            try (ResultSet generatedKeys = insertNewsApiPosts.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    article.news_api_org_id = generatedKeys.getInt(1);
                } else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }
            }


            System.out.println("Inserted news api org post");
            insertArticles.setInt(1, article.news_api_org_id);
            insertArticles.setString(2, article.url);
            insertArticles.setBoolean(3, article.is_scraped);

            System.out.println(insertArticles);
            insertArticles.executeUpdate();

            System.out.println("Inserted article");
            connection.commit();
        } catch (Exception e) {
            if (connection != null) {
                try {
                    System.err.print("Transaction is being rolled back");
                    connection.rollback();
                } catch (SQLException excep) {
                    e.printStackTrace();
                }
            }
        } finally {
            System.out.println("finally()");
            if (insertNewsApiPosts != null) {
                try {
                    insertNewsApiPosts.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (insertArticles != null) {
                try {
                    insertArticles.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

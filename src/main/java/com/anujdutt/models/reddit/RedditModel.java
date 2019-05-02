package com.anujdutt.models.reddit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RedditModel {


    @SerializedName("kind")
    @Expose
    public String kind;
    @SerializedName("data")
    @Expose
    public Data data;

    public class Child {

        @SerializedName("kind")
        @Expose
        public String kind;
        @SerializedName("data")
        @Expose
        public Data_ data;

    }

    public class Data {

        @SerializedName("after")
        @Expose
        public String after;
        @SerializedName("before")
        @Expose
        public String before;
        @SerializedName("dist")
        @Expose
        public long dist;
        @SerializedName("modhash")
        @Expose
        public String modhash;
        @SerializedName("children")
        @Expose
        public List<Child> children = null;

    }

    public class Data_ {

        @SerializedName("subreddit")
        @Expose
        public String subreddit;
        @SerializedName("name")
        @Expose
        public String name;
        @SerializedName("domain")
        @Expose
        public String domain;
        @SerializedName("is_original_content")
        @Expose
        public boolean isOriginalContent;
        @SerializedName("is_reddit_media_domain")
        @Expose
        public boolean isRedditMediaDomain;
        @SerializedName("is_self")
        @Expose
        public boolean isSelf;
        @SerializedName("is_meta")
        @Expose
        public boolean isMeta;
        @SerializedName("category")
        @Expose
        public String category;
        @SerializedName("created")
        @Expose
        public long created;
        @SerializedName("subreddit_id")
        @Expose
        public String subredditId;
        @SerializedName("id")
        @Expose
        public String id;
        @SerializedName("url")
        @Expose
        public String url;
        @SerializedName("title")
        @Expose
        public String title;

    }
}

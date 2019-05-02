
package com.anujdutt.models.twitter;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Tweet {

    @SerializedName("queryMetadata")
    @Expose
    public QueryMetadata queryMetadata;
    @SerializedName("text")
    @Expose
    public String text;
    @SerializedName("created_at")
    @Expose
    public String createdAt;
    @SerializedName("coordinates")
    @Expose
    public Coordinates coordinates;
    @SerializedName("entities")
    @Expose
    public Entities entities;
    @SerializedName("user")
    @Expose
    public User user;
    @SerializedName("retweet_count")
    @Expose
    public Long retweetCount;
    @SerializedName("id")
    @Expose
    public Long id;
    @SerializedName("favorite_count")
    @Expose
    public Long favoriteCount;

}

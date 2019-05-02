
package com.anujdutt.models.twitter;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Entities {

    @SerializedName("user_mentions")
    @Expose
    public List<Object> userMentions = null;
    @SerializedName("media")
    @Expose
    public List<Object> media = null;
    @SerializedName("hashtags")
    @Expose
    public List<Hashtag> hashtags = null;
    @SerializedName("symbols")
    @Expose
    public List<Object> symbols = null;
    @SerializedName("urls")
    @Expose
    public List<Url> urls = null;

}

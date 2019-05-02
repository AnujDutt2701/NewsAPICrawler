
package com.anujdutt.models.newsapi;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NewsApiOrgModel {

    @SerializedName("status")
    @Expose
    public String status;
    @SerializedName("totalResults")
    @Expose
    public Integer totalResults;
    @SerializedName("articles")
    @Expose
    public List<Article> articles = null;

}

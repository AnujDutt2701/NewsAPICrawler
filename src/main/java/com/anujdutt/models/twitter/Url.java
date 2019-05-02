
package com.anujdutt.models.twitter;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Url {

    @SerializedName("url")
    @Expose
    public String url;
    @SerializedName("expanded_url")
    @Expose
    public String expandedUrl;
    @SerializedName("display_url")
    @Expose
    public String displayUrl;

}

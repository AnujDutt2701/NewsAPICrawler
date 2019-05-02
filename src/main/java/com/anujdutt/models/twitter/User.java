
package com.anujdutt.models.twitter;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("screen_name")
    @Expose
    public String screenName;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("profile_image_url")
    @Expose
    public String profileImageUrl;
    @SerializedName("id")
    @Expose
    public Long id;

}

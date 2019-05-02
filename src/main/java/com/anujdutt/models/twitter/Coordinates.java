
package com.anujdutt.models.twitter;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Coordinates {

    @SerializedName("type")
    @Expose
    public String type;
    @SerializedName("coordinates")
    @Expose
    public List<Object> coordinates = null;

}


package com.anujdutt.models.twitter;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class QueryMetadata {

    @SerializedName("query_city")
    @Expose
    public String queryCity;
    @SerializedName("query")
    @Expose
    public String query;
    @SerializedName("query_time")
    @Expose
    public Long queryTime;
    @SerializedName("query_topic")
    @Expose
    public String queryTopic;
    @SerializedName("query_city_range")
    @Expose
    public Long queryCityRange;

}

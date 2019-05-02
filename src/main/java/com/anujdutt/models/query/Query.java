package com.anujdutt.models.query;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("com.robohorse.robopojogenerator")
public class Query{

	@SerializedName("query")
	public String query;

	@SerializedName("topic")
	public String topic;

	@Override
 	public String toString(){
		return 
			"Query{" + 
			"query = '" + query + '\'' + 
			",topic = '" + topic + '\'' + 
			"}";
		}
}
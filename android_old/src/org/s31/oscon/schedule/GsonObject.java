package org.s31.oscon.schedule;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GsonObject {
	
	public static Gson mGson = new GsonBuilder().setPrettyPrinting().create();
	
	public String toString() {
		return mGson.toJson(this);
	}
	
}

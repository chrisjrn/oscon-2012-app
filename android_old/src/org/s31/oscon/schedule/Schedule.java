package org.s31.oscon.schedule;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class Schedule {
	
	
	public static ArrayList<Event> mSchedule;
	public static Gson mGson = new Gson();
	
	public static void buildSchedule(Context c) {
		AssetManager a = c.getAssets();
		InputStream jsonStream = null;
		try {
			 jsonStream = a.open("oscon_schedule.json");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			Log.wtf("Schedule",e.getStackTrace().toString());
			return;
		}

		Type collectionType = new TypeToken<ArrayList<Event>>(){}.getType();
		mSchedule = mGson.fromJson(new InputStreamReader(jsonStream), collectionType);
		//Log.v("Schedule", ""+mSchedule);
	}

}

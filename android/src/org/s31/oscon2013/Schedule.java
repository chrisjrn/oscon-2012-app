package org.s31.oscon2013;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class Schedule {

	public static List<TimeSlot> mTimeSlots;
	public static List<Event> mSchedule;
	public static Gson mGson = new Gson();
	
	static {
		buildTimeSlots();
	}

	public static void buildSchedule(Context c) {
		AssetManager a = c.getAssets();
		InputStream jsonStream = null;
		try {
			jsonStream = a.open("oscon_schedule.json");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			Log.wtf("Schedule", e.getStackTrace().toString());
			return;
		}

		Type collectionType = new TypeToken<ArrayList<Event>>() {
		}.getType();
		mSchedule = mGson.fromJson(new InputStreamReader(jsonStream),
				collectionType);

		int i = 0;
		for (Event e : mSchedule) {
			e.id = i++;
		}

	}

	public static List<Event> eventsInDateRange(Calendar start, Calendar end) {
		if (mSchedule == null) {
			return null;
		}

		ArrayList<Event> ev = new ArrayList<Event>();
		for (Event e : mSchedule) {
			if (e.title.startsWith("Android-")) {
				Log.v("Schedule", ""+ start + "\n" + end + "\n" + "\n" + e.startDate() + "\n" + e.endDate());
			}
			if (start.compareTo(e.startDate()) <= 0
					&& end.compareTo(e.endDate()) >= 0) {
				ev.add(e);
			}
		}

		return ev;
	}

	private static Calendar newCalendar() {
		Calendar c = Calendar.getInstance(Config.confTimeZone);
		c.setTimeInMillis(0);
		return c;
	}
	
	public static List<TimeSlot> timeSlotsForDay(int year, int month, int day) {
		Calendar c = newCalendar();
		c.set(year,month, day, 0,0,0);
		Calendar d = newCalendar();
		d.set(year,month,day, 23,59,59);

		ArrayList<TimeSlot> slots = new ArrayList<TimeSlot>();
		for (TimeSlot t: mTimeSlots) {
			if (c.compareTo(t.start) <= 0 && d.compareTo(t.end) >= 0) {
				slots.add(t);
			}
		}
		
		//for (TimeSlot s : slots) {
		//	Log.v("Schedule", ""+s.id + " : " + s.start + " TO " + s.end );
		//}

		return slots;
	}

	// Horrible hack, but should work.
	public static void buildTimeSlots() {
		if (mTimeSlots != null)
			return;
		mTimeSlots = new ArrayList<TimeSlot>();
		List<TimeSlot> t = mTimeSlots;

		Calendar c, d; // = Calendar.getInstance(Config.confTimeZone);
		int[][] boundaries = new int[][] {
				new int[] { 9, 0, 12, 30, 13, 30, 17, 00, 17, 30, 22, 00 },
				new int[] { 9, 0, 12, 30, 13, 30, 17, 00, 17, 00, 22, 00 },
				new int[] { 8, 45, 10, 10, 10, 40, 11, 20, 11, 30, 12, 10, 13,
						40, 14, 20, 14, 30, 15, 10, 16, 10, 16, 50, 17, 00, 17,
						40, 17, 40, 22, 00 },
				new int[] { 9, 0, 10, 10, 10, 40, 11, 20, 11, 30, 12, 10, 13,
						40, 14, 20, 14, 30, 15, 10, 16, 10, 16, 50, 17, 00, 17,
						40, 17, 40, 22, 00 },
				new int[] { 9, 0, 9, 40, 10, 00, 10, 40, 11, 00, 11, 40, 11,
						50, 12, 30, 12, 40, 13, 10, 13, 15, 14, 00 } };
		int tsid = 0;
		int year = 2012;
		int month = 7 - 1; // I HATE JAVA
		int day = 16;
		for (int i = 0; i < boundaries.length; i++) {
			for (int j = 0; j < boundaries[i].length; j+=4) {
				c = newCalendar();
				d = newCalendar();
				c.set(year, month, day, boundaries[i][j+0], boundaries[i][j+1]);
				d.set(year, month, day, boundaries[i][j+2], boundaries[i][j+3]);
				t.add(new TimeSlot(tsid++, c, d));
			}
			day++;
		}

		Log.v("Schedule", "Time Slots: " + tsid);
		
		//for (TimeSlot s : mTimeSlots) {
		//	Log.v("Schedule", ""+s.id + " : " + s.start + " TO " + s.end );
		//}
		
	}

}

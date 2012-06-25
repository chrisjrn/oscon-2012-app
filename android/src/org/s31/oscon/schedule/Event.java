package org.s31.oscon.schedule;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

import android.util.Log;

public class Event extends GsonObject implements Comparable {

	public String start;
	public String end;
	public String description;
	public String author;
	public String rawDescription;
	public String title;
	public String room;
	
	// 2012-07-16 12:30:00-08:00
	private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);
	
	@Override
	public int compareTo(Object arg0) {
		// Tie-Break order:
		// 1) Start Time
		// 2) End Time (inverse, so long events show up first)
		// 3) Room
		
		if (!(arg0 instanceof Event)) return -1;
		Event a = (Event) arg0;
		int b = start.compareTo(a.start);
		int c = 0 - end.compareTo(a.end);
		int d = room.compareTo(a.room);
		
		return (b!=0)?b:(c!=0)?c:(d!=0)?d:0;
	}
	
	public Calendar startDate() {
		try {
			return makeDate(start);
		} catch (ParseException e) {
			throw new RuntimeException(e.toString() + e.getStackTrace());
		}
	}
	
	public Calendar endDate() {
		try {
			return makeDate(end);
		} catch (ParseException e) {
			throw new RuntimeException(e.toString() + e.getStackTrace());
		}
	}
	
	private Calendar makeDate(String dateString) throws ParseException {
		Date d = dateFormat.parse(nicerDate(dateString));
		//Log.v("Event", dateString + " // " + d);
		Calendar g = Calendar.getInstance(ScheduleStructure.confTimeZone, Locale.US);
		g.setTimeInMillis(0);
		//g.setTime(d);
		g.set(d.getYear() + 1900, d.getMonth(), d.getDate(), d.getHours(), d.getMinutes(), d.getSeconds());
		//Log.v("Event", dateString + " // " + String.format("%d %d %d %d %d %d", d.getYear() + 1900, d.getMonth(), d.getDate(), d.getHours(), d.getMinutes(), d.getSeconds()));
		//Log.v("Event", dateString + " // " + g);
		return g;	
		
		/*
		Calendar g = Calendar.getInstance(ScheduleStructure.confTimeZone, Locale.US);
		g.setTimeInMillis(0);
		// 2012-07-16 12:30:00-08:00
		//Log.v("Event", dateString);
		Scanner sc = new Scanner(dateString.replace("-", " ").replace(":", " "));
		g.set(Calendar.YEAR, sc.nextInt(10));
		g.set(Calendar.MONTH, sc.nextInt(10) - 1);
		g.set(Calendar.DAY_OF_MONTH, sc.nextInt(10));
		g.set(Calendar.HOUR_OF_DAY, Integer.parseInt(sc.next(), 10));
		g.set(Calendar.MINUTE, sc.nextInt());
		//Log.v("Event", dateString + " // " + g + " // " + g.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.US));
		return g;
		*/
	}
	
	
	private String nicerDate(String date) {
		// Annoying hack because the date string Python gave us isn't acceptable.
		return date.substring(0,19); /* + date.substring(19, 25).replace(":", ""); */
	}
}

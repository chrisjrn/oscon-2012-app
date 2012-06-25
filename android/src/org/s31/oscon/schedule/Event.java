package org.s31.oscon.schedule;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Event extends GsonObject implements Comparable {

	public String start;
	public String end;
	public String description;
	public String author;
	public String rawDescription;
	public String title;
	public String room;
	
	// 2012-07-16 12:30:00-08:00
	private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ssZ", Locale.US);
	
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
	
	public Date startDate() {
		try {
			return dateFormat.parse(nicerDate(start));
		} catch (ParseException e) {
			throw new RuntimeException(e.toString() + e.getStackTrace());
		}
	}
	
	public Date endDate() {
		try {
			return dateFormat.parse(nicerDate(end));
		} catch (ParseException e) {
			throw new RuntimeException(e.toString() + e.getStackTrace());
		}
	}
	
	private String nicerDate(String date) {
		// Annoying hack because the date string Python gave us isn't acceptable.
		return date.substring(0,19) + date.substring(19, 25).replace(":", "");
	}
}

package org.s31.oscon2013;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Event  implements Comparable {

	public String start;
	public String end;
	public String description;
	public String author;
	public String rawDescription;
	public String title;
	public String room;
	public String url;
	
	// 2012-07-16 12:30:00-08:00
	private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);
	
	public Event(String start, String end, String description, String author,
			String rawDescription, String title, String room, String url) {
		super();
		this.start = start;
		this.end = end;
		this.description = description;
		this.author = author;
		this.rawDescription = rawDescription;
		this.title = title;
		this.room = room;
		this.url = url;
	}

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

		Calendar g = Calendar.getInstance(Config.confTimeZone, Locale.US);
		g.setTimeInMillis(0);

		g.set(d.getYear() + 1900, d.getMonth(), d.getDate(), d.getHours(), d.getMinutes(), d.getSeconds());

		return g;	
	}
	
	
	private String nicerDate(String date) {
		// Annoying hack because the date string Python gave us isn't acceptable.
		return date.substring(0,19); /* + date.substring(19, 25).replace(":", ""); */
	}
}

package org.s31.oscon.schedule;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import android.util.Log;

public class ScheduleStructure {

	public static SimpleDateFormat tabDateFormat ;
	public static TimeZone confTimeZone ;
	public static List<Day> days;

	static {
		confTimeZone = TimeZone.getTimeZone("America/Los_Angeles");
		tabDateFormat = new SimpleDateFormat("hh:mma",Locale.US);
		tabDateFormat.setTimeZone(confTimeZone);
		days = new ArrayList<Day>();
		buildScheduleStructure();
	}
	
	
	
	private static void buildScheduleStructure() {
		
		Day monday = new Day(2012,7,16);
		monday.add(
				 9,  0, 12, 30,
				13, 30, 17, 00,
				17, 30, 22, 00
			);
		Day tuesday = new Day(2012,7,17);
		tuesday.add(
				 9,  0, 12, 30,
				13, 30, 17, 00,
				17, 00, 22, 00
			);
		Day wednesday = new Day(2012,7,18);
		wednesday.add( 
				 8, 45, 10, 10,
				10, 40, 11, 20,
				11, 30, 12, 10,
				13, 40, 14, 20,
				14, 30, 15, 10,
				16, 10, 16, 50,
				17, 00, 17, 40,
				17, 40, 22, 00
				);
		Day thursday = new Day(2012,7,19);
		thursday.add(
				9,  0,  10, 10,
				10, 40, 11, 20,
				11, 30, 12, 10,
				13, 40, 14, 20,
				14, 30, 15, 10,
				16, 10, 16, 50,
				17, 00, 17, 40,
				17, 40, 23, 59
			);

		Day friday = new Day(2012,7,20);
		friday.add(
				 9,  0,  9, 40,
				10, 00, 10, 40,
				11, 00, 11, 40,
				11, 50, 12, 30,
				12, 40, 13, 10,
				13, 15, 14, 00
			);
		days.add(monday);
		days.add(tuesday);
		days.add(wednesday);
		days.add(thursday);
		days.add(friday);
		
	}
	
	public static class Day {
		public GregorianCalendar date;
		public List<TimeSlot> timeSlots;
		
		public Day(int year, int month, int day) {
			date = new GregorianCalendar(ScheduleStructure.confTimeZone,Locale.US);
			date.set(year, month, day);
			timeSlots = new ArrayList<TimeSlot>();
		}
		
		public void add(int sh, int sm, int eh, int em) {
			timeSlots.add(new TimeSlot(date, sh, sm, eh, em));
		}
		
		public void add(int ... times) {
			for (int i=0; i < times.length; i+=4) {
				//Log.v("ScheduleStructure", "" + date + String.format("%d:%d - %d:%d", times[i], times[i+1], times[i+2], times[i+3]));
				timeSlots.add(new TimeSlot(date, times[i], times[i+1], times[i+2], times[i+3]));
			}
		}
		
		public String toString() {
			return "" + date + " -- " + timeSlots;
		}
	}

	public static class TimeSlot {
		public GregorianCalendar startDate, endDate;
		
		public TimeSlot(GregorianCalendar date, int sh, int sm, int eh, int em) {
			int year = date.get(Calendar.YEAR), month = date.get(Calendar.MONTH), day = date.get(Calendar.DAY_OF_MONTH);
			// How do we do this properly?
			startDate = new GregorianCalendar(ScheduleStructure.confTimeZone,Locale.US);
			startDate.set(year, month, day, sh, sm);
			endDate = new GregorianCalendar(ScheduleStructure.confTimeZone, Locale.US);
			endDate.set(year, month, day, eh, em);
		}
		
		public String toString() {
			return String.format("%s -- %s", ScheduleStructure.tabDateFormat.format(startDate.getTime()), 
					ScheduleStructure.tabDateFormat.format(endDate.getTime()));
			//return startDate.toString() + " -- " + endDate.toString();
		}
	}



}


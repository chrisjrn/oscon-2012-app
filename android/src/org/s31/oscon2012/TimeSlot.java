package org.s31.oscon2012;

import java.util.Calendar;

public class TimeSlot {
	public int id;
	public Calendar start;
	public Calendar end;
	
	public TimeSlot(int id, Calendar start, Calendar end) {
		this.id = id;
		this.start = start;
		this.end = end;
	}
}

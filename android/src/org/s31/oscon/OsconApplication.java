package org.s31.oscon;

import org.s31.oscon.schedule.Schedule;
import org.s31.oscon.schedule.ScheduleProvider;

import android.app.Application;
import android.content.SharedPreferences;

public class OsconApplication extends Application {

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		
		// Build the global schedule object if it hasn't been done already.
		SharedPreferences p = this.getSharedPreferences(getPackageName() + ".preferences", this.MODE_PRIVATE);
		if (p.getInt("DATABASE_BUILT", 0) == 0) {
			Schedule.buildSchedule(this);
			ScheduleProvider.insertEvents(this, Schedule.mSchedule);
			p.edit().putInt("DATABASE_BUILT", 1).commit();
		}
	}

}

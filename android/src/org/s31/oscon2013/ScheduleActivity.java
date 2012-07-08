package org.s31.oscon2013;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

public class ScheduleActivity extends Activity {

	public static SimpleDateFormat timeOnly;
	static {
		// 9:00am
		timeOnly = new SimpleDateFormat("hh:mma", Locale.US);
		timeOnly.setTimeZone(Config.confTimeZone);
	}

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		// Set up the action bar
		ActionBar actionBar = getActionBar();
		
		// Turns the app icon into an "up" button.
		// A listener is required to make this actually do anything.
		actionBar.setDisplayHomeAsUpEnabled(true);
		
		// Fragments are added to the navigations stack using
		// FragmentTransactions
		FragmentTransaction ft = getFragmentManager().beginTransaction();

		
		// Generate some parameters for the Schedule fragment -- we want to show the first TimeSlot of the day.
		int day = getIntent().getIntExtra("day", 0);
		Log.v("ScheduleActivity", "Loading day: " + day + " // " + (day+22));
		List<TimeSlot> t = Schedule.timeSlotsForDay(2013, 7, 22 + day);
		int timeSlot = t.get(0).id;
		
		Bundle params = new Bundle();
		params.putInt("time_slot", timeSlot);
		
		// Load an instance of the talk listing fragment
		Fragment f = Fragment.instantiate(this,
				ScheduleFragment.class.getName(), params);

		// Add the fragment to the the UI -- using android.R.id.content as the
		// view container says that we want to use the entirety of the screen to
		// display our fragment
		ft.add(android.R.id.content, f, "schedule");
		
		// Loads up all of the fragments we added before.
		ft.commit();

	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {
		case android.R.id.home:
			// android.R.id.home is the "up" button.
			Intent intent = new Intent(this, DayListActivity.class);

			// This flag unwinds the navigation stack, rather than loading the
			// new activity in the existing stack.
			intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
					| Intent.FLAG_ACTIVITY_NEW_TASK);
			startActivity(intent);
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

}


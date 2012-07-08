package org.s31.oscon2013;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
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
		
		// Turn on the tabs! 
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        
		// Set up a ViewPager for tab swiping
		ViewPager vp = new ViewPager(this);
        vp.setId(R.id.pager); // More on this later
        setContentView(vp);

        // Tabs Adapter!
		TabsAdapter adapter = new TabsAdapter(this, vp);
		
		// Generate some parameters for the Schedule fragment -- we want to show the first TimeSlot of the day.
		int day = getIntent().getIntExtra("day", 0);
		Log.v("ScheduleActivity", "Loading day: " + day + " // " + (day+22));
		List<TimeSlot> t = Schedule.timeSlotsForDay(2013, 7, 22 + day);
		
		// Load tabs for the given day:
		for (TimeSlot ts : t) {
			Bundle args = new Bundle();
			args.putInt("time_slot", ts.id);
			String title = timeOnly.format(ts.start.getTime()) + "--" + timeOnly.format(ts.end.getTime());
			adapter.addTab(actionBar.newTab().setText(title), ScheduleFragment.class, args );
		}

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


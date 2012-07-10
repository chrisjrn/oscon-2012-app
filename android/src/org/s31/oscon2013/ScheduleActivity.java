package org.s31.oscon2013;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

public class ScheduleActivity extends Activity {

	public static SimpleDateFormat timeOnly;
	public Tab mDisplayedTab;
	public boolean mShowCurrentSessionMode;
	private SlidingMenu mMenu;

	static {
		// 9:00am
		timeOnly = new SimpleDateFormat("hh:mma", Locale.US);
		timeOnly.setTimeZone(Config.confTimeZone);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.schedule, menu);

		return true;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		if (getIntent().getExtras() != null
				&& getIntent().getExtras().containsKey("show_days")) {
			Intent i = new Intent(this, DayListActivity.class);
			startActivity(i);
		}

		// Set up the action bar
		ActionBar actionBar = getActionBar();

		// Turn on the tabs!
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		setContentView(R.layout.schedule_activity);

		// Set up a ViewPager for tab swiping
		ViewPager vp = (ViewPager) (findViewById(R.id.pager));

		// Set up the slidey menu
		mMenu = new SlidingMenu(this);
		mMenu.setMode(SlidingMenu.LEFT);
		mMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
		mMenu.setBehindWidth(300);
		mMenu.setFadeDegree(0.35f);
		mMenu.attachToActivity(this, SlidingMenu.SLIDING_WINDOW);
		mMenu.setMenu(R.layout.menu_frame);

		getFragmentManager().beginTransaction()
				.add(R.id.menu_frame, new DayListFragment()).commit();

		// Tabs Adapter!
		TabsAdapter adapter = new TabsAdapter(this, vp);

		// Determine whether this is launching the app (so show what's on now)
		// or if it's loading a new day.
		int day = getIntent().getIntExtra("day", -1);
		mShowCurrentSessionMode = false;

		Calendar startOfConference = Calendar.getInstance(Config.confTimeZone);
		startOfConference.set(2013, Calendar.JULY, 22, 0, 0);
		Calendar c = Calendar.getInstance(Config.confTimeZone);
		c.setTimeInMillis(System.currentTimeMillis());
		//c.set(2013, Calendar.JULY, 24, 11, 11); // FORCE TIME TO BE 11AM
												// WEDNESDAY FOR NOW.

		// c.setTimeInMillis(System.currentTimeMillis()); // get current time
		if (day == -1) {
			day = c.get(Calendar.DAY_OF_MONTH) - 22;
			mShowCurrentSessionMode = true;
			if (c.before(startOfConference)) {
				day = 0;
				mShowCurrentSessionMode = false;
			}
		}

		if (!mShowCurrentSessionMode) {
			c.set(2013, Calendar.JULY, day + 22, 0, 0);
		}

		// If we're in current sessions mode, then we don't want a home button!
		// Turns the app icon into an "up" button.
		// A listener is required to make this actually do anything.
		actionBar.setDisplayHomeAsUpEnabled(!mShowCurrentSessionMode);

		setTitle(c.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG,
				Locale.US));

		Log.v("ScheduleActivity", "Loading day: " + day + " // " + (day + 22));
		List<TimeSlot> t = Schedule.timeSlotsForDay(2013, 7, 22 + day);

		TimeSlot pts = new TimeSlot(9999, t.get(0).start, t.get(0).start);

		// Load tabs for the given day:
		Tab tab = null;
		mDisplayedTab = null;
		for (TimeSlot ts : t) {
			Bundle args = new Bundle();
			args.putInt("time_slot", ts.id);
			String title = timeOnly.format(ts.start.getTime()) + "--"
					+ timeOnly.format(ts.end.getTime());
			tab = actionBar.newTab().setText(title);
			adapter.addTab(tab, ScheduleFragment.class, args);

			// Show the current session if we're in a session.
			// Otherwise show the next session coming up if we're between
			// sessions
			if (mDisplayedTab == null) {
				if (c.compareTo(ts.start) >= 0 && c.compareTo(ts.end) <= 0) {
					actionBar.selectTab(tab);
					mDisplayedTab = tab;
					Log.v("ScheduleActivity", "Selecting Tab: " + title);
				} else if (c.compareTo(pts.end) >= 0
						&& c.compareTo(ts.start) <= 0) {
					actionBar.selectTab(tab);
					mDisplayedTab = tab;
					Log.v("ScheduleActivity", "Selecting Tab: " + title);
				}
			}
			pts = ts;
		}

		if (mShowCurrentSessionMode) {
			// If we're not in a session, display the last tab
			if (mDisplayedTab == null && tab != null) {
				actionBar.selectTab(tab);
			}
		}

	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		Intent intent;

		switch (item.getItemId()) {
		case R.id.schedule_go_to_today:
			// If we're on the current day, then just jump to the tab,
			// otherwise, act as if it's an up-nav.
			if (mShowCurrentSessionMode && mDisplayedTab != null) {
				getActionBar().selectTab(mDisplayedTab);
				return true;
			} else {
				intent = new Intent(this, ScheduleActivity.class);

				// This flag unwinds the navigation stack, rather than loading
				// the
				// new activity in the existing stack.
				intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
						| Intent.FLAG_ACTIVITY_NEW_TASK);
				startActivity(intent);
				return true;
			}
		case android.R.id.home:
			toggleMenu();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	public void toggleMenu() {
		if (mMenu.isMenuShowing()) {
			mMenu.showContent();
		} else {
			mMenu.showMenu();
		}
	}

}

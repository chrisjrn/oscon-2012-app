package org.s31.oscon;

import org.s31.oscon.schedule.ScheduleStructure;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class OsconAppActivity extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// setContentView(R.layout.main);

		// Notice that setContentView() is not used, because we use the root
		// android.R.id.content as the container for each fragment

		// setup action bar for tabs
		ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		actionBar.setDisplayShowTitleEnabled(false);
		
		// Turns the app icon into an "up" button.
		actionBar.setHomeButtonEnabled(true);

		// Load tabs for the given day:
		int day = this.getIntent().getIntExtra("day", 0);
		int j = 0;
		for (ScheduleStructure.TimeSlot i : ScheduleStructure.days.get(day).timeSlots) {
			Bundle args = new Bundle();
			args.putInt("day", day);
			args.putInt("time_slot", j);
			Tab tab = actionBar
					.newTab()
					.setText("" + i)
					.setTabListener(
							new SimpleTabListener<ScheduleFragment>(this, ""
									+ i, ScheduleFragment.class, args));
			actionBar.addTab(tab);

			j++;
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main_activity, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		Log.v("OsconAppActivity", "onOptionsItemSelected");
		switch (item.getItemId()) {
		case android.R.id.home:
			// app icon in action bar clicked; go home
			Intent intent = new Intent(this, DayListActivity.class);

			// This flag unwinds the navigation stack, rather than loading the
			// new activity in the existing stack.
			intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
			startActivity(intent);
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

}

/** TabListener copied from developer.android.com */
class SimpleTabListener<T extends Fragment> implements TabListener {
	private Fragment mFragment;
	private final Activity mActivity;
	private final String mTag;
	private final Class<T> mClass;
	private final Bundle mArguments;

	/**
	 * Constructor used each time a new tab is created.
	 * 
	 * @param activity
	 *            The host Activity, used to instantiate the fragment
	 * @param tag
	 *            The identifier tag for the fragment
	 * @param clz
	 *            The fragment's Class, used to instantiate the fragment
	 */
	public SimpleTabListener(Activity activity, String tag, Class<T> clz,
			Bundle args) {
		mActivity = activity;
		mTag = tag;
		mClass = clz;
		mArguments = args;
	}

	/* The following are each of the ActionBar.TabListener callbacks */

	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		// Check if the fragment is already initialized
		if (mFragment == null) {
			// If not, instantiate and add it to the activity
			mFragment = Fragment.instantiate(mActivity, mClass.getName(),
					mArguments);
			ft.add(android.R.id.content, mFragment, mTag);
		} else {
			// If it exists, simply attach it in order to show it
			ft.attach(mFragment);
		}
	}

	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		if (mFragment != null) {
			// Detach the fragment, because another one is being attached
			ft.detach(mFragment);
		}
	}

	public void onTabReselected(Tab tab, FragmentTransaction ft) {
		// User selected the already selected tab. Usually do nothing.
	}
}
package org.s31.oscon2013;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

public class ScheduleActivity extends Activity {

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

		// Load an instance of the talk listing fragment
		Fragment f = Fragment.instantiate(this,
				ScheduleFragment.class.getName());

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


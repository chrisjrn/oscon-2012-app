package org.s31.oscon2012;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;

public class ScheduleActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

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


}


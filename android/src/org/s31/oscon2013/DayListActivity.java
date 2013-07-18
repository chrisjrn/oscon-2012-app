package org.s31.oscon2013;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;

public class DayListActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		FragmentTransaction ft = getFragmentManager().beginTransaction();
		Fragment f = Fragment
				.instantiate(this, DayListFragment.class.getName());
		ft.add(android.R.id.content, f, "default");
		ft.commit();
	}
}

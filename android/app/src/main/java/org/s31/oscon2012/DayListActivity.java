package org.s31.oscon2012;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.MenuItem;

public class DayListActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getActionBar().setDisplayHomeAsUpEnabled(true);

		FragmentTransaction ft = getFragmentManager().beginTransaction();
		Fragment f = Fragment
				.instantiate(this, DayListFragment.class.getName());
		ft.add(android.R.id.content, f, "default");
		ft.commit();
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// android.R.id.home is the "up" button.
			finish();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}
}

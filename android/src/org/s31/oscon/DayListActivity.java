package org.s31.oscon;

import org.s31.oscon.schedule.Schedule;
import org.s31.oscon.schedule.ScheduleProvider;

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

		buildScheduleIfNeeded();

	}

	// Need to make this faster.
	private void buildScheduleIfNeeded() {

		// Build the global schedule object if it hasn't been done already.
		final SharedPreferences p = this.getSharedPreferences(getPackageName()
				+ ".preferences", this.MODE_PRIVATE);
		if (p.getInt("DATABASE_BUILT", 0) == 0) {
			final ProgressDialog pd = ProgressDialog.show(this, null,
					"[[ Building Schedule ]]");

			new AsyncTask<Void, Void, Boolean>() {

				@Override
				protected Boolean doInBackground(Void... params) {

					Schedule.buildSchedule(DayListActivity.this);
					ScheduleProvider.insertEvents(DayListActivity.this,
							Schedule.mSchedule);

					return true;
				}

				@Override
				protected void onPostExecute(Boolean result) {
					// TODO Auto-generated method stub
					super.onPostExecute(result);
					p.edit().putInt("DATABASE_BUILT", 1).commit();
					pd.dismiss();
				}

			}.execute(null, null);
		}

	}

}

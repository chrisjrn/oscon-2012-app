package org.s31.oscon;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;

public class ScheduleItemActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		FragmentTransaction ft = getFragmentManager().beginTransaction();
		Fragment f = Fragment
				.instantiate(this, ScheduleItemFragment.class.getName(), getIntent().getExtras());
		ft.add(android.R.id.content, f, "default");
		ft.commit();
		
	}

}

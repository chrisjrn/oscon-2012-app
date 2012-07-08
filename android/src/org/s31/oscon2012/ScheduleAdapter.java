package org.s31.oscon2012;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;


public class ScheduleAdapter extends ArrayAdapter<Event> {

	public ScheduleAdapter(Context context, int textViewResourceId,
			List<Event> objects) {
		super(context, textViewResourceId, objects);

	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		return null;
	}


}

package org.s31.oscon2013;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


public class ScheduleAdapter extends ArrayAdapter<Event> {

	public ScheduleAdapter(Context context, int textViewResourceId,
			List<Event> objects) {
		super(context, textViewResourceId, objects);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		// This is a bit convoluted, unfortunately.
		LayoutInflater li = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View v = li.inflate(R.layout.schedule_item, null);
		
		// Gets the event item with the data that we're trying to load up
		Event e = getItem(position);
		TextView tv;
		
		tv = (TextView)v.findViewById(R.id.event_title);
		tv.setText(e.title);
		
		tv = (TextView)v.findViewById(R.id.event_author);
		tv.setText(e.author);
		
		tv = (TextView)v.findViewById(R.id.event_room);
		tv.setText(e.room);
		
		return v;
	}

}

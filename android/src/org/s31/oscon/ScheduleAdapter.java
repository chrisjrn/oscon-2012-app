package org.s31.oscon;

import java.util.List;

import org.s31.oscon.schedule.Event;

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
		// TODO Auto-generated constructor stub
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View v = ((LayoutInflater)(getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE))).inflate(R.layout.schedule_item, null);
		Event e = getItem(position);
		((TextView)(v.findViewById(R.id.event_title))).setText(e.title);
		((TextView)(v.findViewById(R.id.event_author))).setText(e.author);
		((TextView)(v.findViewById(R.id.event_room))).setText(e.room + " // " + e.startDate() + "--" + e.endDate());
		return v;
	}


}

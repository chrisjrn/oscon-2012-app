package org.s31.oscon;

import org.s31.oscon.schedule.ScheduleProvider;

import android.app.ListFragment;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.SimpleCursorAdapter;


public class ScheduleFragment extends ListFragment {
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		// Something something with arguments
		
		
		// Set the list up.
		Cursor c = getActivity().getContentResolver().query(ScheduleProvider.CONTENT_URI,
				ScheduleProvider.defaultProjection, null, null,
				"" + ScheduleProvider.ColumnNames.START + " ASC");
		SimpleCursorAdapter a = new SimpleCursorAdapter(getActivity(), R.layout.schedule_item, c, new String[] {
				ScheduleProvider.ColumnNames.TITLE, ScheduleProvider.ColumnNames.AUTHOR, ScheduleProvider.ColumnNames.ROOM, 	
			}, new int[] {
				R.id.event_title, R.id.event_author, R.id.event_room
			}, 0);
		setListAdapter(a);		
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onViewCreated(view, savedInstanceState);
		
		// Warble the things.
		
	}

}

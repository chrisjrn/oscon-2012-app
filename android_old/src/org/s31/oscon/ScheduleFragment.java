package org.s31.oscon;

import java.util.Calendar;
import java.util.Locale;

import org.s31.oscon.schedule.ScheduleProvider;
import org.s31.oscon.schedule.ScheduleStructure;

import android.app.ListFragment;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.SimpleCursorAdapter;

public class ScheduleFragment extends ListFragment {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		// Something something with arguments
		Bundle args = getArguments();
		int day = args.getInt("day", 0);
		int timeSlot = args.getInt("time_slot", 0);

		ScheduleStructure.TimeSlot ts = ScheduleStructure.days.get(day).timeSlots
				.get(timeSlot);
		Log.v("ScheduleFragment", "" + ts.startDate + " / " + ts.endDate);

		// Set the list up.
		Cursor c = getActivity().getContentResolver().query(
				ScheduleProvider.CONTENT_URI,
				ScheduleProvider.defaultProjection,
				ScheduleProvider.ColumnNames.START + ">= ? AND "
						+ ScheduleProvider.ColumnNames.END + " <= ?",
				new String[] { "" + ts.startDate.getTimeInMillis(),
						"" + ts.endDate.getTimeInMillis() },
				"" + ScheduleProvider.ColumnNames.START + " ASC");

		SimpleCursorAdapter a = new SimpleCursorAdapter(getActivity(),
				R.layout.schedule_item, c, new String[] {
						ScheduleProvider.ColumnNames.TITLE,
						ScheduleProvider.ColumnNames.AUTHOR,
						ScheduleProvider.ColumnNames.ROOM, }, new int[] {
						R.id.event_title, R.id.event_author, R.id.event_room },
				0);
		setListAdapter(a);
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onViewCreated(view, savedInstanceState);

		this.getListView().setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// TODO Auto-generated method stub
				showScheduleItem( getListAdapter().getItemId(position) );
				
			}
		});

	}
	
	public void showScheduleItem(long scheduleId) {
		Intent i = new Intent(this.getActivity(), ScheduleItemActivity.class);
		i.putExtra("id", scheduleId);
		this.getActivity().startActivity(i);
	}

}

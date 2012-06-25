package org.s31.oscon;

import org.s31.oscon.schedule.ScheduleProvider;

import android.app.ListActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.SimpleCursorAdapter;

public class OsconAppActivity extends ListActivity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// setContentView(R.layout.main);

		// ArrayAdapter<Event> a = new ScheduleAdapter(this,
		// R.layout.schedule_item, Schedule.mSchedule);
		// this.setListAdapter(a);

		Cursor c = getContentResolver().query(ScheduleProvider.CONTENT_URI,
				ScheduleProvider.defaultProjection, null, null,
				"" + ScheduleProvider.ColumnNames.START + " ASC");
		SimpleCursorAdapter a = new SimpleCursorAdapter(this, R.layout.schedule_item, c, new String[] {
				ScheduleProvider.ColumnNames.TITLE, ScheduleProvider.ColumnNames.AUTHOR, ScheduleProvider.ColumnNames.ROOM, 	
			}, new int[] {
				R.id.event_title, R.id.event_author, R.id.event_room
			}, 0);
		setListAdapter(a);
	}
}
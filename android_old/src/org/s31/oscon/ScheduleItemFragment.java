package org.s31.oscon;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import org.s31.oscon.schedule.ScheduleProvider;
import org.s31.oscon.schedule.ScheduleStructure;

import android.app.Fragment;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ScheduleItemFragment extends Fragment {

	public String mTitle;
	public String mAuthor;
	public String mDescription;
	public String mRoom;
	public Calendar mStartDate;
	public Calendar mEndDate;
	public String mUrl;

	public TextView mTitleView;
	public TextView mAuthorView;
	public TextView mDescriptionView;
	public TextView mRoomView;
	public TextView mDateView;
	
	public static SimpleDateFormat dateAndTime;
	public static SimpleDateFormat timeOnly;
	static {
		dateAndTime = new SimpleDateFormat("EEE, d LLLLLL yyyy hh:mma",Locale.US);
		dateAndTime.setTimeZone(ScheduleStructure.confTimeZone);
		timeOnly = new SimpleDateFormat("hh:mma",Locale.US);
		timeOnly.setTimeZone(ScheduleStructure.confTimeZone);
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		Cursor c = getActivity().getContentResolver().query(
				Uri.withAppendedPath(ScheduleProvider.CONTENT_URI, ""+getArguments().getLong("id",0)) ,
				ScheduleProvider.defaultProjection,
				null, null, null);
		c.moveToFirst();
		
		Log.v("ScheduleItemFragment","Got: " + c);
		
		mTitle = c.getString(c.getColumnIndex(ScheduleProvider.ColumnNames.TITLE));
		mAuthor = c.getString(c.getColumnIndex(ScheduleProvider.ColumnNames.AUTHOR));
		mDescription = c.getString(c.getColumnIndex(ScheduleProvider.ColumnNames.DESCRIPTION));
		mRoom= c.getString(c.getColumnIndex(ScheduleProvider.ColumnNames.ROOM));

		mStartDate = Calendar.getInstance(ScheduleStructure.confTimeZone, Locale.US);
		mStartDate.setTimeInMillis(c.getLong(c.getColumnIndex(ScheduleProvider.ColumnNames.START)));
		Log.v("ScheduleItemFragment", "TimeZone: " + mStartDate.getTimeZone());

		mEndDate = Calendar.getInstance(ScheduleStructure.confTimeZone, Locale.US);
		mEndDate.setTimeInMillis(c.getLong(c.getColumnIndex(ScheduleProvider.ColumnNames.END)));
		
		mUrl = c.getString(c.getColumnIndex(ScheduleProvider.ColumnNames.URL));
		
		c.close();
		
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		return inflater.inflate(R.layout.schedule_item_fragment, null);
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		//super.onViewCreated(view, savedInstanceState);

		mTitleView = ((TextView)view.findViewById(R.id.schedule_item_title));
		mTitleView.setText(mTitle);

		mAuthorView = ((TextView)view.findViewById(R.id.schedule_item_author));
		mAuthorView.setText(mAuthor);

		mRoomView = ((TextView)view.findViewById(R.id.schedule_item_room));
		mRoomView.setText(mRoom);
		
		mDateView = ((TextView)view.findViewById(R.id.schedule_item_date));
		mDateView.setText(String.format("%s--%s", dateAndTime.format(mStartDate.getTime()),timeOnly.format(mEndDate.getTime())));

		mDescriptionView = ((TextView)view.findViewById(R.id.schedule_item_description));
		mDescriptionView.setText(mDescription);

	}

}

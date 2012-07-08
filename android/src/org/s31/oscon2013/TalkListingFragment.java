package org.s31.oscon2013;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class TalkListingFragment extends Fragment {

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

	// Set up some date formatters so we can display the time.
	public static SimpleDateFormat dateAndTime;
	public static SimpleDateFormat timeOnly;
	static {
		// Tuesday, 17 July 2012 9:00am
		dateAndTime = new SimpleDateFormat("EEE, d LLLLLL yyyy hh:mma",
				Locale.US);
		dateAndTime.setTimeZone(Config.confTimeZone);
		// 9:00am
		timeOnly = new SimpleDateFormat("hh:mma", Locale.US);
		timeOnly.setTimeZone(Config.confTimeZone);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// Some data that describes a single talk.
		
		int event = getArguments().getInt("event",0);
		Event e = Schedule.mSchedule.get(event);
		mTitle = e.title;
		mAuthor = e.author;
		mDescription = e.description;
		mRoom = e.room;

		mStartDate = e.startDate();

		mEndDate = e.endDate();
		
		mUrl = e.url;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.talk_listing_fragment, null);
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		
		mTitleView = ((TextView)view.findViewById(R.id.talk_listing_title));
		mTitleView.setText(mTitle);

		mAuthorView = ((TextView)view.findViewById(R.id.talk_listing_author));
		mAuthorView.setText(mAuthor);

		mRoomView = ((TextView)view.findViewById(R.id.talk_listing_room));
		mRoomView.setText(mRoom);
		
		mDateView = ((TextView)view.findViewById(R.id.talk_listing_date));
		mDateView.setText(String.format("%s--%s", dateAndTime.format(mStartDate.getTime()),timeOnly.format(mEndDate.getTime())));

		mDescriptionView = ((TextView)view.findViewById(R.id.talk_listing_description));
		mDescriptionView.setText(mDescription);

	}
			
}

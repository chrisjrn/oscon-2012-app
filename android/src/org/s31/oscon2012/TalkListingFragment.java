package org.s31.oscon2012;

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
		mTitle = "Android-Fu: Awesome Apps for Ice Cream Sandwich and Beyond";
		mAuthor = "Paris Buttfield Addison, Christopher Neugebauer, Jon Manning";
		mDescription = "In this session you'll learn why you can't consider UX and design an optional extra when designing mobile apps for Android, how to tell an awesome app from a terrible app, and the basics of both designing and coding for the latest and greatest Android platform (Android 4.0 and beyond). Stylish apps aren't just for that other mobile platform, and Android is surprisingly easy to get started with.";
		mRoom = "Portland 255";

		mStartDate = Calendar.getInstance(Config.confTimeZone, Locale.US);
		mStartDate.set(2012, 7, 17, 9, 00);

		mEndDate = Calendar.getInstance(Config.confTimeZone, Locale.US);
		mEndDate.set(2012, 7, 17, 12, 30);

		mUrl = "http://www.oscon.com/oscon2012/public/schedule/detail/24288";
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

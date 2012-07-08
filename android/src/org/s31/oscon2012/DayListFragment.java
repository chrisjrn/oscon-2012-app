package org.s31.oscon2012;

import java.util.Arrays;
import java.util.List;

import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;

public class DayListFragment extends ListFragment {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {

		super.onViewCreated(view, savedInstanceState);

		ArrayAdapter<String> a = new ArrayAdapter<String>(this.getActivity(),
				R.layout.text_only_list_item, getListOfDays());
		this.setListAdapter(a);

		
		this.getListView().setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// TODO Auto-generated method stub
				launchScheduleForDay(position);
			}
		});

	}
	
	private void launchScheduleForDay(int day) {
		Intent i = new Intent(this.getActivity(), ScheduleActivity.class);
		
		i.putExtra("day", day); // This adds the value to the intent.
		
		this.getActivity().startActivity(i);
	}
	
	private List<String> getListOfDays() {
		return Arrays.asList(new String[] {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday"});
	}
	

}

package bg.unisofia.fmi.contactapp.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ListView;
import bg.unisofia.fmi.contactapp.R;
import bg.unisofia.fmi.contactapp.UserListAdapter;
import bg.unisofia.fmi.contactapp.service.SessionService;
import bg.unisofia.fmi.contactapp.service.UserService;

public class UserListFragment extends Fragment {
	
	private SessionService sessionService;
	private UserService userService;

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		sessionService = new SessionService(activity);
		userService = new UserService(getActivity());
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_user_list, container, false);
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		final ListView listView = (ListView) getView();		
		listView.setAdapter(new UserListAdapter(userService.getAllUsers()));
		listView.setSelection(sessionService.getSelectedUserIndex());
		listView.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				sessionService.setSelectedUserIndex(position);
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
			}
		});
	}
	
	@Override
	public void onDetach() {
		super.onDetach();
		sessionService = null;
		userService = null;
	}
}

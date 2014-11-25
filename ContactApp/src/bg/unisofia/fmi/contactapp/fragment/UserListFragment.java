package bg.unisofia.fmi.contactapp.fragment;

import bg.unisofia.fmi.contactapp.R;
import bg.unisofia.fmi.contactapp.UserListAdapter;
import bg.unisofia.fmi.contactapp.R.layout;
import bg.unisofia.fmi.contactapp.service.UserService;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class UserListFragment extends Fragment {
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_user_list, container, false);
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		final ListView listView = (ListView) getView();
		UserService userService = new UserService(getActivity());
		listView.setAdapter(new UserListAdapter(userService.getAllUsers()));
	}
}

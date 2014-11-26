package bg.unisofia.fmi.contactapp.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import bg.unisofia.fmi.contactapp.BaseFragment;
import bg.unisofia.fmi.contactapp.R;
import bg.unisofia.fmi.contactapp.UserListAdapter;

public class UserListFragment extends BaseFragment {
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_user_list, container, false);
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		final ListView listView = (ListView) getView();	
		final UserListAdapter userListAdapter = new UserListAdapter(getBaseActivity().getUserService().getAllUsers());
		listView.setAdapter(userListAdapter);
		listView.setSelection(getBaseActivity().getSessionService().getSelectedUserIndex());
		listView.setOnItemClickListener((OnItemClickListener)getActivity());
	}
}

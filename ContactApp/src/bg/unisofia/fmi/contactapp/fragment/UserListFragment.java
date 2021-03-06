package bg.unisofia.fmi.contactapp.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ListView;

import bg.unisofia.fmi.contactapp.R;
import bg.unisofia.fmi.contactapp.adapter.UserListAdapter;
import bg.unisofia.fmi.contactapp.service.android.SmsService;
import bg.unisofia.fmi.contactapp.service.android.SmsServiceConnection;

public class UserListFragment extends BaseFragment implements TextWatcher {
	
	private EditText nameFilterView;
	
	private UserListAdapter userListAdapter;
	
	private SmsServiceConnection smsServiceConnection;

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		smsServiceConnection = new SmsServiceConnection();
		getActivity().bindService(new Intent(activity, SmsService.class), smsServiceConnection, Context.BIND_AUTO_CREATE);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_user_list, container, false);
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		final ListView listView = (ListView) getView().findViewById(R.id.user_list);
		userListAdapter = new UserListAdapter(getBaseActivity().getUserService().getAllUsers());
		listView.setAdapter(userListAdapter);
		listView.setSelection(getBaseActivity().getSessionService().getSelectedUserIndex());
		listView.setOnItemClickListener((OnItemClickListener)getActivity());
		nameFilterView = (EditText) getView().findViewById(R.id.name_filter);
		nameFilterView.addTextChangedListener(this);
	}
	
	@Override
	public void onDetach() {
		super.onDetach();
		getActivity().unbindService(smsServiceConnection);
		smsServiceConnection = null;
	}

	@Override
	public void beforeTextChanged(CharSequence s, int start, int count,
			int after) {
		// empty
	}

	@Override
	public void onTextChanged(CharSequence s, int start, int before, int count) {
		// emtpy
	}

	@Override
	public void afterTextChanged(Editable s) {
		userListAdapter.getFilter().filter(s.toString());
	}
}

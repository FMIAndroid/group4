package bg.unisofia.fmi.contactapp.fragment;

import android.app.Fragment;

import bg.unisofia.fmi.contactapp.activity.BaseActivity;

public class BaseFragment extends Fragment {
	public BaseActivity getBaseActivity() {
		return (BaseActivity) getActivity();
	}
}

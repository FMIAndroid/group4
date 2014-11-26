package bg.unisofia.fmi.contactapp;

import android.app.Fragment;

public class BaseFragment extends Fragment {
	public BaseActivity getBaseActivity() {
		return (BaseActivity) getActivity();
	}
}

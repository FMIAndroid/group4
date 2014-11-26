package bg.unisofia.fmi.contactapp.fragment;

import bg.unisofia.fmi.contactapp.activity.BaseActivity;
import android.app.Fragment;

public class BaseFragment extends Fragment {
	public BaseActivity getBaseActivity() {
		return (BaseActivity) getActivity();
	}
}

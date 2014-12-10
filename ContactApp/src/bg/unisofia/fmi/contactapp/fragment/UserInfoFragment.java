package bg.unisofia.fmi.contactapp.fragment;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import bg.unisofia.fmi.contactapp.R;
import bg.unisofia.fmi.contactapp.adapter.NoteListAdapter;
import bg.unisofia.fmi.contactapp.database.ContactAppContract;
import bg.unisofia.fmi.contactapp.database.ContactAppDbHelper;
import bg.unisofia.fmi.contactapp.model.Note;
import bg.unisofia.fmi.contactapp.model.User;
import bg.unisofia.fmi.contactapp.service.SessionService;

public class UserInfoFragment extends BaseFragment implements
		OnSharedPreferenceChangeListener, OnClickListener {

	@SuppressWarnings("unused")
	private static final String TAG = UserInfoFragment.class.getSimpleName();

	private User user;

	private TextView fullNameView;

	private ListView noteListView;

	private ContactAppDbHelper dbHelper;

	private Button addNoteButton;

	private EditText noteEditText;

	private NoteListAdapter noteListAdapter;

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		dbHelper = new ContactAppDbHelper(activity);
		noteListAdapter = new NoteListAdapter(getActivity(), findNotes());
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_user_info, container, false);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		fullNameView = (TextView) getView().findViewById(R.id.fullNameValue);
		noteListView = (ListView) getView().findViewById(R.id.noteList);
		noteListView.setAdapter(noteListAdapter);
		noteEditText = (EditText) getView().findViewById(R.id.noteEditText);
		addNoteButton = (Button) getView().findViewById(R.id.addNoteButton);
		addNoteButton.setOnClickListener(this);
		update();
		getBaseActivity().getSessionService()
				.registerOnSharedPreferenceChangeListener(this);
	}

	@Override
	public void onSharedPreferenceChanged(SharedPreferences sharedPreferences,
			String key) {
		if (key == SessionService.SELECTED_USER_INDEX) {
			update();
		}
	}

	private void update() {
		user = getBaseActivity().getUserService().getUser(
				getBaseActivity().getSessionService().getSelectedUserIndex());
		fullNameView.setText(user.getFullName());
		updateNotes();
	}
	
	private void updateNotes() {
		noteListAdapter.swapCursor(findNotes());
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		getBaseActivity().getSessionService()
				.unregisterOnSharedPreferenceChangeListener(this);
	}

	@Override
	public void onDetach() {
		super.onDetach();
		noteListAdapter = null;
		dbHelper.close();
		dbHelper = null;
	}

	@Override
	public void onClick(View v) {
		if(v.getId() == R.id.addNoteButton) {
			Note note = new Note();
			note.setUserId(getBaseActivity().getSessionService().getSelectedUserIndex());
			note.setContent(noteEditText.getText().toString());
			dbHelper.getWritableDatabase().insert(ContactAppContract.Note.TABLE_NAME, null, note.getContentValues());
			updateNotes();
		}
	}
	
	private Cursor findNotes() {
		return dbHelper.getReadableDatabase().query(
				ContactAppContract.Note.TABLE_NAME, null, null, null, null,
				null, null);
	}
}

package bg.unisofia.fmi.contactapp.adapter;

import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;
import bg.unisofia.fmi.contactapp.model.Note;

public class NoteListAdapter extends CursorAdapter {

    public NoteListAdapter(Context context, Cursor c) {
        super(context, c, false);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
    	final TextView textView = new TextView(context);
    	bindTextView(textView, cursor);
        return textView;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
    	final TextView textView = (TextView) view;
    	bindTextView(textView, cursor);
    	
    }
    
    private void bindTextView(TextView textView, Cursor cursor) {
    	final Note note = Note.fromCursor(cursor);
    	textView.setText(note.getContent());
    }
}
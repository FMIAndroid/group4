package bg.unisofia.fmi.contactapp.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import bg.unisofia.fmi.contactapp.model.Note;

public class ContactAppDbHelper extends SQLiteOpenHelper{

	private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "ContactApp.db";
    
    private static final String SQL_CREATE_NOTES = String.format("CREATE TABLE %s(%s INTEGER, %s TEXT, %s INTEGER)",
    		ContactAppContract.Note.TABLE_NAME,
    		ContactAppContract.Note._ID,
    		ContactAppContract.Note.CONTENT,
    		ContactAppContract.Note.USER_ID);
    		
    private static final String SQL_DROP_NOTES = String.format("DROP TABLE %s", ContactAppContract.Note.TABLE_NAME); 

    public ContactAppDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(SQL_CREATE_NOTES);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL(SQL_DROP_NOTES);
		onCreate(db);
	}
	
	public void insertNote(Note note) {
		getWritableDatabase().insert(ContactAppContract.Note.TABLE_NAME, null, note.getContentValues());
	}
	
	public Cursor findNotesByUserId(int id) {
		return getReadableDatabase().query(
				ContactAppContract.Note.TABLE_NAME, null, String.format("%s = ?", ContactAppContract.Note.USER_ID), new String[]{Integer.toString(id)} , null,
				null, null);
	}

}

package bg.unisofia.fmi.contactapp.model;

import android.content.ContentValues;
import android.database.Cursor;

import bg.unisofia.fmi.contactapp.database.ContactAppContract;

public class Note {
    private long id;

    private long userId;

    private String content;

    public Note() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public ContentValues getContentValues() {
        ContentValues values =  new ContentValues();
        values.put(ContactAppContract.Note._ID, id);
        values.put(ContactAppContract.Note.CONTENT, content);
        values.put(ContactAppContract.Note.USER_ID, userId);
        return values;
    }

    public static Note fromCursor(Cursor c) {
        final Note note = new Note();

        int idColumnIndex = c.getColumnIndexOrThrow(ContactAppContract.Note._ID);
        note.setId(c.isNull(idColumnIndex)
                ? null
                : c.getLong(idColumnIndex));

        note.setUserId(c.getLong(c.getColumnIndexOrThrow(ContactAppContract.Note.USER_ID)));
        note.setContent(c.getString(c.getColumnIndexOrThrow(ContactAppContract.Note.CONTENT)));

        return note;
    }

}
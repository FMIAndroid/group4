package bg.unisofia.fmi.contactapp.database;

import android.provider.BaseColumns;

public class ContactAppContract {
    public ContactAppContract() {
        // empty
    }

    public static abstract class Note implements BaseColumns {
        public static final String TABLE_NAME = "notes";
        public static final String CONTENT = "content";
        public static final String USER_ID = "user_id";
    }
}
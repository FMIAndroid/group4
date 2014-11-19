package bg.unisofia.fmi.contactapp;

import java.sql.SQLException;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

	private static final String TAG = DatabaseHelper.class.getSimpleName();
	
	private static final String DATABASE_NAME = "mydb.db";

	private static final int DATABASE_VERSION = 1;

	private RuntimeExceptionDao<User, Integer> userDao;

	public DatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION, R.raw.ormlite_config);
		userDao = null;
	}

	/**
	 * This is called when the database is first created. Usually you should call createTable statements here to create
	 * the tables that will store your data.
	 */
	@Override
	public void onCreate(SQLiteDatabase db, ConnectionSource connectionSource) {
		try {
			TableUtils.createTable(connectionSource, User.class);
		} catch (SQLException e) {
			Log.e(TAG, e.getMessage(), e);
			throw new RuntimeException(e);
		}

		RuntimeExceptionDao<User, Integer> dao = getUserDao();
		User user = new User();
		dao.create(user);
	}

	/**
	 * This is called when your application is upgraded and it has a higher version number. This allows you to adjust
	 * the various data to match the new version number.
	 */
	@Override
	public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource, int oldVersion, int newVersion) {
		try {
			TableUtils.dropTable(connectionSource, User.class, true);
			onCreate(db, connectionSource);
		} catch (SQLException e) {
			Log.e(TAG, e.getMessage(), e);
			throw new RuntimeException(e);
		}
	}

	public RuntimeExceptionDao<User, Integer> getUserDao() {
		if (userDao == null) {
			userDao = getRuntimeExceptionDao(User.class);
		}
		return userDao;
	}

	/**
	 * Close the database connections and clear any cached DAOs.
	 */
	@Override
	public void close() {
		super.close();
		userDao = null;
	}
}

package org.s31.oscon.schedule;

import java.util.List;
import java.util.Set;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.provider.BaseColumns;
import android.text.TextUtils;
import android.util.Log;

public class ScheduleProvider extends ContentProvider {

	/* 	public String start;
	public String end;
	public String description;
	public String author;
	public String rawDescription;
	public String title;
	public String room;
	*/
	
	public static final class ColumnNames implements BaseColumns {
		public static final String START = "start";
		public static final String END = "end";
		public static final String DESCRIPTION = "description";
		public static final String AUTHOR = "author";
		public static final String TITLE = "title";
		public static final String ROOM = "room";
		public static final String IN_SCHEDULE = "in_schedule";
		
		/** This class should not be instantiated. */
		private ColumnNames() {
		}
	}
	
	public static final String[] defaultProjection = {
		ColumnNames._ID,
		ColumnNames.START,
		ColumnNames.END,
		ColumnNames.DESCRIPTION,
		ColumnNames.AUTHOR,
		ColumnNames.TITLE,
		ColumnNames.ROOM,
		ColumnNames.IN_SCHEDULE,
	};

	/**
	 * This class helps open, create, and upgrade the database file.
	 */
	private static class DatabaseHelper extends SQLiteOpenHelper {

		private static final String DATABASE_NAME = "events.db";
		private static final int DATABASE_VERSION = 1;
		
		private Context mContext;

		DatabaseHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
			mContext = context;
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL("CREATE TABLE " + TABLE_NAME + " (" 
					+ ColumnNames._ID + " INTEGER PRIMARY KEY," 
					+ ColumnNames.START + " INTEGER,"
					+ ColumnNames.END + " INTEGER,"
					+ ColumnNames.DESCRIPTION + " TEXT," 
					+ ColumnNames.AUTHOR + " TEXT," 
					+ ColumnNames.TITLE + " INTEGER,"
					+ ColumnNames.ROOM + " TEXT,"
					+ ColumnNames.IN_SCHEDULE + " INTEGER"
					+ ");");
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			Log.e(getClass().getName(),
					"onUpgrade() called but we've never changed the database version.  This should never happen.");
			db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
			onCreate(db);
		}
	}

	private static final String AUTHORITY = ScheduleProvider.class.getName()
			.toLowerCase();
	public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY
			+ "/");
	private static final UriMatcher sUriMatcher;
	public static final String TABLE_NAME = "events";
	private static final int URITYPE_ALL = 1;
	private static final int URITYPE_SPECIFIC_EVENT = 2;

	static {
		sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
		sUriMatcher.addURI(AUTHORITY, null, URITYPE_ALL);
		sUriMatcher.addURI(AUTHORITY, "#", URITYPE_SPECIFIC_EVENT);
	}

	private DatabaseHelper mOpenHelper;
	
	@Override
	public boolean onCreate() {
		mOpenHelper = new DatabaseHelper(getContext());
		return true;
	}
	
	@Override
	public int delete(Uri uri, String where, String[] whereArgs) {
		int count;
		SQLiteDatabase db = mOpenHelper.getWritableDatabase();
		String id;

		switch (sUriMatcher.match(uri)) {
		case URITYPE_ALL:
			count = db.delete(TABLE_NAME, where, whereArgs);
			break;

		case URITYPE_SPECIFIC_EVENT:
			id = uri.getPathSegments().get(0);
			count = db.delete(TABLE_NAME,
					ColumnNames._ID
							+ "="
							+ id
							+ (!TextUtils.isEmpty(where) ? " AND (" + where
									+ ')' : ""), whereArgs);
			break;

		default:
			throw new IllegalArgumentException("Unknown URI: " + uri);
		}

		if (count > 0)
			getContext().getContentResolver().notifyChange(uri, null);

		return count;
	}

	@Override
	public String getType(Uri uri) {
		switch (sUriMatcher.match(uri)) {
		case URITYPE_ALL:
			return "vnd.android.cursor.dir/vnd.vardr.android_ng.user";

		case URITYPE_SPECIFIC_EVENT:
			return "vnd.android.cursor.item/vnd.vardr.android_ng.user";

		default:
			throw new IllegalArgumentException("Unknown URI: " + uri);
		}
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
				
		SQLiteDatabase db = mOpenHelper.getWritableDatabase();
		long rowId;
		
		switch (sUriMatcher.match(uri)) {
		case URITYPE_ALL:
			rowId = db.insert(TABLE_NAME, null, values);
			break;

		default:
			throw new IllegalArgumentException("Unknown URI: " + uri);
		}

		if (rowId < 0)
			throw new SQLException("Failed to insert row into " + uri);
		
		// TODO: Can we get away with only notifying the specific URI? And
		// observers can watch all children of the generic URI?
		Uri specificUri = ContentUris.withAppendedId(uri, rowId);
		getContext().getContentResolver().notifyChange(specificUri, null);
		getContext().getContentResolver().notifyChange(uri, null);

		return specificUri;
	}
	
	public static int insertEvents(Context c, List<Event> events) {
		ContentValues[] values = new ContentValues[events.size()];
		
		int j = 0;
		for (Event i : events) {
			ContentValues v = new ContentValues();

			v.put(ColumnNames.START, i.startDate().getTimeInMillis());
			v.put(ColumnNames.END, i.endDate().getTimeInMillis());
			v.put(ColumnNames.AUTHOR, i.author);
			v.put(ColumnNames.TITLE, i.title);
			v.put(ColumnNames.ROOM, i.room);
			v.put(ColumnNames.IN_SCHEDULE, 0);
			
			if (i.description.trim().equals("")) {
				v.put(ColumnNames.DESCRIPTION, i.rawDescription);
			} else {
				v.put(ColumnNames.DESCRIPTION, i.description);
			}
			values[j++] = v; 
		}
		return c.getContentResolver().bulkInsert(CONTENT_URI, values);
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();

		switch (sUriMatcher.match(uri)) {
		case URITYPE_ALL:
			queryBuilder.setTables(TABLE_NAME);
			break;

		case URITYPE_SPECIFIC_EVENT:
			queryBuilder.setTables(TABLE_NAME);
			queryBuilder.appendWhere(ColumnNames._ID + "="
					+ uri.getPathSegments().get(0));
			break;

		default:
			throw new IllegalArgumentException("Unknown URI: " + uri);
		}

		// Get the database and run the query
		SQLiteDatabase db = mOpenHelper.getReadableDatabase();
		Cursor cursor = queryBuilder.query(db, projection, selection,
				selectionArgs, null, null, sortOrder);
		cursor.setNotificationUri(getContext().getContentResolver(), uri);

		return cursor;
	}

	@Override
	public int update(Uri uri, ContentValues values, String where,
			String[] whereArgs) {
		int count;
		SQLiteDatabase db = mOpenHelper.getWritableDatabase();
		String id;

		switch (sUriMatcher.match(uri)) {
		case URITYPE_ALL:
			count = db.update(TABLE_NAME, values, where, whereArgs);
			break;

		case URITYPE_SPECIFIC_EVENT:
			id = uri.getPathSegments().get(0);
			count = db.update(TABLE_NAME, values,
					ColumnNames._ID
							+ "="
							+ id
							+ (!TextUtils.isEmpty(where) ? " AND (" + where
									+ ')' : ""), whereArgs);
			break;

		default:
			throw new IllegalArgumentException("Unknown URI: " + uri);
		}

		if (count > 0)
			getContext().getContentResolver().notifyChange(uri, null);

		return count;
	}
}

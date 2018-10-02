package se.mah.ad8885.sensorlab4;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Kristofer Svensson on 2017-02-21.
 */

/**
 * A standard database-class with some added functionality for data relevant to the user.
 */
public class MyDBHandler extends SQLiteOpenHelper {


    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "userInfo.db";

    public static final String TABLE_LOGIN = "login";
    public static final String COLUMN_ID_LOGIN = "_id";
    public static final String COLUMN_NAME = "_name";
    public static final String COLUMN_PASSWORD = "_password";
    public static final String COLUMN_STEPS = "_steps";
    public static final String COLUMN_START_TIME = "_startTime";

    private static final String CREATE_TABLE_LOGIN = "CREATE TABLE " + TABLE_LOGIN + "( " +
            COLUMN_ID_LOGIN + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_NAME + " TEXT, " +
            COLUMN_PASSWORD + " TEXT, " +
            COLUMN_STEPS + " INTEGER, " +
//            COLUMN_START_TIME + " REAL " +
            COLUMN_START_TIME + " DOUBLE " +
            ");";

    public MyDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_LOGIN);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS" + TABLE_LOGIN);
        onCreate(db);
    }

    public void addNewUser(User userInfo) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, userInfo.get_name());
        values.put(COLUMN_PASSWORD, userInfo.get_password());
        values.put(COLUMN_STEPS, 0);
        values.put(COLUMN_START_TIME, 1);
        db.insert(TABLE_LOGIN, null, values);
        db.close();
    }

    public void setStartTime(String name, double startTime) {
//        long startTimeStamp = startTime;
        double startTimeStamp = startTime;
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_START_TIME, startTimeStamp);
        db.update(TABLE_LOGIN, values, COLUMN_NAME + " = '" + name + "'", null);
    }

    public void addUserSteps(String name) {
        int steps = getUserSteps(name) + 1;
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_STEPS, steps);
        db.update(TABLE_LOGIN, values, COLUMN_NAME + " = '" + name + "'", null);
    }

    public void resetUserSteps(String name) {
        int steps = 0;
        double time = 1;
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_STEPS, steps);
        values.put(COLUMN_START_TIME, time);
        db.update(TABLE_LOGIN, values, COLUMN_NAME + " = '" + name + "'", null);
    }


    public boolean checkUserNameTaken(String name) {
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_LOGIN + " WHERE " + COLUMN_NAME + " = '" + name + "'";
        //Cursor point to a location in your results
        Cursor c = db.rawQuery(query, null);
        //Move to the first row in your results
        c.moveToFirst();
        boolean nameExists = c.getCount() > 0;
        db.close();
        return nameExists;
    }

    public String getUserPassword(String name) {
        String dbString = "";
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_LOGIN + " WHERE " + COLUMN_NAME + " = '" + name + "'";
        //Cursor point to a location in your results
        Cursor c = db.rawQuery(query, null);
        //Move to the first row in your results
        c.moveToFirst();

        dbString = c.getString(c.getColumnIndex(COLUMN_PASSWORD));
        db.close();
        return dbString;
    }

    public int getUserSteps(String name) {
        int steps = 0;
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_LOGIN + " WHERE " + COLUMN_NAME + " = '" + name + "'";
        //Cursor point to a location in your results
        Cursor c = db.rawQuery(query, null);
        //Move to the first row in your results
        c.moveToFirst();
        steps = c.getInt(c.getColumnIndex(COLUMN_STEPS));
        db.close();
        return steps;
    }

    public double getUserStartTime(String name) {
        double startTime = 0;
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_LOGIN + " WHERE " + COLUMN_NAME + " = '" + name + "'";
        //Cursor point to a location in your results
        Cursor c = db.rawQuery(query, null);
        //Move to the first row in your results
        c.moveToFirst();
        startTime = c.getLong(c.getColumnIndex(COLUMN_START_TIME));
        db.close();
        return startTime;
    }
}

package com.ep.babylon.provider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Eduardo on 26/08/2015.
 */
public class ContactsDataBase extends SQLiteOpenHelper {

    private static final String TAG = "ContactsDataBase";
    private static final int DATA_BASE_VERSION = 1;
    private static final String DATA_BASE_NAME = "contacts_data_base";


    public static final String TABLE_CONTACTS = "contacts";
    public static final String ID = "_id";
    public static final String COLUMN_FIRST_NAME = "first_name";
    public static final String COLUMN_SURNAME = "surname";
    public static final String COLUMN_ADDRESS = "address";
    public static final String COLUMN_PHONE_NUMBER = "phone_number";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_CONTACTS_ID = "contacts_id";
    public static final String COLUMN_CREATED_AT = "createdAt";
    public static final String COLUMN_UPDATED_AT = "updatedAt";

    private static final String CREATE_TABLE_VIDEOS = "create table " + TABLE_CONTACTS
            + " ("
            + ID + " integer primary key autoincrement, "
            + COLUMN_FIRST_NAME + " text not null, "
            + COLUMN_PHONE_NUMBER + " text not null, "
            + COLUMN_EMAIL + " text not null, "
            + COLUMN_CONTACTS_ID + " integer not null, "
            + COLUMN_SURNAME + " text not null, "
            + COLUMN_ADDRESS + " text not null, "
            + COLUMN_CREATED_AT + " text not null,"
            + COLUMN_UPDATED_AT + " text not null" +
            ");";


    public ContactsDataBase(Context context) {
        super(context, DATA_BASE_NAME, null, DATA_BASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);
        db.execSQL(CREATE_TABLE_VIDEOS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(TAG, "Upgrading database. Existing contents will be lost. ["
                + oldVersion + "]->[" + newVersion + "]");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);
        onCreate(db);
    }


}

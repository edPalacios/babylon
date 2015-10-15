package com.ep.babylon.ormLite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.ep.babylon.model.Contact;
import com.ep.test.babylon.R;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

/**
 * Created by Eduardo on 15/10/2015.
 */
public class ContactDataBaseHelper extends OrmLiteSqliteOpenHelper {

    private Dao<ContactRow, Integer> contactRowDao;
    private static final String DATABASE_NAME = "com.ep.babylon.ormLite.ormLite";
    private static final int DATABASE_VERSION = 1;
    private static final String TAG = "ContactDataBaseHelper";



    public ContactDataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION, R.raw.ormlite_config);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, ContactRow.class);
        } catch (SQLException e) {
            Log.e(TAG, "Error creating OrmLite data base " + e.getMessage());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource, int i, int i1) {
        onCreate(sqLiteDatabase, connectionSource);
    }

    public Dao<ContactRow, Integer> getContactRowDao() throws SQLException {
        if (contactRowDao == null) {
            contactRowDao = getDao(ContactRow.class);
        }
        return contactRowDao;
    }

}


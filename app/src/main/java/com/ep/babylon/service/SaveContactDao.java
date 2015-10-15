package com.ep.babylon.service;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.RemoteException;
import android.util.Log;

import com.ep.babylon.model.Contact;
import com.ep.babylon.provider.ContactsContentProvider;
import com.ep.babylon.provider.ContactsContentResolver;
import com.ep.babylon.provider.ContactsDataBase;

/**
 * Created by Eduardo on 15/10/2015.
 */
public class SaveContactDao {

    private static final String TAG = "SaveContactDao";
    private static SaveContactDao instance;
    private String[] projection = {
            ContactsDataBase.COLUMN_CONTACTS_ID,
    };

    private SaveContactDao () {

    }

    public static SaveContactDao getInstance() {
        if (instance == null) {
            instance =  new SaveContactDao();
        }
        return instance;
    }

    public void insertNewContactInDB(ContactsContentResolver resolver, Contact contact) {
        try {
            if (!isContactInDB(resolver, contact)) {
                Log.d(TAG, "Insert new row");
                resolver.insert(
                        ContactsContentProvider.CONTENT_URI_CONTACTS,
                        getContactValues(contact));
            }

        } catch (RemoteException e) {
            Log.e(TAG, "Error saving contacts in data base " + e.getMessage());
        }
    }

    public boolean isContactInDB(ContactsContentResolver resolver, Contact contact) throws RemoteException {
        Cursor cursor = resolver.query(
                ContactsContentProvider.CONTENT_URI_CONTACTS,
                projection,
                ContactsDataBase.COLUMN_CONTACTS_ID + "=?",
                new String[]{String.valueOf(contact.getId())},
                null
        );
        boolean isContactInDB = cursor.moveToNext();
        cursor.close();
        return isContactInDB;
    }

    private ContentValues getContactValues(Contact contact) {
        ContentValues values = new ContentValues();
        values.put(ContactsDataBase.COLUMN_FIRST_NAME, contact.getFirst_name());
        values.put(ContactsDataBase.COLUMN_SURNAME, contact.getSurname());
        values.put(ContactsDataBase.COLUMN_ADDRESS, contact.getAddress());
        values.put(ContactsDataBase.COLUMN_PHONE_NUMBER, contact.getPhone_number());
        values.put(ContactsDataBase.COLUMN_EMAIL, contact.getEmail());
        values.put(ContactsDataBase.COLUMN_CONTACTS_ID, contact.getId());
        values.put(ContactsDataBase.COLUMN_CREATED_AT, contact.getCreatedAt());
        values.put(ContactsDataBase.COLUMN_UPDATED_AT, contact.getUpdatedAt());
        return values;
    }
}

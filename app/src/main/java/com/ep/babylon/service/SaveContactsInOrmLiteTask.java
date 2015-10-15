package com.ep.babylon.service;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.ep.babylon.model.Contact;
import com.ep.babylon.ormLite.DaoContactRowImpl;

import java.util.List;

/**
 * Created by Eduardo on 15/10/2015.
 */
public class SaveContactsInOrmLiteTask extends AsyncTask<List<Contact>, Void, Void> {

    private Context context;
    private static final String TAG = "SaveContactsInOrmLite";

    public SaveContactsInOrmLiteTask(Context context) {
        this.context = context;
    }

    @Override
    protected Void doInBackground(List<Contact>... params) {
        if (params.length == 1) {
            List<Contact> mContacts = params[0];
            DaoContactRowImpl daoContactRow = new DaoContactRowImpl(context);
            for (Contact contact : mContacts) {
                daoContactRow.saveContact(contact);
            }

            List<Contact> contacts = daoContactRow.loadContacts();
            for (Contact contact : contacts) {
                Log.i(TAG, "Contact read at OrmLite: " + contact.getFirst_name());
            }
        }
        return null;
    }


}

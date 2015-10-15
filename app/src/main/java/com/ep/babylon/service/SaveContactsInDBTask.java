package com.ep.babylon.service;

import android.content.Context;
import android.os.AsyncTask;

import com.ep.babylon.model.Contact;
import com.ep.babylon.ormLite.DaoContactRowImpl;
import com.ep.babylon.provider.ContactsContentResolver;

import java.util.List;

/**
 * Created by Eduardo on 14/10/2015.
 * In this class we are saving data in SQLite  and OrmLite.
 * The app displays the one saved in SQLite but could work as well with ormLite
 */
public class SaveContactsInDBTask extends AsyncTask<List<Contact>, Void, Void> {

    private Context context;

    public SaveContactsInDBTask(Context context) {
        this.context = context;
    }


    @Override
    protected Void doInBackground(List<Contact>... params) {
        if (params.length == 1) {
            List<Contact> mContacts = params[0];
            ContactsContentResolver resolver = new ContactsContentResolver(context);
            for (Contact contact : mContacts) {
                SaveContactDao.getInstance().insertNewContactInDB(resolver, contact);
            }
        }
        return null;
    }


}

package com.ep.babylon.ormLite;

import android.content.Context;
import android.util.Log;

import com.ep.babylon.model.Contact;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.QueryBuilder;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Eduardo on 15/10/2015.
 */
public class DaoContactRowImpl implements ContactRowOps {

    private static final String TAG = "DaoContactRowImpl";
    private Context context;
    private ContactDataBaseHelper dataBaseHelper;

    public DaoContactRowImpl(Context context) {
        this.context = context;
    }

    private ContactDataBaseHelper  openDataBase() {
        if (dataBaseHelper == null) {
            dataBaseHelper = OpenHelperManager.getHelper(context, ContactDataBaseHelper.class);
        }
        return dataBaseHelper;
    }


    @Override
    public boolean saveContact(Contact contact) {
        int contadSaved;
        try {
            Dao contactDao = openDataBase().getContactRowDao();
            contadSaved = contactDao.create(convertContactToContactRow(contact));
        } catch (SQLException e) {
            Log.e(TAG, "Error saving contact in OrmLite" + e.getMessage());
            return false;
        }
        Log.i(TAG, "Contact was saved in OrmLite");
        return contadSaved == 1;
    }

    @Override
    public List<Contact> loadContacts() {
        try {
            Log.i(TAG, "Reding contacts in OrmLite");
            return convertContactRowToContact(openDataBase().getContactRowDao().queryForAll());
        } catch (SQLException e) {
            Log.e(TAG, "Error reading contacts from OrmLite" + e.getMessage());
            return Collections.EMPTY_LIST;
        }
    }

    private ContactRow convertContactToContactRow(Contact contact) {
        return new ContactRow(
                contact.getFirst_name(),
                contact.getSurname(),
                contact.getAddress(),
                contact.getPhone_number(),
                contact.getEmail(),
                contact.getId(),
                contact.getCreatedAt(),
                contact.getUpdatedAt()

        );
    }


    private List<Contact> convertContactRowToContact(List<ContactRow> contactRows) {
        List<Contact> contacts = new ArrayList<>(contactRows.size());
        for (ContactRow row : contactRows) {
            Contact contact = new Contact(
                    row.getFirst_name(),
                    row.getSurname(),
                    row.getAddress(),
                    row.getPhone_number(),
                    row.getEmail(),
                    row.getContactId(),
                    row.getCreatedAt(),
                    row.getUpdatedAt()
            );
            contacts.add(contact);
        }
        return contacts;
    }
}

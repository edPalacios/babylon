package com.ep.babylon.ormLite;

import com.ep.babylon.model.Contact;

import java.util.List;

/**
 * Created by Eduardo on 15/10/2015.
 */
public interface ContactRowOps {

    public boolean saveContact(Contact contact);
    public List<Contact> loadContacts();
}

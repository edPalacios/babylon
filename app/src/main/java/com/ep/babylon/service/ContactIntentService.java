package com.ep.babylon.service;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;

import com.ep.babylon.retrofit.ContactServerMediator;

/**
 * Created by Eduardo on 14/10/2015.
 *
 */
public class ContactIntentService extends IntentService {

    public ContactIntentService() {
        super("ContactIntentService");
    }

    public static Intent makeIntent(Context context) {
        return new Intent(context, ContactIntentService.class);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        ContactServerMediator serverMediator = new ContactServerMediator(getApplicationContext());
        serverMediator.callGetContacts();

    }


}

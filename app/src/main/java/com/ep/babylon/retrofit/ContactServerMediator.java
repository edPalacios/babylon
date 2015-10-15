package com.ep.babylon.retrofit;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.widget.Toast;

import com.ep.babylon.model.Avatar;
import com.ep.babylon.model.Contact;
import com.ep.babylon.ormLite.DaoContactRowImpl;
import com.ep.babylon.service.SaveContactsInDBTask;
import com.ep.babylon.service.SaveContactsInOrmLiteTask;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;
/**
 * Created by Eduardo on 14/10/2015.
 *
 */
public class ContactServerMediator {

    private static final String TAG = "ContactServerMediator";
    private static final String CONTACTS_END_POINT = "http://fast-gorge.herokuapp.com";
    private static final String ERROR_ACCESSING_CONTACTS = "Error accessing contacts";
    public static final String ACTION_RESPONSE_OK = "com.ep.babylon.retrofit.response.OK";
    public static final String EXTRA_AVATARS = "avatars";
    public static final String AVATARS_50DP_END_POINT = "http://api.adorable.io/avatars/50/";
    private static ContactClient contactClient;
    private final Retrofit retrofit;
    private final SimpleOkHttpClient httpClient;
    private final Context context;

    public ContactServerMediator(Context context) {
        httpClient = new SimpleOkHttpClient();
        retrofit = new Retrofit.Builder()
                .baseUrl(CONTACTS_END_POINT)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient)
                .build();
        contactClient = retrofit.create(ContactClient.class);
        this.context = context;
    }

    public void callGetContacts() {
        Call<Contact[]> call =  contactClient.getContacts();
        call.enqueue(new Callback<Contact[]>() {
            @Override
            public void onResponse(Response<Contact[]> response, Retrofit retrofit) {
                Contact[] mContacts = response.body();
                List<Contact> contactsToListActivity = new ArrayList<> (Arrays.asList(mContacts));
                ArrayList<Avatar> avatars= getAvatars(contactsToListActivity);
                SaveContactsInDBTask saveContactsInDBTask = new SaveContactsInDBTask(context);
                saveContactsInDBTask.execute(contactsToListActivity);
                SaveContactsInOrmLiteTask saveContactsInOrmLiteTask = new SaveContactsInOrmLiteTask(context);
                saveContactsInOrmLiteTask.execute(contactsToListActivity);
                sendBroadcast(avatars);
            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(context, ERROR_ACCESSING_CONTACTS, Toast.LENGTH_SHORT).show();
                Log.e(TAG, ERROR_ACCESSING_CONTACTS + t.getMessage());
            }
        });
    }

    private ArrayList<Avatar> getAvatars(List<Contact> contactsToListActivity) {
        ArrayList<RequestCreator> requestCreators = new ArrayList<RequestCreator>(contactsToListActivity.size());
        ArrayList<Avatar> avatars = new ArrayList<>(requestCreators.size());
        for (Contact contact : contactsToListActivity) {
            requestCreators.add(Picasso.with(context)
                    .load(AVATARS_50DP_END_POINT + contact.getAddress()));
        }
        for (RequestCreator creator : requestCreators) {
            avatars.add(new Avatar(creator));
        }
        return avatars;
    }


    private void sendBroadcast( ArrayList<Avatar> avatars) {
        LocalBroadcastManager.getInstance(context).sendBroadcast(new Intent(
                ACTION_RESPONSE_OK
        ).addCategory(Intent.CATEGORY_DEFAULT)
        .putParcelableArrayListExtra(EXTRA_AVATARS, avatars));
    }

}

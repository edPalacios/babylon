package com.ep.babylon.view;

import android.app.FragmentManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.ep.babylon.model.Avatar;
import com.ep.babylon.model.Contact;
import com.ep.babylon.retrofit.ContactServerMediator;
import com.ep.babylon.service.ContactIntentService;
import com.ep.test.babylon.R;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Eduardo on 14/10/2015.
 *
 */
public class ContactListActivity extends AppCompatActivity {

    private static final String CONTACT_FRAGMENT = "contact_fragment";

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    private ContactBroadcastReceiver contactBroadcastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_list);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        contactBroadcastReceiver = new ContactBroadcastReceiver();

        if (isNetworkConnected()) {
            startService(ContactIntentService.makeIntent(getApplicationContext()));
        }
    }

    private void displayContactListFragment(ArrayList<Avatar>avatars) {
        FragmentManager fm = getFragmentManager();
        fm.beginTransaction()
                .add(
                        R.id.contact_list_fragment_container,
                        ContactListFragment.newInstance(avatars),
                        ContactListFragment.FRAGMENT_TAG
                )
                .addToBackStack(CONTACT_FRAGMENT)
                .commit();
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerDownloadReceiver();
    }

    private void registerDownloadReceiver() {
        IntentFilter intentFilter =
                new IntentFilter(ContactServerMediator.ACTION_RESPONSE_OK);
        intentFilter.addCategory(Intent.CATEGORY_DEFAULT);

        LocalBroadcastManager.getInstance(this)
                .registerReceiver(contactBroadcastReceiver,
                        intentFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(contactBroadcastReceiver);
    }

    private boolean isNetworkConnected() {
        ConnectivityManager cm
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null;
    }

    private class ContactBroadcastReceiver extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getExtras().get(ContactServerMediator.EXTRA_AVATARS) != null) {
                ArrayList<Avatar>avatars = (ArrayList<Avatar>) intent.getExtras()
                        .get(ContactServerMediator.EXTRA_AVATARS);
                displayContactListFragment(avatars);
            }
        }
    }

}

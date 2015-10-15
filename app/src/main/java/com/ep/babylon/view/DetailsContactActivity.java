package com.ep.babylon.view;

import android.app.FragmentManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;

import com.ep.babylon.model.Avatar;
import com.ep.babylon.model.Contact;
import com.ep.babylon.retrofit.ContactServerMediator;
import com.ep.babylon.service.LoadDetailAvatarIntentService;
import com.ep.test.babylon.R;

/**
 * Created by Eduardo on 14/10/2015.
 */
public class DetailsContactActivity extends AppCompatActivity {

    private Contact contact;
    private Avatar avatar;
    private AvatarReceiver avatarReceiver;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_contact);
        getExtrasFromIntent();
        avatarReceiver = new AvatarReceiver();
        startService(LoadDetailAvatarIntentService
                .makeIntent(getApplicationContext(), contact.getAddress()));
    }

    private void displayDetailsFragment() {
        FragmentManager fm = getFragmentManager();
        fm.beginTransaction().add(
                R.id.details_fragment_container,
                DetailContactFragment.newInstance(contact, avatar),
                DetailContactFragment.FRAGMENT_TAG
        ).commit();
    }

    private void getExtrasFromIntent() {
        if (getIntent().getExtras().get(ContactListFragment.EXTRA_CONTACT) != null) {
            contact = (Contact) getIntent().getExtras().get(ContactListFragment.EXTRA_CONTACT);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerAvatarReceiver();
    }

    @Override
    protected void onPause() {
        super.onPause();
        LocalBroadcastManager.getInstance(getApplicationContext())
                .unregisterReceiver(avatarReceiver);
    }

    private void registerAvatarReceiver() {
        IntentFilter intentFilter =
                new IntentFilter(LoadDetailAvatarIntentService.ACTION_AVATAR_RESPONSE);
        intentFilter.addCategory(Intent.CATEGORY_DEFAULT);

        LocalBroadcastManager.getInstance(this)
                .registerReceiver(avatarReceiver,
                        intentFilter);
    }

    private class AvatarReceiver extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getExtras().get(LoadDetailAvatarIntentService.EXTRA_AVATAR_DETAIL) != null) {
                avatar = (Avatar) intent.getExtras()
                        .get(LoadDetailAvatarIntentService.EXTRA_AVATAR_DETAIL);
                displayDetailsFragment();
            }
        }
    }
}

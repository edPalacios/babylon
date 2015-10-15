package com.ep.babylon.service;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;

import com.ep.babylon.model.Avatar;
import com.ep.babylon.model.Contact;
import com.ep.babylon.view.DetailsContactActivity;
import com.squareup.picasso.Picasso;

/**
 * Created by Eduardo on 15/10/2015.
 */
public class LoadDetailAvatarIntentService extends IntentService {

    private static final String EXTRA_CONTACT_ADDRESS = "extra_contact_address";
    public static final String ACTION_AVATAR_RESPONSE = "com.ep.babylon.service.AVATAR";
    public static final String EXTRA_AVATAR_DETAIL = "extra_avatar_detail";
    public static final String AVATARS_150DP_END_POINT = "http://api.adorable.io/avatars/150/";

    public LoadDetailAvatarIntentService() {
        super("LoadDetailAvatar");
    }

    public static Intent makeIntent(Context context, String address) {
        return new Intent(context, LoadDetailAvatarIntentService.class)
                .putExtra(EXTRA_CONTACT_ADDRESS, address);
    }


    @Override
    protected void onHandleIntent(Intent intent) {
        String address = null;
        if (intent.getExtras().get(EXTRA_CONTACT_ADDRESS) != null) {
            address = (String) intent.getExtras().get(EXTRA_CONTACT_ADDRESS);
        }
        Avatar avatar = new Avatar(Picasso.with(getApplicationContext()).load(AVATARS_150DP_END_POINT + address));
        senBroadcastReceiver(avatar);
    }

    private void senBroadcastReceiver(Avatar avatar) {
        LocalBroadcastManager.getInstance(getApplicationContext())
                .sendBroadcast(new Intent(ACTION_AVATAR_RESPONSE)
                        .addCategory(Intent.CATEGORY_DEFAULT)
                        .putExtra(EXTRA_AVATAR_DETAIL, avatar));
    }
}

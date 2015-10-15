package com.ep.babylon.provider;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.RemoteException;

/**
 * Created by Eduardo on 15/07/2015.
 */
public class ContactsContentResolver {

    private final ContentResolver contentResolver;

    public ContactsContentResolver(Context context) {
        contentResolver = context.getContentResolver();
    }


    public Uri insert(Uri uri,ContentValues cvs)
            throws RemoteException {
        return contentResolver.insert(uri,
                cvs);
    }

    public Cursor query(Uri uri,
                        String[] projection,
                        String selection,
                        String[] selectionArgs,
                        String sortOrder)
            throws RemoteException {
        return contentResolver.query(uri,
                projection,
                selection,
                selectionArgs,
                sortOrder);
    }

}

package com.ep.babylon.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;

/**
 * Created by Eduardo on 26/08/2015.
 */
public class ContactsContentProvider extends ContentProvider {

    private ContactsDataBase contactsDataBase;

    private static final String AUTHORITY = "com.ep.babylon.provider.ContactsContentProvider";
    private static final String CONTACTS_BASE_PATH = "contact";
    private static final String URL_CONTACT = "content://" + AUTHORITY + "/" + CONTACTS_BASE_PATH;
    public static final Uri CONTENT_URI_CONTACTS = Uri.parse(URL_CONTACT);
    public static final int CONTACTS = 100;
    public static final int CONTACT_ID = 110;

    private static final UriMatcher sURIMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        sURIMatcher.addURI(AUTHORITY, CONTACTS_BASE_PATH, CONTACTS);
        sURIMatcher.addURI(AUTHORITY, CONTACTS_BASE_PATH + "/#", CONTACT_ID);
    }

    @Override
    public boolean onCreate() {
        Context context = getContext();
        contactsDataBase = new ContactsDataBase(context);
        return true;
    }

    @Override
    public Cursor query(
            Uri uri,
            String[] projection,
            String selection,
            String[] selectionArgs,
            String sortOrder) {
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
        queryBuilder.setTables(ContactsDataBase.TABLE_CONTACTS);

        if (CONTACT_ID == sURIMatcher.match(uri)) {
            StringBuilder where = new StringBuilder(ContactsDataBase.ID);
            where.append("=");
            where.append(uri.getLastPathSegment());
            queryBuilder.appendWhere(where);
        }

        return queryBuilder.query(
                contactsDataBase.getReadableDatabase(),
                projection,
                selection,
                selectionArgs,
                null,
                null,
                sortOrder
        );
    }

    @Override
    public String getType(Uri uri) {
        return null;

    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {

        SQLiteDatabase database = contactsDataBase.getWritableDatabase();
        long insertedRowId = 0;
        if (sURIMatcher.match(uri) == CONTACTS) {
            insertedRowId = database.insert(ContactsDataBase.TABLE_CONTACTS, null, values);
        }
        database.close();
        if (insertedRowId > 0){
            getContext().getContentResolver().notifyChange(uri, null);
            return buildUri(insertedRowId);
        }  else {
            throw new android.database.SQLException("Failed to insert row into " + uri);
        }

    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
       throw new UnsupportedOperationException();
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        throw new UnsupportedOperationException();
    }

    public static Uri buildUri(Long id) {
        return android.content.ContentUris.withAppendedId(CONTENT_URI_CONTACTS, id);
    }


}

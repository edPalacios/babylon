package com.ep.babylon.view;

import android.app.Fragment;
import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.ep.babylon.model.Avatar;
import com.ep.babylon.model.Contact;
import com.ep.babylon.provider.ContactsContentProvider;
import com.ep.babylon.provider.ContactsDataBase;
import com.ep.test.babylon.R;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnItemClick;

/**
 * Created by Eduardo on 15/10/2015.
 */
public class ContactListFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {

    @Bind(R.id.contacts_listview)
    ListView contactsListView;
    @Bind(R.id.contacts_avatar_listview)
    ListView contactsAvatarListView;

    private static final String ARGS_AVATARS = "arguments_avatars";
    public static final String FRAGMENT_TAG = "ContactListFragment";
    public static final String EXTRA_AVATAR = "extra_avatar_to_display";
    public static final String EXTRA_CONTACT = "extra_contact_to_display";
    private static final int URL_LOADER = 0;
    private SimpleCursorAdapter  cursorAdapter;
    private ArrayList<Avatar>avatars;

    public String[] columsFromDB = {
            ContactsDataBase.COLUMN_FIRST_NAME,
            ContactsDataBase.COLUMN_SURNAME
    };
    public int[] viewsToPopulate = {
            R.id.contact_first_name,
            R.id.contact_surname
    };

    public static ContactListFragment newInstance(ArrayList<Avatar>avatars) {
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(ARGS_AVATARS, avatars);
        ContactListFragment fragment = new ContactListFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().getTitle();
        getCursorAdapter();
        getLoaderManager().initLoader(URL_LOADER, null, this);
        avatars = (ArrayList<Avatar>) getArguments().get(ARGS_AVATARS);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View contactsView = inflater.inflate(R.layout.content_contact_list, container, false);
        ButterKnife.bind(this, contactsView);
        contactsListView.setAdapter(cursorAdapter);
        return contactsView;
    }


    @OnItemClick({R.id.contacts_listview, R.id.contacts_avatar_listview})
    void onItemSelected(int position) {
        displayContactDetail(position);
    }

    private void displayContactDetail(int position) {
        Contact contact = getContact(position);
        Intent intent = new Intent(getActivity(), DetailsContactActivity.class);
        intent.putExtra(EXTRA_CONTACT, contact);
        intent.putExtra(EXTRA_AVATAR, avatars.get(position));
        startActivity(intent);
    }

    @Nullable
    private Contact getContact(int position) {
        Contact contact = null;
        Cursor cursor = cursorAdapter.getCursor();
        if (cursor.moveToPosition(position)) {
            contact = new Contact(
                    cursor.getString(cursor.getColumnIndex(ContactsDataBase.COLUMN_FIRST_NAME)),
                    cursor.getString(cursor.getColumnIndex(ContactsDataBase.COLUMN_SURNAME)),
                    cursor.getString(cursor.getColumnIndex(ContactsDataBase.COLUMN_ADDRESS)),
                    cursor.getString(cursor.getColumnIndex(ContactsDataBase.COLUMN_PHONE_NUMBER)),
                    cursor.getString(cursor.getColumnIndex(ContactsDataBase.COLUMN_EMAIL)),
                    cursor.getLong(cursor.getColumnIndex(ContactsDataBase.COLUMN_CONTACTS_ID)),
                    cursor.getString(cursor.getColumnIndex(ContactsDataBase.COLUMN_CREATED_AT)),
                    cursor.getString(cursor.getColumnIndex(ContactsDataBase.COLUMN_UPDATED_AT))
            );
        }
        return contact;
    }

    private void getCursorAdapter() {
        cursorAdapter = new SimpleCursorAdapter(
                getActivity(),
                R.layout.content_contact_item,
                null,
                columsFromDB,
                viewsToPopulate,
                0
        );
    }


    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        String[] projection = {
                ContactsDataBase.ID,
                ContactsDataBase.COLUMN_FIRST_NAME,
                ContactsDataBase.COLUMN_SURNAME,
                ContactsDataBase.COLUMN_ADDRESS,
                ContactsDataBase.COLUMN_PHONE_NUMBER,
                ContactsDataBase.COLUMN_EMAIL,
                ContactsDataBase.COLUMN_CONTACTS_ID,
                ContactsDataBase.COLUMN_CREATED_AT,
                ContactsDataBase.COLUMN_UPDATED_AT
        };
        return new CursorLoader(
                getActivity(),
                ContactsContentProvider.CONTENT_URI_CONTACTS,
                projection,
                null,
                null,
                null
        );
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        cursorAdapter.swapCursor(data);
        contactsAvatarListView.setAdapter(new AvatarAdapter(getActivity(), avatars));
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        cursorAdapter.swapCursor(null);
    }
}



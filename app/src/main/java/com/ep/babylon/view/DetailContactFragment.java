package com.ep.babylon.view;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ep.babylon.model.Avatar;
import com.ep.babylon.model.Contact;
import com.ep.test.babylon.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Eduardo on 15/10/2015.
 */
public class DetailContactFragment extends Fragment {

    public static final String FRAGMENT_TAG = "DetailContactFragment";
    private static final String ARGS_CONTACT = "argument_contact";
    private static final String ARGS_AVATAR = "argument_avatar";

    @Bind(R.id.detail_avatar)
    ImageView avatarImg;
    @Bind(R.id.detail_first_name)
    TextView firstNameLbl;
    @Bind(R.id.detail_surname)
    TextView surnameLbl;
    @Bind(R.id.detail_address)
    TextView addressLbl;
    @Bind(R.id.detail_email)
    TextView emailLbl;
    @Bind(R.id.detail_id)
    TextView idLbl;
    @Bind(R.id.detail_created_at)
    TextView createdAtLbl;
    @Bind(R.id.detail_updated_at)
    TextView updatedAtLbl;

    private Contact contact;
    private Avatar avatar;

    public static DetailContactFragment newInstance (Contact contact, Avatar avatar) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(ARGS_CONTACT, contact);
        bundle.putParcelable(ARGS_AVATAR, avatar);
        DetailContactFragment fragment = new DetailContactFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        contact = getArguments().getParcelable(ARGS_CONTACT);
        avatar = getArguments().getParcelable(ARGS_AVATAR);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View detailView = inflater.inflate(R.layout.fragment_details_contact, container, false);
        ButterKnife.bind(this, detailView);
        populateTextViews();
        return detailView;
    }

    private void populateTextViews() {
        firstNameLbl.setText(contact.getFirst_name());
        surnameLbl.setText(contact.getSurname());
        addressLbl.setText(contact.getAddress());
        emailLbl.setText(contact.getEmail());
        idLbl.setText(String.valueOf(contact.getId()));
        createdAtLbl.setText(contact.getCreatedAt());
        updatedAtLbl.setText(contact.getUpdatedAt());
        avatar.getRequestCreator().into(avatarImg);
    }

}

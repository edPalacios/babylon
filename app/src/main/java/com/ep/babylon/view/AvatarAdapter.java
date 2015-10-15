package com.ep.babylon.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.ep.babylon.model.Avatar;
import com.ep.babylon.model.Contact;
import com.ep.test.babylon.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Eduardo on 15/10/2015.
 */
public class AvatarAdapter extends BaseAdapter {

    private static class ViewHolder {
        private ImageView avatar;
    }

    private LayoutInflater inflater;
    private ArrayList<Avatar> avatars;

    public AvatarAdapter(Context context, ArrayList<Avatar>avatars) {
        inflater = LayoutInflater.from(context);
        this.avatars = avatars;
    }

    @Override
    public int getCount() {
        return avatars.size();
    }

    @Override
    public Object getItem(int position) {
        return avatars.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder h;
        if (convertView == null) {
            h = new ViewHolder();
            convertView = inflater.inflate(R.layout.content_avatar_item, parent, false);
            h.avatar = (ImageView) convertView.findViewById(R.id.avatar_image_view);
            Avatar avatar = (Avatar) getItem(position);
            avatar.getRequestCreator().into(h.avatar);
            convertView.setTag(h);
        } else {
            h = (ViewHolder) convertView.getTag();
        }
        return convertView;
    }

}

package com.ep.babylon.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.squareup.picasso.RequestCreator;

/**
 * Created by Eduardo on 15/10/2015.
 */
public class Avatar implements Parcelable{

    private RequestCreator requestCreator;

    public Avatar(RequestCreator requestCreator) {
        this.requestCreator = requestCreator;
    }

    protected Avatar(Parcel in) {
    }

    public RequestCreator getRequestCreator() {
        return requestCreator;
    }

    public static final Creator<Avatar> CREATOR = new Creator<Avatar>() {
        @Override
        public Avatar createFromParcel(Parcel in) {
            return new Avatar(in);
        }

        @Override
        public Avatar[] newArray(int size) {
            return new Avatar[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }
}

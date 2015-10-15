package com.ep.babylon.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Eduardo on 14/10/2015.
 *
 */
public class Contact implements Parcelable {

    private String first_name;
    private String surname;
    private String address;
    private String phone_number;
    private String email;
    private Long id;
    private String createdAt;
    private String updatedAt;

    public Contact(String first_name, String surname) {
        this.first_name = first_name;
        this.surname = surname;
    }

    public Contact(
                   String first_name,
                   String surname,
                   String address,
                   String phone_number,
                   String email,
                   Long id,
                   String createdAt,
                   String updatedAt
    ) {
        this.first_name = first_name;
        this.surname = surname;
        this.address = address;
        this.phone_number = phone_number;
        this.email = email;
        this.id = id;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getSurname() {
        return surname;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public String getEmail() {
        return email;
    }

    public Long getId() {
        return id;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }


    /**
     * Parcelable implementation
     */
    public static final Creator<Contact> CREATOR = new Creator<Contact>() {
        @Override
        public Contact createFromParcel(Parcel in) {
            return new Contact(in);
        }

        @Override
        public Contact[] newArray(int size) {
            return new Contact[size];
        }
    };

    protected Contact(Parcel in) {
        first_name = in.readString();
        surname = in.readString();
        address = in.readString();
        phone_number = in.readString();
        email = in.readString();
        id = in.readLong();
        createdAt = in.readString();
        updatedAt = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(first_name);
        dest.writeString(surname);
        dest.writeString(address);
        dest.writeString(phone_number);
        dest.writeString(email);
        dest.writeLong(id);
        dest.writeString(createdAt);
        dest.writeString(updatedAt);
    }
}

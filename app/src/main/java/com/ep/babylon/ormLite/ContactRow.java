package com.ep.babylon.ormLite;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by Eduardo on 15/10/2015.
 */

@DatabaseTable(tableName = "contacts")
public class ContactRow {

    public static final String ID = "_id";
    public static final String FIRST_NAME = "first_name";
    public static final String SURNAME = "surname";
    public static final String ADDRESS = "address";
    public static final String PHONE_NUMBER = "phone_number";
    public static final String EMAIL = "email";
    public static final String CONTACT_ID = "contact_Id";
    public static final String CREATED_AT = "created_at";
    public static final String UPDATED_AT = "updated_at";

    @DatabaseField(generatedId = true, columnName = ID)
    private int _id;
    @DatabaseField(columnName = FIRST_NAME)
    private String first_name;
    @DatabaseField(columnName = SURNAME)
    private String surname;
    @DatabaseField(columnName = ADDRESS)
    private String address;
    @DatabaseField(columnName = PHONE_NUMBER)
    private String phone_number;
    @DatabaseField(columnName = EMAIL)
    private String email;
    @DatabaseField(columnName = CONTACT_ID)
    private Long contactId;
    @DatabaseField(columnName = CREATED_AT)
    private String createdAt;
    @DatabaseField(columnName = UPDATED_AT)
    private String updatedAt;

    public ContactRow() {

    }

    public ContactRow(String first_name,
                      String surname,
                      String address,
                      String phone_number,
                      String email,
                      Long contactId,
                      String createdAt,
                      String updatedAt) {
        this.first_name = first_name;
        this.surname = surname;
        this.address = address;
        this.phone_number = phone_number;
        this.email = email;
        this.contactId = contactId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getContactId() {
        return contactId;
    }

    public void setContactId(Long contactId) {
        this.contactId = contactId;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }
}

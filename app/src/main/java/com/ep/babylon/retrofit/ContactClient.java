package com.ep.babylon.retrofit;

import com.ep.babylon.model.Contact;

import retrofit.Call;
import retrofit.http.GET;



public interface ContactClient {

    public static final String CONTACT_PATH = "/contacts";

    @GET(CONTACT_PATH)
    Call<Contact[]> getContacts();

}

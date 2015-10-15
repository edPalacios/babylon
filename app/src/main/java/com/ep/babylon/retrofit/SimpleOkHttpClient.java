package com.ep.babylon.retrofit;

import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Response;

import java.io.IOException;




/**
 * Created by Eduardo on 14/10/2015.
 *
 */
public class SimpleOkHttpClient extends  OkHttpClient {

    private OkHttpClient client;


    public SimpleOkHttpClient() {
        client = new OkHttpClient();
        client.interceptors().add(new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Response response = chain.proceed(chain.request());
            return response;
            }
        });
    }


}

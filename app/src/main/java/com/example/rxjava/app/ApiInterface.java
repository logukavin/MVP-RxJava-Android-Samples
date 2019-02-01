package com.example.rxjava.app;

import com.example.rxjava.model.ContactResponse;

import retrofit2.http.GET;
import rx.Observable;

public interface ApiInterface {

    @GET(ApiUrl.API_CONTACT_LIST)
    Observable<ContactResponse> getContact();

}

package com.example.rxjava.app;

import android.content.Context;

import com.example.rxjava.model.ContactResponse;
import com.example.rxjava.utils.RxJavaUtils;

import rx.Observable;

public class AppRepo {

    private ApiInterface appApi;

    public AppRepo(ApiInterface appApi) {
        this.appApi = appApi;
    }

    public Observable<ContactResponse> setContact() {
        return appApi.getContact().compose(RxJavaUtils.applyErrorTransformer())
                .map(contactResponse -> {
                            return contactResponse;
                });
    }

}

package com.example.rxjava.ui.contact;

import com.example.rxjava.base.LoadDataView;
import com.example.rxjava.model.ContactResponse;

public interface ContactView extends LoadDataView {

    void onContactResponse(ContactResponse contactResponse);
}

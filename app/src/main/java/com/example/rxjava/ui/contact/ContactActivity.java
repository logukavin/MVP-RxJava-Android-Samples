package com.example.rxjava.ui.contact;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.rxjava.R;
import com.example.rxjava.base.BaseActivity;
import com.example.rxjava.model.ContactResponse;
import com.example.rxjava.model.ContactsItem;
import com.example.rxjava.ui.adapter.ContactAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ContactActivity extends BaseActivity implements ContactView {


    @BindView(R.id.rv_contact_recycle)
    RecyclerView rvContactRecycle;

    private ContactAdapter contactAdapter;
    private ContactPresenter contactPresenter;

    @Override
    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        contactPresenter = new ContactPresenter();
        contactPresenter.setView(this);
        contactPresenter.getContact();


        rvContactRecycle.setLayoutManager(new LinearLayoutManager(this));
        contactAdapter = new ContactAdapter(this);
        rvContactRecycle.setAdapter(contactAdapter);

    }

    @Override
    public void onContactResponse(ContactResponse contactResponse) {
        if (contactResponse != null && contactResponse.getContacts() != null && contactResponse.getContacts().size() > 0) {
            contactAdapter.setContactList(contactResponse.getContacts());
        }
    }

    @Override
    public void showError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public Context context() {
        return this;
    }


    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }
}

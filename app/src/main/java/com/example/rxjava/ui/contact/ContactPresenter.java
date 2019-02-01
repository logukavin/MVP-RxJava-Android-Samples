package com.example.rxjava.ui.contact;

import com.example.rxjava.app.AppController;
import com.example.rxjava.app.AppRepo;
import com.example.rxjava.base.ApstracBasePresenter;
import com.example.rxjava.utils.RxJavaUtils;

public class ContactPresenter extends ApstracBasePresenter<ContactView> {
    private ContactView view;
    private AppRepo appRepo;

    @Override
    public void setView(ContactView view) {
        super.setView(view);
        this.view = view;
        appRepo = AppController.getInstance().getAppRepo();

    }

    @Override
    public void destroy() {
        super.destroy();
    }

    @Override
    public void destroyView() {
        super.destroyView();
    }

    public void getContact() {
        view.showLoading();
        appRepo.setContact().compose(RxJavaUtils.applyObserverSchedulers())
                .subscribe(contactResponse -> {
                    if (view != null) {
                        if (contactResponse != null)
                            view.onContactResponse(contactResponse);
                        view.hideLoading();
                    }
                }, throwable -> {
                    if (view != null) {
                        view.showError(throwable.getMessage());
                        view.hideLoading();
                    }
                });


    }

}

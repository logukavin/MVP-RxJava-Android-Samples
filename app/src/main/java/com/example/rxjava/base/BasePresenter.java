package com.example.rxjava.base;

public interface BasePresenter<V extends BaseView> {

    void setView(V view);

    void destroyView();

    void destroy();
}

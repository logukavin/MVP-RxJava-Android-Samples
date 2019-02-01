package com.example.rxjava.base;

public class ApstracBasePresenter<V extends BaseView> implements BasePresenter<V> {
    protected V view;

    @Override
    public void setView(V view) {
        this.view = view;

    }

    @Override
    public void destroyView() {
        view = null;

    }

    @Override
    public void destroy() {
        destroy();

    }


}

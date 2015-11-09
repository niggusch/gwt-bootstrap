package com.pragmatics.gwtp.client.application.breadcrumb;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

public class BreadCrumbModule extends AbstractPresenterModule {
    @Override
    protected void configure() {
        bindPresenter(BreadCrumbPresenter.class, BreadCrumbPresenter.MyView.class, BreadCrumbView.class, BreadCrumbPresenter.MyProxy.class);
    }
}
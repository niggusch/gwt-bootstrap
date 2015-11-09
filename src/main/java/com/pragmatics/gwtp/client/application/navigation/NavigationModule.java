package com.pragmatics.gwtp.client.application.navigation;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

public class NavigationModule extends AbstractPresenterModule {
    @Override
    protected void configure() {
        bindPresenter(NavigationPresenter.class, NavigationPresenter.MyView.class, NavigationView.class, NavigationPresenter.MyProxy.class);
    }
}
package com.pragmatics.gwtp.client.application.product;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

public class ProductModule extends AbstractPresenterModule {
    @Override
    protected void configure() {
        bindPresenter(ProductPresenter.class, ProductPresenter.MyView.class, ProductView.class, ProductPresenter.MyProxy.class);
    }
}
package com.pragmatics.gwtp.client.application.products;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

public class ProductsModule extends AbstractPresenterModule {
    @Override
    protected void configure() {
        bindPresenter(ProductsPresenter.class, ProductsPresenter.MyView.class, ProductsView.class, ProductsPresenter.MyProxy.class);
    }
}
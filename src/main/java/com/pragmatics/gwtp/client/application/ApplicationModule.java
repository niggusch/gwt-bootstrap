package com.pragmatics.gwtp.client.application;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;
import com.pragmatics.gwtp.client.application.breadcrumb.BreadCrumbModule;
import com.pragmatics.gwtp.client.application.navigation.NavigationModule;
import com.pragmatics.gwtp.client.application.product.ProductModule;
import com.pragmatics.gwtp.client.application.products.ProductsModule;
import com.pragmatics.gwtp.client.application.home.HomeModule;

public class ApplicationModule extends AbstractPresenterModule {
	@Override
	protected void configure() {
		install(new HomeModule());
		install(new ProductModule());
		install(new ProductsModule());
		install(new NavigationModule());
		install(new BreadCrumbModule());

		bindPresenter(ApplicationPresenter.class, ApplicationPresenter.MyView.class, ApplicationView.class,
				ApplicationPresenter.MyProxy.class);

	}
}

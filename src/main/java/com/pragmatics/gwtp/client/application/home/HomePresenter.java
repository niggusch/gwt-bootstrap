package com.pragmatics.gwtp.client.application.home;

import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.Proxy;
import com.pragmatics.gwtp.client.application.ApplicationPresenter;

public class HomePresenter extends Presenter<HomePresenter.MyView, HomePresenter.MyProxy> {
	interface MyView extends View {
	}

	@ProxyStandard
	interface MyProxy extends Proxy<HomePresenter> {
	}

	@Inject
	HomePresenter(EventBus eventBus, MyView view, MyProxy proxy) {
		super(eventBus, view, proxy, ApplicationPresenter.SLOT_SetMainContent);
	}

}

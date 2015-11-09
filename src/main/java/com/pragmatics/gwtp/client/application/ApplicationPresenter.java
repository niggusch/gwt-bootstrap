package com.pragmatics.gwtp.client.application;

import javax.inject.Inject;

import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.annotations.Title;
import com.gwtplatform.mvp.client.presenter.slots.NestedSlot;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.gwtplatform.mvp.shared.proxy.PlaceRequest;
import com.pragmatics.gwtp.client.application.breadcrumb.BreadCrumbPresenter;
import com.pragmatics.gwtp.client.application.home.HomePresenter;
import com.pragmatics.gwtp.client.application.navigation.NavigationPresenter;
import com.pragmatics.gwtp.client.place.NameTokens;

public class ApplicationPresenter extends Presenter<ApplicationPresenter.MyView, ApplicationPresenter.MyProxy> {
	interface MyView extends View {
	}

	public static final NestedSlot SLOT_SetMainNavigation = new NestedSlot();

	public static final NestedSlot SLOT_SetMainBreadCrumb = new NestedSlot();

	public static final NestedSlot SLOT_SetMainContent = new NestedSlot();

	@ProxyCodeSplit
	@NameToken(NameTokens.homePage)
	@Title("Home")
	public interface MyProxy extends ProxyPlace<ApplicationPresenter> {
	}

	@Inject
	BreadCrumbPresenter breadCrumbPresenter;

	@Inject
	NavigationPresenter navigationPresenter;

	@Inject
	HomePresenter homePresenter;

	@Inject
	ApplicationPresenter(EventBus eventBus, MyView view, MyProxy proxy) {
		super(eventBus, view, proxy, RevealType.Root);
	}

	@Override
	protected void onReveal() {
		super.onReveal();
		setInSlot(SLOT_SetMainNavigation, navigationPresenter);
		setInSlot(SLOT_SetMainBreadCrumb, breadCrumbPresenter);
	}

	@Override
	public void prepareFromRequest(PlaceRequest request) {
		super.prepareFromRequest(request);
		if (request.getNameToken().equals(NameTokens.homePage)) {
			setInSlot(SLOT_SetMainContent, homePresenter);
		}
	}
}

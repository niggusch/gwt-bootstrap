package com.pragmatics.gwtp.client.application.navigation;

import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.Proxy;
import com.gwtplatform.mvp.shared.proxy.PlaceRequest;
import com.pragmatics.gwtp.client.application.ApplicationPresenter;
import com.pragmatics.gwtp.client.place.NameTokens;
import com.pragmatics.gwtp.client.place.ParameterTokens;

public class NavigationPresenter extends Presenter<NavigationPresenter.MyView, NavigationPresenter.MyProxy>
		implements NavigationUiHandlers {

	interface MyView extends View, HasUiHandlers<NavigationUiHandlers> {
	}

	@ProxyStandard
	interface MyProxy extends Proxy<NavigationPresenter> {
	}

	@Inject
	private PlaceManager placeManager;

	@Inject
	NavigationPresenter(EventBus eventBus, MyView view, MyProxy proxy) {
		super(eventBus, view, proxy, ApplicationPresenter.SLOT_SetMainNavigation);
		getView().setUiHandlers(this);

	}

	@Override
	public void revealAllProductsList() {
		PlaceRequest request = new PlaceRequest.Builder()
				.nameToken(NameTokens.productList)
				.with(ParameterTokens.TOKEN_TYPE, ParameterTokens.TYPE_ALL_PRODUCTS)
				.build();
		placeManager.revealRelativePlace(request);
	}

	@Override
	public void revealFavoriteProductsList() {
		PlaceRequest request = new PlaceRequest.Builder()
				.nameToken(NameTokens.productList)
				.with(ParameterTokens.TOKEN_TYPE, ParameterTokens.TYPE_FAVORITE_PRODUCTS)
				.build();
		placeManager.revealRelativePlace(request);
	}

	@Override
	public void revealSpecialsList() {
		PlaceRequest request = new PlaceRequest.Builder()
				.nameToken(NameTokens.productList)
				.with(ParameterTokens.TOKEN_TYPE, ParameterTokens.TYPE_SPECIALS)
				.build();
		placeManager.revealRelativePlace(request);
	}

}

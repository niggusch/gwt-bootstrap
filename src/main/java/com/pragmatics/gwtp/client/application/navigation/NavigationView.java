package com.pragmatics.gwtp.client.application.navigation;

import javax.inject.Inject;

import org.gwtbootstrap3.client.ui.AnchorListItem;
import org.gwtbootstrap3.client.ui.Navbar;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;

class NavigationView extends ViewWithUiHandlers<NavigationUiHandlers> implements NavigationPresenter.MyView {
	interface Binder extends UiBinder<Navbar, NavigationView> {
	}

	@UiField
	AnchorListItem all;

	@UiField
	AnchorListItem favorites;

	@UiField
	AnchorListItem specials;

	@Inject
	NavigationView(Binder uiBinder) {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@UiHandler("all")
	void onAllClicked(ClickEvent event) {
		getUiHandlers().revealAllProductsList();
	}

	@UiHandler("favorites")
	void onFavoritesClicked(ClickEvent event) {
		getUiHandlers().revealFavoriteProductsList();
	}

	@UiHandler("specials")
	void onSpecialsClicked(ClickEvent event) {
		getUiHandlers().revealSpecialsList();
	}

}

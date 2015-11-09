package com.pragmatics.gwtp.client.application.product;

import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.annotations.TitleFunction;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.gwtplatform.mvp.client.proxy.SetPlaceTitleHandler;
import com.gwtplatform.mvp.shared.proxy.PlaceRequest;
import com.pragmatics.gwtp.client.application.ApplicationPresenter;
import com.pragmatics.gwtp.client.place.NameTokens;
import com.pragmatics.gwtp.client.place.ParameterTokens;
import com.pragmatics.gwtp.shared.Product;
import com.pragmatics.gwtp.shared.ProductDatabase;

public class ProductPresenter extends Presenter<ProductPresenter.MyView, ProductPresenter.MyProxy> {
	interface MyView extends View {
		void setBackLinkHistoryToken(String historyToken);

		void setMessage(String string);

		void setProductDetails(Product product);
	}

	@ProxyCodeSplit
	@NameToken(NameTokens.product)
	public interface MyProxy extends ProxyPlace<ProductPresenter> {
	}

	@Inject
	private PlaceManager placeManager;

	@Inject
	private ProductDatabase db;

	private int id;

	@Inject
	ProductPresenter(EventBus eventBus, MyView view, MyProxy proxy) {
		super(eventBus, view, proxy, ApplicationPresenter.SLOT_SetMainContent);
	}

	@Override
	public void prepareFromRequest(PlaceRequest request) {
		super.prepareFromRequest(request);
		String idString = request.getParameter(ParameterTokens.TOKEN_ID, null);
		try {
			id = Integer.parseInt(idString);
		} catch (NumberFormatException e) {
			id = 0;
		}
	}

	@TitleFunction
	public void getListTitle(PlaceRequest request, final SetPlaceTitleHandler handler) {
		handler.onSetPlaceTitle(db.get(id).getName());
	}

	@Override
	protected void onReset() {
		super.onReset();

		getView().setMessage("Loading...");
		getView().setBackLinkHistoryToken(placeManager.buildRelativeHistoryToken(-1));
		// should be async
		getView().setProductDetails(db.get(id));
		getView().setMessage("");
	}

}

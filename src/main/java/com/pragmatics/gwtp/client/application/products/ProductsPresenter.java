package com.pragmatics.gwtp.client.application.products;

import java.util.List;

import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.annotations.TitleFunction;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.gwtplatform.mvp.shared.proxy.PlaceRequest;
import com.pragmatics.gwtp.client.application.ApplicationPresenter;
import com.pragmatics.gwtp.client.place.NameTokens;
import com.pragmatics.gwtp.client.place.ParameterTokens;
import com.pragmatics.gwtp.shared.Product;
import com.pragmatics.gwtp.shared.ProductDatabase;

public class ProductsPresenter extends Presenter<ProductsPresenter.MyView, ProductsPresenter.MyProxy> {
	interface MyView extends View {
		void setBackLinkHistoryToken(String historyToken);

		void setList(List<Product> products);

		void setMessage(String string);

		void setTitle(String title);
	}

	@ProxyCodeSplit
	@NameToken(NameTokens.productList)
	public interface MyProxy extends ProxyPlace<ProductsPresenter> {
	}

	@Inject
	private PlaceManager placeManager;

	@Inject
	private ProductDatabase db;
	private String currentType = ParameterTokens.TYPE_ALL_PRODUCTS;

	@Inject
	ProductsPresenter(EventBus eventBus, MyView view, MyProxy proxy) {
		super(eventBus, view, proxy, ApplicationPresenter.SLOT_SetMainContent);
	}

	@TitleFunction
	public static String getListTitle(PlaceRequest request) {
		return getTitleFor(request.getParameter(ParameterTokens.TOKEN_TYPE, null));
	}

	private static String getTitleFor(String type) {
		switch (type) {
		case ParameterTokens.TYPE_FAVORITE_PRODUCTS:
			return "Favorite products";
		case ParameterTokens.TYPE_SPECIALS:
			return "Specials";
		default:
			return "All products";
		}
	}

	@Override
	public void prepareFromRequest(PlaceRequest request) {
		super.prepareFromRequest(request);

		String type = request.getParameter(ParameterTokens.TOKEN_TYPE, ParameterTokens.TYPE_ALL_PRODUCTS);
		switch (type) {
		case ParameterTokens.TYPE_FAVORITE_PRODUCTS:
			currentType = ParameterTokens.TYPE_FAVORITE_PRODUCTS;
			break;
		case ParameterTokens.TYPE_SPECIALS:
			currentType = ParameterTokens.TYPE_SPECIALS;
			break;
		default:
			currentType = ParameterTokens.TYPE_ALL_PRODUCTS;
			break;
		}

		getView().setTitle(getTitleFor(currentType));
	}

	@Override
	protected void onReset() {
		super.onReset();
		getView().setMessage("Loading list...");
		getView().setBackLinkHistoryToken(placeManager.buildRelativeHistoryToken(-1));
		// should be async:
		getView().setList(db.getMatching(getFlags()));
		getView().setMessage("");

	}

	private int getFlags() {
		if (currentType.equals(ParameterTokens.TYPE_FAVORITE_PRODUCTS)) {
			return Product.FLAG_FAVORITE;
		} else if (currentType.equals(ParameterTokens.TYPE_SPECIALS)) {
			return Product.FLAG_SPECIAL;
		}
		return 0;
	}
}

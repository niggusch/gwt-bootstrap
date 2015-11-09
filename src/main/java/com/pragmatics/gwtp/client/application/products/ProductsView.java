package com.pragmatics.gwtp.client.application.products;

import java.util.List;

import javax.inject.Inject;

import org.gwtbootstrap3.client.ui.Anchor;
import org.gwtbootstrap3.client.ui.Badge;
import org.gwtbootstrap3.client.ui.Heading;
import org.gwtbootstrap3.client.ui.ListGroup;
import org.gwtbootstrap3.client.ui.ListGroupItem;
import org.gwtbootstrap3.client.ui.html.Text;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewImpl;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.shared.proxy.PlaceRequest;
import com.pragmatics.gwtp.client.place.NameTokens;
import com.pragmatics.gwtp.client.place.ParameterTokens;
import com.pragmatics.gwtp.shared.Product;

class ProductsView extends ViewImpl implements ProductsPresenter.MyView {
	interface Binder extends UiBinder<Widget, ProductsView> {
	}

	@UiField
	Heading heading;

	@UiField
	Anchor back;

	@UiField
	Text message;

	@UiField
	ListGroup products;

	ListGroupItem product;
	@Inject
	private PlaceManager placeManager;

	@Inject
	ProductsView(Binder uiBinder) {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@Override
	public void setBackLinkHistoryToken(String historyToken) {
		back.setTargetHistoryToken(historyToken);
	}

	@Override
	public void setMessage(String string) {
		message.setText(string);
	}

	@Override
	public void setTitle(String title) {
		this.heading.setText(title);

	}

	@Override
	public void setList(List<Product> products) {
		for (Product p : products) {
			ListGroupItem product = new ListGroupItem();
			PlaceRequest request = new PlaceRequest.Builder().nameToken(NameTokens.product)
					.with(ParameterTokens.TOKEN_ID, Integer.toString(p.getId())).build();
			Anchor productLink = new Anchor();
			productLink.setText(p.getName());
			productLink.setTargetHistoryToken(placeManager.buildRelativeHistoryToken(request));
			product.add(productLink);
			product.add(new Badge(p.getQuantity()+""));
			this.products.add(product);
		}

	}
}

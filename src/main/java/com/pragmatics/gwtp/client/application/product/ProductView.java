package com.pragmatics.gwtp.client.application.product;

import javax.inject.Inject;

import org.gwtbootstrap3.client.ui.Anchor;
import org.gwtbootstrap3.client.ui.Badge;
import org.gwtbootstrap3.client.ui.ListGroupItem;
import org.gwtbootstrap3.client.ui.html.Text;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewImpl;
import com.pragmatics.gwtp.shared.Product;

class ProductView extends ViewImpl implements ProductPresenter.MyView {
	interface Binder extends UiBinder<Widget, ProductView> {
	}

	@UiField
	Anchor back;

	@UiField
	Text message;

	@UiField
	ListGroupItem product;

	@Inject
	ProductView(Binder uiBinder) {
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
	public void setProductDetails(Product product) {
		this.product.clear();
		this.product.add(new Text(product.getName()));
		this.product.add(new Badge(product.getPrice() + " $"));
		this.product.add(new Badge(product.getQuantity() + " #"));

	}

}

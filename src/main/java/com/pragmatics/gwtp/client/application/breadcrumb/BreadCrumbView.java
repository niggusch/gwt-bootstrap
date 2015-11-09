package com.pragmatics.gwtp.client.application.breadcrumb;

import java.util.List;

import javax.inject.Inject;

import org.gwtbootstrap3.client.ui.AnchorListItem;
import org.gwtbootstrap3.client.ui.Breadcrumbs;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewImpl;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.shared.proxy.PlaceRequest;

class BreadCrumbView extends ViewImpl implements BreadCrumbPresenter.MyView {
	interface Binder extends UiBinder<Widget, BreadCrumbView> {
	}

	@UiField
	Breadcrumbs breadcrumbs;

	@Inject
	private PlaceManager placeManager;

	@Inject
	BreadCrumbView(Binder uiBinder) {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@Override
	public void setInSlot(Object slot, IsWidget content) {
		if (slot == BreadCrumbPresenter.SLOT_BreadCrumb) {
			breadcrumbs.clear();
			breadcrumbs.add(content);
		} else {
			super.setInSlot(slot, content);
		}
	}

	@Override
	public void clearBreadcrumbs(int breadcrumbSize) {
		breadcrumbs.clear();
		for (int i = 0; i < breadcrumbSize; ++i) {
			AnchorListItem breadcrumb = new AnchorListItem("...");
			breadcrumb.setTargetHistoryToken(placeManager.buildRelativeHistoryToken(i + 1));
			breadcrumbs.add(breadcrumb);
		}
	}

	@Override
	public void setBreadcrumbs(int index, String title) {
		AnchorListItem breadcrumb = (AnchorListItem) breadcrumbs.getWidget(index);
		if (title == null) {
			breadcrumb.setText("Unknown title");
		} else {
			breadcrumb.setText(title);
		}

	}
}

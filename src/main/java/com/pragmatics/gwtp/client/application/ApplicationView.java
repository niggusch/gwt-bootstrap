package com.pragmatics.gwtp.client.application;

import javax.inject.Inject;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.gwtplatform.mvp.client.ViewImpl;

class ApplicationView extends ViewImpl implements ApplicationPresenter.MyView {
	interface Binder extends UiBinder<ScrollPanel, ApplicationView> {
	}

	@UiField
	SimplePanel navigationContainer;

	@UiField
	SimplePanel breadcrumbContainer;

	@UiField
	SimplePanel contentContainer;

	@Inject
	ApplicationView(Binder uiBinder) {
		initWidget(uiBinder.createAndBindUi(this));

		bindSlot(ApplicationPresenter.SLOT_SetMainNavigation, navigationContainer);
		bindSlot(ApplicationPresenter.SLOT_SetMainBreadCrumb, breadcrumbContainer);
		bindSlot(ApplicationPresenter.SLOT_SetMainContent, contentContainer);
	}

}

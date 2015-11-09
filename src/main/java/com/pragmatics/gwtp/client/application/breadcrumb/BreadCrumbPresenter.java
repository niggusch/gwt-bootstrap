package com.pragmatics.gwtp.client.application.breadcrumb;

import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.presenter.slots.NestedSlot;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.Proxy;
import com.gwtplatform.mvp.client.proxy.SetPlaceTitleHandler;
import com.gwtplatform.mvp.shared.proxy.PlaceRequest;
import com.pragmatics.gwtp.client.application.ApplicationPresenter;

public class BreadCrumbPresenter extends Presenter<BreadCrumbPresenter.MyView, BreadCrumbPresenter.MyProxy> {

	interface MyView extends View {
		void clearBreadcrumbs(int breadcrumbSize);

		void setBreadcrumbs(int index, String title);
	}

	public static final NestedSlot SLOT_BreadCrumb = new NestedSlot();

	@ProxyStandard
	interface MyProxy extends Proxy<BreadCrumbPresenter> {
	}

	@Inject
	private PlaceManager placeManager;

	@Inject
	BreadCrumbPresenter(EventBus eventBus, MyView view, MyProxy proxy) {
		super(eventBus, view, proxy, ApplicationPresenter.SLOT_SetMainBreadCrumb);
	}

	@Override
	public void prepareFromRequest(PlaceRequest request) {
		super.prepareFromRequest(request);
	}

	@Override
	protected void onReset() {
		super.onReset();

		int size = placeManager.getHierarchyDepth();

		getView().clearBreadcrumbs(size);

		for (int i = 0; i < size; ++i) {

			final int index = i;

			placeManager.getTitle(i, new SetPlaceTitleHandler() {
				@Override
				public void onSetPlaceTitle(String title) {
					getView().setBreadcrumbs(index, title);
				}
			});
		}
	}

}

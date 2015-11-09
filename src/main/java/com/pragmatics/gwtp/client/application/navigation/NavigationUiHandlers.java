package com.pragmatics.gwtp.client.application.navigation;

import com.gwtplatform.mvp.client.UiHandlers;

/**
 * {@link ApplicationDesktopView}'s {@link UiHandlers}.
 */
public interface NavigationUiHandlers extends UiHandlers {

	void revealAllProductsList();

	void revealFavoriteProductsList();

	void revealSpecialsList();

}

package com.pragmatics.gwtp.client.gin;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;
import com.gwtplatform.mvp.client.gin.DefaultModule;
import com.pragmatics.gwtp.client.application.ApplicationModule;
import com.pragmatics.gwtp.client.place.NameTokens;

/**
 * See more on setting up the PlaceManager on <a href=
 * "// See more on: https://github.com/ArcBees/GWTP/wiki/PlaceManager" >
 * DefaultModule's > DefaultPlaceManager</a>
 */
public class ClientModule extends AbstractPresenterModule {
	@Override
	protected void configure() {
		install(new DefaultModule.Builder().defaultPlace(NameTokens.homePage).errorPlace(NameTokens.homePage)
				.unauthorizedPlace(NameTokens.homePage).build());

		// install(new
		// DefaultModule.Builder().tokenFormatter(RouteTokenFormatter.class).build());
		install(new ApplicationModule());
		//
		// // DefaultPlaceManager Places
		// bindConstant().annotatedWith(DefaultPlace.class).to(NameTokens.homePage);
		// bindConstant().annotatedWith(ErrorPlace.class).to(NameTokens.homePage);
		// bindConstant().annotatedWith(UnauthorizedPlace.class).to(NameTokens.homePage);

	}
}

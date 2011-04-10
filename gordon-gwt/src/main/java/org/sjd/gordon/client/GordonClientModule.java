package org.sjd.gordon.client;

import net.customware.gwt.presenter.client.DefaultEventBus;
import net.customware.gwt.presenter.client.EventBus;
import net.customware.gwt.presenter.client.gin.AbstractPresenterModule;

import org.sjd.gordon.client.navigation.NavigationPresenter;
import org.sjd.gordon.client.navigation.NavigationView;
import org.sjd.gordon.client.security.LoginPresenter;
import org.sjd.gordon.client.security.LoginView;

import com.google.inject.Singleton;

public class GordonClientModule extends AbstractPresenterModule {

	@Override
	protected void configure() {
		bind(EventBus.class).to(DefaultEventBus.class).in(Singleton.class);
		bindPresenter(LoginPresenter.class, LoginPresenter.LoginDisplay.class,LoginView.class);
		bindPresenter(NavigationPresenter.class, NavigationPresenter.NavigationDisplay.class,NavigationView.class);
		//bind(MainPresenter.class).in(Singleton.class);
	}

}

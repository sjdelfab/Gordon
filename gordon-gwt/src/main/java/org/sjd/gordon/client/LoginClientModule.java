package org.sjd.gordon.client;

import org.sjd.gordon.client.security.LoginPanel;
import org.sjd.gordon.client.security.LoginPresenter;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.inject.Singleton;
import com.gwtplatform.mvp.client.DefaultProxyFailureHandler;
import com.gwtplatform.mvp.client.RootPresenter;
import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;
import com.gwtplatform.mvp.client.proxy.ParameterTokenFormatter;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.ProxyFailureHandler;
import com.gwtplatform.mvp.client.proxy.TokenFormatter;

public class LoginClientModule extends AbstractPresenterModule {

	@Override
	protected void configure() {
		bind(EventBus.class).to(SimpleEventBus.class).in(Singleton.class);
		bind(PlaceManager.class).to(LoginPlaceManager.class).in(Singleton.class);
		bind(TokenFormatter.class).to(ParameterTokenFormatter.class).in(Singleton.class);
		bind(RootPresenter.class).asEagerSingleton();
		bind(ProxyFailureHandler.class).to(DefaultProxyFailureHandler.class).in(Singleton.class);
		
		bindPresenter(LoginPresenter.class, LoginPresenter.LoginPanelView.class, LoginPanel.class, LoginPresenter.LoginPanelProxy.class);
	}
}

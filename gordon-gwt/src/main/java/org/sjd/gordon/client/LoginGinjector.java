package org.sjd.gordon.client;

import org.sjd.gordon.client.security.LoginPresenter;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;
import com.google.inject.Provider;
import com.gwtplatform.dispatch.client.gin.DispatchAsyncModule;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.ProxyFailureHandler;

@GinModules({DispatchAsyncModule.class, LoginClientModule.class})
public interface LoginGinjector extends Ginjector {

	EventBus getEventBus();
	Provider<LoginPresenter> getLoginPresenter();
	PlaceManager getPlaceManager();
	ProxyFailureHandler getProxyFailureHandler();
}

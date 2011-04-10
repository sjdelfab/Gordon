package org.sjd.gordon.client;


import net.customware.gwt.dispatch.client.gin.StandardDispatchModule;
import net.customware.gwt.presenter.client.EventBus;

import org.sjd.gordon.client.navigation.NavigationPresenter;
import org.sjd.gordon.client.security.LoginPresenter;

import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;

@GinModules({StandardDispatchModule.class, GordonClientModule.class})
public interface GordonGinjector extends Ginjector {

	LoginPresenter getLoginPresenter();
	NavigationPresenter getNavigationPresenter();
	EventBus getEventBus();
}

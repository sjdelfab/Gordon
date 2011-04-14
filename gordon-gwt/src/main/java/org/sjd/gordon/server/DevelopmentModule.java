package org.sjd.gordon.server;

import net.customware.gwt.dispatch.server.guice.ActionHandlerModule;

import org.sjd.gordon.server.security.DevelopmentAuthenticationHandler;
import org.sjd.gordon.server.stocks.DevelopmentGetStocksHandler;
import org.sjd.gordon.shared.navigation.GetStocks;
import org.sjd.gordon.shared.security.Login;

public class DevelopmentModule extends ActionHandlerModule {

	@Override
	protected void configureHandlers() {
		bindHandler(Login.class, DevelopmentAuthenticationHandler.class);
		bindHandler(GetStocks.class, DevelopmentGetStocksHandler.class);
	}

}

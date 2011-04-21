package org.sjd.gordon.server;

import net.customware.gwt.dispatch.server.guice.ActionHandlerModule;

import org.sjd.gordon.server.devhandlers.DevelopmentAuthenticationHandler;
import org.sjd.gordon.server.devhandlers.DevelopmentGetExchangesHandler;
import org.sjd.gordon.server.devhandlers.DevelopmentGetStocksHandler;
import org.sjd.gordon.server.devhandlers.DevelopmentGetTradeHistoryHandler;
import org.sjd.gordon.shared.navigation.GetExchanges;
import org.sjd.gordon.shared.navigation.GetStocks;
import org.sjd.gordon.shared.security.Login;
import org.sjd.gordon.shared.viewer.GetTradeHistory;

public class DevelopmentModule extends ActionHandlerModule {

	@Override
	protected void configureHandlers() {
		bindHandler(Login.class, DevelopmentAuthenticationHandler.class);
		bindHandler(GetStocks.class, DevelopmentGetStocksHandler.class);
		bindHandler(GetExchanges.class, DevelopmentGetExchangesHandler.class);
		bindHandler(GetTradeHistory.class, DevelopmentGetTradeHistoryHandler.class);
	}

}

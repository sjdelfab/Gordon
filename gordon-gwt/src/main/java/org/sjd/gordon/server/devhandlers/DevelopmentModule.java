package org.sjd.gordon.server.devhandlers;

import org.sjd.gordon.server.LogoutHandler;
import org.sjd.gordon.shared.navigation.GetExchanges;
import org.sjd.gordon.shared.navigation.GetStocks;
import org.sjd.gordon.shared.security.Login;
import org.sjd.gordon.shared.security.Logout;
import org.sjd.gordon.shared.viewer.GetStockDetails;
import org.sjd.gordon.shared.viewer.GetTradeHistory;

import com.gwtplatform.dispatch.server.guice.HandlerModule;

public class DevelopmentModule extends HandlerModule {

	@Override
	protected void configureHandlers() {
		bindHandler(Login.class, DevelopmentAuthenticationHandler.class);
		bindHandler(GetStocks.class, DevelopmentGetStocksHandler.class);
		bindHandler(GetExchanges.class, DevelopmentGetExchangesHandler.class);
		bindHandler(GetTradeHistory.class, DevelopmentGetTradeHistoryHandler.class);
		bindHandler(GetStockDetails.class, DevelopmentGetStockDetailsHandler.class);
		bindHandler(Logout.class, LogoutHandler.class);
	}

}

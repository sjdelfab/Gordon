package org.sjd.gordon.server;

import org.sjd.gordon.shared.navigation.GetExchanges;
import org.sjd.gordon.shared.navigation.GetStocks;
import org.sjd.gordon.shared.security.Logout;
import org.sjd.gordon.shared.viewer.GetStockDetails;
import org.sjd.gordon.shared.viewer.GetTradeHistory;

import com.google.inject.Singleton;
import com.gwtplatform.dispatch.server.guice.HandlerModule;

public class GuiceJpaServerModule extends HandlerModule {

	@Override
	protected void configureHandlers() {
		bind(GuiceJPAInitializer.class).in(Singleton.class);
		bindHandler(GetStocks.class, GetStocksJpaHandler.class);
		bindHandler(GetExchanges.class, GetExchangesJpaHandler.class);
		bindHandler(GetTradeHistory.class, GetTradeHistoryJpaHandler.class);
		bindHandler(GetStockDetails.class, GetStockDetailsJpaHandler.class);
		bindHandler(Logout.class, LogoutHandler.class);
	}

}

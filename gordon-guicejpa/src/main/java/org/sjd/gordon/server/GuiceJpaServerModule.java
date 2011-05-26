package org.sjd.gordon.server;

import org.sjd.gordon.shared.navigation.GetExchangesAction;
import org.sjd.gordon.shared.navigation.GetStocksAction;
import org.sjd.gordon.shared.security.LogoutAction;
import org.sjd.gordon.shared.viewer.GetStockProfileAction;
import org.sjd.gordon.shared.viewer.GetTradeHistoryAction;

import com.google.inject.Singleton;
import com.gwtplatform.dispatch.server.guice.HandlerModule;

public class GuiceJpaServerModule extends HandlerModule {

	@Override
	protected void configureHandlers() {
		bind(GuiceJPAInitializer.class).in(Singleton.class);
		bindHandler(GetStocksAction.class, GetStocksJpaHandler.class);
		bindHandler(GetExchangesAction.class, GetExchangesJpaHandler.class);
		bindHandler(GetTradeHistoryAction.class, GetTradeHistoryJpaHandler.class);
		bindHandler(GetStockProfileAction.class, GetStockProfileJpaHandler.class);
		bindHandler(LogoutAction.class, LogoutHandler.class);
	}

}

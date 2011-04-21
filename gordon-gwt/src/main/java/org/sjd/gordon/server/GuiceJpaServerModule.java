package org.sjd.gordon.server;

import net.customware.gwt.dispatch.server.guice.ActionHandlerModule;

import org.sjd.gordon.shared.navigation.GetExchanges;
import org.sjd.gordon.shared.navigation.GetStocks;
import org.sjd.gordon.shared.security.Login;
import org.sjd.gordon.shared.viewer.GetTradeHistory;

import com.google.inject.Singleton;

public class GuiceJpaServerModule extends ActionHandlerModule {

	@Override
	protected void configureHandlers() {
		bind(GuiceJPAInitializer.class).in(Singleton.class);
		bindHandler(Login.class, AuthenticationJpaHandler.class);
		bindHandler(GetStocks.class, GetStocksJpaHandler.class);
		bindHandler(GetExchanges.class, GetExchangesJpaHandler.class);
		bindHandler(GetTradeHistory.class, GetTradeHistoryJpaHandler.class);
	}

}

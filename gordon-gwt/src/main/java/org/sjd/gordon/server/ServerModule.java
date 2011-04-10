package org.sjd.gordon.server;

import net.customware.gwt.dispatch.server.guice.ActionHandlerModule;

import org.sjd.gordon.server.security.AuthenticationHandler;
import org.sjd.gordon.server.stocks.CSVGetStocksHandler;
import org.sjd.gordon.server.stocks.DummyGetStocksHandler;
import org.sjd.gordon.shared.navigation.GetStocks;
import org.sjd.gordon.shared.security.Login;

public class ServerModule extends ActionHandlerModule {

	@Override
	protected void configureHandlers() {
		bindHandler(Login.class, AuthenticationHandler.class);
		bindHandler(GetStocks.class, DummyGetStocksHandler.class);
		//bind(Log.class).toProvider(LogProvider.class).in(Singleton.class);
	}

}

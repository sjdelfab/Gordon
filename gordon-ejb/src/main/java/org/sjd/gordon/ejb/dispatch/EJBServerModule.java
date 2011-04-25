package org.sjd.gordon.ejb.dispatch;

import javax.naming.Context;
import javax.naming.InitialContext;

import net.customware.gwt.dispatch.server.guice.ActionHandlerModule;

import org.sjd.gordon.shared.navigation.GetExchanges;
import org.sjd.gordon.shared.navigation.GetStocks;
import org.sjd.gordon.shared.security.Login;
import org.sjd.gordon.shared.viewer.GetStockDetails;
import org.sjd.gordon.shared.viewer.GetTradeHistory;

import com.google.inject.Singleton;

public class EJBServerModule extends ActionHandlerModule {

	@Override
	protected void configureHandlers() {
		bind(Context.class).to(InitialContext.class).in(Singleton.class);
		bindHandler(Login.class, AuthenticationEJBHandler.class);
		bindHandler(GetStocks.class, GetStocksEJBHandler.class);
		bindHandler(GetExchanges.class, GetExchangesEJBHandler.class);
		bindHandler(GetTradeHistory.class, GetTradeHistoryEJBHandler.class);
		bindHandler(GetStockDetails.class, GetStockDetailsEJBHandler.class);
	}

}

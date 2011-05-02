package org.sjd.gordon.server.devhandlers;

import org.sjd.gordon.server.LogoutHandler;
import org.sjd.gordon.shared.navigation.GetExchanges;
import org.sjd.gordon.shared.navigation.GetStocks;
import org.sjd.gordon.shared.registry.DeleteRegistryEntry;
import org.sjd.gordon.shared.registry.EditRegistryEntry;
import org.sjd.gordon.shared.registry.GetAllStockDetails;
import org.sjd.gordon.shared.security.Login;
import org.sjd.gordon.shared.security.Logout;
import org.sjd.gordon.shared.viewer.GetStockDetails;
import org.sjd.gordon.shared.viewer.GetTradeHistory;

import com.gwtplatform.dispatch.server.guice.HandlerModule;

public class DevelopmentModule extends HandlerModule {

	@Override
	protected void configureHandlers() {
		bindHandler(Login.class, AuthenticationDevHandler.class);
		bindHandler(GetStocks.class, GetStocksDevHandler.class);
		bindHandler(GetExchanges.class, GetExchangesDevHandler.class);
		bindHandler(GetTradeHistory.class, GetTradeHistoryDevHandler.class);
		bindHandler(GetStockDetails.class, GetStockDetailsDevHandler.class);
		bindHandler(Logout.class, LogoutHandler.class);
		bindHandler(GetAllStockDetails.class, GetAllStockDetailsDevHandler.class);
		bindHandler(DeleteRegistryEntry.class, DeleteRegistryEntryDevHandler.class);
		bindHandler(EditRegistryEntry.class, EditRegistryEntryDevHandler.class);
	}

}

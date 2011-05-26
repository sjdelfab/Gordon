package org.sjd.gordon.server.devhandlers;

import org.sjd.gordon.server.LogoutHandler;
import org.sjd.gordon.shared.navigation.GetExchangesAction;
import org.sjd.gordon.shared.navigation.GetStocksAction;
import org.sjd.gordon.shared.registry.DeleteRegistryEntryAction;
import org.sjd.gordon.shared.registry.EditRegistryEntryAction;
import org.sjd.gordon.shared.registry.GetAllRegistryEntriesAction;
import org.sjd.gordon.shared.registry.GetGicsSectorsAction;
import org.sjd.gordon.shared.security.ChangeUserPasswordAction;
import org.sjd.gordon.shared.security.DeleteUserAction;
import org.sjd.gordon.shared.security.EditUserAction;
import org.sjd.gordon.shared.security.GetAllUsersAction;
import org.sjd.gordon.shared.security.GetCurrentUserAction;
import org.sjd.gordon.shared.security.LogoutAction;
import org.sjd.gordon.shared.viewer.GetStockProfileAction;
import org.sjd.gordon.shared.viewer.GetTradeHistoryAction;
import org.sjd.gordon.shared.viewer.UpdateBusinessSummaryAction;

import com.gwtplatform.dispatch.server.guice.HandlerModule;

public class DevelopmentModule extends HandlerModule {

	@Override
	protected void configureHandlers() {
		bindHandler(GetStocksAction.class, GetStocksDevHandler.class);
		bindHandler(GetExchangesAction.class, GetExchangesDevHandler.class);
		bindHandler(GetTradeHistoryAction.class, GetTradeHistoryDevHandler.class);
		bindHandler(GetStockProfileAction.class, GetStockProfileDevHandler.class);
		bindHandler(LogoutAction.class, LogoutHandler.class);
		
		bindHandler(GetAllRegistryEntriesAction.class, GetAllStockDetailsDevHandler.class);
		bindHandler(DeleteRegistryEntryAction.class, DeleteRegistryEntryDevHandler.class);
		bindHandler(EditRegistryEntryAction.class, EditRegistryEntryDevHandler.class);
		bindHandler(GetGicsSectorsAction.class, GetGicsNamesDevHandler.class);
		
		bindHandler(GetAllUsersAction.class, GetAllUsersDevHandler.class);
		bindHandler(DeleteUserAction.class, DeleteUserDevHandler.class);
		bindHandler(EditUserAction.class, EditUserDevHandler.class);
		bindHandler(GetCurrentUserAction.class, CurrentUserDevHandler.class);
		bindHandler(ChangeUserPasswordAction.class, ChangeUserPasswordDevHandler.class);
		
		bindHandler(UpdateBusinessSummaryAction.class, UpdateBusinessSummaryDevHandler.class);
	}

}

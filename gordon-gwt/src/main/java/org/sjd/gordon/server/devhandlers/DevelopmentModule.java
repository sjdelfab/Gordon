package org.sjd.gordon.server.devhandlers;

import org.sjd.gordon.importing.profile.StockEquityImporterService;
import org.sjd.gordon.importing.tradehistory.CSVTradeHistoryImportService;
import org.sjd.gordon.server.LogoutHandler;
import org.sjd.gordon.server.devhandlers.data.DeleteDividendDevHandler;
import org.sjd.gordon.server.devhandlers.data.DeleteStockSplitDevHandler;
import org.sjd.gordon.server.devhandlers.data.DeleteTreasuryHeldStockDevHandler;
import org.sjd.gordon.server.devhandlers.data.EditDividendDevHandler;
import org.sjd.gordon.server.devhandlers.data.EditStockSplitDevHandler;
import org.sjd.gordon.server.devhandlers.data.EditTreasuryHeldStockDevHandler;
import org.sjd.gordon.server.devhandlers.data.GetAllStockDetailsDevHandler;
import org.sjd.gordon.server.devhandlers.data.GetDividendHistoryDevHandler;
import org.sjd.gordon.server.devhandlers.data.GetExchangesDevHandler;
import org.sjd.gordon.server.devhandlers.data.GetStockAdminDatasetDevHandler;
import org.sjd.gordon.server.devhandlers.data.GetStockProfileDevHandler;
import org.sjd.gordon.server.devhandlers.data.GetStockSplitsDevHandler;
import org.sjd.gordon.server.devhandlers.data.GetStocksDevHandler;
import org.sjd.gordon.server.devhandlers.data.GetTradeHistoryDevHandler;
import org.sjd.gordon.server.devhandlers.data.GetTreasuryHeldStockHistoryDevHandler;
import org.sjd.gordon.server.devhandlers.data.UpdateBusinessSummaryDevHandler;
import org.sjd.gordon.server.devhandlers.security.ChangeUserPasswordDevHandler;
import org.sjd.gordon.server.devhandlers.security.CurrentUserDevHandler;
import org.sjd.gordon.server.devhandlers.security.DeleteUserDevHandler;
import org.sjd.gordon.server.devhandlers.security.EditUserDevHandler;
import org.sjd.gordon.server.devhandlers.security.GetAllUsersDevHandler;
import org.sjd.gordon.server.devhandlers.setup.DeleteRegistryEntryDevHandler;
import org.sjd.gordon.server.devhandlers.setup.EditRegistryEntryDevHandler;
import org.sjd.gordon.server.devhandlers.setup.GetGicsNamesDevHandler;
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
import org.sjd.gordon.shared.viewer.DeleteDividendAction;
import org.sjd.gordon.shared.viewer.DeleteStockSplitAction;
import org.sjd.gordon.shared.viewer.DeleteTreasuryHeldStockAction;
import org.sjd.gordon.shared.viewer.EditDividendAction;
import org.sjd.gordon.shared.viewer.EditStockSplitAction;
import org.sjd.gordon.shared.viewer.EditTreasuryHeldStockAction;
import org.sjd.gordon.shared.viewer.GetDividendHistoryAction;
import org.sjd.gordon.shared.viewer.GetStockAdminDatasetAction;
import org.sjd.gordon.shared.viewer.GetStockProfileAction;
import org.sjd.gordon.shared.viewer.GetStockSplitsAction;
import org.sjd.gordon.shared.viewer.GetTradeHistoryAction;
import org.sjd.gordon.shared.viewer.GetTreasuryHeldStockHistoryAction;
import org.sjd.gordon.shared.viewer.UpdateBusinessSummaryAction;

import com.gwtplatform.dispatch.server.guice.HandlerModule;

public class DevelopmentModule extends HandlerModule {

	@Override
	protected void configureHandlers() {
		bind(StockEquityImporterService.class).toProvider(DevStockEquityImporterProvider.class);
		bind(CSVTradeHistoryImportService.class).toProvider(DevCSVTradeHistoryImporterProvider.class);
		
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
		
		bindHandler(GetStockAdminDatasetAction.class, GetStockAdminDatasetDevHandler.class);
		bindHandler(DeleteStockSplitAction.class, DeleteStockSplitDevHandler.class);
		bindHandler(DeleteTreasuryHeldStockAction.class, DeleteTreasuryHeldStockDevHandler.class);
		bindHandler(EditStockSplitAction.class, EditStockSplitDevHandler.class);
		bindHandler(EditTreasuryHeldStockAction.class, EditTreasuryHeldStockDevHandler.class);
		bindHandler(GetStockSplitsAction.class, GetStockSplitsDevHandler.class);
		bindHandler(GetTreasuryHeldStockHistoryAction.class, GetTreasuryHeldStockHistoryDevHandler.class);
		
		bindHandler(DeleteDividendAction.class, DeleteDividendDevHandler.class);
		bindHandler(EditDividendAction.class, EditDividendDevHandler.class);
		bindHandler(GetDividendHistoryAction.class, GetDividendHistoryDevHandler.class);
	}

}

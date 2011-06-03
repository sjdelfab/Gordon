package org.sjd.gordon.ejb.dispatch;

import static com.google.inject.jndi.JndiIntegration.fromJndi;

import javax.naming.Context;
import javax.naming.InitialContext;

import org.sjd.gordon.ejb.CSVTradeHistoryImportServiceLocal;
import org.sjd.gordon.ejb.CSVTradeHistoryImporterEJB;
import org.sjd.gordon.ejb.ExchangeEJB;
import org.sjd.gordon.ejb.ExchangeServiceLocal;
import org.sjd.gordon.ejb.StockEntityEJB;
import org.sjd.gordon.ejb.StockEntityServiceLocal;
import org.sjd.gordon.ejb.StockEquityImporterEJB;
import org.sjd.gordon.ejb.StockEquityImporterServiceLocal;
import org.sjd.gordon.ejb.StockStatisticsEJB;
import org.sjd.gordon.ejb.StockStatisticsServiceLocal;
import org.sjd.gordon.ejb.dispatch.data.DeleteDividendEJBHandler;
import org.sjd.gordon.ejb.dispatch.data.DeleteStockSplitEJBHandler;
import org.sjd.gordon.ejb.dispatch.data.DeleteTreasuryHeldStockEJBHandler;
import org.sjd.gordon.ejb.dispatch.data.EditDividendEJBHandler;
import org.sjd.gordon.ejb.dispatch.data.EditStockSplitEJBHandler;
import org.sjd.gordon.ejb.dispatch.data.EditTreasuryHeldStockEJBHandler;
import org.sjd.gordon.ejb.dispatch.data.GetAllStockDetailsEJBHandler;
import org.sjd.gordon.ejb.dispatch.data.GetDividendHistoryEJBHandler;
import org.sjd.gordon.ejb.dispatch.data.GetExchangesEJBHandler;
import org.sjd.gordon.ejb.dispatch.data.GetStockAdminDatasetEJBHandler;
import org.sjd.gordon.ejb.dispatch.data.GetStockProfileEJBHandler;
import org.sjd.gordon.ejb.dispatch.data.GetStockSplitsEJBHandler;
import org.sjd.gordon.ejb.dispatch.data.GetStocksEJBHandler;
import org.sjd.gordon.ejb.dispatch.data.GetTradeHistoryEJBHandler;
import org.sjd.gordon.ejb.dispatch.data.GetTreasuryHeldStockHistoryEJBHandler;
import org.sjd.gordon.ejb.dispatch.data.UpdateBusinessSummaryEJBHandler;
import org.sjd.gordon.ejb.dispatch.security.ChangeUserPasswordEJBHandler;
import org.sjd.gordon.ejb.dispatch.security.CurrentUserEJBHandler;
import org.sjd.gordon.ejb.dispatch.security.DeleteUserEJBHandler;
import org.sjd.gordon.ejb.dispatch.security.EditUserEJBHandler;
import org.sjd.gordon.ejb.dispatch.security.GetAllUsersEJBHandler;
import org.sjd.gordon.ejb.dispatch.setup.DeleteRegistryEntryEJBHandler;
import org.sjd.gordon.ejb.dispatch.setup.EditRegistryEntryEJBHandler;
import org.sjd.gordon.ejb.dispatch.setup.GetGicsNamesEJBHandler;
import org.sjd.gordon.ejb.security.UserEJB;
import org.sjd.gordon.ejb.security.UserServiceLocal;
import org.sjd.gordon.ejb.setup.GicsEJB;
import org.sjd.gordon.ejb.setup.GicsServiceLocal;
import org.sjd.gordon.importing.profile.StockEquityImporterService;
import org.sjd.gordon.importing.tradehistory.CSVTradeHistoryImportService;
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

import com.google.inject.Singleton;
import com.gwtplatform.dispatch.server.guice.HandlerModule;

public class EJBServerModule extends HandlerModule {

	@Override
	protected void configureHandlers() {
		bind(Context.class).to(InitialContext.class).in(Singleton.class);
		bind(ExchangeServiceLocal.class).toProvider(fromJndi(ExchangeServiceLocal.class, getEjbJndiName(ExchangeServiceLocal.class,ExchangeEJB.class)));
		bind(StockEntityServiceLocal.class).toProvider(fromJndi(StockEntityServiceLocal.class, getEjbJndiName(StockEntityServiceLocal.class,StockEntityEJB.class)));
		bind(UserServiceLocal.class).toProvider(fromJndi(UserServiceLocal.class, getEjbJndiName(UserServiceLocal.class, UserEJB.class)));
		bind(GicsServiceLocal.class).toProvider(fromJndi(GicsServiceLocal.class, getEjbJndiName(GicsServiceLocal.class, GicsEJB.class)));
		
		bind(StockStatisticsServiceLocal.class).toProvider(fromJndi(StockStatisticsServiceLocal.class, getEjbJndiName(StockStatisticsServiceLocal.class, StockStatisticsEJB.class)));
		
		bind(StockEquityImporterService.class).toProvider(fromJndi(StockEquityImporterServiceLocal.class,
				getEjbJndiName(StockEquityImporterServiceLocal.class,StockEquityImporterEJB.class)));
		bind(CSVTradeHistoryImportService.class).toProvider(fromJndi(CSVTradeHistoryImportServiceLocal.class, 
				getEjbJndiName(CSVTradeHistoryImportServiceLocal.class,CSVTradeHistoryImporterEJB.class)));

		// Handlers
		bindHandler(GetStocksAction.class, GetStocksEJBHandler.class);
		bindHandler(GetExchangesAction.class, GetExchangesEJBHandler.class);
		bindHandler(GetTradeHistoryAction.class, GetTradeHistoryEJBHandler.class);
		bindHandler(GetStockProfileAction.class, GetStockProfileEJBHandler.class);
		bindHandler(LogoutAction.class, LogoutHandler.class);
		bindHandler(GetAllRegistryEntriesAction.class, GetAllStockDetailsEJBHandler.class);
		bindHandler(DeleteRegistryEntryAction.class, DeleteRegistryEntryEJBHandler.class);
		bindHandler(EditRegistryEntryAction.class, EditRegistryEntryEJBHandler.class);
		bindHandler(GetGicsSectorsAction.class, GetGicsNamesEJBHandler.class);
	
		bindHandler(GetAllUsersAction.class, GetAllUsersEJBHandler.class);
		bindHandler(DeleteUserAction.class, DeleteUserEJBHandler.class);
		bindHandler(EditUserAction.class, EditUserEJBHandler.class);
		bindHandler(GetCurrentUserAction.class, CurrentUserEJBHandler.class);
		bindHandler(ChangeUserPasswordAction.class, ChangeUserPasswordEJBHandler.class);
		
		bindHandler(UpdateBusinessSummaryAction.class, UpdateBusinessSummaryEJBHandler.class);
		bindHandler(GetStockAdminDatasetAction.class, GetStockAdminDatasetEJBHandler.class);
		bindHandler(DeleteStockSplitAction.class, DeleteStockSplitEJBHandler.class);
		bindHandler(DeleteTreasuryHeldStockAction.class, DeleteTreasuryHeldStockEJBHandler.class);
		bindHandler(EditStockSplitAction.class, EditStockSplitEJBHandler.class);
		bindHandler(EditTreasuryHeldStockAction.class, EditTreasuryHeldStockEJBHandler.class);
		bindHandler(GetStockSplitsAction.class, GetStockSplitsEJBHandler.class);
		bindHandler(GetTreasuryHeldStockHistoryAction.class, GetTreasuryHeldStockHistoryEJBHandler.class);
		
		bindHandler(DeleteDividendAction.class, DeleteDividendEJBHandler.class);
		bindHandler(EditDividendAction.class, EditDividendEJBHandler.class);
		bindHandler(GetDividendHistoryAction.class, GetDividendHistoryEJBHandler.class);

	}

	public static String PREFIX = "gordon-gwt-1.0";
	
	public static String getEjbJndiName(Class<?> interfaceClass, Class<?> ejbClass) {
		return "java:global/" + PREFIX + "/" + ejbClass.getSimpleName() + "!" + interfaceClass.getName();
	}
	
}

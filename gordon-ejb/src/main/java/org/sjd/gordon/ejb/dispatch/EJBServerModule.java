package org.sjd.gordon.ejb.dispatch;

import static com.google.inject.jndi.JndiIntegration.fromJndi;

import javax.naming.Context;
import javax.naming.InitialContext;

import org.sjd.gordon.ejb.ExchangeEJB;
import org.sjd.gordon.ejb.ExchangeService;
import org.sjd.gordon.ejb.StockEntityEJB;
import org.sjd.gordon.ejb.StockEntityService;
import org.sjd.gordon.ejb.dispatch.data.GetAllStockDetailsEJBHandler;
import org.sjd.gordon.ejb.dispatch.data.GetExchangesEJBHandler;
import org.sjd.gordon.ejb.dispatch.data.GetStockProfileEJBHandler;
import org.sjd.gordon.ejb.dispatch.data.GetStocksEJBHandler;
import org.sjd.gordon.ejb.dispatch.data.GetTradeHistoryEJBHandler;
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
import org.sjd.gordon.ejb.security.UserService;
import org.sjd.gordon.ejb.setup.GicsEJB;
import org.sjd.gordon.ejb.setup.GicsService;
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

import com.google.inject.Singleton;
import com.gwtplatform.dispatch.server.guice.HandlerModule;

public class EJBServerModule extends HandlerModule {

	@Override
	protected void configureHandlers() {
		bind(Context.class).to(InitialContext.class).in(Singleton.class);
		bind(ExchangeService.class).toProvider(fromJndi(ExchangeService.class, getEjbJndiName(ExchangeService.class,ExchangeEJB.class)));
		bind(StockEntityService.class).toProvider(fromJndi(StockEntityService.class, getEjbJndiName(StockEntityService.class,StockEntityEJB.class)));
		bind(UserService.class).toProvider(fromJndi(UserService.class, getEjbJndiName(UserService.class, UserEJB.class)));
		bind(GicsService.class).toProvider(fromJndi(GicsService.class, getEjbJndiName(GicsService.class, GicsEJB.class)));
		
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
	}

	public static String PREFIX = "gordon-gwt-1.0";
	
	private static String getEjbJndiName(Class<?> interfaceClass, Class<?> ejbClass) {
		return "java:global/" + PREFIX + "/" + ejbClass.getSimpleName() + "!" + interfaceClass.getName();
	}
	
}

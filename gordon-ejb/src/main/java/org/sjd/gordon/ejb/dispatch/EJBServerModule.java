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
import org.sjd.gordon.ejb.dispatch.data.GetStockDetailsEJBHandler;
import org.sjd.gordon.ejb.dispatch.data.GetStocksEJBHandler;
import org.sjd.gordon.ejb.dispatch.data.GetTradeHistoryEJBHandler;
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
import org.sjd.gordon.shared.navigation.GetExchanges;
import org.sjd.gordon.shared.navigation.GetStocks;
import org.sjd.gordon.shared.registry.DeleteRegistryEntry;
import org.sjd.gordon.shared.registry.EditRegistryEntry;
import org.sjd.gordon.shared.registry.GetAllStockDetails;
import org.sjd.gordon.shared.registry.GetGicsSectors;
import org.sjd.gordon.shared.security.ChangeUserPassword;
import org.sjd.gordon.shared.security.DeleteUser;
import org.sjd.gordon.shared.security.EditUser;
import org.sjd.gordon.shared.security.GetAllUserDetails;
import org.sjd.gordon.shared.security.GetCurrentUser;
import org.sjd.gordon.shared.security.Logout;
import org.sjd.gordon.shared.viewer.GetStockDetails;
import org.sjd.gordon.shared.viewer.GetTradeHistory;

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
		bindHandler(GetStocks.class, GetStocksEJBHandler.class);
		bindHandler(GetExchanges.class, GetExchangesEJBHandler.class);
		bindHandler(GetTradeHistory.class, GetTradeHistoryEJBHandler.class);
		bindHandler(GetStockDetails.class, GetStockDetailsEJBHandler.class);
		bindHandler(Logout.class, LogoutHandler.class);
		bindHandler(GetAllStockDetails.class, GetAllStockDetailsEJBHandler.class);
		bindHandler(DeleteRegistryEntry.class, DeleteRegistryEntryEJBHandler.class);
		bindHandler(EditRegistryEntry.class, EditRegistryEntryEJBHandler.class);
		bindHandler(GetGicsSectors.class, GetGicsNamesEJBHandler.class);
	
		bindHandler(GetAllUserDetails.class, GetAllUsersEJBHandler.class);
		bindHandler(DeleteUser.class, DeleteUserEJBHandler.class);
		bindHandler(EditUser.class, EditUserEJBHandler.class);
		bindHandler(GetCurrentUser.class, CurrentUserEJBHandler.class);
		bindHandler(ChangeUserPassword.class, ChangeUserPasswordEJBHandler.class);		
	}

	public static String PREFIX = "gordon-gwt-1.0";
	
	private static String getEjbJndiName(Class<?> interfaceClass, Class<?> ejbClass) {
		return "java:global/" + PREFIX + "/" + ejbClass.getSimpleName() + "!" + interfaceClass.getName();
	}
	
}

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
import org.sjd.gordon.ejb.dispatch.setup.DeleteRegistryEntryEJBHandler;
import org.sjd.gordon.ejb.dispatch.setup.EditRegistryEntryEJBHandler;
import org.sjd.gordon.ejb.security.AuthenticationEJBHandler;
import org.sjd.gordon.ejb.security.UserEJB;
import org.sjd.gordon.ejb.security.UserService;
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

import com.google.inject.Singleton;
import com.gwtplatform.dispatch.server.guice.HandlerModule;

public class EJBServerModule extends HandlerModule {

	@Override
	protected void configureHandlers() {
		bind(Context.class).to(InitialContext.class).in(Singleton.class);
		bind(ExchangeService.class).toProvider(fromJndi(ExchangeService.class, getEjbJndiName(ExchangeService.class,ExchangeEJB.class)));
		bind(StockEntityService.class).toProvider(fromJndi(StockEntityService.class, getEjbJndiName(StockEntityService.class,StockEntityEJB.class)));
		bind(UserService.class).toProvider(fromJndi(UserService.class, getEjbJndiName(UserService.class, UserEJB.class)));
		bindHandler(Login.class, AuthenticationEJBHandler.class);
		bindHandler(GetStocks.class, GetStocksEJBHandler.class);
		bindHandler(GetExchanges.class, GetExchangesEJBHandler.class);
		bindHandler(GetTradeHistory.class, GetTradeHistoryEJBHandler.class);
		bindHandler(GetStockDetails.class, GetStockDetailsEJBHandler.class);
		bindHandler(Logout.class, LogoutHandler.class);
		bindHandler(GetAllStockDetails.class, GetAllStockDetailsEJBHandler.class);
		bindHandler(DeleteRegistryEntry.class, DeleteRegistryEntryEJBHandler.class);
		bindHandler(EditRegistryEntry.class, EditRegistryEntryEJBHandler.class);		
	}

	public static String PREFIX = "gordon-gwt-1.0";
	
	private static String getEjbJndiName(Class<?> interfaceClass, Class<?> ejbClass) {
		return "java:global/" + PREFIX + "/" + ejbClass.getSimpleName() + "!" + interfaceClass.getName();
	}
	
}

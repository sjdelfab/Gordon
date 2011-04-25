package org.sjd.gordon.ejb.dispatch;

import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.NamingException;

import net.customware.gwt.dispatch.server.ActionHandler;
import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.ActionException;
import net.customware.gwt.dispatch.shared.DispatchException;

import org.sjd.gordon.ejb.ExchangeEJB;
import org.sjd.gordon.model.Exchange;
import org.sjd.gordon.shared.navigation.GetExchanges;
import org.sjd.gordon.shared.navigation.GotExchanges;

import com.google.inject.Inject;

public class GetExchangesEJBHandler implements ActionHandler<GetExchanges,GotExchanges> {

	@Inject
	private Context ctx;
	
	@Override
	public GotExchanges execute(GetExchanges getExchanges, ExecutionContext context) throws DispatchException {
		ExchangeEJB exchangeEjb = null;
		try {
			exchangeEjb = (ExchangeEJB) ctx.lookup("java:global/gordon-gwt-1.0/ExchangeEJB!org.sjd.gordon.ejb.ExchangeEJB");
		} catch (NamingException e) {
			throw new ActionException(e);
		}
		if (exchangeEjb == null) {
			throw new RuntimeException("EJB not found.");
		}
		return new GotExchanges(new ArrayList<Exchange>(exchangeEjb.getExchanges()));
	}
	
	@Override
	public Class<GetExchanges> getActionType() {
		return GetExchanges.class;
	}

	@Override
	public void rollback(GetExchanges getExchanges, GotExchanges gotExchanges, ExecutionContext arg2) throws DispatchException {
		// Nothing
	}

}

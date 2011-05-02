package org.sjd.gordon.ejb.dispatch.data;

import java.util.ArrayList;

import org.sjd.gordon.ejb.ExchangeService;
import org.sjd.gordon.model.Exchange;
import org.sjd.gordon.shared.navigation.GetExchanges;
import org.sjd.gordon.shared.navigation.GotExchanges;

import com.google.inject.Inject;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

public class GetExchangesEJBHandler implements ActionHandler<GetExchanges,GotExchanges> {

	@Inject
	private ExchangeService exchangeEjb;
	
	@Override
	public GotExchanges execute(GetExchanges getExchanges, ExecutionContext context) throws ActionException {
		return new GotExchanges(new ArrayList<Exchange>(exchangeEjb.getExchanges()));
	}
	
	@Override
	public Class<GetExchanges> getActionType() {
		return GetExchanges.class;
	}

	@Override
	public void undo(GetExchanges action, GotExchanges result, ExecutionContext arg2) throws ActionException {
		// Nothing
	}

}

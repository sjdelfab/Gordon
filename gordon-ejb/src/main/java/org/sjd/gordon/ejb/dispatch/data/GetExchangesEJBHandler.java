package org.sjd.gordon.ejb.dispatch.data;

import java.util.ArrayList;

import org.sjd.gordon.ejb.ExchangeService;
import org.sjd.gordon.model.Exchange;
import org.sjd.gordon.shared.navigation.GetExchangesAction;
import org.sjd.gordon.shared.navigation.GetExchangesResult;

import com.google.inject.Inject;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

public class GetExchangesEJBHandler implements ActionHandler<GetExchangesAction,GetExchangesResult> {

	@Inject
	private ExchangeService exchangeEjb;
	
	@Override
	public GetExchangesResult execute(GetExchangesAction getExchanges, ExecutionContext context) throws ActionException {
		return new GetExchangesResult(new ArrayList<Exchange>(exchangeEjb.getExchanges()));
	}
	
	@Override
	public Class<GetExchangesAction> getActionType() {
		return GetExchangesAction.class;
	}

	@Override
	public void undo(GetExchangesAction action, GetExchangesResult result, ExecutionContext arg2) throws ActionException {
		// Nothing
	}

}

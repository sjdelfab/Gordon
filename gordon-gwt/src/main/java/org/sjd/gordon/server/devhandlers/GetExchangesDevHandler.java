package org.sjd.gordon.server.devhandlers;

import java.util.ArrayList;

import org.sjd.gordon.model.Exchange;
import org.sjd.gordon.shared.navigation.GetExchangesAction;
import org.sjd.gordon.shared.navigation.GetExchangesResult;

import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

public class GetExchangesDevHandler implements ActionHandler<GetExchangesAction,GetExchangesResult> {

	@Override
	public GetExchangesResult execute(GetExchangesAction getExchanges, ExecutionContext context) throws ActionException {
		return new GetExchangesResult(create());
	}
	
	private ArrayList<Exchange> create() {
		ArrayList<Exchange> list = new ArrayList<Exchange>();
		Exchange exchange = new Exchange();
		exchange.setCode("ASX");
		exchange.setName("Australian Stock Exchange");
		exchange.setId(Integer.valueOf(1));
		list.add(exchange);
		return list;
	}

	@Override
	public Class<GetExchangesAction> getActionType() {
		return GetExchangesAction.class;
	}

	@Override
	public void undo(GetExchangesAction action, GetExchangesResult result, ExecutionContext context) throws ActionException {
		// Nothing
	}

}

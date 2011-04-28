package org.sjd.gordon.server.devhandlers;

import java.util.ArrayList;

import org.sjd.gordon.model.Exchange;
import org.sjd.gordon.shared.navigation.GetExchanges;
import org.sjd.gordon.shared.navigation.GotExchanges;

import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

public class DevelopmentGetExchangesHandler implements ActionHandler<GetExchanges,GotExchanges> {

	@Override
	public GotExchanges execute(GetExchanges getExchanges, ExecutionContext context) throws ActionException {
		return new GotExchanges(create());
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
	public Class<GetExchanges> getActionType() {
		return GetExchanges.class;
	}

	@Override
	public void undo(GetExchanges action, GotExchanges result, ExecutionContext context) throws ActionException {
		// Nothing
	}

}

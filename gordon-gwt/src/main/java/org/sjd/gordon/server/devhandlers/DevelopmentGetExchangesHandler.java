package org.sjd.gordon.server.devhandlers;

import java.util.ArrayList;

import net.customware.gwt.dispatch.server.ActionHandler;
import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;

import org.sjd.gordon.model.Exchange;
import org.sjd.gordon.shared.navigation.GetExchanges;
import org.sjd.gordon.shared.navigation.GotExchanges;

public class DevelopmentGetExchangesHandler implements ActionHandler<GetExchanges,GotExchanges> {

	@Override
	public GotExchanges execute(GetExchanges getExchanges, ExecutionContext context) throws DispatchException {
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
	public void rollback(GetExchanges getExchanges, GotExchanges gotExchanges, ExecutionContext arg2) throws DispatchException {
		// Nothing
	}

}

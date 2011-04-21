package org.sjd.gordon.server;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import net.customware.gwt.dispatch.server.ActionHandler;
import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;

import org.sjd.gordon.model.Exchange;
import org.sjd.gordon.shared.navigation.GetExchanges;
import org.sjd.gordon.shared.navigation.GotExchanges;

import com.google.inject.Inject;

public class GetExchangesJpaHandler implements ActionHandler<GetExchanges,GotExchanges> {

	@Inject EntityManager em;
	
	@Override
	public GotExchanges execute(GetExchanges getExchanges, ExecutionContext context) throws DispatchException {
		String getAllExchanges = "SELECT e FROM Exchange e";
    	TypedQuery<Exchange> query = em.createQuery(getAllExchanges, Exchange.class);
    	List<Exchange> exchanges = query.getResultList();
		return new GotExchanges(new ArrayList<Exchange>(exchanges));
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

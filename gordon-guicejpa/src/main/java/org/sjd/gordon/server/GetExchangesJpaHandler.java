package org.sjd.gordon.server;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.sjd.gordon.model.Exchange;
import org.sjd.gordon.shared.navigation.GetExchangesAction;
import org.sjd.gordon.shared.navigation.GetExchangesResult;

import com.google.inject.Inject;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

public class GetExchangesJpaHandler implements ActionHandler<GetExchangesAction,GetExchangesResult> {

	@Inject EntityManager em;
	
	@Override
	public GetExchangesResult execute(GetExchangesAction getExchanges, ExecutionContext context) throws ActionException {
		String getAllExchanges = "SELECT e FROM Exchange e";
    	TypedQuery<Exchange> query = em.createQuery(getAllExchanges, Exchange.class);
    	List<Exchange> exchanges = query.getResultList();
		return new GetExchangesResult(new ArrayList<Exchange>(exchanges));
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

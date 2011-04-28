package org.sjd.gordon.server.devhandlers;

import java.util.HashMap;
import java.util.Map;

import org.sjd.gordon.shared.viewer.GetStockDetails;
import org.sjd.gordon.shared.viewer.GotStockDetails;
import org.sjd.gordon.shared.viewer.StockDetails;

import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

public class DevelopmentGetStockDetailsHandler implements ActionHandler<GetStockDetails, GotStockDetails> {

	private static Map<Long,StockDetails> detailsMap = new HashMap<Long,StockDetails>();
	
	static {
		StockDetails stockDetails = new StockDetails();
		stockDetails.setCode("ABC");
		stockDetails.setName("ABC Ltd");
		detailsMap.put(Long.valueOf(1), stockDetails);
		stockDetails = new StockDetails();
		stockDetails.setCode("BHP");
		stockDetails.setName("BHP Ltd");
		detailsMap.put(Long.valueOf(2), stockDetails);
	}
	
	@Override
	public GotStockDetails execute(GetStockDetails getDetails, ExecutionContext context) throws ActionException {
		return new GotStockDetails(detailsMap.get(getDetails.getStockId()));
	}

	@Override
	public Class<GetStockDetails> getActionType() {
		return GetStockDetails.class;
	}

	@Override
	public void undo(GetStockDetails action, GotStockDetails result, ExecutionContext context) throws ActionException { }

}

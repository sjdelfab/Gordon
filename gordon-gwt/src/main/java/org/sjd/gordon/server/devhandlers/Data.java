package org.sjd.gordon.server.devhandlers;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.sjd.gordon.shared.viewer.StockDetails;

class Data {

	static Map<Long,StockDetails> detailsMap = new HashMap<Long,StockDetails>();
	
	static {
		StockDetails stockDetails = new StockDetails();
		stockDetails.setId(Long.valueOf(1));
		stockDetails.setCode("ABC");
		stockDetails.setName("ABC Ltd");
		stockDetails.set("listDate", new Date());
		stockDetails.set("lastTradeDate", new Date());
		detailsMap.put(Long.valueOf(1), stockDetails);
		stockDetails = new StockDetails();
		stockDetails.setId(Long.valueOf(2));
		stockDetails.setCode("BHP");
		stockDetails.setName("BHP Ltd");
		stockDetails.set("listDate", new Date());
		stockDetails.set("lastTradeDate", new Date());
		detailsMap.put(Long.valueOf(2), stockDetails);
	}
	
	static Long getMaxId() {
		return Collections.max(detailsMap.keySet());
	}
}

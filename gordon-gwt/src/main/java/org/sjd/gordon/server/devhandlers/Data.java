package org.sjd.gordon.server.devhandlers;

import java.math.BigDecimal;
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
		stockDetails.setListDate(new Date());
		stockDetails.setLastTradeDate(new Date());
		stockDetails.setPrimarySectorName("Energy");
		stockDetails.setPrimaryIndustryGroupName("Energy");
		stockDetails.setCurrentPrice(new BigDecimal("3.45"));
		detailsMap.put(Long.valueOf(1), stockDetails);
		stockDetails = new StockDetails();
		stockDetails.setId(Long.valueOf(2));
		stockDetails.setCode("BHP");
		stockDetails.setName("BHP Ltd");
		stockDetails.setListDate(new Date());
		stockDetails.setLastTradeDate(new Date());
		stockDetails.setPrimarySectorName("Materials");
		stockDetails.setPrimaryIndustryGroupName("Materials");
		stockDetails.setCurrentPrice(new BigDecimal("68.1"));
		detailsMap.put(Long.valueOf(2), stockDetails);
	}
	
	static Long getMaxId() {
		return Collections.max(detailsMap.keySet());
	}
}

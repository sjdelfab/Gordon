package org.sjd.gordon.server.devhandlers;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.sjd.gordon.shared.security.UserDetail;
import org.sjd.gordon.shared.viewer.StockDetail;

class Data {

	static Map<Long,StockDetail> detailsMap = new HashMap<Long,StockDetail>();
	static Map<Integer,UserDetail> users = new HashMap<Integer,UserDetail>();
	
	static {
		StockDetail stockDetails = new StockDetail();
		stockDetails.setId(Long.valueOf(1));
		stockDetails.setCode("ABC");
		stockDetails.setName("ABC Ltd");
		stockDetails.setListDate(new Date());
		stockDetails.setLastTradeDate(new Date());
		stockDetails.setPrimarySectorName("Energy");
		stockDetails.setPrimaryIndustryGroupName("Energy");
		stockDetails.setCurrentPrice(new BigDecimal("3.45"));
		detailsMap.put(Long.valueOf(1), stockDetails);
		stockDetails = new StockDetail();
		stockDetails.setId(Long.valueOf(2));
		stockDetails.setCode("BHP");
		stockDetails.setName("BHP Ltd");
		stockDetails.setListDate(new Date());
		stockDetails.setLastTradeDate(new Date());
		stockDetails.setPrimarySectorName("Materials");
		stockDetails.setPrimaryIndustryGroupName("Materials");
		stockDetails.setCurrentPrice(new BigDecimal("68.1"));
		detailsMap.put(Long.valueOf(2), stockDetails);
		
		UserDetail user = new UserDetail();
		user.setId(Integer.valueOf(1));
		user.setFirstName("Simon");
		user.setLastName("Smith");
		user.setUID("ssmith");
		user.setActive(Boolean.TRUE);
		user.setRoles("USER, ADMIN");
		user.setVersion(Integer.valueOf(1));
		users.put(user.getId(), user);
		
		user = new UserDetail();
		user.setId(Integer.valueOf(2));
		user.setFirstName("John");
		user.setLastName("Doe");
		user.setUID("jdoe");
		user.setActive(Boolean.TRUE);
		user.setRoles("USER");
		user.setVersion(Integer.valueOf(1));
		users.put(user.getId(), user);
	}
	
	static Long getStockMaxId() {
		return Collections.max(detailsMap.keySet());
	}
	
	static Integer getUserMaxId() {
		return Collections.max(users.keySet());
	}

}

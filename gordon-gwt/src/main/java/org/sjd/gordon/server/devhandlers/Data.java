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
		char endLetter = 'A';
		for (int i = 0; i < 50; i++) {
			StockDetail stockDetails = new StockDetail();
			stockDetails.setId(Long.valueOf(i+1));
			String code = null;
			if (i < 26) {
				code = "AA" + endLetter;
				endLetter++;
			} else if (i == 26) {
				endLetter = 'A';
				code = "AB" + endLetter;
				endLetter++;
			} else {
				code = "AB" + endLetter;
				endLetter++;
			}
			stockDetails.setCode(code);
			stockDetails.setName(code + " Ltd");
			stockDetails.setListDate(new Date());
			stockDetails.setLastTradeDate(new Date());
			if (i % 2 == 0) {
				stockDetails.setPrimarySectorName("Energy");
				stockDetails.setPrimaryIndustryGroupName("Energy");
			} else {
				stockDetails.setPrimarySectorName("Materials");
				stockDetails.setPrimaryIndustryGroupName("Materials");
			}
			stockDetails.setCurrentPrice(new BigDecimal("3.45"));
			detailsMap.put(Long.valueOf(i), stockDetails);
		}
		
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

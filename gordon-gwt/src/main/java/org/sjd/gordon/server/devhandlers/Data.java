package org.sjd.gordon.server.devhandlers;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.sjd.gordon.model.Dividend;
import org.sjd.gordon.model.StockSplit;
import org.sjd.gordon.model.TreasuryHeldStock;
import org.sjd.gordon.shared.security.UserDetail;
import org.sjd.gordon.shared.viewer.StockDetail;

public class Data {

	public static Map<Long,StockDetail> detailsMap = new HashMap<Long,StockDetail>();
	public static Map<Integer,UserDetail> users = new HashMap<Integer,UserDetail>();
	public static Map<Long,ArrayList<StockSplit>> splitsMap = new HashMap<Long,ArrayList<StockSplit>>();
	public static Map<Long,ArrayList<TreasuryHeldStock>> heldInTreasuryMap = new HashMap<Long,ArrayList<TreasuryHeldStock>>();
	public static Map<Long,ArrayList<Dividend>> dividendMap = new HashMap<Long,ArrayList<Dividend>>();
	public static long stockSplitCounter = 1l;
	public static long treasuryHeldStockCounter = 1l;
	public static long dividendCounter = 1l;
	
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
		
		ArrayList<StockSplit> splits = new ArrayList<StockSplit>();
		StockSplit split = new StockSplit();
		split.setDate(new Date());
		split.setFactor(new BigDecimal("1.5"));
		splits.add(split);
		splitsMap.put(Long.valueOf(1),splits);
		
		ArrayList<TreasuryHeldStock> treasuryHeldStock = new ArrayList<TreasuryHeldStock>();
		TreasuryHeldStock held = new TreasuryHeldStock();
		held.setDate(new Date());
		held.setVolume(1000);
		treasuryHeldStock.add(held);
		heldInTreasuryMap.put(Long.valueOf(1),treasuryHeldStock);
		
		ArrayList<Dividend> dividends = new ArrayList<Dividend>();
		Dividend dividend = new Dividend();
		dividend.setAmount(new BigDecimal("0.50"));
		dividend.setAnnouncementDate(new Date());
		dividend.setDate(new Date());
		dividend.setId(dividendCounter++);
		dividends.add(dividend);
		dividendMap.put(Long.valueOf(1), dividends);
	}
	
	public static Long getStockMaxId() {
		return Collections.max(detailsMap.keySet());
	}
	
	public static Integer getUserMaxId() {
		return Collections.max(users.keySet());
	}

}

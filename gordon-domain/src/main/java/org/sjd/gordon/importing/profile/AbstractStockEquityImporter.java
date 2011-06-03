package org.sjd.gordon.importing.profile;

import java.util.List;

import javax.persistence.EntityManager;

import org.sjd.gordon.importing.profile.jaxb.DividendHistory.Dividend;
import org.sjd.gordon.importing.profile.jaxb.StockEquity;
import org.sjd.gordon.importing.profile.jaxb.StockSplits.Split;
import org.sjd.gordon.importing.profile.jaxb.TreasuryHeldStockHistory.TreasuryHeldStock;
import org.sjd.gordon.model.BusinessSummary;
import org.sjd.gordon.model.Exchange;
import org.sjd.gordon.model.GicsIndustryGroup;
import org.sjd.gordon.model.StockEntity;
import org.sjd.gordon.model.StockSplit;
import org.sjd.gordon.services.ExchangeService;
import org.sjd.gordon.services.GicsService;

public abstract class AbstractStockEquityImporter implements StockEquityImporterService {

	@Override
	public void importStockEquity(StockEquity stockEquity) {
		StockEntity stockEntity = new StockEntity();
		stockEntity.setActive(Boolean.TRUE);
		stockEntity.setCode(stockEquity.getSymbol());
		stockEntity.setName(stockEquity.getName());
		stockEntity.setFloatVolume(stockEquity.getFloatVolume());
		Exchange exchange = getExchangeService().getExchangeByCode(stockEquity.getExchange());
		stockEntity.setExchange(exchange);
		GicsIndustryGroup industryGroup = getGicsService().findIndustryGroupByName(stockEquity.getIndustryGroup());
		stockEntity.setGicsIndustryGroup(industryGroup);
		
		BusinessSummary businessSummary = new BusinessSummary();
		businessSummary.setSummary(stockEquity.getBusinessSummary());
		stockEntity.setBusinessSummary(businessSummary);
		
		List<Split> splits = stockEquity.getStockSplits().getSplit();
		for(Split split: splits) {
			StockSplit stockSplit = new StockSplit();
			stockSplit.setDate(split.getDate().toGregorianCalendar().getTime());
			stockSplit.setFactor(split.getFactor());
			stockEntity.addStockSplit(stockSplit);
		}
		
		List<TreasuryHeldStock> treasuryHeldStockHistory = stockEquity.getTreasuryHeldStockHistory().getTreasuryHeldStock();
		for(TreasuryHeldStock heldStock: treasuryHeldStockHistory) {
			org.sjd.gordon.model.TreasuryHeldStock treasuryHeldStock = new org.sjd.gordon.model.TreasuryHeldStock();
			treasuryHeldStock.setDate(heldStock.getDate().toGregorianCalendar().getTime());
			treasuryHeldStock.setVolume(heldStock.getVolume());
			stockEntity.addHeldStock(treasuryHeldStock);
		}
		
		List<Dividend> dividendHistory = stockEquity.getDividendHistory().getDividend();
		for(Dividend dividend: dividendHistory) {
			org.sjd.gordon.model.Dividend newDividend = new org.sjd.gordon.model.Dividend();
			newDividend.setAmount(dividend.getAmount());
			newDividend.setAnnouncementDate(dividend.getAnnouncementDate().toGregorianCalendar().getTime());
			newDividend.setDate(dividend.getDate().toGregorianCalendar().getTime());
			stockEntity.addDividend(newDividend);
		}
		getEntityManager().persist(stockEntity);
	}
	
	abstract protected EntityManager getEntityManager();
	abstract protected GicsService getGicsService();
	abstract protected ExchangeService getExchangeService();
}

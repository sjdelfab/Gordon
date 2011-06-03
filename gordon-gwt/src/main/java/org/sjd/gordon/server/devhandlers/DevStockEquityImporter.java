package org.sjd.gordon.server.devhandlers;

import javax.persistence.EntityManager;

import org.sjd.gordon.importing.profile.AbstractStockEquityImporter;
import org.sjd.gordon.importing.profile.jaxb.StockEquity;
import org.sjd.gordon.services.ExchangeService;
import org.sjd.gordon.services.GicsService;

public class DevStockEquityImporter extends AbstractStockEquityImporter {

	@Override
	public void importStockEquity(StockEquity stockEquity) {
		System.out.println("Importing: " + stockEquity.getName());
	}
	
	@Override
	protected EntityManager getEntityManager() {
		return null;
	}

	@Override
	protected GicsService getGicsService() {
		return null;
	}

	@Override
	protected ExchangeService getExchangeService() {
		return null;
	}

}

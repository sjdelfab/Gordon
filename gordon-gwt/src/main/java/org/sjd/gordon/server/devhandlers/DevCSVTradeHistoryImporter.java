package org.sjd.gordon.server.devhandlers;

import java.io.Reader;

import javax.persistence.EntityManager;

import org.sjd.gordon.importing.ImportException;
import org.sjd.gordon.importing.tradehistory.AbstractCSVTradeHistoryImporter;

public class DevCSVTradeHistoryImporter extends AbstractCSVTradeHistoryImporter {

	@Override
	public void importTradeHistory(String exchangeCode, String symbol, Reader csvFile) throws ImportException {
		System.out.println("Importing CSV for stockId = " + symbol);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) { }
	}
	
	@Override
	protected EntityManager getEntityManager() {
		return null;
	}

	@Override
	protected Long getStockId(String exchangeCode, String symbol) {
		return null;
	}

}

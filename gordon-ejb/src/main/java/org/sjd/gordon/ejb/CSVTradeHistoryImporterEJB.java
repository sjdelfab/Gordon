package org.sjd.gordon.ejb;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.sjd.gordon.importing.ImportException;
import org.sjd.gordon.importing.tradehistory.AbstractCSVTradeHistoryImporter;
import org.sjd.gordon.model.Exchange;
import org.sjd.gordon.model.StockEntity;

@Stateless
public class CSVTradeHistoryImporterEJB extends AbstractCSVTradeHistoryImporter implements CSVTradeHistoryImportServiceLocal {
	
	@EJB(name="java:global/gordon-gwt-1.0/StockEntityEJB!org.sjd.gordon.ejb.StockEntityServiceLocal")
	private StockEntityServiceLocal stockEntityEjb;
	@EJB(name="java:global/gordon-gwt-1.0/ExchangeEJB!org.sjd.gordon.ejb.ExchangeServiceLocal")
	private ExchangeServiceLocal exchangeEjb;
	
	@PersistenceContext
    private EntityManager em; 
 
	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	@Override
	protected Long getStockId(String exchangeName, String symbol) throws ImportException {
		Exchange exchange = exchangeEjb.getExchangeByName(exchangeName);
		StockEntity stockEntity = stockEntityEjb.getStock(exchange.getId(),symbol);
		if (stockEntity == null) {
			throw new ImportException("Unable to find stock entity with symbol '" + symbol + "' in exchange '" + exchangeName + "'");
		}
		return stockEntity.getId();
	}
}

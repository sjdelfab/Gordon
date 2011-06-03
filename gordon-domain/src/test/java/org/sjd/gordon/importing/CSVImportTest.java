package org.sjd.gordon.importing;

import static org.junit.Assert.assertEquals;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import javax.persistence.EntityManager;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.sjd.gordon.importing.tradehistory.AbstractCSVTradeHistoryImporter;
import org.sjd.gordon.model.AbstractJpaTest;
import org.sjd.gordon.model.Exchange;
import org.sjd.gordon.model.StockDayTradeRecord;
import org.sjd.gordon.model.StockEntity;

public class CSVImportTest extends AbstractJpaTest {

	private StockEntity stock;
	private Exchange exchange;
	private static AbstractCSVTradeHistoryImporter csvImporter;

	@BeforeClass
	public static void beforeClass() throws Exception {
		initEntityManager();
		
	}

	@Before
	public void beforeTest() {
		csvImporter = new AbstractCSVTradeHistoryImporter() {
			@Override
			protected EntityManager getEntityManager() {
				return em;
			}
			@Override
			protected Long getStockId(String exchangeCode, String symbol) {
				return stock.getId();
			}
		};
		stock = createStock();
		exchange = stock.getExchange();

		tx.begin();
		em.persist(exchange);
		em.persist(stock);
		tx.commit();
	}

	@Test
	public void import_stock() throws Exception {
		ClassLoader cl = this.getClass().getClassLoader();
		InputStream in = cl.getResourceAsStream("org/sjd/gordon/importing/trade_data.csv");
		InputStreamReader reader = new InputStreamReader(in);
		tx.begin();
		csvImporter.importTradeHistory(exchange.getName(),stock.getCode(), reader);
		tx.commit();
		String getAllTrades = "select t from StockDayTradeRecord t where t.stockId=" + stock.getId();
        List<StockDayTradeRecord> trades = em.createQuery(getAllTrades,StockDayTradeRecord.class).getResultList(); 
        assertEquals(10, trades.size());
	}
}

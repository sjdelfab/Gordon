package org.sjd.gordon.importing;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.InputStream;
import java.util.List;

import javax.persistence.EntityManager;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.sjd.gordon.importing.profile.AbstractStockEquityImporter;
import org.sjd.gordon.importing.profile.JaxbContextFactory;
import org.sjd.gordon.importing.profile.jaxb.StockEquities;
import org.sjd.gordon.importing.profile.jaxb.StockEquity;
import org.sjd.gordon.model.AbstractJpaTest;
import org.sjd.gordon.model.Exchange;
import org.sjd.gordon.model.GicsIndustryGroup;
import org.sjd.gordon.model.GicsSector;
import org.sjd.gordon.model.StockEntity;
import org.sjd.gordon.services.AbstractExchangeService;
import org.sjd.gordon.services.AbstractGicsService;

public class StockEquityImporterTest extends AbstractJpaTest {
	
	private static AbstractStockEquityImporter importer;
	private static AbstractGicsService gicsService;
	private static AbstractExchangeService exchangeService;
	
	@BeforeClass 
    public static void beforeClass() throws Exception {
		initEntityManager();
		
		gicsService = new AbstractGicsService() {
			@Override
			protected EntityManager getEntityManager() {
				return em;
			}
		};
		exchangeService = new AbstractExchangeService() {
			@Override
			protected EntityManager getEntityManager() {
				return em;
			}
		};
		importer = new AbstractStockEquityImporter() {
			@Override
			protected EntityManager getEntityManager() {
				return em;
			}

			@Override
			protected AbstractGicsService getGicsService() {
				return gicsService;
			}

			@Override
			protected AbstractExchangeService getExchangeService() {
				return exchangeService;
			}
		};
    } 
 
    @Before 
    public void beforeTest() { 
        initTransaction();
        Exchange exchange = new Exchange();
    	exchange.setActive(true);
    	exchange.setCode("ASX");
    	exchange.setName("Australian Exchange");
    	tx.begin(); 
    	em.persist(exchange);
    	
    	GicsSector sector = new GicsSector();
    	sector.setActive(true);
    	sector.setCode(10);
    	sector.setName("Services");
    	GicsIndustryGroup group = new GicsIndustryGroup();
    	group.setActive(true);
    	group.setCode(1010);
    	group.setName("Grocery Stores");
    	sector.addIndustryGroup(group);
    	
        em.persist(sector); 
        tx.commit(); 
    }
    
    @Test 
    public void import_stock() throws Exception {
    	ClassLoader cl = this.getClass().getClassLoader();
    	InputStream in = cl.getResourceAsStream("org/sjd/gordon/importing/import_profile.xml");
    	StockEquities stockEquities = JaxbContextFactory.getFactory().unmarshal(in);
    	tx.begin();
		for (StockEquity stockEquity : stockEquities.getStockEquity()) {
			importer.importStockEquity(stockEquity);
		}
    	tx.commit(); 
    	String getAllStocks = "select s from StockEntity s WHERE s.code = 'WOW'";
        List<StockEntity> stocks = em.createQuery(getAllStocks,StockEntity.class).getResultList(); 
        assertEquals(1, stocks.size());
        StockEntity stock = stocks.get(0);
        assertNotNull(stock.getBusinessSummary());
        assertEquals(1, stock.getDividendHistory().size());
        assertEquals(1, stock.getStockSplits().size());
        assertEquals(1, stock.getTreasuryHeldStockHistory().size());
    }

}

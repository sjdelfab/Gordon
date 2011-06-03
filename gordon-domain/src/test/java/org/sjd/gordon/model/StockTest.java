package org.sjd.gordon.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.junit.Test;

public class StockTest extends AbstractJpaTest {

    @Test 
    public void should_create_a_stock() throws Exception { 
    	StockEntity stock = createStock();
    	Exchange exchange = stock.getExchange();
    	
    	tx.begin(); 
        em.persist(exchange);
        em.persist(stock); 
        tx.commit(); 
        assertNotNull("ID should not be null", stock.getId()); 
 
        // Retrieves all the stocks from the database
        String getAllStocks = "select s from StockEntity s WHERE s.id = " + stock.getId();
        List<StockEntity> stocks = em.createQuery(getAllStocks,StockEntity.class).getResultList(); 
        assertEquals(1, stocks.size()); 
    }

    @Test 
    public void should_create_a_stock_and_day_trade() throws Exception { 
    	StockEntity stock = createStock();
    	Exchange exchange = stock.getExchange();
    	
    	tx.begin(); 
        em.persist(exchange);
        em.persist(stock); 
        tx.commit(); 
        assertNotNull("ID should not be null", stock.getId()); 
 
        StockDayTradeRecord tradeRecord = new StockDayTradeRecord();
        tradeRecord.setClosePrice(new BigDecimal("10.0"));
        tradeRecord.setDate(new Date());
        tradeRecord.setHighPrice(new BigDecimal("11.0"));
        tradeRecord.setLowPrice(new BigDecimal("9.0"));
        tradeRecord.setOpenPrice(new BigDecimal("9.0"));
        tradeRecord.setStockId(stock.getId());
        tradeRecord.setVolume(123l);
        
        tx.begin(); 
        em.persist(tradeRecord);
        tx.commit(); 
        assertNotNull("ID should not be null", tradeRecord.getId()); 
        
        // Retrieves all the trades from the database
        String getAllTrades = "select t from StockDayTradeRecord t where t.stockId=" + stock.getId();
        List<StockDayTradeRecord> trades = em.createQuery(getAllTrades,StockDayTradeRecord.class).getResultList(); 
        assertEquals(1, trades.size()); 
    }
    
    @Test 
    public void should_create_a_stock_and_business_summary() throws Exception { 
    	StockEntity stock = createStock();
    	Exchange exchange = stock.getExchange();
    	
    	tx.begin(); 
        em.persist(exchange);
        em.persist(stock); 
        tx.commit(); 
        assertNotNull("ID should not be null", stock.getId());
        
        BusinessSummary summary = new BusinessSummary();
        summary.setSummary("Business summary blah blah");
        stock.setBusinessSummary(summary);
        
        tx.begin();
        em.persist(stock); 
        em.persist(summary);
        tx.commit(); 
        assertNotNull("ID should not be null", summary.getId());
        
        // Retrieve the summary from the database
        String getSummary = "select bs from BusinessSummary bs";
        List<BusinessSummary> summaries = em.createQuery(getSummary,BusinessSummary.class).getResultList(); 
        assertEquals(1, summaries.size()); 
    }
    
    @Test 
    public void should_create_a_stock_with_dividend() throws Exception { 
    	StockEntity stock = createStock();
    	Exchange exchange = stock.getExchange();
    	
    	tx.begin(); 
        em.persist(exchange);
        em.persist(stock); 
        tx.commit(); 
        assertNotNull("ID should not be null", stock.getId());
        
        Dividend newDividend = new Dividend();
        newDividend.setAmount(new BigDecimal("0.65"));
        newDividend.setAnnouncementDate(new Date());
        newDividend.setDate(new Date());
        stock.addDividend(newDividend);
        
        tx.begin();
        em.merge(stock);
        tx.commit(); 
        assertNotNull("ID should not be null", stock.getDividendHistory().get(0).getId());
        
        // Retrieve the Dividend from the database
        String getDividends = "select d from Dividend d";
        List<Dividend> dividends = em.createQuery(getDividends,Dividend.class).getResultList(); 
        assertEquals(1, dividends.size());
        
        // Now remove
        Dividend d = stock.getDividendHistory().remove(0);
        tx.begin();
        em.remove(d);
        tx.commit();
        dividends = em.createQuery(getDividends,Dividend.class).getResultList(); 
        assertEquals(0, dividends.size());
        
        String getAllStocks = "select s from StockEntity s WHERE s.id = " + stock.getId();
        List<StockEntity> stocks = em.createQuery(getAllStocks,StockEntity.class).getResultList(); 
        assertEquals(0, stocks.get(0).getDividendHistory().size()); 
    }
}

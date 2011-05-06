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
        String getAllStocks = "select s from StockEntity s";
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
        
        // Retrieves all the books from the database
        String getAllTrades = "select t from StockDayTradeRecord t where t.stockId=" + stock.getId();
        List<StockDayTradeRecord> trades = em.createQuery(getAllTrades,StockDayTradeRecord.class).getResultList(); 
        assertEquals(1, trades.size()); 
    }
    

}

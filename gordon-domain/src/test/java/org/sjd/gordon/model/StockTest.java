package org.sjd.gordon.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

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
        tradeRecord.setClosePrice(10.0d);
        tradeRecord.setDate(new Date());
        tradeRecord.setHighPrice(11.0d);
        tradeRecord.setLowPrice(9.0d);
        tradeRecord.setOpenPrice(9.0d);
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

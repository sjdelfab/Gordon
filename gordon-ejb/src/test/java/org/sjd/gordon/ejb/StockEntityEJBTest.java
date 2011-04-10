package org.sjd.gordon.ejb;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Date;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.sjd.gordon.model.Exchange;
import org.sjd.gordon.model.StockDayTradeRecord;
import org.sjd.gordon.model.StockEntity;

public class StockEntityEJBTest extends AbstractEJBTest {
 
	private static Exchange exchange;
	
	@BeforeClass 
	public static void createExchange() throws Exception {
		initContainer();
		ExchangeEJB exchangeEJB = (ExchangeEJB) ctx.lookup("java:global/classes/ExchangeEJB"); 
   	 
    	exchange = new Exchange();
    	exchange.setActive(true);
    	exchange.setCode("ASX");
    	exchange.setName("Australian Exchange");
        exchange = exchangeEJB.createExchange(exchange); 
	}
	
    @Test 
    public void create_a_stock() throws Exception {
    	StockEntity stock = new StockEntity();
    	stock.setCode("BHP");
    	stock.setExchange(exchange);
    	stock.setLastTradeDate(new Date());
    	stock.setListDate(new Date());
    	stock.setName("BHP Ltd");
    	
    	StockEntityEJB stockEntityEjb = (StockEntityEJB) ctx.lookup("java:global/classes/StockEntityEJB");
    	stock = stockEntityEjb.createStock(stock);
    	assertNotNull("ID should not be null", stock.getId());
    	
    	stock = stockEntityEjb.findStockById(stock.getId());
    	assertNotNull("Stock should not be null", stock);
    	
    	stock.setCode("DEF");
    	stock = stockEntityEjb.updateStock(stock);
    	assertEquals("Must have changed code.","DEF",stock.getCode());
    	
    	StockEntity bhpStock = stock;
    	
    	stock = new StockEntity();
    	stock.setCode("ANZ");
    	stock.setExchange(exchange);
    	stock.setLastTradeDate(new Date());
    	stock.setListDate(new Date());
    	stock.setName("ANZ Ltd");
    	stockEntityEjb.createStock(stock);
    	
    	List<StockEntity> stocks = stockEntityEjb.getStocks(exchange);
    	assertEquals("Must have 2 stocks",2,stocks.size());
    	
    	stockEntityEjb.deleteStock(bhpStock);
    	stocks = stockEntityEjb.getStocks(exchange);
    	assertEquals("Must have 1 stocks",1,stocks.size());
    	
    	StockDayTradeRecord dayTrade = new StockDayTradeRecord();
    	dayTrade.setClosePrice(10.0);
    	dayTrade.setDate(new Date());
    	dayTrade.setHighPrice(11.0);
    	dayTrade.setLowPrice(9.0);
    	dayTrade.setOpenPrice(9.0);
    	dayTrade.setStockId(stock.getId());
    	dayTrade.setVolume(1000l);
    	
    	dayTrade = stockEntityEjb.addDayTrade(dayTrade);
    	assertNotNull("ID should not be null", dayTrade.getId());
    	List<StockDayTradeRecord> dayTrades = stockEntityEjb.getDayTradeData(stock);
    	assertEquals("Must have 1 day trade",1,dayTrades.size());
    	
    	stockEntityEjb.deleteAllDayTrades(stock);
    	dayTrades = stockEntityEjb.getDayTradeData(stock);
    	assertEquals("Must have 0 day trade",0,dayTrades.size());
    }
}

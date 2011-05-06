package org.sjd.gordon.ejb;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.sjd.gordon.ejb.setup.GicsService;
import org.sjd.gordon.model.Exchange;
import org.sjd.gordon.model.GicsIndustryGroup;
import org.sjd.gordon.model.GicsSector;
import org.sjd.gordon.model.StockDayTradeRecord;
import org.sjd.gordon.model.StockEntity;

public class StockEntityEJBTest extends AbstractEJBTest {
 
	private static Exchange exchange;
	private static GicsSector sector;
	
	@BeforeClass 
	public static void createExchange() throws Exception {
		truncateDatabase();
		ExchangeService exchangeEJB = (ExchangeService) AllEjbTests.ctx.lookup("java:global/classes/ExchangeEJB!org.sjd.gordon.ejb.ExchangeService"); 
   	 
    	exchange = new Exchange();
    	exchange.setActive(true);
    	exchange.setCode("ASX");
    	exchange.setName("Australian Exchange");
        exchange = exchangeEJB.createExchange(exchange);
        
        GicsService gicsService = (GicsService) AllEjbTests.ctx.lookup("java:global/classes/GicsEJB!org.sjd.gordon.ejb.setup.GicsService");
        sector = new GicsSector();
    	sector.setActive(true);
    	sector.setCode(10);
    	sector.setName("Energy");
    	GicsIndustryGroup group = new GicsIndustryGroup();
    	group.setActive(true);
    	group.setCode(1010);
    	group.setName("Energy");
    	sector.addIndustryGroup(group);
    	sector = gicsService.createSector(sector);
	}
	
    @Test 
    public void create_a_stock() throws Exception {
    	StockEntity stock = new StockEntity();
    	stock.setCode("BHP");
    	stock.setExchange(exchange);
    	stock.setName("BHP Ltd");
    	stock.setGicsIndustryGroup(sector.getIndustryGroups().get(0));
    	
    	StockEntityService stockEntityEjb = (StockEntityService) AllEjbTests.ctx.lookup("java:global/classes/StockEntityEJB!org.sjd.gordon.ejb.StockEntityService");
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
    	stock.setName("ANZ Ltd");
    	stockEntityEjb.createStock(stock);
    	
    	List<StockEntity> stocks = stockEntityEjb.getStocks(exchange.getId());
    	assertEquals("Must have 2 stocks",2,stocks.size());
    	
    	stockEntityEjb.deleteStock(bhpStock);
    	stocks = stockEntityEjb.getStocks(exchange.getId());
    	assertEquals("Must have 1 stocks",1,stocks.size());
    	
    	StockDayTradeRecord dayTrade = new StockDayTradeRecord();
    	dayTrade.setClosePrice(new BigDecimal("10"));
    	dayTrade.setDate(new Date());
    	dayTrade.setHighPrice(new BigDecimal("11.0"));
    	dayTrade.setLowPrice(new BigDecimal("9.0"));
    	dayTrade.setOpenPrice(new BigDecimal("9.0"));
    	dayTrade.setStockId(stock.getId());
    	dayTrade.setVolume(1000l);
    	
    	dayTrade = stockEntityEjb.addDayTrade(dayTrade);
    	assertNotNull("ID should not be null", dayTrade.getId());
    	List<StockDayTradeRecord> dayTrades = stockEntityEjb.getDayTradeData(stock.getId());
    	assertEquals("Must have 1 day trade",1,dayTrades.size());
    	
    	stockEntityEjb.deleteAllDayTrades(stock);
    	dayTrades = stockEntityEjb.getDayTradeData(stock.getId());
    	assertEquals("Must have 0 day trade",0,dayTrades.size());
    }
    
    @Test 
    public void first_and_last_trade_day() throws Exception {
    	StockEntity stock = new StockEntity();
    	stock.setCode("ABC");
    	stock.setExchange(exchange);
    	stock.setName("ABC Ltd");
    	
    	StockEntityService stockEntityEjb = (StockEntityService) AllEjbTests.ctx.lookup("java:global/classes/StockEntityEJB!org.sjd.gordon.ejb.StockEntityService");
    	stock = stockEntityEjb.createStock(stock);
    	
    	SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
    	
    	StockDayTradeRecord dayTrade = new StockDayTradeRecord();
    	dayTrade.setClosePrice(new BigDecimal("10.0"));
    	dayTrade.setDate(format.parse("01/01/2010"));
    	dayTrade.setHighPrice(new BigDecimal("11.0"));
    	dayTrade.setLowPrice(new BigDecimal("9.0"));
    	dayTrade.setOpenPrice(new BigDecimal("9.0"));
    	dayTrade.setStockId(stock.getId());
    	dayTrade.setVolume(1000l);
    	
    	stockEntityEjb.addDayTrade(dayTrade);
    	
    	dayTrade = new StockDayTradeRecord();
    	dayTrade.setClosePrice(new BigDecimal("11.0"));
    	dayTrade.setDate(format.parse("02/01/2010"));
    	dayTrade.setHighPrice(new BigDecimal("12.0"));
    	dayTrade.setLowPrice(new BigDecimal("10.0"));
    	dayTrade.setOpenPrice(new BigDecimal("10.0"));
    	dayTrade.setStockId(stock.getId());
    	dayTrade.setVolume(2000l);
    	
    	stockEntityEjb.addDayTrade(dayTrade);
    	
    	dayTrade = new StockDayTradeRecord();
    	dayTrade.setClosePrice(new BigDecimal("12.0"));
    	dayTrade.setDate(format.parse("03/01/2010"));
    	dayTrade.setHighPrice(new BigDecimal("13.0"));
    	dayTrade.setLowPrice(new BigDecimal("11.0"));
    	dayTrade.setOpenPrice(new BigDecimal("11.0"));
    	dayTrade.setStockId(stock.getId());
    	dayTrade.setVolume(3000l);
    	
    	stockEntityEjb.addDayTrade(dayTrade);
    	
    	dayTrade = stockEntityEjb.getFirstTradeDay(stock.getId());
    	
    	assertEquals(1000l,dayTrade.getVolume());
    	
    	dayTrade = stockEntityEjb.getLastTradeDay(stock.getId());
    	
    	assertEquals(3000l,dayTrade.getVolume());
    }
}

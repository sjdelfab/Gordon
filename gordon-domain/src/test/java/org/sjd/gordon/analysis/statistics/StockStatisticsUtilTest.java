package org.sjd.gordon.analysis.statistics;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Test;
import org.sjd.gordon.model.AbstractJpaTest;
import org.sjd.gordon.model.Exchange;
import org.sjd.gordon.model.StockDayTradeRecord;
import org.sjd.gordon.model.StockEntity;

public class StockStatisticsUtilTest extends AbstractJpaTest {

	private static Long stockId;
	private static SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	
	@BeforeClass
	public static void before() throws Exception {
		initEntityManager();
		tx = em.getTransaction(); 
		StockEntity stock = createStock();
    	Exchange exchange = stock.getExchange();
    	
    	tx.begin(); 
        em.persist(exchange);
        em.persist(stock); 
        tx.commit(); 
        stockId = stock.getId(); 
        
        ArrayList<StockDayTradeRecord> trades = new ArrayList<StockDayTradeRecord>();
        StockDayTradeRecord tradeRecord = new StockDayTradeRecord();
        tradeRecord.setClosePrice(new BigDecimal("10.0"));
        tradeRecord.setDate(dateFormat.parse("01/01/2011"));
        tradeRecord.setHighPrice(new BigDecimal("11.0"));
        tradeRecord.setLowPrice(new BigDecimal("7.0"));
        tradeRecord.setOpenPrice(new BigDecimal("8.0"));
        tradeRecord.setStockId(stock.getId());
        tradeRecord.setVolume(1l);
        trades.add(tradeRecord);
        
        tradeRecord = new StockDayTradeRecord();
        tradeRecord.setClosePrice(new BigDecimal("11.0"));
        tradeRecord.setDate(dateFormat.parse("02/01/2011"));
        tradeRecord.setHighPrice(new BigDecimal("12.0"));
        tradeRecord.setLowPrice(new BigDecimal("9.0"));
        tradeRecord.setOpenPrice(new BigDecimal("10.0"));
        tradeRecord.setStockId(stock.getId());
        tradeRecord.setVolume(2l);
        trades.add(tradeRecord);
        
        tradeRecord = new StockDayTradeRecord();
        tradeRecord.setClosePrice(new BigDecimal("12.0"));
        tradeRecord.setDate(dateFormat.parse("03/01/2011"));
        tradeRecord.setHighPrice(new BigDecimal("13.0"));
        tradeRecord.setLowPrice(new BigDecimal("10.0"));
        tradeRecord.setOpenPrice(new BigDecimal("11.0"));
        tradeRecord.setStockId(stock.getId());
        tradeRecord.setVolume(3l);
        trades.add(tradeRecord);

        tx.begin();
        for(StockDayTradeRecord tradeDay: trades) {
            em.persist(tradeDay);
        }
        tx.commit(); 
 
	}
	
	@Test 
    public void get_max_price() throws Exception { 
		BigDecimal max = StockStatisticsUtil.getMaxPrice(em, stockId, dateFormat.parse("01/01/2011"), dateFormat.parse("03/01/2011"));
		assertEquals(new BigDecimal("13"), max);
	}
	
	@Test 
    public void get_min_price() throws Exception { 
		BigDecimal min = StockStatisticsUtil.getMinPrice(em, stockId, dateFormat.parse("01/01/2011"), dateFormat.parse("03/01/2011"));
		assertEquals(new BigDecimal("7"), min);
	}
	
	@Test 
    public void get_average_volume() throws Exception { 
		Double averageVolume = StockStatisticsUtil.getAverageVolume(em, stockId, dateFormat.parse("01/01/2011"), dateFormat.parse("03/01/2011"));
		assertEquals(Double.valueOf(2), averageVolume);
	}

	@Test 
    public void get_percentage_change() throws Exception { 
		BigDecimal percChange = StockStatisticsUtil.getPercentageChange(em, stockId, dateFormat.parse("01/01/2010"), dateFormat.parse("04/01/2011"));
		assertEquals(new BigDecimal("0.278"), percChange);
	}

	@Test 
    public void get_average_price() throws Exception { 
		Double averagePrice = StockStatisticsUtil.getAveragePrice(em, stockId, dateFormat.parse("01/01/2010"), dateFormat.parse("04/01/2011"));
		assertEquals(Double.valueOf("11"), averagePrice);
	}

}

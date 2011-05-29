package org.sjd.gordon.ejb;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import org.sjd.gordon.model.BusinessSummary;
import org.sjd.gordon.model.Dividend;
import org.sjd.gordon.model.StockDayTradeRecord;
import org.sjd.gordon.model.StockEntity;
import org.sjd.gordon.model.StockSplit;
import org.sjd.gordon.model.TreasuryHeldStock;

@Local
public interface StockEntityService {

    public StockEntity findStockById(Long id);
    public StockEntity createStock(StockEntity stock);
    public StockEntity updateStock(StockEntity stock);
    public void deleteStock(StockEntity stock);
    public List<StockEntity> getStocks(Integer exchangeId);
    public List<StockEntity> getStocks(Integer exchangeId, Integer offset, Integer pageSize);
    public List<StockDayTradeRecord> getDayTradeData(Long stockId);
    public StockDayTradeRecord addDayTrade(StockDayTradeRecord trade);
    public void deleteAllDayTrades(StockEntity stock);
    public StockDayTradeRecord getFirstTradeDay(Long stockId);
    public StockDayTradeRecord getLastTradeDay(Long stockId);
    public int getStockCount(Integer exchangeId);
    public BusinessSummary getBusinessSummary(Long stockId);
    public BusinessSummary updateBusinessSummary(BusinessSummary summary);
    public BusinessSummary addBusinessSummary(BusinessSummary newBusinessSummary);
    public BigDecimal getMaxPrice(Long stockId, Date startDate, Date endDate);
    public BigDecimal getMinPrice(Long stockId, Date startDate, Date endDate);
    public Double getAverageVolume(Long stockId, Date startDate, Date endDate);
    public BigDecimal getPercentageChange(Long stockId, Date startDate, Date endDate);
    public Double getAveragePrice(Long stockId, Date startDate, Date endDate);
    public Long getSharesOutstanding(StockEntity stock);
    
    public List<StockSplit> getStockSplits(Long stockId);
    public List<TreasuryHeldStock> getTreasuryHeldStockHistory(Long stockId);
    public List<Dividend> getDividendHistory(Long stockId);
    public StockSplit findStockSplitById(Long id);
    public void delete(StockSplit stockSplit);
    public TreasuryHeldStock findTreasuryHeldStockById(Long id);
    public void delete(TreasuryHeldStock heldStock);
    public Dividend findDividendById(Long id);
    public void delete(Dividend dividend);
	public StockSplit createStockSplit(StockSplit newSplit);
	public StockSplit updateStockSplit(StockSplit stockSplit);
	public TreasuryHeldStock createTreasuryHeldStock(TreasuryHeldStock stockHeld);
	public TreasuryHeldStock updateTreasuryHeldStock(TreasuryHeldStock stockHeld);
	public Dividend createDividend(Dividend newDividend);
	public Dividend updateDividend(Dividend dividend);
    
}

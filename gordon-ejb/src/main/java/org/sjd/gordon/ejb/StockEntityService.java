package org.sjd.gordon.ejb;

import java.util.List;

import javax.ejb.Local;

import org.sjd.gordon.model.StockDayTradeRecord;
import org.sjd.gordon.model.StockEntity;

@Local
public interface StockEntityService {

    public StockEntity findStockById(Long id);
    public StockEntity createStock(StockEntity stock);
    public StockEntity updateStock(StockEntity stock);
    public void deleteStock(StockEntity stock);
    public List<StockEntity> getStocks(Integer exchangeId);
    public List<StockDayTradeRecord> getDayTradeData(Long stockId);
    public StockDayTradeRecord addDayTrade(StockDayTradeRecord trade);
    public void deleteAllDayTrades(StockEntity stock);
    public StockDayTradeRecord getFirstTradeDay(Long stockId);
    public StockDayTradeRecord getLastTradeDay(Long stockId);
}

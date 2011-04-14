package org.sjd.gordon.dao;

import java.util.Date;
import java.util.List;

import org.sjd.gordon.model.StockDayTradeRecord;
import org.sjd.gordon.model.StockEntity;


public interface SecurityHistoryDAO {
    
    public List<StockDayTradeRecord> getAllDayTrades(StockEntity security) throws SecurityHistoryDataException;
    
    public List<StockDayTradeRecord> getDayTrades(StockEntity security, Date startDate, Date endDate) throws SecurityHistoryDataException;
    
    public StockDayTradeRecord getLastDayTrade(StockEntity security) throws SecurityHistoryDataException;
    
    public void appendDayTrades(StockEntity security, List<StockDayTradeRecord> newDayTrades) throws SecurityHistoryDataException;
    
    public void createStockHistory(StockEntity security, List<StockDayTradeRecord> data) throws SecurityHistoryDataException;
    
    public void delete(StockEntity security) throws SecurityHistoryDataException;
    
}

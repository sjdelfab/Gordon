package org.sjd.gordon.dao;

import java.util.Date;
import java.util.List;

import org.sjd.gordon.model.Exchange;
import org.sjd.gordon.model.StockEntity;

public interface SecurityRegistryDAO {

    public int insertRegistryEntry(StockEntity entry) throws SecurityRegistryDAOException;
    
    public List<StockEntity> getRegistrations(Exchange exchange) throws SecurityRegistryDAOException;
    
    public List<StockEntity> getStocksSortedByCode(Exchange exchange) throws SecurityRegistryDAOException;
    
    public List<StockEntity> getStocksSortedByName(Exchange exchange) throws SecurityRegistryDAOException;
    
    public boolean exists(String code, Exchange exchange) throws SecurityRegistryDAOException;
    
    public void updateLastTradeDate(StockEntity stock, Date date) throws SecurityRegistryDAOException;
    
    public Date getLastTradeDate(StockEntity stock) throws SecurityRegistryDAOException;
    
    public Date getListDate(StockEntity stock) throws SecurityRegistryDAOException;
    
    public List<Exchange> getExchanges() throws SecurityRegistryDAOException;
    
    public Exchange getExchange(String code) throws SecurityRegistryDAOException;
    
    public StockEntity getSecurity(String code, String exchangeCode) throws SecurityRegistryDAOException;
    
    public StockEntity insertRegistryEntry(String code, String name, Exchange exchangeCode, 
                                   boolean active, Date listDate, Date lastTradeDate) throws SecurityRegistryDAOException;
    
    public void delete(StockEntity entry) throws SecurityRegistryDAOException;
    
    public void update(StockEntity entry) throws SecurityRegistryDAOException;
    
}

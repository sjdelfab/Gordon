package org.sjd.gordon.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.sjd.gordon.model.Exchange;
import org.sjd.gordon.model.StockDayTradeRecord;
import org.sjd.gordon.model.StockEntity;

@Stateless
public class StockEntityEJB {

	@PersistenceContext(unitName = "gordon") 
    private EntityManager em; 
 
    public StockEntity findStockById(Long id) { 
        return em.find(StockEntity.class, id); 
    } 
 
    public StockEntity createStock(StockEntity stock) { 
        em.persist(stock); 
        return stock; 
    }
    
    public StockEntity updateStock(StockEntity stock) {
    	return em.merge(stock);
    }
    
    public void deleteStock(StockEntity stock) {
    	deleteAllDayTrades(stock);
    	// TODO Delete unitary data
    	// TODO Delete tabular datasets
    	em.remove(em.merge(stock));
    }
    
    public List<StockEntity> getStocks(Exchange exchange) {
    	String getStockByExchange = "SELECT s FROM StockEntity s WHERE s.exchange.id = :id";
    	TypedQuery<StockEntity> query = em.createQuery(getStockByExchange, StockEntity.class);
    	query.setParameter("id", exchange.getId());
    	return query.getResultList();
    }

    public List<StockDayTradeRecord> getDayTradeData(StockEntity stock) {
    	String getDayTradeData = "SELECT t FROM StockDayTradeRecord t WHERE t.stockId = :stockId";
    	TypedQuery<StockDayTradeRecord> query = em.createQuery(getDayTradeData, StockDayTradeRecord.class);
    	query.setParameter("stockId", stock.getId());
    	return query.getResultList();
    }
    
    public StockDayTradeRecord addDayTrade(StockDayTradeRecord trade) {
    	em.persist(trade);
    	return trade;
    }
    
    public void deleteAllDayTrades(StockEntity stock) {
    	String deleteAllDayTrades = "DELETE FROM StockDayTradeRecord t WHERE t.stockId = :stockId";
    	Query query = em.createQuery(deleteAllDayTrades);
    	query.setParameter("stockId", stock.getId());
    	query.executeUpdate();
    }
}

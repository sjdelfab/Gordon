package org.sjd.gordon.services;

import java.util.Collections;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.sjd.gordon.model.BusinessSummary;
import org.sjd.gordon.model.Dividend;
import org.sjd.gordon.model.StockDayTradeRecord;
import org.sjd.gordon.model.StockEntity;
import org.sjd.gordon.model.StockSplit;
import org.sjd.gordon.model.TreasuryHeldStock;

public abstract class AbstractStockEntityService implements StockEntityService {

	abstract protected EntityManager getEntityManager();
	
    public StockEntity findStockById(Long id) {
        return getEntityManager().find(StockEntity.class, id); 
    } 
 
    public StockEntity createStock(StockEntity stock) { 
    	getEntityManager().persist(stock); 
        return stock; 
    }
    
    public StockEntity updateStock(StockEntity stock) {
    	return getEntityManager().merge(stock);
    }
    
    public void deleteStock(StockEntity stock) {
    	deleteAllDayTrades(stock);
    	// TODO Delete unitary data
    	// TODO Delete tabular datasets
    	getEntityManager().remove(getEntityManager().merge(stock));
    }
    
    public List<StockEntity> getStocks(Integer exchangeId) {
    	String getStockByExchange = "SELECT s FROM StockEntity s WHERE s.exchange.id = :id";
    	TypedQuery<StockEntity> query = getEntityManager().createQuery(getStockByExchange, StockEntity.class);
    	query.setParameter("id", exchangeId);
    	return query.getResultList();
    }

    public List<StockEntity> getStocks(Integer exchangeId, Integer offset, Integer pageSize) {
    	String getStockByExchange = "SELECT s FROM StockEntity s WHERE s.exchange.id = :id ORDER BY s.name";
    	TypedQuery<StockEntity> query = getEntityManager().createQuery(getStockByExchange, StockEntity.class).setFirstResult(offset).setMaxResults(pageSize);
    	query.setParameter("id", exchangeId);
    	return query.getResultList();
    }
    
    public int getStockCount(Integer exchangeId) {
    	String getStockByExchange = "SELECT COUNT(s) FROM StockEntity s WHERE s.exchange.id = :id";
    	TypedQuery<Long> query = getEntityManager().createQuery(getStockByExchange, Long.class);
    	query.setParameter("id", exchangeId);
    	Long count = query.getSingleResult();
    	return count.intValue();
    }
    
    
    public List<StockDayTradeRecord> getDayTradeData(Long stockId) {
    	String getDayTradeData = "SELECT t FROM StockDayTradeRecord t WHERE t.stockId = :stockId";
    	TypedQuery<StockDayTradeRecord> query = getEntityManager().createQuery(getDayTradeData, StockDayTradeRecord.class);
    	query.setParameter("stockId", stockId);
    	return query.getResultList();
    }
    
    public StockDayTradeRecord addDayTrade(StockDayTradeRecord trade) {
    	getEntityManager().persist(trade);
    	return trade;
    }
    
    public void deleteAllDayTrades(StockEntity stock) {
    	String deleteAllDayTrades = "DELETE FROM StockDayTradeRecord t WHERE t.stockId = :stockId";
    	Query query = getEntityManager().createQuery(deleteAllDayTrades);
    	query.setParameter("stockId", stock.getId());
    	query.executeUpdate();
    }

	@Override
	public StockDayTradeRecord getFirstTradeDay(Long stockId) {
		String getFirstTradeDay = "SELECT t FROM StockDayTradeRecord t WHERE t.id = " +
				"(SELECT MIN(s.id) FROM StockDayTradeRecord s WHERE s.stockId = :stockId)";
    	TypedQuery<StockDayTradeRecord> query = getEntityManager().createQuery(getFirstTradeDay, StockDayTradeRecord.class);
    	query.setParameter("stockId", stockId);
    	List<StockDayTradeRecord> result = query.getResultList();
    	if (result.isEmpty()) {
		    return null;
    	} else {
    		return result.get(0);
    	}
	}

	@Override
	public StockDayTradeRecord getLastTradeDay(Long stockId) {
		String getFirstTradeDay = "SELECT t FROM StockDayTradeRecord t WHERE t.id = "
				+ "(SELECT MAX(s.id) FROM StockDayTradeRecord s WHERE s.stockId = :stockId)";
		TypedQuery<StockDayTradeRecord> query = getEntityManager().createQuery(getFirstTradeDay, StockDayTradeRecord.class);
		query.setParameter("stockId", stockId);
		List<StockDayTradeRecord> result = query.getResultList();
		if (result.isEmpty()) {
			return null;
		} else {
			return result.get(0);
		}
	}
	
	@Override
	public BusinessSummary updateBusinessSummary(BusinessSummary summary) {
		return getEntityManager().merge(summary);
	}
	
	@Override
	public BusinessSummary addBusinessSummary(BusinessSummary newBusinessSummary) {
		getEntityManager().persist(newBusinessSummary);
		return newBusinessSummary;
	}

	@Override
	public List<StockSplit> getStockSplits(Long stockId) {
		StockEntity stock = findStockById(stockId);
		List<StockSplit> stockSplits = stock.getStockSplits();
		if (stockSplits == null) {
			return Collections.emptyList();
		}
		return stockSplits;
	}

	@Override
	public List<TreasuryHeldStock> getTreasuryHeldStockHistory(Long stockId) {
		StockEntity stock = findStockById(stockId);
		List<TreasuryHeldStock> heldStockHistory = stock.getTreasuryHeldStockHistory();
		if (heldStockHistory == null) {
			return Collections.emptyList();
		}
		return heldStockHistory;
	}

	@Override
	public List<Dividend> getDividendHistory(Long stockId) {
		StockEntity stock = findStockById(stockId);
		List<Dividend> dividendHistory = stock.getDividendHistory();
		if (dividendHistory == null) {
			return Collections.emptyList();
		}
		return dividendHistory;
	}

	@Override
	public StockSplit findStockSplitById(Long id) {
		return getEntityManager().find(StockSplit.class,id);
	}

	@Override
	public void delete(StockSplit stockSplit) {
		getEntityManager().remove(getEntityManager().merge(stockSplit));
	}

	@Override
	public TreasuryHeldStock findTreasuryHeldStockById(Long id) {
		return getEntityManager().find(TreasuryHeldStock.class,id);
	}

	@Override
	public void delete(TreasuryHeldStock heldStock) {
		getEntityManager().remove(getEntityManager().merge(heldStock));
	}

	@Override
	public Dividend findDividendById(Long id) {
		return getEntityManager().find(Dividend.class,id);
	}

	@Override
	public void delete(Dividend dividend) {
		getEntityManager().remove(getEntityManager().merge(dividend));
	}

	@Override
	public StockSplit createStockSplit(Long stockId, StockSplit newSplit) {
		StockEntity stock = findStockById(stockId);
		stock.addStockSplit(newSplit);
		getEntityManager().merge(stock);
        return stock.getLastStockSplitInList(); 
	}

	@Override
	public StockSplit updateStockSplit(StockSplit stockSplit) {
		return getEntityManager().merge(stockSplit);
	}

	@Override
	public TreasuryHeldStock createTreasuryHeldStock(Long stockId, TreasuryHeldStock stockHeld) {
		StockEntity stock = findStockById(stockId);
		stock.addHeldStock(stockHeld);
		getEntityManager().merge(stock);
        return stock.getLastTreasuryHeldStockInList();
	}

	@Override
	public TreasuryHeldStock updateTreasuryHeldStock(TreasuryHeldStock stockHeld) {
		return getEntityManager().merge(stockHeld);
	}

	@Override
	public Dividend createDividend(Long stockId, Dividend newDividend) {
		StockEntity stock = findStockById(stockId);
		stock.addDividend(newDividend);
		getEntityManager().merge(stock);
        return stock.getLastDividendInList();
	}

	@Override
	public Dividend updateDividend(Dividend dividend) {
		return getEntityManager().merge(dividend);
	}

	@Override
	public StockEntity getStock(Integer exchangeId, String symbol) {
		String getStockByExchange = "SELECT s FROM StockEntity s WHERE s.exchange.id = :id AND s.code = :code";
    	TypedQuery<StockEntity> query = getEntityManager().createQuery(getStockByExchange, StockEntity.class);
    	query.setParameter("id", exchangeId);
    	query.setParameter("code",symbol);
    	List<StockEntity> stocks = query.getResultList();
    	if (stocks.isEmpty()) {
    		return null;
    	}
    	return stocks.get(0);
	}
}

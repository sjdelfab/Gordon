package org.sjd.gordon.ejb;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.sjd.gordon.analysis.statistics.StockStatisticsUtil;
import org.sjd.gordon.model.BusinessSummary;
import org.sjd.gordon.model.Dividend;
import org.sjd.gordon.model.StockDayTradeRecord;
import org.sjd.gordon.model.StockEntity;
import org.sjd.gordon.model.StockSplit;
import org.sjd.gordon.model.TreasuryHeldStock;

@Stateless
public class StockEntityEJB implements StockEntityService {

	@PersistenceContext 
    private EntityManager em; 
 
    public StockEntity findStockById(Long id) {
        return em.find(StockEntity.class, id); 
    } 
 
    @RolesAllowed({"ADMIN"})
    public StockEntity createStock(StockEntity stock) { 
        em.persist(stock); 
        return stock; 
    }
    
    @RolesAllowed({"ADMIN"})
    public StockEntity updateStock(StockEntity stock) {
    	return em.merge(stock);
    }
    
    @RolesAllowed({"ADMIN"})
    public void deleteStock(StockEntity stock) {
    	deleteAllDayTrades(stock);
    	// TODO Delete unitary data
    	// TODO Delete tabular datasets
    	em.remove(em.merge(stock));
    }
    
    public List<StockEntity> getStocks(Integer exchangeId) {
    	String getStockByExchange = "SELECT s FROM StockEntity s WHERE s.exchange.id = :id";
    	TypedQuery<StockEntity> query = em.createQuery(getStockByExchange, StockEntity.class);
    	query.setParameter("id", exchangeId);
    	return query.getResultList();
    }

    public List<StockEntity> getStocks(Integer exchangeId, Integer offset, Integer pageSize) {
    	String getStockByExchange = "SELECT s FROM StockEntity s WHERE s.exchange.id = :id ORDER BY s.name";
    	TypedQuery<StockEntity> query = em.createQuery(getStockByExchange, StockEntity.class).setFirstResult(offset).setMaxResults(pageSize);
    	query.setParameter("id", exchangeId);
    	return query.getResultList();
    }
    
    public int getStockCount(Integer exchangeId) {
    	String getStockByExchange = "SELECT COUNT(s) FROM StockEntity s WHERE s.exchange.id = :id";
    	TypedQuery<Long> query = em.createQuery(getStockByExchange, Long.class);
    	query.setParameter("id", exchangeId);
    	Long count = query.getSingleResult();
    	return count.intValue();
    }
    
    
    public List<StockDayTradeRecord> getDayTradeData(Long stockId) {
    	String getDayTradeData = "SELECT t FROM StockDayTradeRecord t WHERE t.stockId = :stockId";
    	TypedQuery<StockDayTradeRecord> query = em.createQuery(getDayTradeData, StockDayTradeRecord.class);
    	query.setParameter("stockId", stockId);
    	return query.getResultList();
    }
    
    public StockDayTradeRecord addDayTrade(StockDayTradeRecord trade) {
    	em.persist(trade);
    	return trade;
    }
    
    @RolesAllowed({"ADMIN"})
    public void deleteAllDayTrades(StockEntity stock) {
    	String deleteAllDayTrades = "DELETE FROM StockDayTradeRecord t WHERE t.stockId = :stockId";
    	Query query = em.createQuery(deleteAllDayTrades);
    	query.setParameter("stockId", stock.getId());
    	query.executeUpdate();
    }

	@Override
	public StockDayTradeRecord getFirstTradeDay(Long stockId) {
		String getFirstTradeDay = "SELECT t FROM StockDayTradeRecord t WHERE t.id = " +
				"(SELECT MIN(s.id) FROM StockDayTradeRecord s WHERE s.stockId = :stockId)";
    	TypedQuery<StockDayTradeRecord> query = em.createQuery(getFirstTradeDay, StockDayTradeRecord.class);
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
		TypedQuery<StockDayTradeRecord> query = em.createQuery(getFirstTradeDay, StockDayTradeRecord.class);
		query.setParameter("stockId", stockId);
		List<StockDayTradeRecord> result = query.getResultList();
		if (result.isEmpty()) {
			return null;
		} else {
			return result.get(0);
		}
	}
	
	@Override
	public BusinessSummary getBusinessSummary(Long stockId) {
		String getSummary = "SELECT bs FROM BusinessSummary bs WHERE bs.stockId=:stockId";
		TypedQuery<BusinessSummary> query = em.createQuery(getSummary, BusinessSummary.class);
		query.setParameter("stockId", stockId);
		List<BusinessSummary> summaries = query.getResultList();
		if (summaries.isEmpty()) {
			return null;
		}
		return summaries.get(0);
	}
	
	@Override
	public BusinessSummary updateBusinessSummary(BusinessSummary summary) {
		return em.merge(summary);
	}
	
	@Override
	public BusinessSummary addBusinessSummary(BusinessSummary newBusinessSummary) {
		em.persist(newBusinessSummary);
		return newBusinessSummary;
	}
	
	@Override
    public BigDecimal getMaxPrice(Long stockId, Date startDate, Date endDate) {
    	return StockStatisticsUtil.getMaxPrice(em, stockId, startDate, endDate);
    }
    
	@Override
    public BigDecimal getMinPrice(Long stockId, Date startDate, Date endDate) {
    	return StockStatisticsUtil.getMinPrice(em, stockId, startDate, endDate);
    }
    
	@Override
    public Double getAverageVolume(Long stockId, Date startDate, Date endDate) {
    	return StockStatisticsUtil.getAverageVolume(em, stockId, startDate, endDate);
    }
    
	@Override
    public BigDecimal getPercentageChange(Long stockId, Date startDate, Date endDate) {
    	return StockStatisticsUtil.getPercentageChange(em, stockId, startDate, endDate);
    }
    
	@Override
    public Double getAveragePrice(Long stockId, Date startDate, Date endDate) {
    	return StockStatisticsUtil.getAveragePrice(em, stockId, startDate, endDate);
    }
    
	@Override
    public Long getSharesOutstanding(StockEntity stock) {
    	return StockStatisticsUtil.calculateSharesOutstanding(em, stock);
    }

	@Override
	public List<StockSplit> getStockSplits(Long stockId) {
		String getStockSplits = "SELECT s FROM StockSplit s WHERE s.stockId = :stockId";
		TypedQuery<StockSplit> query = em.createQuery(getStockSplits, StockSplit.class);
		query.setParameter("stockId", stockId);
		return query.getResultList();
	}

	@Override
	public List<TreasuryHeldStock> getTreasuryHeldStockHistory(Long stockId) {
		String getTreasuryHeldStock = "SELECT t FROM TreasuryHeldStock t WHERE t.stockId = :stockId";
		TypedQuery<TreasuryHeldStock> query = em.createQuery(getTreasuryHeldStock, TreasuryHeldStock.class);
		query.setParameter("stockId", stockId);
		return query.getResultList();
	}

	@Override
	public List<Dividend> getDividendHistory(Long stockId) {
		String getDividends = "SELECT d FROM Dividend d WHERE d.stockId = :stockId";
		TypedQuery<Dividend> query = em.createQuery(getDividends, Dividend.class);
		query.setParameter("stockId", stockId);
		return query.getResultList();
	}

	@Override
	public StockSplit findStockSplitById(Long id) {
		return em.find(StockSplit.class,id);
	}

	@Override
	public void delete(StockSplit stockSplit) {
		em.remove(em.merge(stockSplit));
	}

	@Override
	public TreasuryHeldStock findTreasuryHeldStockById(Long id) {
		return em.find(TreasuryHeldStock.class,id);
	}

	@Override
	public void delete(TreasuryHeldStock heldStock) {
		em.remove(em.merge(heldStock));
	}

	@Override
	public Dividend findDividendById(Long id) {
		return em.find(Dividend.class,id);
	}

	@Override
	public void delete(Dividend dividend) {
		em.remove(em.merge(dividend));
	}

	@Override
	public StockSplit createStockSplit(StockSplit newSplit) {
		em.persist(newSplit); 
        return newSplit; 
	}

	@Override
	public StockSplit updateStockSplit(StockSplit stockSplit) {
		return em.merge(stockSplit);
	}

	@Override
	public TreasuryHeldStock createTreasuryHeldStock(TreasuryHeldStock stockHeld) {
		em.persist(stockHeld); 
        return stockHeld;
	}

	@Override
	public TreasuryHeldStock updateTreasuryHeldStock(TreasuryHeldStock stockHeld) {
		return em.merge(stockHeld);
	}

	@Override
	public Dividend createDividend(Dividend newDividend) {
		em.persist(newDividend); 
        return newDividend;
	}

	@Override
	public Dividend updateDividend(Dividend dividend) {
		return em.merge(dividend);
	}
}

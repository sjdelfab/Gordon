package org.sjd.gordon.analysis.statistics;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.sjd.gordon.model.StockDayTradeRecord;
import org.sjd.gordon.model.StockEntity;
import org.sjd.gordon.model.StockSplit;
import org.sjd.gordon.model.TreasuryHeldStock;

public abstract class AbstractStockStatisticsService implements StockStatisticsService {
	
	abstract protected EntityManager getEntityManager();
	
	@Override
	public BigDecimal getMaxPrice(Long stockId, Date startDate, Date endDate) {
		String getMax = "SELECT MAX(t.highPrice) FROM StockDayTradeRecord t WHERE t.stockId = :stockId AND t.date >= :startDate AND t.date <= :endDate";
		TypedQuery<BigDecimal> query = getEntityManager().createQuery(getMax, BigDecimal.class);
		query.setParameter("stockId", stockId);
		query.setParameter("startDate", startDate);
		query.setParameter("endDate",endDate);
		return query.getSingleResult();
	}

	@Override
	public BigDecimal getMinPrice(Long stockId, Date startDate, Date endDate) {
		String getMin = "SELECT MIN(t.lowPrice) FROM StockDayTradeRecord t WHERE t.stockId = :stockId AND t.date >= :startDate AND t.date <= :endDate";
		TypedQuery<BigDecimal> query = getEntityManager().createQuery(getMin, BigDecimal.class);
		query.setParameter("stockId", stockId);
		query.setParameter("startDate", startDate);
		query.setParameter("endDate",endDate);
		return query.getSingleResult();
	}

	@Override
	public Double getAverageVolume(Long stockId, Date startDate, Date endDate) {
		String getAverage = "SELECT AVG(t.volume) FROM StockDayTradeRecord t WHERE t.stockId = :stockId AND t.date >= :startDate AND t.date <= :endDate";
		TypedQuery<Double> query = getEntityManager().createQuery(getAverage, Double.class);
		query.setParameter("stockId", stockId);
		query.setParameter("startDate", startDate);
		query.setParameter("endDate",endDate);
		return query.getSingleResult();
	}

	@Override
	public Double getAveragePrice(Long stockId, Date startDate, Date endDate) {
		String getAverage = "SELECT AVG(t.closePrice) FROM StockDayTradeRecord t WHERE t.stockId = :stockId AND t.date >= :startDate AND t.date <= :endDate";
		TypedQuery<Double> query = getEntityManager().createQuery(getAverage, Double.class);
		query.setParameter("stockId", stockId);
		query.setParameter("startDate", startDate);
		query.setParameter("endDate",endDate);
		return query.getSingleResult();
	}

	@Override
	public BigDecimal getPercentageChange(Long stockId, Date startDate, Date endDate) {
		String getStart = "SELECT t FROM StockDayTradeRecord t WHERE t.stockId = :stockId AND t.date = :startDate";
		TypedQuery<StockDayTradeRecord> query = getEntityManager().createQuery(getStart, StockDayTradeRecord.class);
		query.setParameter("stockId", stockId);
		query.setParameter("startDate", startDate);
		List<StockDayTradeRecord> firstList = query.getResultList();
		StockDayTradeRecord start = null;
		if (firstList.isEmpty()) {
			// TODO: Requires more efficient query?
			getStart = "SELECT t FROM StockDayTradeRecord t WHERE t.stockId = :stockId AND t.date = " +
					     "(SELECT MIN(sdt.date) FROM StockDayTradeRecord sdt WHERE sdt.stockId = :stockId)";
			query = getEntityManager().createQuery(getStart, StockDayTradeRecord.class);
			query.setParameter("stockId", stockId);
			start = query.getSingleResult();
		} else {
			start = firstList.get(0);
		}
		String getEnd = "SELECT t FROM StockDayTradeRecord t WHERE t.stockId = :stockId AND t.date = :endDate";
		query = getEntityManager().createQuery(getEnd, StockDayTradeRecord.class);
		query.setParameter("stockId", stockId);
		query.setParameter("endDate", endDate);
		List<StockDayTradeRecord> lastList = query.getResultList();
		StockDayTradeRecord end = null;
		if (lastList.isEmpty()) {
			// TODO: Requires more efficient query?
			getEnd = "SELECT t FROM StockDayTradeRecord t WHERE t.stockId = :stockId AND t.date = " +
					   "(SELECT MAX(sdt.date) FROM StockDayTradeRecord sdt WHERE sdt.stockId = :stockId)";
			query = getEntityManager().createQuery(getEnd, StockDayTradeRecord.class);
			query.setParameter("stockId", stockId);
			end = query.getSingleResult();
		} else {
			end = lastList.get(0);
		}
		BigDecimal two = new BigDecimal("2.0");
		BigDecimal aveStart = start.getHighPrice().add(start.getLowPrice()).divide(two);
		BigDecimal aveEnd = end.getHighPrice().add(end.getLowPrice()).divide(two);
		MathContext mc = new MathContext(3);
		return aveEnd.subtract(aveStart).divide(aveStart,mc);
	}
	
	@Override
	public Long calculateSharesOutstanding(StockEntity stock) {
		BigDecimal shareVolume = new BigDecimal(stock.getFloatVolume());
		List<StockSplit> splits = stock.getStockSplits();
		for(StockSplit split: splits) {
			shareVolume = shareVolume.multiply(split.getFactor());
		}
		List<TreasuryHeldStock> stockHeld = stock.getTreasuryHeldStockHistory();
		if (!stockHeld.isEmpty()) {
			shareVolume.subtract(new BigDecimal(stockHeld.get(stockHeld.size()-1).getVolume()));
		}
		return shareVolume.longValue();
	}
	
}

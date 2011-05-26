package org.sjd.gordon.analysis.statistics;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.sjd.gordon.model.StockDayTradeRecord;
import org.sjd.gordon.model.StockEntity;

// TODO Interface/abstract class?
public class StockStatisticsUtil {
	
	public static BigDecimal getMaxPrice(EntityManager em, Long stockId, Date startDate, Date endDate) {
		String getMax = "SELECT MAX(t.highPrice) FROM StockDayTradeRecord t WHERE t.stockId = :stockId AND t.date >= :startDate AND t.date <= :endDate";
		TypedQuery<BigDecimal> query = em.createQuery(getMax, BigDecimal.class);
		query.setParameter("stockId", stockId);
		query.setParameter("startDate", startDate);
		query.setParameter("endDate",endDate);
		return query.getSingleResult();
	}

	public static BigDecimal getMinPrice(EntityManager em, Long stockId, Date startDate, Date endDate) {
		String getMin = "SELECT MIN(t.lowPrice) FROM StockDayTradeRecord t WHERE t.stockId = :stockId AND t.date >= :startDate AND t.date <= :endDate";
		TypedQuery<BigDecimal> query = em.createQuery(getMin, BigDecimal.class);
		query.setParameter("stockId", stockId);
		query.setParameter("startDate", startDate);
		query.setParameter("endDate",endDate);
		return query.getSingleResult();
	}

	public static Double getAverageVolume(EntityManager em, Long stockId, Date startDate, Date endDate) {
		String getAverage = "SELECT AVG(t.volume) FROM StockDayTradeRecord t WHERE t.stockId = :stockId AND t.date >= :startDate AND t.date <= :endDate";
		TypedQuery<Double> query = em.createQuery(getAverage, Double.class);
		query.setParameter("stockId", stockId);
		query.setParameter("startDate", startDate);
		query.setParameter("endDate",endDate);
		return query.getSingleResult();
	}

	public static Double getAveragePrice(EntityManager em, Long stockId, Date startDate, Date endDate) {
		String getAverage = "SELECT AVG(t.closePrice) FROM StockDayTradeRecord t WHERE t.stockId = :stockId AND t.date >= :startDate AND t.date <= :endDate";
		TypedQuery<Double> query = em.createQuery(getAverage, Double.class);
		query.setParameter("stockId", stockId);
		query.setParameter("startDate", startDate);
		query.setParameter("endDate",endDate);
		return query.getSingleResult();
	}

	public static BigDecimal getPercentageChange(EntityManager em, Long stockId, Date startDate, Date endDate) {
		String getStart = "SELECT t FROM StockDayTradeRecord t WHERE t.stockId = :stockId AND t.date = :startDate";
		TypedQuery<StockDayTradeRecord> query = em.createQuery(getStart, StockDayTradeRecord.class);
		query.setParameter("stockId", stockId);
		query.setParameter("startDate", startDate);
		List<StockDayTradeRecord> firstList = query.getResultList();
		StockDayTradeRecord start = null;
		if (firstList.isEmpty()) {
			// TODO: Requires more efficient query?
			getStart = "SELECT t FROM StockDayTradeRecord t WHERE t.stockId = :stockId AND t.date = " +
					     "(SELECT MIN(sdt.date) FROM StockDayTradeRecord sdt WHERE sdt.stockId = :stockId)";
			query = em.createQuery(getStart, StockDayTradeRecord.class);
			query.setParameter("stockId", stockId);
			start = query.getSingleResult();
		} else {
			start = firstList.get(0);
		}
		String getEnd = "SELECT t FROM StockDayTradeRecord t WHERE t.stockId = :stockId AND t.date = :endDate";
		query = em.createQuery(getEnd, StockDayTradeRecord.class);
		query.setParameter("stockId", stockId);
		query.setParameter("endDate", endDate);
		List<StockDayTradeRecord> lastList = query.getResultList();
		StockDayTradeRecord end = null;
		if (lastList.isEmpty()) {
			// TODO: Requires more efficient query?
			getEnd = "SELECT t FROM StockDayTradeRecord t WHERE t.stockId = :stockId AND t.date = " +
					   "(SELECT MAX(sdt.date) FROM StockDayTradeRecord sdt WHERE sdt.stockId = :stockId)";
			query = em.createQuery(getEnd, StockDayTradeRecord.class);
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
	
	public static Long calculateSharesOutstanding(EntityManager em, StockEntity stock) {
		BigDecimal shareVolume = new BigDecimal(stock.getFloatVolume());
		String getSplitFactors = "SELECT s.factor FROM StockSplit s WHERE s.stockId = :stockId ORDER BY s.date";
		TypedQuery<BigDecimal> factorsQuery = em.createQuery(getSplitFactors,BigDecimal.class);
		factorsQuery.setParameter("stockId", stock.getId());
		List<BigDecimal> factors = factorsQuery.getResultList();
		for(BigDecimal factor: factors) {
			shareVolume = shareVolume.multiply(factor);
		}
		// TODO: Requires more efficient query?
		String getStockHeldInTreasury = "SELECT t.volume FROM TreasuryHeldStock t WHERE t.stockId = :stockId AND t.date = " +
				"(SELECT MAX(ths.date) FROM TreasuryHeldStock ths WHERE ths.stockId = :stockId)";
		TypedQuery<Long> stockHeldInTreasuryQuery = em.createQuery(getStockHeldInTreasury, Long.class);
		List<Long> stockHeld = stockHeldInTreasuryQuery.getResultList();
		if (!stockHeld.isEmpty()) {
			shareVolume.subtract(new BigDecimal(stockHeld.get(0)));
		}
		return shareVolume.longValue();
	}
	
}

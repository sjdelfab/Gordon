package org.sjd.gordon.analysis.statistics;

import java.math.BigDecimal;
import java.util.Date;

import org.sjd.gordon.model.StockEntity;

public interface StockStatisticsService {

	public BigDecimal getMaxPrice(Long stockId, Date startDate, Date endDate);
	public BigDecimal getMinPrice(Long stockId, Date startDate, Date endDate);
	public Double getAverageVolume(Long stockId, Date startDate, Date endDate);
	public Double getAveragePrice(Long stockId, Date startDate, Date endDate);
	public BigDecimal getPercentageChange(Long stockId, Date startDate, Date endDate);
	public Long calculateSharesOutstanding(StockEntity stock);
}

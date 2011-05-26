package org.sjd.gordon.shared.viewer;

import java.io.Serializable;
import java.math.BigDecimal;

public class StockStatistics implements Serializable {

	private static final long serialVersionUID = -1918323888695765032L;
	
	private BigDecimal percentageChange, high, low, movingAverage;
	private Integer averageVolume;
	
	public BigDecimal getPercentageChange() {
		return percentageChange;
	}
	
	public void setPercentageChange(BigDecimal percentageChange) {
		this.percentageChange = percentageChange;
	}
	
	public BigDecimal getHigh() {
		return high;
	}
	
	public void setHigh(BigDecimal high) {
		this.high = high;
	}
	
	public BigDecimal getLow() {
		return low;
	}
	
	public void setLow(BigDecimal low) {
		this.low = low;
	}
	
	public BigDecimal getMovingAverage() {
		return movingAverage;
	}
	
	public void setMovingAverage(BigDecimal movingAverage) {
		this.movingAverage = movingAverage;
	}
	
	public Integer getAverageVolume() {
		return averageVolume;
	}
	
	public void setAverageVolume(Integer volume) {
		this.averageVolume = volume;
	}
	
}

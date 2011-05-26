package org.sjd.gordon.shared.viewer;

import java.io.Serializable;

import org.sjd.gordon.model.BusinessSummary;

public class StockProfile implements Serializable {

	private static final long serialVersionUID = -7144195470566262648L;
	
	private StockDetail detail;
	private BusinessSummary businessSummary;
	private StockStatistics stockStatistics;
	private ValuationMeasures valuationMeasures;
	private Integer sharesOutstanding;
	
	public void setDetail(StockDetail detail) {
		this.detail = detail;
	}
	
	public StockDetail getDetail() {
		return detail;
	}
	
	public void setBusinessSummary(BusinessSummary businessSummary) {
		this.businessSummary = businessSummary;
	}
	
	public BusinessSummary getBusinessSummary() {
		return businessSummary;
	}
	
	public void setStockStatistics(StockStatistics stockStatistics) {
		this.stockStatistics = stockStatistics;
	}
	
	public StockStatistics getStockStatistics() {
		return stockStatistics;
	}
	
	public void setValuationMeasures(ValuationMeasures valuationMeasures) {
		this.valuationMeasures = valuationMeasures;
	}
	
	public ValuationMeasures getValuationMeasures() {
		return valuationMeasures;
	}
	
	public Integer getSharesOutstanding() {
		return sharesOutstanding;
	}
	
	public void setSharesOutstanding(Integer sharesOutstanding) {
		this.sharesOutstanding = sharesOutstanding;
	}
	
}

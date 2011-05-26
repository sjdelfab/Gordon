package org.sjd.gordon.shared.viewer;

import java.io.Serializable;
import java.math.BigDecimal;

public class ValuationMeasures implements Serializable {

	private static final long serialVersionUID = 7638230315856024816L;

	private BigDecimal marketCapitalisation, enterpriseValue, trailingPE, forwardPE, pegRatio, 
	                   priceToSalesRatio, priceToBookRatio, enterpriseValueToRevenueRatio, enterpriseValueToEBITDA;

	public BigDecimal getMarketCapitalisation() {
		return marketCapitalisation;
	}

	public void setMarketCapitalisation(BigDecimal marketCapitalisation) {
		this.marketCapitalisation = marketCapitalisation;
	}

	public BigDecimal getEnterpriseValue() {
		return enterpriseValue;
	}

	public void setEnterpriseValue(BigDecimal enterpriseValue) {
		this.enterpriseValue = enterpriseValue;
	}

	public BigDecimal getTrailingPE() {
		return trailingPE;
	}

	public void setTrailingPE(BigDecimal trailingPE) {
		this.trailingPE = trailingPE;
	}

	public BigDecimal getForwardPE() {
		return forwardPE;
	}

	public void setForwardPE(BigDecimal forwardPE) {
		this.forwardPE = forwardPE;
	}

	public BigDecimal getPegRatio() {
		return pegRatio;
	}

	public void setPegRatio(BigDecimal pegRatio) {
		this.pegRatio = pegRatio;
	}

	public BigDecimal getPriceToSalesRatio() {
		return priceToSalesRatio;
	}

	public void setPriceToSalesRatio(BigDecimal priceToSalesRatio) {
		this.priceToSalesRatio = priceToSalesRatio;
	}

	public BigDecimal getPriceToBookRatio() {
		return priceToBookRatio;
	}

	public void setPriceToBookRatio(BigDecimal priceToBookRatio) {
		this.priceToBookRatio = priceToBookRatio;
	}

	public BigDecimal getEnterpriseValueToRevenueRatio() {
		return enterpriseValueToRevenueRatio;
	}

	public void setEnterpriseValueToRevenueRatio(
			BigDecimal enterpriseValueToRevenueRatio) {
		this.enterpriseValueToRevenueRatio = enterpriseValueToRevenueRatio;
	}

	public BigDecimal getEnterpriseValueToEBITDA() {
		return enterpriseValueToEBITDA;
	}

	public void setEnterpriseValueToEBITDA(BigDecimal enterpriseValueToEBITDA) {
		this.enterpriseValueToEBITDA = enterpriseValueToEBITDA;
	}
	
}

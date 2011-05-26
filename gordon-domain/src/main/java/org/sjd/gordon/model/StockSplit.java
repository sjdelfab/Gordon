package org.sjd.gordon.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "stock_splits")
public class StockSplit implements Serializable {
	
	private static final long serialVersionUID = 7668815606662922867L;
	
	@Column(nullable = false,name="stock_id")
	private Long stockId;
	@Column(nullable = false,name="date")
	@Temporal(TemporalType.DATE)
    private Date date;
	@Column(nullable = false,name="factor")
    private BigDecimal factor;
	
	public Long getStockId() {
		return stockId;
	}
	
	public void setStockId(Long stockId) {
		this.stockId = stockId;
	}
	
	public Date getDate() {
		return date;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
	
	public BigDecimal getFactor() {
		return factor;
	}
	
	public void setFactor(BigDecimal factor) {
		this.factor = factor;
	}

}

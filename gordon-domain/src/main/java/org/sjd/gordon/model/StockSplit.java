package org.sjd.gordon.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

@Entity
@Table(name = "stock_splits")
@SequenceGenerator(schema="GORDON",name = "stockSplitIdSeqGenerator", sequenceName  = "SEQ_STOCK_SPLIT",initialValue=1, allocationSize=1)
public class StockSplit implements Serializable {
	
	private static final long serialVersionUID = 7668815606662922867L;
	
	@Id @GeneratedValue(generator="stockSplitIdSeqGenerator")
    private Long id;	
	@Column(nullable = false,name="stock_id")
	private Long stockId;
	@Version 
    private Integer version;
	@Column(nullable = false,name="date")
	@Temporal(TemporalType.DATE)
    private Date date;
	@Column(nullable = false,name="factor")
    private BigDecimal factor;
	
	public StockSplit() {}
	
	public StockSplit(StockSplit data) {
		if (data != null) {
			id = data.id;
			stockId = data.stockId;
			date = new Date(data.date.getTime());
			factor = data.factor;
			version = data.version;
		}
	}

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

	public Integer getVersion() {
		return version;
	}

	public void mergeTo(StockSplit data) {
		date = new Date(data.date.getTime());
		factor = data.factor;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long newId) {
		this.id = newId;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StockSplit other = (StockSplit) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (stockId == null) {
			if (other.stockId != null)
				return false;
		} else if (!stockId.equals(other.stockId))
			return false;
		return true;
	}

}

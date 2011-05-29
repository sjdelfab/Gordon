package org.sjd.gordon.model;

import java.io.Serializable;
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
@Table(name = "treasury_held_stock")
@SequenceGenerator(schema="GORDON",name = "treasuryHeldStockIdSeqGenerator", sequenceName  = "SEQ_TREASURY_HELD",initialValue=1, allocationSize=1)
public class TreasuryHeldStock implements Serializable {

	private static final long serialVersionUID = 4768766015598730001L;
	
	@Id @GeneratedValue(generator="treasuryHeldStockIdSeqGenerator")
    private Long id;	
	@Column(nullable = false,name="stock_id")
	private Long stockId;
	@Version 
    private Integer version;
	@Column(nullable = false,name="date")
	@Temporal(TemporalType.DATE)
    private Date date;
	@Column(nullable = false,name="volume")
    private Integer volume;
	
	public TreasuryHeldStock() { }
	
	public TreasuryHeldStock(TreasuryHeldStock data) {
		if (data != null) {
			id = data.id;
			date = new Date(data.date.getTime());
			volume = data.volume;
			stockId = data.stockId;
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
	
	public Integer getVolume() {
		return volume;
	}
	
	public void setVolume(Integer volume) {
		this.volume = volume;
	}
	
	public Integer getVersion() {
		return version;
	}

	public void mergeTo(TreasuryHeldStock data) {
		date = new Date(data.date.getTime());
		volume = data.volume;
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
		TreasuryHeldStock other = (TreasuryHeldStock) obj;
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

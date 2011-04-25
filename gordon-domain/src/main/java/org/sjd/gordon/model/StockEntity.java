package org.sjd.gordon.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "stock")
public class StockEntity extends SecurityEntity implements Serializable {

	private static final long serialVersionUID = -1729116164665674381L;

	@ManyToOne
	@JoinColumn(name="EXCHANGE_ID",nullable = false, referencedColumnName="ID")
	private Exchange exchange;
	@Column(nullable = false,name="list_date")
	@Temporal(TemporalType.DATE)
	private Date listDate;
	@Column(nullable = false,name="last_trade_date")
	@Temporal(TemporalType.DATE)
	private Date lastTradeDate;
	
	public StockEntity() {}
	
	public Exchange getExchange() {
		return exchange;
	}
	
	public void setExchange(Exchange exchange) {
		this.exchange = exchange;
	}
	
	public Date getListDate() {
		return listDate;
	}
	
	public void setListDate(Date listDate) {
		this.listDate = listDate;
	}
	
	public Date getLastTradeDate() {
		return lastTradeDate;
	}
	
	public void setLastTradeDate(Date lastTradeDate) {
		this.lastTradeDate = lastTradeDate;
	}
	
	
}

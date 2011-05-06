/*
 * StockDayRecord.java
 *
 * Created on 2 May 2007, 21:56
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

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

@Entity
@Table(name = "stock_day_trade")
@SequenceGenerator(schema="GORDON",name = "dayTradeIdSeqGenerator", sequenceName  = "SEQ_STOCK_DAY_TRADE",initialValue=1, allocationSize=1)
public class StockDayTradeRecord implements Serializable {
    
	private static final long serialVersionUID = -2137239251321161474L;
	
	@Id @GeneratedValue(generator="dayTradeIdSeqGenerator")
    private int id;
	@Column(nullable = false,name="stock_id")
	private long stockId;
	@Column(nullable = false,name="date")
	@Temporal(TemporalType.DATE)
    private Date date;
	@Column(nullable = false,name="open_price")
    private BigDecimal openPrice;
	@Column(nullable = false,name="high_price")
    private BigDecimal highPrice;
	@Column(nullable = false,name="low_price")
    private BigDecimal lowPrice;
	@Column(nullable = false,name="close_price")
    private BigDecimal closePrice;
	@Column(nullable = false,name="volume")
    private long volume;
   
    public StockDayTradeRecord() { }

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public BigDecimal getOpenPrice() {
		return openPrice;
	}

	public void setOpenPrice(BigDecimal openPrice) {
		this.openPrice = openPrice;
	}

	public BigDecimal getHighPrice() {
		return highPrice;
	}

	public void setHighPrice(BigDecimal highPrice) {
		this.highPrice = highPrice;
	}

	public BigDecimal getLowPrice() {
		return lowPrice;
	}

	public void setLowPrice(BigDecimal lowPrice) {
		this.lowPrice = lowPrice;
	}

	public BigDecimal getClosePrice() {
		return closePrice;
	}

	public void setClosePrice(BigDecimal closePrice) {
		this.closePrice = closePrice;
	}

	public long getVolume() {
		return volume;
	}

	public void setVolume(long volume) {
		this.volume = volume;
	}

	public int getId() {
		return id;
	}

	public long getStockId() {
		return stockId;
	}

	public void setStockId(long stockId) {
		this.stockId = stockId;
	}
	
}

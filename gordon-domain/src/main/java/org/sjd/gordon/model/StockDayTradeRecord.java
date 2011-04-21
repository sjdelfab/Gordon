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
    private double openPrice;
	@Column(nullable = false,name="high_price")
    private double highPrice;
	@Column(nullable = false,name="low_price")
    private double lowPrice;
	@Column(nullable = false,name="close_price")
    private double closePrice;
	@Column(nullable = false,name="volume")
    private long volume;
   
    public StockDayTradeRecord() { }

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public double getOpenPrice() {
		return openPrice;
	}

	public void setOpenPrice(double openPrice) {
		this.openPrice = openPrice;
	}

	public double getHighPrice() {
		return highPrice;
	}

	public void setHighPrice(double highPrice) {
		this.highPrice = highPrice;
	}

	public double getLowPrice() {
		return lowPrice;
	}

	public void setLowPrice(double lowPrice) {
		this.lowPrice = lowPrice;
	}

	public double getClosePrice() {
		return closePrice;
	}

	public void setClosePrice(double closePrice) {
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

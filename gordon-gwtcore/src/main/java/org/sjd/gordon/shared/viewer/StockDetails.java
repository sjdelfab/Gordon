package org.sjd.gordon.shared.viewer;

import java.io.Serializable;
import java.util.Date;

import org.sjd.gordon.model.StockEntity;

public class StockDetails implements Serializable {
	
	private static final long serialVersionUID = 2946987725770341556L;
	
	private Long id;
	private String name;
	private String code;
	private Date listDate;
	private Date lastTradeDate;
	
	public StockDetails() {}
	
	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getCode() {
		return code;
	}
	
	public void setCode(String code) {
		this.code = code;
	}
	
	public Date getListDate() {
		return listDate;
	}
	
	public Date getLastTradeDate() {
		return lastTradeDate;
	}
	
	public static StockDetails fromEntity(StockEntity stockEntity) {
		StockDetails stockDetails = new StockDetails();
		stockDetails.id = stockEntity.getId();
		stockDetails.name = stockEntity.getName();
		stockDetails.code = stockEntity.getCode();
		stockDetails.listDate = stockEntity.getListDate();
		stockDetails.lastTradeDate = stockEntity.getLastTradeDate();
		return stockDetails;
	}

}

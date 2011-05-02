package org.sjd.gordon.shared.viewer;

import java.io.Serializable;
import java.util.Date;

import org.sjd.gordon.model.StockEntity;

import com.extjs.gxt.ui.client.data.BaseModel;

public class StockDetails extends BaseModel implements Serializable {
	
	private static final long serialVersionUID = 2946987725770341556L;
	
	private Long id;

	public StockDetails() {}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return (String) get("name");
	}
	
	public void setName(String name) {
		set("name", name);
	}
	
	public String getCode() {
		return (String) get("code");
	}
	
	public void setCode(String code) {
		set("code", code);
	}
	
	public Date getListDate() {
		return (Date) get("listDate");
	}
	
	public Date getLastTradeDate() {
		return (Date) get("lastTradeDate");
	}
	
	public static StockDetails fromEntity(StockEntity stockEntity) {
		StockDetails stockDetails = new StockDetails();
		stockDetails.id = stockEntity.getId();
		stockDetails.setName(stockEntity.getName());
		stockDetails.setCode(stockEntity.getCode());
		stockDetails.set("listDate",stockEntity.getListDate());
		stockDetails.set("lastTradeDate",stockEntity.getLastTradeDate());
		return stockDetails;
	}

	public void mergeTo(StockDetails newStockDetails) {
		setCode(newStockDetails.getCode());
		setName(newStockDetails.getName());
	}

}

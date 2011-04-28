package org.sjd.gordon.shared.navigation;

import java.io.Serializable;

import org.sjd.gordon.model.StockEntity;

public class StockName implements Serializable {

	private static final long serialVersionUID = 6871936620590068205L;
	
	private Long id;
	private String name;
	private String code;
	
	public StockName() {}
	
	// For dev mode usage only
	public StockName(Long id) {
		this.id = id;
	}
	
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
	
	public static StockName fromEntity(StockEntity stockEntity) {
		StockName stock = new StockName();
		stock.id = stockEntity.getId();
		stock.name = stockEntity.getName();
		stock.code = stockEntity.getCode();
		return stock;
	}
	
}

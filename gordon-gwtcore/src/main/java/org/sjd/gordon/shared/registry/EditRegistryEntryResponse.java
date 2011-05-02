package org.sjd.gordon.shared.registry;

import java.io.Serializable;

import com.gwtplatform.dispatch.shared.Result;

public class EditRegistryEntryResponse implements Serializable, Result {
	
	private static final long serialVersionUID = 992084910401817234L;
	
	private Long stockId;
	
	public EditRegistryEntryResponse() { }
	
	public EditRegistryEntryResponse(Long stockId) {
		this.stockId = stockId;
	}
	
	public Long getStockId() {
		return stockId;
	}

}

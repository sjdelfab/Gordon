package org.sjd.gordon.shared.registry;

import java.io.Serializable;

import org.sjd.gordon.shared.viewer.StockDetail;

import com.gwtplatform.dispatch.shared.Result;

public class EditRegistryEntryResponse implements Serializable, Result {
	
	private static final long serialVersionUID = 992084910401817234L;
	
	private StockDetail stock;
	
	
	public EditRegistryEntryResponse() { }
	
	public EditRegistryEntryResponse(StockDetail stock) {
		this.stock = stock;
	}
	
	public StockDetail getStock() {
		return stock;
	}

}

package org.sjd.gordon.shared.registry;

import java.io.Serializable;

import org.sjd.gordon.shared.viewer.StockDetails;

import com.gwtplatform.dispatch.shared.Result;

public class EditRegistryEntryResponse implements Serializable, Result {
	
	private static final long serialVersionUID = 992084910401817234L;
	
	private StockDetails stock;
	
	
	public EditRegistryEntryResponse() { }
	
	public EditRegistryEntryResponse(StockDetails stock) {
		this.stock = stock;
	}
	
	public StockDetails getStock() {
		return stock;
	}

}

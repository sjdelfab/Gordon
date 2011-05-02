package org.sjd.gordon.shared.registry;

import java.io.Serializable;

import com.gwtplatform.dispatch.shared.UnsecuredActionImpl;

public class DeleteRegistryEntry extends UnsecuredActionImpl<DeleteRegistryEntryResponse> implements Serializable {
	
	private static final long serialVersionUID = 430614148737827199L;
	
	private Long stockId;
	
	public DeleteRegistryEntry() { }
	
	public DeleteRegistryEntry(Long stockId) {
		this.stockId = stockId;
	}
	
	public Long getStockId() {
		return stockId;
	}

}

package org.sjd.gordon.shared.registry;

import java.io.Serializable;

import org.sjd.gordon.shared.viewer.StockDetail;

import com.gwtplatform.dispatch.shared.UnsecuredActionImpl;

public class EditRegistryEntry extends UnsecuredActionImpl<EditRegistryEntryResponse> implements Serializable {

	private static final long serialVersionUID = 4851344234581096095L;
	
	public static enum EditType {ADD, UPDATE};
	
	private StockDetail stockDetails;
	private Integer exchangeId;
	private EditType editType;
	
	public EditRegistryEntry() { }
	
	public EditRegistryEntry(StockDetail details, Integer exchangeId, EditType editType) { 
		this.stockDetails = details;
		this.exchangeId = exchangeId;
		this.editType = editType;
	}
	
	public StockDetail getStockDetails() {
		return stockDetails;
	}
	
	public Integer getExchangeId() {
		return exchangeId;
	}
	
	public EditType getEditType() {
		return editType;
	}

}

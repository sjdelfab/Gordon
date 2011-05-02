package org.sjd.gordon.shared.registry;

import java.io.Serializable;

import com.gwtplatform.dispatch.shared.UnsecuredActionImpl;

public class GetAllStockDetails extends UnsecuredActionImpl<GotAllStockDetails> implements Serializable {

	private static final long serialVersionUID = 26510750765975248L;
	
	private Integer exchangeId;
	
	public GetAllStockDetails() {}
	
	public GetAllStockDetails(Integer exchangeId) {
		this.exchangeId = exchangeId;
	}
	
	public Integer getExchangeId() {
		return exchangeId;
	}

}

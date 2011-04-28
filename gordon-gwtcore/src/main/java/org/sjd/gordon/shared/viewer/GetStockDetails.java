package org.sjd.gordon.shared.viewer;

import java.io.Serializable;

import com.gwtplatform.dispatch.shared.UnsecuredActionImpl;

public class GetStockDetails extends UnsecuredActionImpl<GotStockDetails> implements Serializable {
	

	private static final long serialVersionUID = 3079572278718211356L;
	
	private Long stockId;
	
	public GetStockDetails() { }
	
	public GetStockDetails(Long stockId) {
		this.stockId = stockId;
	}

	public Long getStockId() {
		return stockId;
	}

}

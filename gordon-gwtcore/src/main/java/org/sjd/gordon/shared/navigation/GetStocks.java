package org.sjd.gordon.shared.navigation;

import java.io.Serializable;

import com.gwtplatform.dispatch.shared.UnsecuredActionImpl;

public class GetStocks extends UnsecuredActionImpl<GotStocks> implements Serializable {

	private static final long serialVersionUID = 4956388101107782497L;
	
	private Integer exchangeId;
	
	public GetStocks() { }
	
	public GetStocks(Integer exchangeId) {
		this.exchangeId = exchangeId;
	}

	public Integer getExchangeId() {
		return exchangeId;
	}

}

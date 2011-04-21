package org.sjd.gordon.shared.navigation;

import java.io.Serializable;

import net.customware.gwt.dispatch.shared.Action;

public class GetStocks implements Serializable, Action<GotStocks> {

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

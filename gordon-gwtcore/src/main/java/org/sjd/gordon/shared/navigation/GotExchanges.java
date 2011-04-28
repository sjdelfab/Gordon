package org.sjd.gordon.shared.navigation;

import java.io.Serializable;
import java.util.ArrayList;

import org.sjd.gordon.model.Exchange;

import com.gwtplatform.dispatch.shared.Result;

public class GotExchanges implements Serializable, Result {

	private static final long serialVersionUID = 3535099931547003896L;

	private ArrayList<Exchange> exchanges;
	
	public GotExchanges() {
		this(new ArrayList<Exchange>(0));
	}
	
	public GotExchanges(ArrayList<Exchange> exchanges) {
		this.exchanges = exchanges;
	}
	
	public ArrayList<Exchange> getExchanges() {
		return exchanges;
	}

}
package org.sjd.gordon.client.navigation;

import java.util.ArrayList;

import org.sjd.gordon.client.AbstractCallback;
import org.sjd.gordon.model.Exchange;
import org.sjd.gordon.shared.navigation.GotExchanges;

import com.google.gwt.user.client.rpc.AsyncCallback;

public abstract class LoadExchangesCallback extends AbstractCallback implements AsyncCallback<GotExchanges> {

	@Override
	public void onSuccess(GotExchanges exchanges) {
		loaded(exchanges.getExchanges());
	}

	public abstract void loaded(ArrayList<Exchange> exchanges);


}

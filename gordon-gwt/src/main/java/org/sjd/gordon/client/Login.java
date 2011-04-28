package org.sjd.gordon.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.gwtplatform.mvp.client.DelayedBindRegistry;

public class Login implements EntryPoint {

	private final LoginGinjector injector = GWT.create(LoginGinjector.class);
	
	public void onModuleLoad() {
		DelayedBindRegistry.bind(injector);
	    injector.getPlaceManager().revealCurrentPlace();
	}
}

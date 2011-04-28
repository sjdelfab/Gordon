package org.sjd.gordon.client;

import org.sjd.gordon.client.main.MainPagePresenter;

import com.google.gwt.event.shared.EventBus;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.proxy.PlaceManagerImpl;
import com.gwtplatform.mvp.client.proxy.PlaceRequest;
import com.gwtplatform.mvp.client.proxy.TokenFormatter;

public class GordonPlaceManager extends PlaceManagerImpl {

	  @Inject
	  public GordonPlaceManager(EventBus eventBus, TokenFormatter tokenFormatter) {
	    super(eventBus, tokenFormatter);
	  }

	  @Override
	  public void revealDefaultPlace() {
	    revealPlace(new PlaceRequest(MainPagePresenter.nameToken));
	  }
	  
}

package org.sjd.gordon.client;

import org.sjd.gordon.client.security.LoginPresenter;

import com.google.gwt.event.shared.EventBus;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.proxy.PlaceManagerImpl;
import com.gwtplatform.mvp.client.proxy.PlaceRequest;
import com.gwtplatform.mvp.client.proxy.TokenFormatter;

public class LoginPlaceManager extends PlaceManagerImpl {

	  @Inject
	  public LoginPlaceManager(EventBus eventBus, TokenFormatter tokenFormatter) {
	    super(eventBus, tokenFormatter);
	  }

	  @Override
	  public void revealDefaultPlace() {
	    revealPlace(new PlaceRequest(LoginPresenter.nameToken));
	  }
}

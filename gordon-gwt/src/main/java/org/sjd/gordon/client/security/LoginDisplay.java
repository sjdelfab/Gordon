package org.sjd.gordon.client.security;

import net.customware.gwt.presenter.client.widget.WidgetDisplay;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.HasValue;

public interface LoginDisplay extends WidgetDisplay {
	public HasValue<String> getUserName();
	public HasValue<String> getPassword();
	public HasClickHandlers getLogin();
}

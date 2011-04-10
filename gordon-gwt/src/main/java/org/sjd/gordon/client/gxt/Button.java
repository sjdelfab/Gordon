package org.sjd.gordon.client.gxt;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.HasText;

public class Button extends com.extjs.gxt.ui.client.widget.button.Button implements HasClickHandlers, HasText {

	public Button(String text) {
		super(text);
	}
	
	@Override
	public HandlerRegistration addClickHandler(ClickHandler handler) {
		return addHandler(handler, ClickEvent.getType());
	}


}

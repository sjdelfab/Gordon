package org.sjd.gordon.client.gxt;

import com.extjs.gxt.ui.client.event.DomEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.widget.Label;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerRegistration;

public class ClickableLabel extends Label implements HasClickHandlers {

	public ClickableLabel(String text) {
		super(text);
		addListener(Events.OnMouseDown, new Listener<DomEvent>() {
			@Override
			public void handleEvent(DomEvent be) {
				ClickableLabel.this.fireEvent(new ClickEvent() {});
			}
		});
	}
	
	@Override
	public HandlerRegistration addClickHandler(ClickHandler handler) {
		return addHandler(handler, ClickEvent.getType());
	}

}

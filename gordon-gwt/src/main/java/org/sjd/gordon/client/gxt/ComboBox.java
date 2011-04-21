package org.sjd.gordon.client.gxt;

import com.extjs.gxt.ui.client.data.ModelData;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.HasValue;

public class ComboBox<D extends ModelData> extends com.extjs.gxt.ui.client.widget.form.ComboBox<D> implements HasValue<D>, HasClickHandlers{

	@Override
	public HandlerRegistration addValueChangeHandler(ValueChangeHandler<D> handler) {
		return super.addHandler(handler,ValueChangeEvent.getType());
	}

	@Override
	public void setValue(D value, boolean arg1) {
		// TODO Boolean argument.
		super.setValue(value);
	}

	@Override
	public HandlerRegistration addClickHandler(ClickHandler handler) {
		return super.addHandler(handler,ClickEvent.getType());
	}

}

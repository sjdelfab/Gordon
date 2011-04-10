package org.sjd.gordon.client.gxt;

import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.HasValue;

public class TextField<T> extends com.extjs.gxt.ui.client.widget.form.TextField<T> implements HasValue<T> {

	@Override
	public HandlerRegistration addValueChangeHandler(ValueChangeHandler<T> arg0) {
		return addValueChangeHandler(arg0);
	}

	@Override
	public void setValue(T value, boolean arg1) {
		// TODO What is the boolean value for?
		setValue(value);
	}


}

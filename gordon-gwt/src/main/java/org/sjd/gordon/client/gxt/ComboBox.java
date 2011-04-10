package org.sjd.gordon.client.gxt;

import com.extjs.gxt.ui.client.data.ModelData;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.HasValue;

public class ComboBox<D extends ModelData> extends com.extjs.gxt.ui.client.widget.form.ComboBox<D> implements HasValue<D>{

	@Override
	public HandlerRegistration addValueChangeHandler(ValueChangeHandler<D> handler) {
		return addValueChangeHandler(handler);
	}

	@Override
	public void setValue(D value, boolean arg1) {
		// TODO Boolean argument.
		setValue(value);
	}

}

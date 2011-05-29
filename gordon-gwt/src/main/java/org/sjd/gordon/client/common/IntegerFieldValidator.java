package org.sjd.gordon.client.common;

import com.extjs.gxt.ui.client.widget.form.Field;
import com.extjs.gxt.ui.client.widget.form.Validator;

public class IntegerFieldValidator implements Validator {

	@Override
	public String validate(Field<?> field, String value) {
		try {
			Long.parseLong(value);
		} catch (NumberFormatException ex) {
			return "Can only enter non-decimal numbers";
		}
		return null;
	}

}

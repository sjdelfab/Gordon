package org.sjd.gordon.client.common;

import com.extjs.gxt.ui.client.widget.form.Field;
import com.extjs.gxt.ui.client.widget.form.Validator;

public class DecimalFieldValidator implements Validator {

	@Override
	public String validate(Field<?> field, String value) {
		try {
			Double.parseDouble(value);
		} catch (NumberFormatException ex) {
			return "Can only enter numbers";
		}
		return null;
	}

}

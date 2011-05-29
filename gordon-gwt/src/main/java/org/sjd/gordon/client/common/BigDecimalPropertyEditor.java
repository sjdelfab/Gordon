package org.sjd.gordon.client.common;

import java.math.BigDecimal;

import com.extjs.gxt.ui.client.widget.form.PropertyEditor;

public class BigDecimalPropertyEditor implements PropertyEditor<BigDecimal> {

	@Override
	public String getStringValue(BigDecimal value) {
		return value.toPlainString();
	}

	@Override
	public BigDecimal convertStringValue(String value) {
		return new BigDecimal(value);
	}

}

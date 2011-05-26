package org.sjd.gordon.client.viewer;

import org.sjd.gordon.model.BusinessSummary;

import com.gwtplatform.mvp.client.UiHandlers;

public interface StockProfileUIHandler extends UiHandlers {
	
	public void updateBusinessSummary(BusinessSummary summary);

}

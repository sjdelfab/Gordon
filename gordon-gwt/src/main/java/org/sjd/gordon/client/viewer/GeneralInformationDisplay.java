package org.sjd.gordon.client.viewer;

import net.customware.gwt.presenter.client.widget.WidgetDisplay;

import org.sjd.gordon.model.StockEntity;

public interface GeneralInformationDisplay extends WidgetDisplay {

	public void setStock(StockEntity stock);
}

package org.sjd.gordon.shared.viewer;

import org.sjd.gordon.model.StockSplit;

import com.gwtplatform.dispatch.annotation.GenDispatch;
import com.gwtplatform.dispatch.annotation.In;
import com.gwtplatform.dispatch.shared.UnsecuredActionImpl;

@GenDispatch(isSecure = false, serviceName = UnsecuredActionImpl.DEFAULT_SERVICE_NAME)
public class DeleteStockSplit {

	@In(1) StockSplit stockSplit;
}

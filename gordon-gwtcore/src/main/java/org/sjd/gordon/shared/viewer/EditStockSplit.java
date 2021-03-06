package org.sjd.gordon.shared.viewer;

import org.sjd.gordon.model.StockSplit;
import org.sjd.gordon.shared.util.EditType;

import com.gwtplatform.dispatch.annotation.GenDispatch;
import com.gwtplatform.dispatch.annotation.In;
import com.gwtplatform.dispatch.annotation.Out;
import com.gwtplatform.dispatch.shared.UnsecuredActionImpl;

@GenDispatch(isSecure = false, serviceName = UnsecuredActionImpl.DEFAULT_SERVICE_NAME)
public class EditStockSplit {
	
	@In(1) StockSplit newStockSplit;
	@In(2) Long stockId;
	@In(3) EditType editType;
	@Out(1) StockSplit updatedStockSplit;	
}

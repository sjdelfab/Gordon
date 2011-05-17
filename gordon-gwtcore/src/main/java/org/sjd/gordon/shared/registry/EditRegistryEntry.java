package org.sjd.gordon.shared.registry;

import org.sjd.gordon.shared.viewer.StockDetail;

import com.gwtplatform.dispatch.annotation.GenDispatch;
import com.gwtplatform.dispatch.annotation.In;
import com.gwtplatform.dispatch.annotation.Out;
import com.gwtplatform.dispatch.shared.UnsecuredActionImpl;

@GenDispatch(isSecure = false, serviceName = UnsecuredActionImpl.DEFAULT_SERVICE_NAME)
public class EditRegistryEntry {
	
	public static enum EditType {ADD, UPDATE};
	
	@In(1) StockDetail stockDetails;
	@In(2) Integer exchangeId;
	@In(3) EditType editType;
	@Out(1) StockDetail stock;
	
}

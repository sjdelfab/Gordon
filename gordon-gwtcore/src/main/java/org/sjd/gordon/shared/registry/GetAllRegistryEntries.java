package org.sjd.gordon.shared.registry;

import java.util.ArrayList;

import org.sjd.gordon.shared.viewer.StockDetail;

import com.gwtplatform.dispatch.annotation.GenDispatch;
import com.gwtplatform.dispatch.annotation.In;
import com.gwtplatform.dispatch.annotation.Out;
import com.gwtplatform.dispatch.shared.UnsecuredActionImpl;

@GenDispatch(isSecure = false, serviceName = UnsecuredActionImpl.DEFAULT_SERVICE_NAME)
public class GetAllRegistryEntries {

	@In(1) Integer exchangeId;
	@In(2) Integer limit;
	@In(3) Integer offset;
	@Out(1) ArrayList<StockDetail> stocks;
	@Out(2) Integer totalCount;
	
}

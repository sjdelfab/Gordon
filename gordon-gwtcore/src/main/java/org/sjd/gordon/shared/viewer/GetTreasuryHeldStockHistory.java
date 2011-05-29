package org.sjd.gordon.shared.viewer;

import java.util.ArrayList;

import org.sjd.gordon.model.TreasuryHeldStock;

import com.gwtplatform.dispatch.annotation.GenDispatch;
import com.gwtplatform.dispatch.annotation.In;
import com.gwtplatform.dispatch.annotation.Out;
import com.gwtplatform.dispatch.shared.UnsecuredActionImpl;

@GenDispatch(isSecure = false, serviceName = UnsecuredActionImpl.DEFAULT_SERVICE_NAME)
public class GetTreasuryHeldStockHistory {

	@In(1) Long stockId;
	@Out(1) ArrayList<TreasuryHeldStock> treasuryHeldStockHistory;
}

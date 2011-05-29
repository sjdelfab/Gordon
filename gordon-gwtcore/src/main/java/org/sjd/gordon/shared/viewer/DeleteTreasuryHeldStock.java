package org.sjd.gordon.shared.viewer;

import org.sjd.gordon.model.TreasuryHeldStock;

import com.gwtplatform.dispatch.annotation.GenDispatch;
import com.gwtplatform.dispatch.annotation.In;
import com.gwtplatform.dispatch.shared.UnsecuredActionImpl;

@GenDispatch(isSecure = false, serviceName = UnsecuredActionImpl.DEFAULT_SERVICE_NAME)
public class DeleteTreasuryHeldStock {
	
	@In(1) TreasuryHeldStock treasuryHeldStock;

}

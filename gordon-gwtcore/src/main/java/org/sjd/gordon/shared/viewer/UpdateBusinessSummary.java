package org.sjd.gordon.shared.viewer;

import org.sjd.gordon.model.BusinessSummary;

import com.gwtplatform.dispatch.annotation.GenDispatch;
import com.gwtplatform.dispatch.annotation.In;
import com.gwtplatform.dispatch.annotation.Out;
import com.gwtplatform.dispatch.shared.UnsecuredActionImpl;

@GenDispatch(isSecure = false, serviceName = UnsecuredActionImpl.DEFAULT_SERVICE_NAME)
public class UpdateBusinessSummary {

	@In(1) BusinessSummary newBusinessSummary;
	@Out(1) BusinessSummary updatedBusinessSummary;
	
}

package org.sjd.gordon.shared.viewer;

import org.sjd.gordon.model.Dividend;
import org.sjd.gordon.shared.util.EditType;

import com.gwtplatform.dispatch.annotation.GenDispatch;
import com.gwtplatform.dispatch.annotation.In;
import com.gwtplatform.dispatch.annotation.Out;
import com.gwtplatform.dispatch.shared.UnsecuredActionImpl;

@GenDispatch(isSecure = false, serviceName = UnsecuredActionImpl.DEFAULT_SERVICE_NAME)
public class EditDividend {

	@In(1) Dividend newDividend;
	@In(2) EditType editType;
	@Out(1) Dividend updatedDividend;	

}

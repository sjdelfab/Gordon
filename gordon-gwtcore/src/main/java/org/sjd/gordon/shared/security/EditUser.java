package org.sjd.gordon.shared.security;

import com.gwtplatform.dispatch.annotation.GenDispatch;
import com.gwtplatform.dispatch.annotation.In;
import com.gwtplatform.dispatch.annotation.Out;
import com.gwtplatform.dispatch.shared.UnsecuredActionImpl;

@GenDispatch(isSecure = false, serviceName = UnsecuredActionImpl.DEFAULT_SERVICE_NAME)
public class EditUser {

	public static enum EditType {ADD, UPDATE};
	
	@In(1) UserDetail newUserDetails;
	@In(2) EditType editType;
	@Out(1) UserDetail updatedUserDetails;

}

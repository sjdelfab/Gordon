package org.sjd.gordon.shared.security;

import com.gwtplatform.dispatch.annotation.GenDispatch;
import com.gwtplatform.dispatch.annotation.In;
import com.gwtplatform.dispatch.shared.UnsecuredActionImpl;

@GenDispatch(isSecure = false, serviceName = UnsecuredActionImpl.DEFAULT_SERVICE_NAME)
public class ChangeUserPassword {
		
	@In(1) Integer userId;
	@In(2) String newPassword;
	
}

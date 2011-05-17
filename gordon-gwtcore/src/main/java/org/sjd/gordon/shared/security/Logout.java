package org.sjd.gordon.shared.security;

import com.gwtplatform.dispatch.annotation.GenDispatch;
import com.gwtplatform.dispatch.shared.UnsecuredActionImpl;

@GenDispatch(isSecure = false, serviceName = UnsecuredActionImpl.DEFAULT_SERVICE_NAME)
public class Logout { }

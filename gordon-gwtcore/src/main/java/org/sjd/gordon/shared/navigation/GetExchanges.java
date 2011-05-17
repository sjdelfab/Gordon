package org.sjd.gordon.shared.navigation;

import java.util.ArrayList;

import org.sjd.gordon.model.Exchange;

import com.gwtplatform.dispatch.annotation.GenDispatch;
import com.gwtplatform.dispatch.annotation.Out;
import com.gwtplatform.dispatch.shared.UnsecuredActionImpl;

@GenDispatch(isSecure = false, serviceName = UnsecuredActionImpl.DEFAULT_SERVICE_NAME)
public class GetExchanges {

	@Out(1) ArrayList<Exchange> exchanges;
}

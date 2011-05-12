package org.sjd.gordon.ejb.dispatch.setup;

import org.hamcrest.Description;
import org.jmock.api.Action;
import org.jmock.api.Invocation;

public class ReturnParameter implements Action {

	private int paramIndex;
	
	public ReturnParameter(int paramIndex) {
		this.paramIndex = paramIndex;
	}
	
	@Override
	public void describeTo(Description description) {
		description.appendText("Return parameter");
	}

	@Override
	public Object invoke(Invocation invocation) throws Throwable {
		Object param = modify(invocation.getParameter(paramIndex));
		return param;
	}
	
	protected Object modify(Object param) {
		return param;
	}
}

package org.sjd.gordon.server;

import net.customware.gwt.dispatch.server.guice.GuiceStandardDispatchServlet;

import com.google.inject.servlet.ServletModule;

public class DispatchServletModule extends ServletModule {
	
	@Override
	protected void configureServlets() {
		super.configureServlets();
		serve("/Gordon/dispatch").with(GuiceStandardDispatchServlet.class);
	}

}

package org.sjd.gordon.server;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.persist.jpa.JpaPersistModule;
import com.google.inject.servlet.GuiceServletContextListener;

public class GordonGuiceServletConfig extends GuiceServletContextListener {

	private boolean developmentMode = false;
	
	@Override
	protected Injector getInjector() {
		Injector injector = null;
		if (developmentMode) {
			injector = Guice.createInjector(new DevelopmentModule(), new DispatchServletModule());
		} else {
			injector = Guice.createInjector(new GuiceJpaServerModule(), new DispatchServletModule(), new JpaPersistModule("gordon"));
		}
		return injector;
	}

}

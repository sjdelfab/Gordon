package org.sjd.gordon.server;

import org.sjd.gordon.ejb.dispatch.EJBServerModule;
import org.sjd.gordon.server.devhandlers.DevelopmentModule;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.persist.jpa.JpaPersistModule;
import com.google.inject.servlet.GuiceServletContextListener;

public class GordonGuiceServletConfig extends GuiceServletContextListener {

	public static enum Mode { DEV, GUICE_JPA, EJB };
	
	private Mode mode = Mode.EJB;
	
	@Override
	protected Injector getInjector() {
		Injector injector = null;
		if (mode == Mode.DEV) {
			injector = Guice.createInjector(new DevelopmentModule(), new DispatchServletModule());
		} else if (mode == Mode.GUICE_JPA) {
			injector = Guice.createInjector(new GuiceJpaServerModule(), new DispatchServletModule(), new JpaPersistModule("gordon"));
		} else {
			injector = Guice.createInjector(new EJBServerModule(), new DispatchServletModule());
		}
		return injector;
	}

}

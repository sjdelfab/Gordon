package org.sjd.gordon.server;

import org.sjd.gordon.server.devhandlers.DevelopmentModule;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import com.google.inject.servlet.GuiceServletContextListener;
import com.gwtplatform.dispatch.server.guice.HandlerModule;

public class GordonGuiceServletConfig extends GuiceServletContextListener {

	public static enum Mode {
		DEV, GUICE_JPA, EJB
	};

	private Mode mode = Mode.DEV;

	@Override
	protected Injector getInjector() {
		Injector injector = null;
		if (mode == Mode.DEV) {
			injector = Guice.createInjector(new CommonModule(), new DevelopmentModule(), new DispatchServletModule());
		} else if (mode == Mode.GUICE_JPA) {
			String guiceJpaModuleClass = "org.sjd.gordon.server.GuiceJpaServerModule";
			String guiceJpaPersistModuleClass = "com.google.inject.persist.jpa.JpaPersistModule";
			try {
				Class<?> clazz = Class.forName(guiceJpaModuleClass);
				HandlerModule handlerModule = (HandlerModule) clazz.newInstance();
				clazz = Class.forName(guiceJpaPersistModuleClass);
				Module jpaPeristModule = (Module)clazz.getDeclaredConstructors()[0].newInstance("gordon");
				injector = Guice.createInjector(new CommonModule(), handlerModule, new DispatchServletModule(),jpaPeristModule);
			} catch (Throwable cause) {
				throw new RuntimeException(cause);
			}
		} else {
			String ejbModuleClass = "org.sjd.gordon.ejb.dispatch.EJBServerModule";
			try {
				Class<?> clazz = Class.forName(ejbModuleClass);
				HandlerModule handlerModule = (HandlerModule) clazz.newInstance();
				injector = Guice.createInjector(new CommonModule(),handlerModule, new DispatchServletModule());
			} catch (Throwable cause) {
				throw new RuntimeException(cause);
			}
		}
		return injector;
	}

}

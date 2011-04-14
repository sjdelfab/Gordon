package org.sjd.gordon.server;

import com.google.inject.Inject;
import com.google.inject.persist.PersistService;

public class GuiceJPAInitializer {
	
	@Inject
	public GuiceJPAInitializer(PersistService service) {
		service.start();
		// At this point JPA is started and ready.
	}
}

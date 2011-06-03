package org.sjd.gordon.server;

import com.google.inject.servlet.ServletModule;
import com.gwtplatform.dispatch.server.guice.DispatchServiceImpl;
import com.gwtplatform.dispatch.shared.ActionImpl;

public class DispatchServletModule extends ServletModule {
	
	@Override
	protected void configureServlets() {
		super.configureServlets();
		serve("/Gordon/StockEquityFileImporter").with(StockEquityFileImportHandler.class);
		serve("/Gordon/TradeHistoryFileImporter").with(TradeHistoryFileImportHandler.class);
		serve("/" + ActionImpl.DEFAULT_SERVICE_NAME + "*").with(DispatchServiceImpl.class);
	}

}

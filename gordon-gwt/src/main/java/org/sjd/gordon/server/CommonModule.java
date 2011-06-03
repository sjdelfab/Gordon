package org.sjd.gordon.server;

import com.google.inject.AbstractModule;

public class CommonModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(TradeHistoryFileImportHandler.class).asEagerSingleton();
		bind(StockEquityFileImportHandler.class).asEagerSingleton();
	}

}

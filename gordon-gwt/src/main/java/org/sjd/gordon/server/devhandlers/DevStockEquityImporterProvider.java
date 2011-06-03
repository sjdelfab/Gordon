package org.sjd.gordon.server.devhandlers;

import org.sjd.gordon.importing.profile.StockEquityImporterService;

import com.google.inject.Provider;

public class DevStockEquityImporterProvider implements Provider<StockEquityImporterService>{

	@Override
	public StockEquityImporterService get() {
		return new DevStockEquityImporter();
	}

}

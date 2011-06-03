package org.sjd.gordon.server.devhandlers;

import org.sjd.gordon.importing.tradehistory.CSVTradeHistoryImportService;

import com.google.inject.Provider;

public class DevCSVTradeHistoryImporterProvider implements Provider<CSVTradeHistoryImportService> {

	@Override
	public CSVTradeHistoryImportService get() {
		return new DevCSVTradeHistoryImporter();
	}

}

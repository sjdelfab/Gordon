package org.sjd.gordon.server;

import java.io.Reader;
import java.util.Map;

import org.sjd.gordon.importing.ImportException;
import org.sjd.gordon.importing.tradehistory.CSVTradeHistoryImportService;

import com.google.inject.Inject;
import com.google.inject.Provider;

@SuppressWarnings("serial")
public class TradeHistoryFileImportHandler extends AbstractFileImportHandler {

	@Inject
	private Provider<CSVTradeHistoryImportService> importProvider;
	
	@Override
	protected void importFile(Map<String,String> parameters, Reader reader) throws ImportException {
		String exchangeName = parameters.get("exchange");
		String symbol = parameters.get("stockCode");
		System.out.println("Exchange = " + exchangeName + ", Symbol = " + symbol);
		importProvider.get().importTradeHistory(exchangeName, symbol, reader);
	}

	
}

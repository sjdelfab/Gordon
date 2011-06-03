package org.sjd.gordon.importing.tradehistory;

import java.io.Reader;

import org.sjd.gordon.importing.ImportException;

public interface CSVTradeHistoryImportService {

	public void importTradeHistory(String exchangeCode, String symbol, Reader csvFile) throws ImportException;
	
}

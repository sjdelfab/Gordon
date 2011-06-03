package org.sjd.gordon.ejb;

import javax.ejb.Local;

import org.sjd.gordon.importing.tradehistory.CSVTradeHistoryImportService;

@Local
public interface CSVTradeHistoryImportServiceLocal extends CSVTradeHistoryImportService { }

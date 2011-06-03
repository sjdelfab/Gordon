package org.sjd.gordon.ejb;

import javax.ejb.Local;

import org.sjd.gordon.importing.profile.StockEquityImporterService;

@Local
public interface StockEquityImporterServiceLocal extends StockEquityImporterService { }

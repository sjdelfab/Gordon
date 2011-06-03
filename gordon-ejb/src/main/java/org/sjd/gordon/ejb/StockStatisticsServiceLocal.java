package org.sjd.gordon.ejb;

import javax.ejb.Local;

import org.sjd.gordon.analysis.statistics.StockStatisticsService;

@Local
public interface StockStatisticsServiceLocal extends StockStatisticsService { }

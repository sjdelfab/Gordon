package org.sjd.gordon.ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.sjd.gordon.analysis.statistics.AbstractStockStatisticsService;

@Stateless
public class StockStatisticsEJB extends AbstractStockStatisticsService implements StockStatisticsServiceLocal {

	@PersistenceContext 
    private EntityManager em; 
	
	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

}

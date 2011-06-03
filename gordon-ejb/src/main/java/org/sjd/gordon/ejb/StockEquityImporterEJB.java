package org.sjd.gordon.ejb;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.sjd.gordon.ejb.setup.GicsServiceLocal;
import org.sjd.gordon.importing.profile.AbstractStockEquityImporter;

@Stateless
public class StockEquityImporterEJB extends AbstractStockEquityImporter implements StockEquityImporterServiceLocal {

	@PersistenceContext
    private EntityManager em;
	@EJB(name="java:global/gordon-gwt-1.0/ExchangeEJB!org.sjd.gordon.ejb.ExchangeServiceLocal")
	private ExchangeServiceLocal exchangeEjb;
	@EJB(name="java:global/gordon-gwt-1.0/GicsEJB!org.sjd.gordon.ejb.setup.GicsServiceLocal")
	private GicsServiceLocal gicsEjb;
	
	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	@Override
	protected GicsServiceLocal getGicsService() {
		return gicsEjb;
	}

	@Override
	protected ExchangeServiceLocal getExchangeService() {
		return exchangeEjb;
	}

}

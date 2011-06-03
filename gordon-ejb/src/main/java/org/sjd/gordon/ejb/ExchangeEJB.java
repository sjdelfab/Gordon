package org.sjd.gordon.ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.sjd.gordon.services.AbstractExchangeService;

@Stateless
public class ExchangeEJB extends AbstractExchangeService implements ExchangeServiceLocal {

	@PersistenceContext
    private EntityManager em; 
 
	@Override
	protected EntityManager getEntityManager() {
		return em;
	}
}

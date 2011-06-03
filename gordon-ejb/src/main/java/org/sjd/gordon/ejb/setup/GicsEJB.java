package org.sjd.gordon.ejb.setup;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.sjd.gordon.services.AbstractGicsService;

@Stateless
public class GicsEJB extends AbstractGicsService implements GicsServiceLocal {

	@PersistenceContext 
    private EntityManager em;
	
    @Override
    protected EntityManager getEntityManager() {
    	return em;
    }
}

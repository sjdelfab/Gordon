package org.sjd.gordon.ejb;

import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.sjd.gordon.model.StockEntity;
import org.sjd.gordon.services.AbstractStockEntityService;

@Stateless
public class StockEntityEJB extends AbstractStockEntityService implements StockEntityServiceLocal {

	@PersistenceContext 
    private EntityManager em; 
 
	@Override
	protected EntityManager getEntityManager() {
		return em;
	}
	 
    @RolesAllowed({"ADMIN"})
    public StockEntity createStock(StockEntity stock) { 
        return super.createStock(stock); 
    }
    
    @RolesAllowed({"ADMIN"})
    public StockEntity updateStock(StockEntity stock) {
    	return super.updateStock(stock);
    }
    
    @RolesAllowed({"ADMIN"})
    public void deleteStock(StockEntity stock) {
    	super.deleteStock(stock);
    }
    
    @RolesAllowed({"ADMIN"})
    public void deleteAllDayTrades(StockEntity stock) {
    	super.deleteAllDayTrades(stock);
    }

}

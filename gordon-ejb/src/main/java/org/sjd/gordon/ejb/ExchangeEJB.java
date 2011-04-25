package org.sjd.gordon.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.sjd.gordon.model.Exchange;

@Stateless
public class ExchangeEJB {

	@PersistenceContext
    private EntityManager em; 
 
    public Exchange findExchangeById(Integer id) { 
        return em.find(Exchange.class, id); 
    } 
 
    public Exchange createExchange(Exchange exchange) { 
        em.persist(exchange); 
        return exchange; 
    }
    
    public List<Exchange> getExchanges() {
    	String getExchanges = "SELECT e FROM Exchange e";
    	return em.createQuery(getExchanges, Exchange.class).getResultList();
    }
}

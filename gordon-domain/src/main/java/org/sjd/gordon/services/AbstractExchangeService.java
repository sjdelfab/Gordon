package org.sjd.gordon.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.sjd.gordon.model.Exchange;

public abstract class AbstractExchangeService implements ExchangeService {

	@Override
    public Exchange findExchangeById(Integer id) { 
        return getEntityManager().find(Exchange.class, id); 
    } 
 
	@Override
    public Exchange createExchange(Exchange exchange) { 
    	getEntityManager().persist(exchange); 
        return exchange; 
    }
    
	@Override
    public List<Exchange> getExchanges() {
    	String getExchanges = "SELECT e FROM Exchange e";
    	return getEntityManager().createQuery(getExchanges, Exchange.class).getResultList();
    }
    
	@Override
    public Exchange getExchangeByName(String name) {
    	String getExchange = "SELECT e FROM Exchange e WHERE e.name = :name";
    	TypedQuery<Exchange> query = getEntityManager().createQuery(getExchange, Exchange.class);
    	query.setParameter("name", name);
    	return query.getSingleResult();
    }
	
	@Override
	public Exchange getExchangeByCode(String code) {
		String getExchange = "SELECT e FROM Exchange e WHERE e.code = :code";
    	TypedQuery<Exchange> query = getEntityManager().createQuery(getExchange, Exchange.class);
    	query.setParameter("code", code);
    	return query.getSingleResult();
	}
    
    abstract protected EntityManager getEntityManager();
}

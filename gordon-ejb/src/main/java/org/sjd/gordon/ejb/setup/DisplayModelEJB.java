package org.sjd.gordon.ejb.setup;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.sjd.gordon.model.Tab;

@Stateless
public class DisplayModelEJB {

	@PersistenceContext 
    private EntityManager em;
	
	public List<Tab> getTabs() {
		String getAllTabs = "SELECT t FROM Tab t";
        return em.createQuery(getAllTabs,Tab.class).getResultList();
	}
	
	public Tab createTab(Tab tab) {
		em.persist(tab);
		return tab;
	}
	
	public void removeTab(Tab tab) {
		em.remove(em.merge(tab));
	}
	
	public Tab updateTab(Tab tab) {
		return em.merge(tab);
	}
}

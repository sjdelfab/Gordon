package org.sjd.gordon.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.sjd.gordon.model.SecurityEntity;
import org.sjd.gordon.model.UnitaryPropertyDefinition;
import org.sjd.gordon.model.UnitaryPropertyValue;

@Stateless
public class UnitaryPropertiesEJB {

	@PersistenceContext(unitName = "gordon") 
    private EntityManager em; 
	
	public UnitaryPropertyDefinition addDefinition(UnitaryPropertyDefinition definition) {
		em.persist(definition);
		return definition;
	}
	
	public UnitaryPropertyDefinition updateDefinition(UnitaryPropertyDefinition definition) {
		return em.merge(definition);
	}
	
	public void deleteDefinition(UnitaryPropertyDefinition definition) {
		em.remove(em.merge(definition));
	}
	
	public List<UnitaryPropertyDefinition> getDefinitions() {
		String getDefinitions = "SELECT d FROM UnitaryPropertyDefinition d";
    	return em.createQuery(getDefinitions, UnitaryPropertyDefinition.class).getResultList();
	}
	
	public UnitaryPropertyValue addValue(UnitaryPropertyValue value) {
		em.persist(value);
		return value;
	}
	
	public List<UnitaryPropertyValue> getValues(SecurityEntity security) {
		String getValues = "SELECT v FROM UnitaryPropertyValue v WHERE v.entityId = :entityId";
		TypedQuery<UnitaryPropertyValue> query = em.createQuery(getValues, UnitaryPropertyValue.class);
		query.setParameter("entityId", security.getId());
		return query.getResultList();
	}
	
	public UnitaryPropertyValue updateValue(UnitaryPropertyValue value) {
		return em.merge(value);
	}
	
}

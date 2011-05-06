package org.sjd.gordon.ejb.setup;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.sjd.gordon.model.GicsIndustryGroup;
import org.sjd.gordon.model.GicsSector;

@Stateless
public class GicsEJB implements GicsService {

	@PersistenceContext 
    private EntityManager em;
	
	@Override
	public GicsSector findSectorById(Integer id) { 
        return em.find(GicsSector.class, id); 
    }
	
	@Override
	public GicsSector createSector(GicsSector sector) { 
        em.persist(sector); 
        return sector; 
    }
	
	@Override
	public List<GicsSector> getSectors() {
		String getGicsSectors = "SELECT s FROM GicsSector s";
		return em.createQuery(getGicsSectors,GicsSector.class).getResultList();
	}

	@Override
	public GicsSector findSectorByIndustryGroupId(Integer industryGroupId) {
		String getGicsSector = "SELECT s FROM GicsSector s " +
							   "JOIN  s.industryGroups g " +
				               "WHERE g.id = :groupId";
		TypedQuery<GicsSector> query = em.createQuery(getGicsSector, GicsSector.class);
		query.setParameter("groupId", industryGroupId);
		return query.getSingleResult();
	}

	@Override
	public GicsIndustryGroup findIndustryGroupById(Integer id) {
		return em.find(GicsIndustryGroup.class, id);
	}
}

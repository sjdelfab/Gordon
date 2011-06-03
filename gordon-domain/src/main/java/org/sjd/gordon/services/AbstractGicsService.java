package org.sjd.gordon.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.sjd.gordon.model.GicsIndustryGroup;
import org.sjd.gordon.model.GicsSector;

public abstract class AbstractGicsService implements GicsService {

	abstract protected EntityManager getEntityManager();
	
	@Override
	public GicsSector findSectorById(Integer id) { 
        return getEntityManager().find(GicsSector.class, id); 
    }
	
	@Override
	public GicsSector createSector(GicsSector sector) { 
		getEntityManager().persist(sector); 
        return sector; 
    }
	
	@Override
	public List<GicsSector> getSectors() {
		String getGicsSectors = "SELECT s FROM GicsSector s";
		return getEntityManager().createQuery(getGicsSectors,GicsSector.class).getResultList();
	}

	@Override
	public GicsSector findSectorByIndustryGroupId(Integer industryGroupId) {
		String getGicsSector = "SELECT s FROM GicsSector s " +
							   "JOIN  s.industryGroups g " +
				               "WHERE g.id = :groupId";
		TypedQuery<GicsSector> query = getEntityManager().createQuery(getGicsSector, GicsSector.class);
		query.setParameter("groupId", industryGroupId);
		return query.getSingleResult();
	}

	@Override
	public GicsIndustryGroup findIndustryGroupById(Integer id) {
		return getEntityManager().find(GicsIndustryGroup.class, id);
	}
	
	@Override
	public GicsIndustryGroup findIndustryGroupByName(String industryGroup) {
		String getGicsSectors = "SELECT g FROM GicsIndustryGroup g WHERE g.name = :name";
		TypedQuery<GicsIndustryGroup> query = getEntityManager().createQuery(getGicsSectors,GicsIndustryGroup.class);
		query.setParameter("name", industryGroup);
		return query.getSingleResult();
	}
}

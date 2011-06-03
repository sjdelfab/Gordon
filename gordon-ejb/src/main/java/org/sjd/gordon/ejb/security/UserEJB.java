package org.sjd.gordon.ejb.security;

import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.sjd.gordon.model.User;
import org.sjd.gordon.services.AbstractUserService;

@Stateless
public class UserEJB extends AbstractUserService implements UserServiceLocal {

	@PersistenceContext
    private EntityManager em;
	
	@Override
	@RolesAllowed({"ADMIN"})
	public void delete(User user) {
		super.delete(user);
	}

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}
	
}

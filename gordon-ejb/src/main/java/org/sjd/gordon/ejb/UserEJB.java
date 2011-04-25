package org.sjd.gordon.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.sjd.gordon.model.User;

@Stateless
public class UserEJB {

	@PersistenceContext
    private EntityManager em;
	
	public User getUser(String userName) {
		String getUser = "SELECT u FROM User u WHERE u.username = :uid";
    	TypedQuery<User> query = em.createQuery(getUser, User.class);
    	query.setParameter("uid", userName);
    	List<User> users = query.getResultList();
    	if (users.isEmpty()) {
    		return null;
    	}
    	return users.get(0);
	}
}

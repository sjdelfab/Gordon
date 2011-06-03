package org.sjd.gordon.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.sjd.gordon.model.Group;
import org.sjd.gordon.model.User;


public abstract class AbstractUserService implements UserService {

	abstract protected EntityManager getEntityManager();
	
	@Override
	public User getUser(String userName) {
		String getUser = "SELECT u FROM User u WHERE u.username = :uid";
    	TypedQuery<User> query = getEntityManager().createQuery(getUser, User.class);
    	query.setParameter("uid", userName);
    	List<User> users = query.getResultList();
    	if (users.isEmpty()) {
    		return null;
    	}
    	return users.get(0);
	}

	@Override
	public List<User> getUsers() {
		String getUser = "SELECT u FROM User u ORDER BY u.lastName";
    	TypedQuery<User> query = getEntityManager().createQuery(getUser, User.class);
    	List<User> users = query.getResultList();
		return users;
	}
	
	@Override
	public void delete(User user) {
		getEntityManager().remove(getEntityManager().merge(user));
	}
	
	@Override
	public User findUserById(Integer id) {
		return getEntityManager().find(User.class, id);
	}

	@Override
	public Group findGroupByName(String name) {
		String getGroup = "SELECT g FROM Group g WHERE g.name = :name";
    	TypedQuery<Group> query = getEntityManager().createQuery(getGroup, Group.class);
    	query.setParameter("name", name);
    	List<Group> groups = query.getResultList();
    	if (groups.isEmpty()) {
    		return null;
    	}
    	return groups.get(0);
	}

	@Override
	public User createUser(User newUser) {
		getEntityManager().persist(newUser);
	    return newUser;
	}

	@Override
	public User updateUser(User user) {
		return getEntityManager().merge(user);
	}
}

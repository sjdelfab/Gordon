package org.sjd.gordon.ejb.security;

import javax.ejb.Local;

import org.sjd.gordon.model.User;

@Local
public interface UserService {

	public User getUser(String userName);
}

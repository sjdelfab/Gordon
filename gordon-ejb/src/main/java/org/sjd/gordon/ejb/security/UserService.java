package org.sjd.gordon.ejb.security;

import java.util.List;

import javax.ejb.Local;

import org.sjd.gordon.model.Group;
import org.sjd.gordon.model.User;

@Local
public interface UserService {

	public User getUser(String userName);
	public List<User> getUsers();
	public void delete(User user);
	public User findUserById(Integer id);
	public Group findGroupByName(String name);
	public User createUser(User newUser);
	public User updateUser(User user);
}

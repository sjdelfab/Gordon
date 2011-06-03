package org.sjd.gordon.services;

import java.util.List;

import org.sjd.gordon.model.Group;
import org.sjd.gordon.model.User;

public interface UserService {

	public User getUser(String userName);
	public List<User> getUsers();
	public void delete(User user);
	public User findUserById(Integer id);
	public Group findGroupByName(String name);
	public User createUser(User newUser);
	public User updateUser(User user);
	
}

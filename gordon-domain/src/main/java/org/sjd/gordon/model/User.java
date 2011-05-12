package org.sjd.gordon.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name = "users")
@SequenceGenerator(schema="GORDON",name = "userIdSeqGenerator", sequenceName  = "SEQ_USER",initialValue=1, allocationSize=1)
public class User {

	@Id @GeneratedValue(generator="userIdSeqGenerator")
	private Integer id;
	@Version 
    private Integer version;
	@Column(nullable = false,name="first_name")
	private String firstName;
	@Column(nullable = false,name="last_name")
	private String lastName;
	@Column(nullable = false)
	private String username;
	@Column(nullable = false)
	private String password;
	@Column(nullable = false)
	private Boolean active; 
	@OneToMany 
	@JoinTable(name = "USER_GROUPS", 
	        joinColumns = @JoinColumn(name = "user_id"), 
	        inverseJoinColumns = @JoinColumn(name = "group_id") ) 
    private List<Group> groups; 

	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getVersion() {
		return version;
	}
	
	public void setVersion(Integer version) {
		this.version = version;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setActive(Boolean active) {
		this.active = active;
	}
	
	public Boolean isActive() {
		return active;
	}
	
	public List<Group> getGroups() {
		return groups;
	}
	
	public void setGroups(List<Group> groups) {
		this.groups = groups;
	}
	
	public void addGroup(Group group) {
		if (groups == null) {
			groups = new ArrayList<Group>();
		}
		groups.add(group);
	}

}

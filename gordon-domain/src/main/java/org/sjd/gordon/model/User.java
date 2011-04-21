package org.sjd.gordon.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name = "authentication_user")
@SequenceGenerator(schema="GORDON",name = "userIdSeqGenerator", sequenceName  = "SEQ_USER",initialValue=1, allocationSize=1)
public class User {

	@Id @GeneratedValue(generator="stockIdSeqGenerator")
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
	
	public Integer getId() {
		return id;
	}
	
	public Integer getVersion() {
		return version;
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
	
}

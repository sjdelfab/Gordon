package org.sjd.gordon.shared.security;

import java.io.Serializable;
import java.util.ArrayList;

import com.extjs.gxt.ui.client.data.BaseModel;

public class UserDetail extends BaseModel implements Serializable {

	private static final long serialVersionUID = -2688147302129711969L;

	public static final String[] DEFINED_ROLES = {"USER","ADMIN"}; 
	
	public static final String FIRST_NAME = "first_name";
	public static final String LAST_NAME = "last_name";
	public static final String UID = "uid";
	public static final String ROLES = "roles";	
	public static final String ACTIVE = "enabled";
	
	private Integer id;
	private Integer version;
	private char[] password;
	
	public UserDetail() {}
	
	public UserDetail(UserDetail detail) {
		setId(detail.getId());
		setVersion(detail.getVersion());
		setFirstName(detail.getFirstName());
		setLastName(detail.getLastName());
		setUID(detail.getUID());
		setActive(detail.isActive());
		setRoles(detail.getRoles());
	}
	
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

	public void setPassword(char[] newPassword) {
		this.password = newPassword;
	}
	
	public char[] getPassword() {
		return password;
	}
	
	public String getFirstName() {
		return (String)get(FIRST_NAME);
	}
	
	public void setFirstName(String firstName) {
		set(FIRST_NAME,firstName);
	}
	
	public String getLastName() {
		return (String)get(LAST_NAME);
	}
	
	public void setLastName(String lastName) {
		set(LAST_NAME,lastName);
	}

	public String getUID() {
		return (String)get(UID);
	}
	
	public void setUID(String uid) {
		set(UID,uid);
	}

	public Boolean isActive() {
		return (Boolean)get(ACTIVE);
	}
	
	public void setActive(Boolean active) {
		set(ACTIVE,active);
	}
	
	public String getRoles() {
		return (String)get(ROLES);
	}
	
	public void setRoles(ArrayList<String> roles) {
		set(ROLES,toString(roles));
	}
	
	public void setRoles(String roles) {
		set(ROLES,roles);
	}
	
	private String toString(ArrayList<String> roles) {
		String rolesString = "";
		for(int i=0; i < roles.size(); i++) {
			rolesString += roles.get(i);
			if (i < roles.size()-1) {
				rolesString += ",";
			}
		}
		return rolesString;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserDetail other = (UserDetail) obj;
		if (getUID() == null) {
			if (other.getUID() != null)
				return false;
		} else if (!getUID().equals(other.getUID()))
			return false;
		return true;
	}

	public void mergeTo(UserDetail newUserDetails) {
		setFirstName(newUserDetails.getFirstName());
		setLastName(newUserDetails.getLastName());
		setRoles(newUserDetails.getRoles());
		setActive(newUserDetails.isActive());
	}	
	
}

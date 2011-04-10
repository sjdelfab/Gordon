package org.sjd.gordon.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

@MappedSuperclass 
@Inheritance(strategy = InheritanceType.JOINED)
public class SecurityEntity implements Serializable {

	private static final long serialVersionUID = 5651612325834821120L;

	@Id @GeneratedValue
	private Long id;
	@Version 
    private Integer version;
	@Column(nullable = false)
	private String name;
	@Column(nullable = false)
	private String code;
	
	public Integer getVersion() {
		return version;
	}
	
	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}	
	
}

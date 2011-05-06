package org.sjd.gordon.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;
import javax.persistence.SequenceGenerator;
import javax.persistence.Version;

@MappedSuperclass 
@Inheritance(strategy = InheritanceType.JOINED)
@SequenceGenerator(schema="GORDON",name = "stockIdSeqGenerator", sequenceName  = "SEQ_SECURITY_ENTITY",initialValue=1, allocationSize=1)
public class SecurityEntity implements Serializable {

	private static final long serialVersionUID = 5651612325834821120L;

	@Id @GeneratedValue(generator="stockIdSeqGenerator")
	private Long id;
	@Version 
    private Integer version;
	@Column(nullable = false)
	private String name;
	@Column(nullable = false)
	private String code;
	@Column(nullable = false)
	private boolean active;
	
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
	
	public void setVersion(Integer version) {
		this.version = version;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
	
	public boolean isActive() {
		return active;
	}
	
}

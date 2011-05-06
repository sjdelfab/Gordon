package org.sjd.gordon.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name = "GICS_INDUSTRY_GROUP")
@SequenceGenerator(schema="GORDON",name = "gicsIndustryGrpIdSeqGenerator", sequenceName  = "SEQ_GICS_INDUSTRY_GRP_SECTOR",initialValue=1, allocationSize=1)
public class GicsIndustryGroup implements Serializable {

	private static final long serialVersionUID = -8396033698375773535L;
	
	@Id @GeneratedValue(generator="gicsIndustryGrpIdSeqGenerator")
    private Integer id;
	@Version 
    private Integer version;
	@Column(nullable = false,name="active")
    private boolean active;
	@Column(nullable = false,name="code")
    private Integer code;
	@Column(nullable = false,name="name")
    private String name;

    public Integer getVersion() {
		return version;
	}
    
	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}
    
	public void setId(Integer id) {
		this.id = id;
	}
}

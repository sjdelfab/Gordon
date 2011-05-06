package org.sjd.gordon.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name = "GICS_SECTOR")
@SequenceGenerator(schema="GORDON",name = "gicsSectorIdSeqGenerator", sequenceName  = "SEQ_GICS_SECTOR",initialValue=1, allocationSize=1)
public class GicsSector implements Serializable {

	private static final long serialVersionUID = -587761247233826402L;
	
	@Id @GeneratedValue(generator="gicsSectorIdSeqGenerator")
    private Integer id;
	@Version 
    private Integer version;
	@Column(nullable = false,name="active")
    private boolean active;
	@Column(nullable = false,name="code")
    private Integer code;
	@Column(nullable = false,name="name")
    private String name;
	@OneToMany(fetch = FetchType.LAZY,
			cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
	@JoinColumn(name = "sector_id") 
	private List<GicsIndustryGroup> industryGroups;
	
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
	
	public void setIndustryGroups(List<GicsIndustryGroup> industryGroups) {
		this.industryGroups = industryGroups;
	}
	
	public List<GicsIndustryGroup> getIndustryGroups() {
		return industryGroups;
	}
	
	public void addIndustryGroup(GicsIndustryGroup group) {
		if (industryGroups == null) {
			industryGroups = new ArrayList<GicsIndustryGroup>();
		}
		industryGroups.add(group);
	}
	
}

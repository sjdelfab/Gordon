package org.sjd.gordon.model;

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
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name = "ui_group")
public class DisplayGroup {

	@Id @GeneratedValue
	private Integer id;
	@Version 
    private Integer version;
	@Column(nullable = false)
	private String name;
	@OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
	@JoinColumn(name = "group_id") 
	private List<UnitaryPropertyDGM> unitaryMembers;
	@OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
	@JoinColumn(name = "group_id") 
	private List<TabularDGM> datasetMembers;

	private Boolean active;
	
	public Integer getVersion() {
		return version;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public List<UnitaryPropertyDGM> getUnitaryMembers() {
		return unitaryMembers;
	}
	
	public void setUnitaryMembers(List<UnitaryPropertyDGM> members) {
		this.unitaryMembers = members;
	}
	
	public Boolean getActive() {
		return active;
	}
	
	public void setActive(Boolean active) {
		this.active = active;
	}
	
	public Integer getId() {
		return id;
	}
	
	public void addMember(UnitaryPropertyDGM groupMember) {
		if (unitaryMembers == null) {
			unitaryMembers = new ArrayList<UnitaryPropertyDGM>();
		}
		unitaryMembers.add(groupMember);
	}

	public List<TabularDGM> getDatasetMembers() {
		return datasetMembers;
	}

	public void setDatasetMembers(List<TabularDGM> datasetMembers) {
		this.datasetMembers = datasetMembers;
	}
	
	public void addMember(TabularDGM groupMember) {
		if (datasetMembers == null) {
			datasetMembers = new ArrayList<TabularDGM>();
		}
		datasetMembers.add(groupMember);
	}	
	
}

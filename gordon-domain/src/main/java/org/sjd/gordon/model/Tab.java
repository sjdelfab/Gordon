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
@Table(name = "ui_tab")
public class Tab {

	@Id @GeneratedValue
	private Integer id;
	@Version 
    private Integer version;
	@Column(nullable = false)
	private String name;
	@Column(nullable = false,name="tab_order")
	private Integer order;
	@Column(nullable = false)
	private Boolean active;
	@OneToMany(fetch = FetchType.EAGER,
			cascade = {CascadeType.ALL})
	@JoinColumn(name = "tab_id")
	private List<DisplayGroup> groups;
	
	public Integer getVersion() {
		return version;
	}
	
	public Integer getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Integer getOrder() {
		return order;
	}
	
	public void setOrder(Integer order) {
		this.order = order;
	}
	
	public Boolean getActive() {
		return active;
	}
	
	public void setActive(Boolean active) {
		this.active = active;
	}
	public List<DisplayGroup> getGroups() {
		return groups;
	}
	
	public void setGroups(List<DisplayGroup> groups) {
		this.groups = groups;
	}
	
	public void addGroup(DisplayGroup group) {
		if (groups == null) {
		   groups = new ArrayList<DisplayGroup>();	
		}
		groups.add(group);
	}
}

package org.sjd.gordon.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

@MappedSuperclass 
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class DisplayGroupMember {
	
	@Id @GeneratedValue
	private Integer id;
	@Version 
    private Integer version;
	@Column(nullable = false,name="member_order")
	private Integer order;

	public Integer getVersion() {
		return version;
	}
	
	public Integer getOrder() {
		return order;
	}
	
	public void setOrder(Integer order) {
		this.order = order;
	}
	
	public Integer getId() {
		return id;
	}
	
}

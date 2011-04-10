package org.sjd.gordon.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity 
@Table(name = "unitary_property_definition") 
public class UnitaryPropertyDefinition {

	@Id @GeneratedValue
	private Integer id;
	@Version 
    private Integer version;
	@Column(nullable = false)
	private String name;
	@Column(nullable = false)
	@Enumerated(EnumType.STRING) 
	private DataType type;
	@Column(length = 100) 
	private String description;
	
	public Integer getId() {
		return id;
	}
	
	public Integer getVersion() {
		return version;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public DataType getType() {
		return type;
	}
	
	public void setType(DataType type) {
		this.type = type;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	
}

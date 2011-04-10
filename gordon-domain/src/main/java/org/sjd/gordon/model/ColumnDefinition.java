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
@Table(name = "column_definition")
public class ColumnDefinition {

	@Id @GeneratedValue
	private Integer id;
	@Version 
    private Integer version;
	@Column(nullable = false,name = "column_order")
	private Integer columnOrder;
	@Column(nullable = false,length = 30)
	private String name;
	@Column(nullable = true,length = 100)
	private String description;
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private DataType type;
	
	public Integer getVersion() {
		return version;
	}
	
	public Integer getColumnOrder() {
		return columnOrder;
	}
	
	public void setColumnOrder(Integer columnOrder) {
		this.columnOrder = columnOrder;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public DataType getType() {
		return type;
	}
	
	public void setType(DataType type) {
		this.type = type;
	}
	
	public Integer getId() {
		return id;
	}
	
}

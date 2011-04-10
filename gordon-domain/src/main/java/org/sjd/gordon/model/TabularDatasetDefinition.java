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
@Table(name = "tabular_dataset_definition")
public class TabularDatasetDefinition {

	@Id @GeneratedValue
	private Integer id;
	@Version 
    private Integer version;
	@Column(nullable = false,length = 30)
	private String name;
	@Column(nullable = true,length = 100)
	private String description;
	@OneToMany(fetch = FetchType.EAGER,
			cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
	@JoinColumn(name = "dataset_id") 
	private List<ColumnDefinition> columnDefinitions;
	
	public Integer getVersion() {
		return version;
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
	
	public List<ColumnDefinition> getColumnDefinitions() {
		return columnDefinitions;
	}
	
	public void setColumnDefinitions(List<ColumnDefinition> definitions) {
		this.columnDefinitions = definitions;
	}
	
	public Integer getId() {
		return id;
	}

	public void addColumnDefinition(ColumnDefinition definition) {
		if (columnDefinitions == null) {
			columnDefinitions = new ArrayList<ColumnDefinition>();
		}
		columnDefinitions.add(definition);
	}
	
}

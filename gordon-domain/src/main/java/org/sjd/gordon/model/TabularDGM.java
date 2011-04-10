package org.sjd.gordon.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ui_tabular_dgm")
public class TabularDGM extends DisplayGroupMember {

	@ManyToOne
	@JoinColumn(name="DATASET_ID",nullable = false, referencedColumnName="ID")
	private TabularDatasetDefinition datasetType;
	@Column(nullable = false)
	@Enumerated(EnumType.STRING) 
	private DatasetVisualisationType visualisationType;
	
	public TabularDatasetDefinition getDatasetType() {
		return datasetType;
	}
	
	public void setDatasetType(TabularDatasetDefinition datasetType) {
		this.datasetType = datasetType;
	}
	
	public DatasetVisualisationType getVisualisationType() {
		return visualisationType;
	}
	
	public void setVisualisationType(DatasetVisualisationType visualisationType) {
		this.visualisationType = visualisationType;
	}
	
}

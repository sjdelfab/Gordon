package org.sjd.gordon.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ui_unitary_dgm")
public class UnitaryPropertyDGM extends DisplayGroupMember {

	@ManyToOne
	@JoinColumn(name="TYPE_ID",nullable = false, referencedColumnName="ID")
	private UnitaryPropertyDefinition dataType;
	
	public UnitaryPropertyDefinition getDataType() {
		return dataType;
	}
	public void setDataType(UnitaryPropertyDefinition dataType) {
		this.dataType = dataType;
	}
}

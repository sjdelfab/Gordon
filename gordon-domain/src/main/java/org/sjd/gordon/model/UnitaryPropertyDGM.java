package org.sjd.gordon.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ui_unitary_dgm")
public class UnitaryPropertyDGM extends DisplayGroupMember {

	@ManyToOne
	@JoinColumn(name="DEFINITION_ID",nullable = false, referencedColumnName="ID")
	private UnitaryPropertyDefinition propertyDefinition;
	
	public UnitaryPropertyDefinition getPropertyDefinition() {
		return propertyDefinition;
	}
	
	public void setPropertyDefinition(UnitaryPropertyDefinition propertyDefinition) {
		this.propertyDefinition = propertyDefinition;
	}
}

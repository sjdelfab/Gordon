package org.sjd.gordon.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

@Entity
@Table(name = "tabular_dataset")
public class TabularDatasetElement {
	
	@Id @GeneratedValue
	private Long id;
	@Version 
    private Integer version;
	@Column(nullable = false,name = "entity_id")
	private Long entityId;
	@ManyToOne
	@JoinColumn(name="COLUMN_DEFINITION_ID",nullable = false, referencedColumnName="ID")
	private ColumnDefinition columnDefinition;
	@Column(nullable = false,name = "row_index")
	private Long rowIndex;
	
	@Column(nullable = true,name="real_number_value")
	private BigDecimal realNumberValue;
	@Column(nullable = true,name="integer_value")
	private Integer integerValue;
	@Column(nullable = true,name="long_value")
	private Long longValue;
	@Column(nullable = true,name="datetime_value")
	@Temporal(TemporalType.TIMESTAMP) 
	private Date dateTimeValue;
	@Column(nullable = true,name="date_value")
	@Temporal(TemporalType.DATE) 
	private Date dateValue;
	@Column(nullable = true,name="string_value")
	private String stringValue;
	@Column(nullable = true,name="enum_value")
	private String enumValue;
	@Column(nullable = true,name="enums")
	private String enums;
	
	public Integer getVersion() {
		return version;
	}
	
	public Long getEntityId() {
		return entityId;
	}
	
	public void setEntityId(Long entityId) {
		this.entityId = entityId;
	}
	
	public ColumnDefinition getColumnDefinition() {
		return columnDefinition;
	}
	
	public void setColumnDefinition(ColumnDefinition definition) {
		this.columnDefinition = definition;
	}
	
	public Long getRowIndex() {
		return rowIndex;
	}
	
	public void setRowIndex(Long rowIndex) {
		this.rowIndex = rowIndex;
	}
	
	public BigDecimal getRealNumberValue() {
		return realNumberValue;
	}
	
	public void setRealNumberValue(BigDecimal realNumberValue) {
		this.realNumberValue = realNumberValue;
	}
	
	public Integer getIntegerValue() {
		return integerValue;
	}
	
	public void setIntegerValue(Integer integerValue) {
		this.integerValue = integerValue;
	}
	
	public Long getLongValue() {
		return longValue;
	}
	
	public void setLongValue(Long longValue) {
		this.longValue = longValue;
	}
	
	public Date getDateTimeValue() {
		return dateTimeValue;
	}
	
	public void setDateTimeValue(Date dateTimeValue) {
		this.dateTimeValue = dateTimeValue;
	}
	
	public Date getDateValue() {
		return dateValue;
	}
	
	public void setDateValue(Date dateValue) {
		this.dateValue = dateValue;
	}
	
	public String getStringValue() {
		return stringValue;
	}
	
	public void setStringValue(String stringValue) {
		this.stringValue = stringValue;
	}
	
	public String getEnumValue() {
		return enumValue;
	}
	
	public void setEnumValue(String enumValue) {
		this.enumValue = enumValue;
	}
	
	public String getEnums() {
		return enums;
	}
	
	public void setEnums(String enums) {
		this.enums = enums;
	}
	
	public Long getId() {
		return id;
	}
	
}

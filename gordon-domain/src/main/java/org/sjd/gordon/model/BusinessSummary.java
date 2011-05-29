package org.sjd.gordon.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name = "business_summary")
@SequenceGenerator(schema="GORDON",name = "businessSummaryIdSeqGenerator", sequenceName  = "SEQ_BUSINESS_SUMMARY",initialValue=1, allocationSize=1)
public class BusinessSummary implements Serializable {

	private static final long serialVersionUID = 681789753779341183L;

	@Id @GeneratedValue(generator="businessSummaryIdSeqGenerator")
    private Long id;
	@Version 
    private Integer version;
	@Column(nullable = false,name="stock_id")
	private Long stockId;
	@Column(name="SUMMARY",nullable = false,length=1000)
	private String summary;

	public BusinessSummary() { }
	
	public BusinessSummary(BusinessSummary businessSummary) {
		id = businessSummary.getId();
		stockId = businessSummary.getStockId();
		version = businessSummary.version;
		this.summary = businessSummary.getSummary();
	}

	public Long getId() {
		return id;
	}
	
	public void setStockId(Long stockId) {
		this.stockId = stockId;
	}
	
	public Long getStockId() {
		return stockId;
	}
	
	public void setSummary(String summary) {
		this.summary = summary;
	}
	
	public String getSummary() {
		return summary;
	}
	
	public Integer getVersion() {
		return version;
	}
}

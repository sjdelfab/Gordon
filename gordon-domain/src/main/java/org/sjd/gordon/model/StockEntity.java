package org.sjd.gordon.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "stock")
public class StockEntity extends SecurityEntity implements Serializable {

	private static final long serialVersionUID = -1729116164665674381L;

	@ManyToOne
	@JoinColumn(name="EXCHANGE_ID",nullable = false, referencedColumnName="ID")
	private Exchange exchange;
	@ManyToOne
	@JoinColumn(name="INDUSTRY_GRP_ID",nullable = true, referencedColumnName="ID")
	private GicsIndustryGroup gicsIndustryGroup;
	
	public StockEntity() {}
	
	public Exchange getExchange() {
		return exchange;
	}
	
	public void setExchange(Exchange exchange) {
		this.exchange = exchange;
	}
	
	public GicsIndustryGroup getGicsIndustryGroup() {
		return gicsIndustryGroup;
	}
	
	public void setGicsIndustryGroup(GicsIndustryGroup industryGroup) {
		this.gicsIndustryGroup = industryGroup;
	}

}

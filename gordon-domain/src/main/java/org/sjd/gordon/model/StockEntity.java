package org.sjd.gordon.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name = "stock")
@SequenceGenerator(schema="GORDON",name="stockIdSeqGenerator", sequenceName="SEQ_SECURITY_ENTITY",initialValue=1, allocationSize=1)
public class StockEntity implements Serializable {

	private static final long serialVersionUID = -1729116164665674381L;

	@Id @GeneratedValue(generator="stockIdSeqGenerator")
	private Long id;
	@Version 
    private Integer version;
	@Column(nullable = false)
	private String name;
	@Column(nullable = false)
	private String code;
	@Column(nullable = false)
	private boolean active;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
	@JoinColumn(name = "stock_id") 
	private List<Dividend> dividendHistory;

	@OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
	@JoinColumn(name = "stock_id") 
	private List<TreasuryHeldStock> treasuryHeldStockHistory;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
	@JoinColumn(name = "stock_id") 
	private List<StockSplit> stockSplits;

	@OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
	@JoinColumn(name = "business_summary_id") 
	private BusinessSummary businessSummary;

	
	@ManyToOne
	@JoinColumn(name="EXCHANGE_ID",nullable = false, referencedColumnName="ID")
	private Exchange exchange;
	@ManyToOne
	@JoinColumn(name="INDUSTRY_GRP_ID",nullable = true, referencedColumnName="ID")
	private GicsIndustryGroup gicsIndustryGroup;
	@Column(name="FLOAT_VOLUME",nullable = false,updatable=false)
	private Integer floatVolume = Integer.valueOf(0);
	
	public StockEntity() {}
	
	public Integer getVersion() {
		return version;
	}
	
	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	public void setVersion(Integer version) {
		this.version = version;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
	
	public boolean isActive() {
		return active;
	}
	
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
	
	public void setFloatVolume(Integer floatVolume) {
		this.floatVolume = floatVolume;
	}
	
	public Integer getFloatVolume() {
		return floatVolume;
	}

	public List<Dividend> getDividendHistory() {
		return dividendHistory;
	}
	
	public Dividend getLastDividendInList() {
		if (dividendHistory == null) {
			return null;
		}
		return dividendHistory.get(dividendHistory.size()-1); 
	}

	public void setDividendHistory(List<Dividend> dividendHistory) {
		this.dividendHistory = dividendHistory;
	}

	public List<TreasuryHeldStock> getTreasuryHeldStockHistory() {
		return treasuryHeldStockHistory;
	}

	public TreasuryHeldStock getLastTreasuryHeldStockInList() {
		if (treasuryHeldStockHistory == null) {
			return null;
		}
		return treasuryHeldStockHistory.get(treasuryHeldStockHistory.size()-1); 
	}
	
	public void setTreasuryHeldStockHistory(List<TreasuryHeldStock> treasuryHeldStockHistory) {
		this.treasuryHeldStockHistory = treasuryHeldStockHistory;
	}

	public BusinessSummary getBusinessSummary() {
		return businessSummary;
	}

	public void setBusinessSummary(BusinessSummary businessSummary) {
		this.businessSummary = businessSummary;
	}

	public List<StockSplit> getStockSplits() {
		return stockSplits;
	}

	public StockSplit getLastStockSplitInList() {
		if (stockSplits == null) {
			return null;
		}
		return stockSplits.get(stockSplits.size()-1); 
	}

	public void setStockSplits(List<StockSplit> stockSplits) {
		this.stockSplits = stockSplits;
	}

	public void addStockSplit(StockSplit stockSplit) {
		if (stockSplits == null) {
			stockSplits = new ArrayList<StockSplit>();
		}
		stockSplits.add(stockSplit);
	}

	public void addHeldStock(TreasuryHeldStock treasuryHeldStock) {
		if (treasuryHeldStockHistory == null) {
			treasuryHeldStockHistory = new ArrayList<TreasuryHeldStock>();
		}
		treasuryHeldStockHistory.add(treasuryHeldStock);
	}

	public void addDividend(Dividend dividend) {
		if (dividendHistory == null) {
			dividendHistory = new ArrayList<Dividend>();
		}
		dividendHistory.add(dividend);
	}

}

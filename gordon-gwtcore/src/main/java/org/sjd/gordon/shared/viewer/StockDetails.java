package org.sjd.gordon.shared.viewer;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import org.sjd.gordon.model.StockEntity;
import org.sjd.gordon.shared.registry.GicsIndustryGroupName;
import org.sjd.gordon.shared.registry.GicsSectorName;

import com.extjs.gxt.ui.client.data.BaseModel;

public class StockDetails extends BaseModel implements Serializable {
	
	private static final long serialVersionUID = 2946987725770341556L;
	
	public static final String GICS_PRIMARY_SECTOR_NAME = "primary_sector_name";
	public static final String GICS_PRIMARY_SECTOR_ID = "primary_sector_id";
	public static final String GICS_PRIMARY_INDUSTRY_GROUP_ID = "primary_industry_group_id";
	public static final String GICS_PRIMARY_INDUSTRY_GROUP_NAME = "primary_industry_group_name";
	
	public static final String NAME = "name";
	public static final String CODE = "code";
	public static final String LIST_DATE = "list_date";
	public static final String LAST_TRADE_DATE = "last_trade_date";
	public static final String CURRENT_PRICE = "current_price";
	
	private Long id;
	private Integer version;

	public StockDetails() {}
	
	public StockDetails(StockDetails details) {
		setId(details.getId());
		setVersion(details.getVersion());
		setName(details.getName());
		setCode(details.getCode());
		setPrimarySectorId(details.getPrimarySectorId());
		setPrimarySectorName(details.getPrimarySectorName());
		setPrimaryIndustryGroupId(details.getPrimaryIndustryGroupId());
		setPrimaryIndustryGroupName(details.getPrimaryIndustryGroupName());
		setCurrentPrice(details.getCurrentPrice());
		setListDate(details.getListDate());
		setLastTradeDate(details.getLastTradeDate());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getVersion() {
		return version;
	}
	
	public void setVersion(Integer version) {
		this.version = version;
	}
	
	public String getName() {
		return (String) get(NAME);
	}
	
	public void setName(String name) {
		set(NAME, name);
	}
	
	public String getCode() {
		return (String) get(CODE);
	}
	
	public void setCode(String code) {
		set(CODE, code);
	}

	public Integer getPrimarySectorId() {
		return (Integer) get(GICS_PRIMARY_SECTOR_ID);
	}
	
	public void setPrimarySectorId(Integer sectorId) {
		set(GICS_PRIMARY_SECTOR_ID,sectorId);
	}	
	
	public String getPrimarySectorName() {
		return (String) get(GICS_PRIMARY_SECTOR_NAME);
	}
	
	public void setPrimarySectorName(String sectorName) {
		set(GICS_PRIMARY_SECTOR_NAME,sectorName);
	}
	
	public Integer getPrimaryIndustryGroupId() {
		return (Integer) get(GICS_PRIMARY_INDUSTRY_GROUP_ID);
	}
	
	public void setPrimaryIndustryGroupId(Integer industryGroupId) {
		set(GICS_PRIMARY_INDUSTRY_GROUP_ID, industryGroupId);
	}
	
	public String getPrimaryIndustryGroupName() {
		return (String) get(GICS_PRIMARY_INDUSTRY_GROUP_NAME);
	}
	
	public void setPrimaryIndustryGroupName(String industryName) {
		set(GICS_PRIMARY_INDUSTRY_GROUP_NAME,industryName);
	}	
	
	public GicsIndustryGroupName getIndustryGroupName() {
		GicsIndustryGroupName industryGroupName = new GicsIndustryGroupName();
		industryGroupName.set(GicsIndustryGroupName.GICS_PRIMARY_INDUSTRY_GROUP_ID, getPrimaryIndustryGroupId());
		industryGroupName.set(GicsIndustryGroupName.GICS_PRIMARY_INDUSTRY_GROUP_NAME, getPrimaryIndustryGroupName());
		return industryGroupName;
	}

	public GicsSectorName getSectorName() {
		GicsSectorName sectorName = new GicsSectorName();
		sectorName.set(GicsSectorName.GICS_PRIMARY_SECTOR_ID, getPrimarySectorId());
		sectorName.set(GicsSectorName.GICS_PRIMARY_SECTOR_NAME, getPrimarySectorName());
		return sectorName;
	}

	public Date getListDate() {
		return (Date) get(LIST_DATE);
	}

	public void setListDate(Date listDate) {
		set(LIST_DATE, listDate);
	}
	
	public Date getLastTradeDate() {
		return (Date) get(LAST_TRADE_DATE);
	}
	
    public void setLastTradeDate(Date lastTradeDate) {
		set(LAST_TRADE_DATE, lastTradeDate);
	}

	public BigDecimal getCurrentPrice() {
		return (BigDecimal) get(CURRENT_PRICE);
	}

	public void setCurrentPrice(BigDecimal currentPrice) {
		set(CURRENT_PRICE, currentPrice);
	}	
	
	public static StockDetails fromEntity(StockEntity stockEntity) {
		StockDetails stockDetails = new StockDetails();
		stockDetails.id = stockEntity.getId();
		stockDetails.version = stockEntity.getVersion();
		stockDetails.setName(stockEntity.getName());
		stockDetails.setCode(stockEntity.getCode());
		stockDetails.setPrimaryIndustryGroupId(stockEntity.getGicsIndustryGroup().getId());
		stockDetails.setPrimaryIndustryGroupName(stockEntity.getGicsIndustryGroup().getName());
		return stockDetails;
	}

	public void mergeTo(StockDetails newStockDetails) {
		setCode(newStockDetails.getCode());
		setName(newStockDetails.getName());
		setPrimaryIndustryGroupId(newStockDetails.getPrimaryIndustryGroupId());
		setPrimaryIndustryGroupName(newStockDetails.getPrimaryIndustryGroupName());
		setPrimarySectorId(newStockDetails.getPrimarySectorId());
		setPrimarySectorName(newStockDetails.getPrimarySectorName());
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StockDetails other = (StockDetails) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public void setPrimarySector(GicsSectorName sector) {
		setPrimarySectorId(sector.getId());
		setPrimarySectorName(sector.getName());
	}

	public void setPrimaryIndustryGroup(GicsIndustryGroupName industryGroup) {
		setPrimaryIndustryGroupId(industryGroup.getId());
		setPrimaryIndustryGroupName(industryGroup.getName());
	}

}

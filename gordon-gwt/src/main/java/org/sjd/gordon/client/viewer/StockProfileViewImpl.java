package org.sjd.gordon.client.viewer;

import java.util.ArrayList;

import org.sjd.gordon.client.common.NamePairTable;
import org.sjd.gordon.client.common.NamePairTableUIHandlerAdapter;
import org.sjd.gordon.client.common.TableNameValuePair;
import org.sjd.gordon.model.BusinessSummary;
import org.sjd.gordon.shared.viewer.StockDetail;
import org.sjd.gordon.shared.viewer.StockProfile;
import org.sjd.gordon.shared.viewer.StockStatistics;
import org.sjd.gordon.shared.viewer.ValuationMeasures;

import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.util.Padding;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Dialog;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.TextArea;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.layout.HBoxLayout;
import com.extjs.gxt.ui.client.widget.layout.HBoxLayout.HBoxLayoutAlign;
import com.extjs.gxt.ui.client.widget.layout.HBoxLayoutData;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.inject.name.Named;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;

public class StockProfileViewImpl extends ViewWithUiHandlers<StockProfileUIHandler> implements StockProfilePresenter.StockProfileView {

	private DateTimeFormat dateFormatter = DateTimeFormat.getFormat("dd/MM/yyyy");

	@Inject
	@Named("width")
	private int width;
	@Inject
	@Named("height")
	private int height;
	private MainContainer main;
	private StockProfile stockProfile;
	private LeftHandColumnPanel leftHandColumnPanel;
	private RightHandColumnPanel rightHandColumnPanel;
	
	private static enum InformationToken {
		
		SHARES_OUTSTANDING("http://en.wikipedia.org/wiki/Shares_outstanding"),
		MARKET_CAPITALISATION("http://en.wikipedia.org/wiki/Market_capitalization"),
		TRAILING_PE_RATIO("http://en.wikipedia.org/wiki/Trailing_P/E"),
		FORWARD_PE_RATIO("http://en.wikipedia.org/wiki/Forward_P/E"),
		ENTERPRISE_VALUE("http://en.wikipedia.org/wiki/Enterprise_Value"),
		PRICE_TO_SALES("http://en.wikipedia.org/wiki/Price_to_sales_ratio"),
		PRICE_TO_BOOK("http://en.wikipedia.org/wiki/P/B_ratio"),
		PEG_RATIO("http://en.wikipedia.org/wiki/PEG_ratio"),
		EV_TO_REVENUE_RATIO("http://www.investopedia.com/terms/e/ev-revenue-multiple.asp"),
		EV_TO_EBITDA_RATIO("http://en.wikipedia.org/wiki/EV/EBITDA");
		
		private String url;
		
		private InformationToken(String url) {
			this.url = url;
		}
		
		public String getUrl() {
			return url;
		}
		
	}
	
	public StockProfileViewImpl() {
		main = new MainContainer();
	}

	private class MainContainer extends LayoutContainer {
		
		@Override
		protected void onRender(Element parent, int index) {
			super.onRender(parent, index);
			HBoxLayout layout = new HBoxLayout();  
	        layout.setPadding(new Padding(0));  
	        layout.setHBoxLayoutAlign(HBoxLayoutAlign.TOP);  
	        setLayout(layout);
	        leftHandColumnPanel = new LeftHandColumnPanel(); 
	        add(leftHandColumnPanel, new HBoxLayoutData(new Margins(0, 8, 0, 0)));
	        rightHandColumnPanel = new RightHandColumnPanel();
	        add(rightHandColumnPanel, new HBoxLayoutData(new Margins(0, 5, 0, 0)));
		}
	}
	
	private static void showAboutInfo(TableNameValuePair namePairValue) {
		InformationToken token = (InformationToken)namePairValue.getInformationToken();
		Window.open(token.getUrl(),"_blank",null);
	}
	
	@Override
	public void setProfile(StockProfile stockProfile) {
		this.stockProfile = stockProfile;
		leftHandColumnPanel.load();
		rightHandColumnPanel.load();
	}
	
	@Override
	public void updateBusinessSummary(BusinessSummary summary) {
		this.stockProfile.setBusinessSummary(summary);
		leftHandColumnPanel.updateBusinessSummary(summary);
	}

	@Override
	public Widget asWidget() {
		main.setSize(width, height-90);
		return main;
	}
	
	private int getPreferredWidth() {
		return width;
	}
	
	private int getPreferredHeight() {
		return height;
	}
	
	private class LeftHandColumnPanel extends VerticalPanel {
			
		private NamePairTable detailsTable;
		private BusinessSummaryPanel summary;
		
		private LeftHandColumnPanel() {
			detailsTable = createDetailsTable();
			summary = new BusinessSummaryPanel();
		}
		
		@Override  
		protected void onRender(Element parent, int pos) {  
		    super.onRender(parent, pos);  
		    setSpacing(5);
		    add(detailsTable);
		    add(summary);
		    setSize(getPreferredWidth()/2 - 15, getPreferredHeight()-110);
		}
		
		private void load() {
			ArrayList<TableNameValuePair> data = new ArrayList<TableNameValuePair>(6);
			StockDetail stockDetails = stockProfile.getDetail();
			data.add(new TableNameValuePair("Name",stockDetails.getName()));
			data.add(new TableNameValuePair("Symbol",stockDetails.getCode()));
			data.add(new TableNameValuePair("GICS Sector",stockDetails.getPrimarySectorName()));
			data.add(new TableNameValuePair("GICS Industry Group",stockDetails.getPrimaryIndustryGroupName()));
			String listDate = "";
			if (stockDetails.getListDate() != null) {
			   listDate = dateFormatter.format(stockDetails.getListDate());
			}
			data.add(new TableNameValuePair("List Date",listDate));
			String lastTradeDate = "";
			if (stockDetails.getLastTradeDate() != null) {
				lastTradeDate = dateFormatter.format(stockDetails.getLastTradeDate());
			}
			data.add(new TableNameValuePair("Last Trade Date",lastTradeDate));
			detailsTable.setData(data);
			
			summary.setSummary(stockProfile.getBusinessSummary());
			doLayout(true);
		}
		
		private void updateBusinessSummary(BusinessSummary businessSummary) {
			summary.updateBusinessSummary(businessSummary);
		}
	}

	private class BusinessSummaryPanel extends ContentPanel {

		private BusinessSummary summary;
		private Html html;
		
		public BusinessSummaryPanel() {
			setHeading("Business Summary");
			Button editButton = new Button();
			editButton.addSelectionListener(new SelectionListener<ButtonEvent>() {
				@Override
				public void componentSelected(ButtonEvent ce) {
					showBusinessSummaryEditDialog();
				}
			});
			editButton.setIconStyle("update");
			getHeader().addTool(editButton);
			setSize(getPreferredWidth()/2 - 15, getPreferredHeight()-325);
			setBodyStyle("padding: 6px");
			setScrollMode(Scroll.AUTOY);
		}
		
		private void setSummary(BusinessSummary summary) {
			this.summary = summary;
			if (summary != null) {
				html = addText(summary.getSummary());	
			}
		}
		
		private void updateBusinessSummary(BusinessSummary summary) {
			remove(html);
			setSummary(summary);
			doLayout(true);
		}
		
		private void showBusinessSummaryEditDialog() {
			final Dialog editDialog = new Dialog();  
			editDialog.setSize(getPreferredWidth()/2 - 15, getPreferredHeight()-325);
		    editDialog.setHeading("Edit - Business Summary");  
		    editDialog.setButtons(Dialog.OKCANCEL);  
		    editDialog.setBodyStyleName("pad-text");
		    editDialog.setLayout(new FitLayout());
		    final TextArea textArea = new TextArea();
		    if (summary != null) {
		        textArea.setValue(summary.getSummary());
		    }
		    editDialog.add(textArea);  
		    editDialog.setScrollMode(Scroll.AUTOY);  
		    editDialog.setHideOnButtonClick(false);
		    editDialog.getButtonById(Dialog.OK).addSelectionListener(new SelectionListener<ButtonEvent>() {
				@Override
				public void componentSelected(ButtonEvent ce) {
					BusinessSummary newSummary = new BusinessSummary();
					if (summary != null) {
						newSummary = new BusinessSummary(summary);
					}
					newSummary.setSummary(textArea.getValue());
					getUiHandlers().updateBusinessSummary(newSummary);
					editDialog.setVisible(false);
				}
			});
		    editDialog.getButtonById(Dialog.CANCEL).addSelectionListener(new SelectionListener<ButtonEvent>() {
				@Override
				public void componentSelected(ButtonEvent ce) {
					editDialog.setVisible(false);
				}
		    });
		    editDialog.setResizable(false);
		    editDialog.show();
		}

	}
	
	private class AbstractNamePairTableUIHandler extends NamePairTableUIHandlerAdapter {
		
		@Override
		public void showAboutInfo(TableNameValuePair namePairValue) {
			StockProfileViewImpl.showAboutInfo(namePairValue);
		}
	}
		
	private NamePairTable createDetailsTable() {
		AbstractNamePairTableUIHandler uiHandler = new AbstractNamePairTableUIHandler();
		NamePairTable table = new NamePairTable(uiHandler,"Details");
		
		int width = getPreferredWidth()/2 - 15;
		table.setSize(width, 162);
		table.setNameColumnWidth(width/2-3);
		table.setValueColumnWidth(width/2-3);
		return table;
	}
	
	private class RightHandColumnPanel extends VerticalPanel {
		
		private StockPriceHistoryTable stockPriceHistoryTable;
		private ShareStatisticsTable shareStatisticsTable;
		private ValuationMeasuresTable valuationMeasuresTable;
		
		@Override  
		protected void onRender(Element parent, int pos) {  
		    super.onRender(parent, pos);  
		    setSpacing(5);
		    stockPriceHistoryTable = new StockPriceHistoryTable();
		    add(stockPriceHistoryTable);
		    shareStatisticsTable = new ShareStatisticsTable();
		    add(shareStatisticsTable);
		    valuationMeasuresTable = new ValuationMeasuresTable();
		    add(valuationMeasuresTable);
		    setSize(getPreferredWidth()/2 - 10, getPreferredHeight()-110);
		}
		
		private void load() {
			stockPriceHistoryTable.load();
			shareStatisticsTable.load();
			valuationMeasuresTable.load();
			doLayout(true);
		}
		
	}

	private NumberFormat percentageFormat = NumberFormat.getPercentFormat();
	private NumberFormat decimalFormat = NumberFormat.getFormat("##.##");
	
	private class StockPriceHistoryTable extends NamePairTable {
		
		private StockPriceHistoryTable() {
			super(new AbstractNamePairTableUIHandler(),"Stock Price History");
			setShowDateRangeButton(true);
			int width = getPreferredWidth()/2 - 10;
			setSize(width, 125);
			setNameColumnWidth(width/2-3);
			setValueColumnWidth(width/2-3);
		}
		
		private void load() {
			ArrayList<TableNameValuePair> data = new ArrayList<TableNameValuePair>(4);
			StockStatistics stockStatistics = stockProfile.getStockStatistics();
			TableNameValuePair namePair = new TableNameValuePair("52-Week Change",percentageFormat.format(stockStatistics.getPercentageChange()));
			namePair.setCanDateRangeable(true);
			data.add(namePair);
			namePair = new TableNameValuePair("52-Week High",decimalFormat.format(stockStatistics.getHigh()));
			namePair.setCanDateRangeable(true);
			data.add(namePair);
			namePair = new TableNameValuePair("52-Week Low",decimalFormat.format(stockStatistics.getLow()));
			namePair.setCanDateRangeable(true);
			data.add(namePair);
			namePair = new TableNameValuePair("50-Day Moving Average",decimalFormat.format(stockStatistics.getMovingAverage()));
			namePair.setCanDateRangeable(true);
			data.add(namePair);
			setData(data);
		}
	}
	
	private NumberFormat largeIntegerFormat = NumberFormat.getFormat("###,###,###,###");
	
	private class ShareStatisticsTable extends NamePairTable {
		
		private ShareStatisticsTable() {
			super(new AbstractNamePairTableUIHandler(),"Share Statistics");
			setShowDateRangeButton(true);
			setShowEditButton(true);
			setShowInfoButton(true);
			int width = getPreferredWidth()/2 - 10;
			setSize(width, 80);
			setNameColumnWidth(width/2-3);
			setValueColumnWidth(width/2-3);
		}
		
		private void load() {
			StockStatistics stockStatistics = stockProfile.getStockStatistics();
			
			ArrayList<TableNameValuePair> data = new ArrayList<TableNameValuePair>(2);
			TableNameValuePair namePair = new TableNameValuePair("Average Volume (10 days)",largeIntegerFormat.format(stockStatistics.getAverageVolume()));
			namePair.setCanDateRangeable(true);
			data.add(namePair);
			namePair = new TableNameValuePair("Shares Outstanding",largeIntegerFormat.format(stockProfile.getSharesOutstanding()));
			namePair.setEditable(true);
			namePair.setShowInfoIcon(true);
			namePair.setInformationToken(InformationToken.SHARES_OUTSTANDING);
			data.add(namePair);
			setData(data);
		}
	}
	
	private class ValuationMeasuresTable extends NamePairTable {
		
		private ValuationMeasuresTable() {
			super(new AbstractNamePairTableUIHandler(),"Valuation Measures");
			setShowInfoButton(true);
			int width = getPreferredWidth()/2 - 10;
			setSize(width, 235);
			setNameColumnWidth(width/2-3);
			setValueColumnWidth(width/2-3);
		}
		
		private void load() {
			ValuationMeasures valuationMeasures = stockProfile.getValuationMeasures();
			
			ArrayList<TableNameValuePair> data = new ArrayList<TableNameValuePair>(9);
			TableNameValuePair namePair = new TableNameValuePair("Marketing Capitalisation",largeIntegerFormat.format(valuationMeasures.getMarketCapitalisation()));
			namePair.setInformationToken(InformationToken.MARKET_CAPITALISATION);
			namePair.setShowInfoIcon(true);
			data.add(namePair);
			namePair = new TableNameValuePair("Enterprise Value",largeIntegerFormat.format(valuationMeasures.getEnterpriseValue()));
			namePair.setInformationToken(InformationToken.ENTERPRISE_VALUE);
			namePair.setShowInfoIcon(true);
			data.add(namePair);
			namePair = new TableNameValuePair("Trailing P/E",decimalFormat.format(valuationMeasures.getTrailingPE()));
			namePair.setInformationToken(InformationToken.TRAILING_PE_RATIO);
			namePair.setShowInfoIcon(true);
			data.add(namePair);
			namePair = new TableNameValuePair("Forward P/E",decimalFormat.format(valuationMeasures.getForwardPE()));
			namePair.setInformationToken(InformationToken.FORWARD_PE_RATIO);
			namePair.setShowInfoIcon(true);
			data.add(namePair);
			namePair = new TableNameValuePair("PEG Ratio (5 year expected)",decimalFormat.format(valuationMeasures.getPegRatio()));
			namePair.setInformationToken(InformationToken.PEG_RATIO);
			namePair.setShowInfoIcon(true);
			data.add(namePair);
			namePair = new TableNameValuePair("Price/Sales",decimalFormat.format(valuationMeasures.getPriceToSalesRatio()));
			namePair.setInformationToken(InformationToken.PRICE_TO_SALES);
			namePair.setShowInfoIcon(true);
			data.add(namePair);
			namePair = new TableNameValuePair("Price/Book",decimalFormat.format(valuationMeasures.getPriceToBookRatio()));
			namePair.setInformationToken(InformationToken.PRICE_TO_BOOK);
			namePair.setShowInfoIcon(true);
			data.add(namePair);
			namePair = new TableNameValuePair("Enterprise Value/Revenue",decimalFormat.format(valuationMeasures.getEnterpriseValueToRevenueRatio()));
			namePair.setInformationToken(InformationToken.EV_TO_REVENUE_RATIO);
			namePair.setShowInfoIcon(true);
			data.add(namePair);
			namePair = new TableNameValuePair("Enterprise Value/EBITDA",decimalFormat.format(valuationMeasures.getEnterpriseValueToEBITDA()));
			namePair.setInformationToken(InformationToken.EV_TO_EBITDA_RATIO);
			namePair.setShowInfoIcon(true);
			data.add(namePair);
			setData(data);			
		}
		
	}
	
}

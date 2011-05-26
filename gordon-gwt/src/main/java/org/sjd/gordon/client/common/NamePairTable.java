package org.sjd.gordon.client.common;

import java.util.ArrayList;
import java.util.List;

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionChangedEvent;
import com.extjs.gxt.ui.client.event.SelectionChangedListener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.google.gwt.user.client.Element;

public class NamePairTable extends ContentPanel {

	private Grid<TableNameValuePair> grid;
	private ListStore<TableNameValuePair> store;
	private int nameColumnWidth;
	private int valueColumnWidth;
	private boolean showInfoButton;
	private boolean showGraphButton;
	private boolean showDateRangeButton;
	private boolean showEditButton;
	private Button infoButton, graphButton, dateRangeButton, editButton;
	private NamePairTableUIHandler uiHandler;
	
	public NamePairTable(NamePairTableUIHandler uiHandler, String heading) {
		this.uiHandler = uiHandler;
		setHeading(heading);
		store = new ListStore<TableNameValuePair>();
	}
	
	public void setData(ArrayList<TableNameValuePair> data) {
		store.add(data);
	}
	
	@Override  
	protected void onRender(Element parent, int pos) { 
		super.onRender(parent, pos);
		if (showInfoButton) {
			infoButton = new Button();
			infoButton.addSelectionListener(new SelectionListener<ButtonEvent>() {
				@Override
				public void componentSelected(ButtonEvent ce) {
					showInformationAbout();
				}
			});
			infoButton.setEnabled(false);
			infoButton.setIconStyle("info");
			getHeader().addTool(infoButton);
		}
		if (showGraphButton) {
			graphButton = new Button();
			graphButton.setEnabled(false);
			graphButton.setIconStyle("graph");
			getHeader().addTool(graphButton);
		}
		if (showDateRangeButton) {
			dateRangeButton = new Button();
			dateRangeButton.setEnabled(false);
			dateRangeButton.setIconStyle("calendar");
			getHeader().addTool(dateRangeButton);
		}
		if (showEditButton) {
			editButton = new Button();
			editButton.setEnabled(false);
			editButton.setIconStyle("update");
			getHeader().addTool(editButton);
		}
		
	    List<ColumnConfig> configs = new ArrayList<ColumnConfig>();  
		  
	    ColumnConfig column = new ColumnConfig();  
	    column.setId(TableNameValuePair.NAME);
	    column.setWidth(nameColumnWidth);  
	    configs.add(column);  
	    
	    column = new ColumnConfig();
	    column.setId(TableNameValuePair.VALUE);  
	    column.setWidth(valueColumnWidth);  
	    configs.add(column);  
	    setLayout(new FitLayout());
	    ColumnModel columnModel = new ColumnModel(configs);
	    grid = new Grid<TableNameValuePair>(store, columnModel);
	    grid.getSelectionModel().addSelectionChangedListener(new SelectionChangedListener<TableNameValuePair>() {
			@Override
			public void selectionChanged(SelectionChangedEvent<TableNameValuePair> se) {
				TableNameValuePair selectedValue = se.getSelectedItem();
				enableButton(dateRangeButton,selectedValue.isCanDateRangeable());
				enableButton(editButton,selectedValue.isEditable());
				enableButton(graphButton,selectedValue.isShowGraph());
				enableButton(infoButton,selectedValue.isShowInfoIcon());
			}
		});
	    grid.setBorders(true);
	    grid.setStripeRows(true);
	    grid.setHideHeaders(true);
	    add(grid);
	}
	
	private void showInformationAbout() {
		TableNameValuePair selectedValue = grid.getSelectionModel().getSelectedItem();
		if (selectedValue != null) {
			uiHandler.showAboutInfo(selectedValue);
		}
	}
	
	private void enableButton(Button button, boolean enable) {
		if (enable) {
			button.setEnabled(true);
		} else if (button != null) {
			button.setEnabled(false);
		}
	}
	
	public void setNameColumnWidth(int nameColumnWidth) {
		this.nameColumnWidth = nameColumnWidth;
	}
	
	public void setValueColumnWidth(int valueColumnWidth) {
		this.valueColumnWidth = valueColumnWidth;
	}
	
	public void setShowDateRangeButton(boolean showDateRangeButton) {
		this.showDateRangeButton = showDateRangeButton;
	}
	
	public void setShowEditButton(boolean showEditButton) {
		this.showEditButton = showEditButton;
	}
	
	public void setShowGraphButton(boolean showGraphButton) {
		this.showGraphButton = showGraphButton;
	}
	
	public void setShowInfoButton(boolean showInfoButton) {
		this.showInfoButton = showInfoButton;
	}
}

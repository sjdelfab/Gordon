package org.sjd.gordon.client.viewer;

import java.util.ArrayList;
import java.util.List;

import org.sjd.gordon.client.Gordon;
import org.sjd.gordon.client.gxt.Button;
import org.sjd.gordon.model.StockSplit;
import org.sjd.gordon.model.TreasuryHeldStock;

import com.extjs.gxt.ui.client.Registry;
import com.extjs.gxt.ui.client.data.BeanModel;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.util.Padding;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.layout.HBoxLayout;
import com.extjs.gxt.ui.client.widget.layout.HBoxLayout.HBoxLayoutAlign;
import com.extjs.gxt.ui.client.widget.layout.HBoxLayoutData;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.inject.name.Named;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;

public class StockAdminViewImpl extends ViewWithUiHandlers<StockAdminUIHandler> implements StockAdminPresenter.StockAdminView {

	@Inject
	@Named("width")
	private int width;
	@Inject
	@Named("height")
	private int height;
	private MainContainer main;
	private StockSplitPanel stockSplitPanel;
	private TreasuryHeldStockPanel treasuryHeldStockPanel;
	
	public StockAdminViewImpl() {
		main = new MainContainer();
	}

	private class MainContainer extends LayoutContainer {
		
		@Override
		protected void onRender(Element parent, int index) {
			super.onRender(parent, index);
			HBoxLayout layout = new HBoxLayout();  
	        layout.setPadding(new Padding(10));  
	        layout.setHBoxLayoutAlign(HBoxLayoutAlign.TOP);  
	        setLayout(layout);
	        stockSplitPanel = new StockSplitPanel(); 
	        add(stockSplitPanel, new HBoxLayoutData(new Margins(0, 8, 0, 0)));
	        treasuryHeldStockPanel = new TreasuryHeldStockPanel();
	        add(treasuryHeldStockPanel, new HBoxLayoutData(new Margins(0, 5, 0, 0)));
		}
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
	
	private class StockSplitPanel extends ContentPanel {
		
		private Button addButton, removeButton, updateButton;
		private ListStore<BeanModel> store;
		private Grid<BeanModel> grid;
		
		private StockSplitPanel() {
			setHeading("Stock Split");
			store = Registry.get(Gordon.STOCK_SPLIT);
			setSize(getPreferredWidth()/2 - 15, getPreferredHeight()-165);
		}
		
		@Override
		protected void onRender(Element parent, int pos) {
			super.onRender(parent, pos);
		    List<ColumnConfig> configs = new ArrayList<ColumnConfig>();  
			  
		    ColumnConfig column = new ColumnConfig();  
		    column.setId("date");  
		    column.setHeader("Date");  
		    column.setWidth(277);  
		    configs.add(column);  
		    
		    column = new ColumnConfig();
		    column.setId("factor");  
		    column.setHeader("Factor");  
		    column.setWidth(277);  
		    configs.add(column);
		    
		    ColumnModel columnModel = new ColumnModel(configs);
		    addButton = new Button();
		    addButton.setIconStyle("add");
		    addButton.addClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					//showAddDialog();
				}
			});
		    getHeader().addTool(addButton);
		    removeButton = new Button();
			removeButton.addClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					//deleteSelectedUser();
				}
			});
		    removeButton.setIconStyle("remove");
		    getHeader().addTool(removeButton);
		    updateButton = new Button();
		    updateButton.addClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
//					final UserDetail selected = getSelectedItem();
//					if (selected != null) {
//						//showUpdateDialog(selected);
//					}
				}
			});
		    updateButton.setIconStyle("update");
		    getHeader().addTool(updateButton);	
		    
		    setLayout(new FitLayout());  
		    
		    grid = new Grid<BeanModel>(store, columnModel);  
		    grid.setBorders(true);
		    getAriaSupport().setPresentation(true);  
		    add(grid);  
		}
		
	}
	
	private class TreasuryHeldStockPanel extends ContentPanel {
		
		private Button addButton, removeButton, updateButton;
		private ListStore<BeanModel> store;
		private Grid<BeanModel> grid;
		
		private TreasuryHeldStockPanel() {
			setHeading("Treasury Held Stock");
			store = Registry.get(Gordon.TREASURY_HELD_STOCK);
			setSize(getPreferredWidth()/2 - 15, getPreferredHeight()-165);
		}
		
		@Override
		protected void onRender(Element parent, int pos) {
			super.onRender(parent, pos);
		    List<ColumnConfig> configs = new ArrayList<ColumnConfig>();  
			  
		    ColumnConfig column = new ColumnConfig();  
		    column.setId("date");  
		    column.setHeader("Date");  
		    column.setWidth(277);  
		    configs.add(column);  
		    
		    column = new ColumnConfig();
		    column.setId("Volume");  
		    column.setHeader("Volume");  
		    column.setWidth(277);  
		    configs.add(column);
		    
		    ColumnModel columnModel = new ColumnModel(configs);
		    addButton = new Button();
		    addButton.setIconStyle("add");
		    addButton.addClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					//showAddDialog();
				}
			});
		    getHeader().addTool(addButton);
		    removeButton = new Button();
			removeButton.addClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					//deleteSelectedUser();
				}
			});
		    removeButton.setIconStyle("remove");
		    getHeader().addTool(removeButton);
		    updateButton = new Button();
		    updateButton.addClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
//					final UserDetail selected = getSelectedItem();
//					if (selected != null) {
//						//showUpdateDialog(selected);
//					}
				}
			});
		    updateButton.setIconStyle("update");
		    getHeader().addTool(updateButton);	
		    
		    setLayout(new FitLayout());  
		    
		    grid = new Grid<BeanModel>(store, columnModel);  
		    grid.setBorders(true);
		    getAriaSupport().setPresentation(true);  
		    add(grid);  
		}
		
	}

	@Override
	public void setSplits(ArrayList<StockSplit> splits) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void remove(StockSplit split) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void add(StockSplit split) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(StockSplit split) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setTreasuryHeldStock(ArrayList<TreasuryHeldStock> heldStock) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void remove(TreasuryHeldStock heldStock) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void add(TreasuryHeldStock heldStock) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(TreasuryHeldStock heldStock) {
		// TODO Auto-generated method stub
		
	}
}

package org.sjd.gordon.client.registry;

import java.util.ArrayList;
import java.util.List;

import org.sjd.gordon.client.gxt.Button;
import org.sjd.gordon.client.registry.RegistryPresenter.EditDialogCallback;
import org.sjd.gordon.client.registry.RegistryPresenter.RegistryPanelView;
import org.sjd.gordon.shared.registry.GicsIndustryGroupName;
import org.sjd.gordon.shared.registry.GicsSectorName;
import org.sjd.gordon.shared.viewer.StockDetails;

import com.allen_sauer.gwt.log.client.Log;
import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.Style.LayoutRegion;
import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.data.BasePagingLoader;
import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.data.PagingLoadResult;
import com.extjs.gxt.ui.client.data.PagingLoader;
import com.extjs.gxt.ui.client.data.PagingModelMemoryProxy;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.MessageBoxEvent;
import com.extjs.gxt.ui.client.event.SelectionChangedEvent;
import com.extjs.gxt.ui.client.event.SelectionChangedListener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Dialog;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.FormPanel.LabelAlign;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.layout.FormData;
import com.extjs.gxt.ui.client.widget.layout.FormLayout;
import com.extjs.gxt.ui.client.widget.toolbar.PagingToolBar;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.inject.name.Named;
import com.gwtplatform.mvp.client.ViewImpl;

public class RegistryViewImpl extends ViewImpl implements RegistryPanelView {

	private static final int MAX_PAGE_SIZE = 25;
	
	@Inject
	@Named("width")
	private int width;
	@Inject
	@Named("height")
	private int height;
	
	private ContentPanel contentPanel;
	
	private PagingModelMemoryProxy proxy;
	private PagingLoader<PagingLoadResult<ModelData>> loader;
	private ListStore<StockDetails> store;
	private Button addButton, removeButton, updateButton;
	private Grid<StockDetails> grid;
	private ArrayList<StockDetails> stocks;
	private ArrayList<GicsSectorName> sectors;
	
	public RegistryViewImpl() {    
		proxy = new PagingModelMemoryProxy(new ArrayList<StockDetails>(0));
	    loader = new BasePagingLoader<PagingLoadResult<ModelData>>(proxy);  
	    loader.setRemoteSort(true);  
	  
	    store = new ListStore<StockDetails>(loader);
	    
	    final PagingToolBar toolBar = new PagingToolBar(MAX_PAGE_SIZE);  
	    toolBar.bind(loader);
	  
	    List<ColumnConfig> configs = new ArrayList<ColumnConfig>();  
	  
	    ColumnConfig column = new ColumnConfig();  
	    column.setId(StockDetails.NAME);  
	    column.setHeader("Company");  
	    column.setWidth(200);  
	    configs.add(column);  
	  
	    column = new ColumnConfig();  
	    column.setId(StockDetails.CODE);  
	    column.setHeader("Code");  
	    column.setWidth(100);  
	    configs.add(column);  
	  
	    column = new ColumnConfig();  
	    column.setId(StockDetails.GICS_PRIMARY_SECTOR_NAME);  
	    column.setHeader("Primary GICS Sector");  
	    column.setWidth(200);  
	    configs.add(column);
	    
	    column = new ColumnConfig();  
	    column.setId(StockDetails.GICS_PRIMARY_INDUSTRY_GROUP_NAME);  
	    column.setHeader("Primary GICS Industry");  
	    column.setWidth(200);  
	    configs.add(column);
	    
	    column = new ColumnConfig();  
	    column.setId(StockDetails.LIST_DATE);  
	    column.setHeader("List Date"); 
	    column.setDateTimeFormat(DateTimeFormat.getFormat("dd/MM/yyyy"));
	    column.setAlignment(HorizontalAlignment.RIGHT);  
	    column.setWidth(75);  
	    configs.add(column);  
	  
	    column = new ColumnConfig(StockDetails.LAST_TRADE_DATE, "Last Trade Date", 150);  
	    column.setAlignment(HorizontalAlignment.RIGHT);  
	    column.setDateTimeFormat(DateTimeFormat.getFormat("dd/MM/yyyy"));  
	    configs.add(column);  

	    column = new ColumnConfig();  
	    column.setId(StockDetails.CURRENT_PRICE);  
	    column.setHeader("Current Price");  
	    column.setWidth(100);  
	    configs.add(column);
	    
	    ColumnModel columnModel = new ColumnModel(configs);  
	  
	    contentPanel = new ContentPanel();
	    ToolBar toolbar = new ToolBar();
	    addButton = new Button();
	    addButton.setIconStyle("add");
	    toolbar.add(addButton);
	    removeButton = new Button();
	    removeButton.setIconStyle("remove");
	    toolbar.add(removeButton);
	    updateButton = new Button();
	    updateButton.setIconStyle("update");
	    toolbar.add(updateButton);
	    contentPanel.setTopComponent(toolbar);
	    contentPanel.setFrame(false);
	    contentPanel.setHeaderVisible(false);
	    contentPanel.setButtonAlign(HorizontalAlignment.CENTER);  
	    contentPanel.setLayout(new FitLayout());  
	    contentPanel.setBottomComponent(toolBar);  
	    
	    grid = new Grid<StockDetails>(store, columnModel);  
	    grid.setBorders(true);
	    grid.getAriaSupport().setDescribedBy(toolBar.getId() + "-display");  
	    contentPanel.add(grid);  
	}
	
	@Override
	public void setStocks(ArrayList<StockDetails> stocks) {
		this.stocks = stocks;
	    proxy.setData(stocks);
	    if (stocks.size() > MAX_PAGE_SIZE) {
	        loader.load(0, MAX_PAGE_SIZE);
	    } else {
	    	loader.load(0, stocks.size());
	    }
	}

	@Override
	public void setGicsSectors(ArrayList<GicsSectorName> sectors) {
		this.sectors = sectors;
	}
	
	@Override
	public Widget asWidget() {
		contentPanel.setSize(width-200, height-60);  
		return contentPanel;
	}

	@Override
	public StockDetails getSelectedItem() {
		return grid.getSelectionModel().getSelectedItem();
	}

	@Override
	public void remove(StockDetails stock) {
		if (store.contains(stock)) {
			store.remove(stock);
			store.commitChanges();
			stocks.remove(stock);
		} else {
			Log.info("Remove: Can't find stock: " + stock.getCode());
		}
	}

	@Override
	public HasClickHandlers getDeleteHandler() {
		return removeButton;
	}

	@Override
	public void confirmDeleteRequest(Listener<MessageBoxEvent> callback) {
		MessageBox box = new MessageBox();  
        box.setButtons(MessageBox.YESNO);  
        box.setIcon(MessageBox.QUESTION);  
        box.setTitle("Delete");
        box.addCallback(callback);
        box.setMessage("Do you wish to delete this entry?");  
        box.show();
	}

	@Override
	public HasClickHandlers getAddHandler() {
		return addButton;
	}

	@Override
	public void showEditDialog(final EditDialogCallback callback) {
		showEditDialog(callback, "Add", null);
	}

	@Override
	public void add(StockDetails details) {
		store.add(details);
		stocks.add(details);
		store.commitChanges();
	}
	
	@Override
	public HasClickHandlers getUpdateHandler() {
		return updateButton;
	}

	@Override
	public void showEditDialog(StockDetails details, EditDialogCallback callback) {
		showEditDialog(callback, "Update", details);
	}
	
	private void showEditDialog(final EditDialogCallback callback, String title, StockDetails details) {
		final Dialog editDialog = new Dialog();  
	    editDialog.setHeading(title);  
	    editDialog.setButtons(Dialog.OKCANCEL);  
	    editDialog.setBodyStyleName("pad-text");
	    BorderLayoutData data = new BorderLayoutData(LayoutRegion.CENTER);
	    final EditorPanel editorPanel = new EditorPanel();
	    if (details != null) {
	    	editorPanel.setDetails(details);
	    }
	    editDialog.add(editorPanel,data);  
	    editDialog.setScrollMode(Scroll.AUTO);  
	    editDialog.setHideOnButtonClick(false);
	    editDialog.getButtonById(Dialog.OK).addSelectionListener(new SelectionListener<ButtonEvent>() {
			@Override
			public void componentSelected(ButtonEvent ce) {
				if (!editorPanel.valid()) {
					ce.cancelBubble();
					return;
				}
				StockDetails details = editorPanel.getStockDetails(); 
				editDialog.setVisible(false);
			    callback.commit(details);
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

	@Override
	public void update(StockDetails details) {
		if (store.contains(details)) {
			store.update(details);
			store.commitChanges();
			int index = stocks.indexOf(details);
			stocks.remove(index);
			if (index == stocks.size()) {
				stocks.add(details);
			} else {
				stocks.add(index, details);
			}
		} else {
			Log.info("Update: Can't find stock: " + details.getCode());			
		}
		
	}	
	
	private class EditorPanel extends LayoutContainer {
		
		private TextField<String> nameTextField = new TextField<String>();
		private TextField<String> codeTextField = new TextField<String>();
		private ComboBox<GicsSectorName> gicsSectorCombo = new ComboBox<GicsSectorName>();
		private ListStore<GicsSectorName> gicsSectorStore;
		private ComboBox<GicsIndustryGroupName> gicsIndustryGroupCombo = new ComboBox<GicsIndustryGroupName>();
		private ListStore<GicsIndustryGroupName> gicsIndustryGroupStore;
		private StockDetails currentDetails;
		
		@Override  
		protected void onRender(Element parent, int index) {
			super.onRender(parent, index);
			
			setStyleAttribute("backgroundColor", "#FFFFFF");
			setStyleAttribute("paddingLeft", "10px");
			setStyleAttribute("paddingTop", "10px");
			FormLayout layout = new FormLayout();
			layout.setLabelAlign(LabelAlign.LEFT);
			setLayout(layout);

			nameTextField.getMessages().setBlankText("Must specify a name.");
			nameTextField.setAllowBlank(false);
			codeTextField.getMessages().setBlankText("Must specify a name.");
			codeTextField.setAllowBlank(false);
			
			nameTextField.setFieldLabel("Name");
			add(nameTextField, new FormData("30%"));

			codeTextField.setFieldLabel("Code");
			add(codeTextField, new FormData("30%"));
			
			gicsSectorStore = new ListStore<GicsSectorName>();
			gicsIndustryGroupStore = new ListStore<GicsIndustryGroupName>();
			gicsIndustryGroupCombo.setStore(gicsIndustryGroupStore);
			
			gicsSectorStore.add(sectors);
			gicsSectorCombo.setFieldLabel("GICS Sector");
			gicsSectorCombo.setStore(gicsSectorStore);
			gicsSectorCombo.setDisplayField(GicsSectorName.GICS_PRIMARY_SECTOR_NAME);
			gicsSectorCombo.setAllowBlank(false);
			gicsSectorCombo.setEditable(true);
			gicsSectorCombo.setForceSelection(true);
			gicsSectorCombo.addSelectionChangedListener(new SelectionChangedListener<GicsSectorName>() {
				@Override
				public void selectionChanged(SelectionChangedEvent<GicsSectorName> se) {
					gicsIndustryGroupStore.removeAll();
					gicsIndustryGroupStore.add(se.getSelectedItem().getIndustryGroups());
					gicsIndustryGroupCombo.setValue(se.getSelectedItem().getIndustryGroups().get(0));
				}
			});
			add(gicsSectorCombo, new FormData("30%"));
			
			gicsIndustryGroupCombo.setFieldLabel("GICS Industry Group");
			gicsIndustryGroupCombo.setDisplayField(GicsIndustryGroupName.GICS_PRIMARY_INDUSTRY_GROUP_NAME);
			gicsIndustryGroupCombo.setAllowBlank(false);
			gicsIndustryGroupCombo.setEditable(true);
			gicsIndustryGroupCombo.setForceSelection(true);
			add(gicsIndustryGroupCombo, new FormData("30%"));
			
			setSize(285,140);
			
			if (currentDetails != null) {
				codeTextField.setValue(currentDetails.getCode());
				nameTextField.setValue(currentDetails.getName());
				GicsSectorName sectorName = currentDetails.getSectorName();
				gicsSectorCombo.setValue(sectors.get(sectors.indexOf(sectorName)));
				gicsIndustryGroupCombo.setValue(currentDetails.getIndustryGroupName());
			}
		}
		
		StockDetails getStockDetails() {
			StockDetails details = new StockDetails();
			details.setPrimarySector(gicsSectorCombo.getSelection().get(0));
			details.setPrimaryIndustryGroup(gicsIndustryGroupCombo.getSelection().get(0));
			details.setCode(codeTextField.getValue());
			details.setName(nameTextField.getValue());
			if (currentDetails != null) {
				details.setCurrentPrice(currentDetails.getCurrentPrice());
				details.setListDate(currentDetails.getListDate());
				details.setLastTradeDate(currentDetails.getLastTradeDate());
				details.setId(currentDetails.getId());
				details.setVersion(currentDetails.getVersion());
			}
			return details;
		}
		
		void setDetails(StockDetails details) {
			this.currentDetails = details;
		}
		
		boolean valid() {
			codeTextField.validate();
			nameTextField.validate();
			gicsSectorCombo.validate();
			gicsIndustryGroupCombo.validate();
			if (codeTextField.getValue() == null || codeTextField.getValue().equals("")) {
				return false;
			}
			if (nameTextField.getValue() == null || nameTextField.getValue().equals("")) {
				return false;
			}
			if (gicsSectorCombo.getSelection().isEmpty() || gicsIndustryGroupCombo.getSelection().isEmpty()) {
				return false;
			}
			return true;
		}
	}

}

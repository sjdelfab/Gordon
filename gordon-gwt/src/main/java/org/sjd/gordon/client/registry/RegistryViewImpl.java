package org.sjd.gordon.client.registry;

import java.util.ArrayList;
import java.util.List;

import org.sjd.gordon.client.gxt.Button;
import org.sjd.gordon.client.registry.RegistryPresenter.EditDialogCallback;
import org.sjd.gordon.client.registry.RegistryPresenter.RegistryPanelView;
import org.sjd.gordon.shared.viewer.StockDetails;

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
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Dialog;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.MessageBox;
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
	
	public RegistryViewImpl() {    
		proxy = new PagingModelMemoryProxy(new ArrayList<StockDetails>(0));
	    loader = new BasePagingLoader<PagingLoadResult<ModelData>>(proxy);  
	    loader.setRemoteSort(true);  
	  
	    store = new ListStore<StockDetails>(loader);  
	  
	    final PagingToolBar toolBar = new PagingToolBar(10);  
	    toolBar.bind(loader);  
	  
	    List<ColumnConfig> configs = new ArrayList<ColumnConfig>();  
	  
	    ColumnConfig column = new ColumnConfig();  
	    column.setId("name");  
	    column.setHeader("Company");  
	    column.setWidth(200);  
	    configs.add(column);  
	  
	    column = new ColumnConfig();  
	    column.setId("code");  
	    column.setHeader("Code");  
	    column.setWidth(100);  
	    configs.add(column);  
	  
	    column = new ColumnConfig();  
	    column.setId("listDate");  
	    column.setHeader("List Date"); 
	    column.setDateTimeFormat(DateTimeFormat.getFormat("MM/dd/yyyy"));
	    column.setAlignment(HorizontalAlignment.RIGHT);  
	    column.setWidth(75);  
	    configs.add(column);  
	  
	    column = new ColumnConfig("lastTradeDate", "Last Trade Date", 150);  
	    column.setAlignment(HorizontalAlignment.RIGHT);  
	    column.setDateTimeFormat(DateTimeFormat.getFormat("MM/dd/yyyy"));  
	    configs.add(column);  
	  
	    ColumnModel cm = new ColumnModel(configs);  
	  
	    contentPanel = new ContentPanel();
	    ToolBar toolbar = new ToolBar();
	    addButton = new Button();
	    Dialog simple = new Dialog();
	    simple.setHeading("Add");
	    simple.setButtons(Dialog.YESNO);
	    simple.setBodyStyleName("pad-text");
	    simple.addText("Add");
	    simple.getItem(0).getFocusSupport().setIgnore(true);
	    simple.setScrollMode(Scroll.AUTO);
	    simple.setHideOnButtonClick(true);
	    
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
	    
	    grid = new Grid<StockDetails>(store, cm);  
	    grid.setBorders(true);
	    grid.getAriaSupport().setDescribedBy(toolBar.getId() + "-display");  
	  
	    contentPanel.add(grid);  
	}
	
	public void setStocks(ArrayList<StockDetails> stocks) {
	    proxy.setData(stocks);
	    loader.load(0, 10);
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
		store.remove(stock);
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
	    editDialog.setHideOnButtonClick(true);
	    editDialog.getButtonById(Dialog.OK).addSelectionListener(new SelectionListener<ButtonEvent>() {
			@Override
			public void componentSelected(ButtonEvent ce) {
				callback.commit(editorPanel.getCode(), editorPanel.getName());
			}
		});
	    editDialog.setResizable(false);
	    editDialog.show();
	}

	@Override
	public void update(StockDetails details) {
		store.update(details);
	}	
	
	private class EditorPanel extends LayoutContainer {
		
		private TextField<String> nameTextField = new TextField<String>();
		private TextField<String> codeTextField = new TextField<String>();
		
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
			setSize(285,75);
		}
		
		String getCode() {
			return codeTextField.getValue();
		}
		
		String getName() {
			return nameTextField.getValue();
		}
		
		void setDetails(StockDetails details) {
			codeTextField.setValue(details.getCode());
			nameTextField.setValue(details.getName());
		}
	}

}

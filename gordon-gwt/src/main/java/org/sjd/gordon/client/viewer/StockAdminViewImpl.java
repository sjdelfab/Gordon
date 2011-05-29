package org.sjd.gordon.client.viewer;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.sjd.gordon.client.Gordon;
import org.sjd.gordon.client.common.AbstractEditPanel;
import org.sjd.gordon.client.common.BigDecimalPropertyEditor;
import org.sjd.gordon.client.common.DecimalFieldValidator;
import org.sjd.gordon.client.common.EditDialog;
import org.sjd.gordon.client.common.EditDialogCallback;
import org.sjd.gordon.client.common.IntegerFieldValidator;
import org.sjd.gordon.client.gxt.Button;
import org.sjd.gordon.model.Dividend;
import org.sjd.gordon.model.StockSplit;
import org.sjd.gordon.model.TreasuryHeldStock;

import com.extjs.gxt.ui.client.Registry;
import com.extjs.gxt.ui.client.data.BeanModel;
import com.extjs.gxt.ui.client.data.BeanModelFactory;
import com.extjs.gxt.ui.client.data.BeanModelLookup;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.MessageBoxEvent;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.util.Padding;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.form.DateField;
import com.extjs.gxt.ui.client.widget.form.FormPanel.LabelAlign;
import com.extjs.gxt.ui.client.widget.form.NumberPropertyEditor;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.layout.FormData;
import com.extjs.gxt.ui.client.widget.layout.FormLayout;
import com.extjs.gxt.ui.client.widget.layout.HBoxLayout;
import com.extjs.gxt.ui.client.widget.layout.HBoxLayout.HBoxLayoutAlign;
import com.extjs.gxt.ui.client.widget.layout.HBoxLayoutData;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
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
	private DividendPanel dividendPanel;
	private RightHandColumnPanel rightHandColumnPanel;
	private BigDecimalPropertyEditor bigDecimalPropertyEditor = new BigDecimalPropertyEditor();
	private DecimalFieldValidator decimalValidator = new DecimalFieldValidator();
	private IntegerFieldValidator integerFieldValidator = new IntegerFieldValidator();
	
	public StockAdminViewImpl() {
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
	        dividendPanel = new DividendPanel(); 
	        add(dividendPanel, new HBoxLayoutData(new Margins(5, 0, 0, 5)));
	        rightHandColumnPanel = new RightHandColumnPanel();
	        add(rightHandColumnPanel, new HBoxLayoutData(new Margins(0, 5, 0, 0)));
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
	
	private void confirmDeleteRequest(Listener<MessageBoxEvent> callback) {
		MessageBox box = new MessageBox();  
        box.setButtons(MessageBox.YESNO);  
        box.setIcon(MessageBox.QUESTION);  
        box.setTitle("Delete");
        box.addCallback(callback);
        box.setMessage("Do you wish to delete this entry?");  
        box.show();
	}	
	
	private class RightHandColumnPanel extends VerticalPanel {
				
		@Override  
		protected void onRender(Element parent, int pos) {  
		    super.onRender(parent, pos);  
		    setSpacing(5);
		    stockSplitPanel = new StockSplitPanel();
		    add(stockSplitPanel);
		    treasuryHeldStockPanel = new TreasuryHeldStockPanel();
		    add(treasuryHeldStockPanel);
		    setSize(getPreferredWidth()/2 - 5, getPreferredHeight()-110);
		}
				
	}
	
	private class StockSplitPanel extends ContentPanel {
		
		private Button addButton, removeButton, updateButton, refreshButton;
		private ListStore<BeanModel> store;
		private Grid<BeanModel> grid;
		private BeanModelFactory beanModelFactory = BeanModelLookup.get().getFactory(StockSplit.class);
		
		private StockSplitPanel() {
			setHeading("Stock Split");
			store = Registry.get(Gordon.STOCK_SPLIT);
			setSize(getPreferredWidth()/2 - 10, getPreferredHeight()/2-80);
		}
		
		@Override
		protected void onRender(Element parent, int pos) {
			super.onRender(parent, pos);
		    List<ColumnConfig> configs = new ArrayList<ColumnConfig>();  
			  
		    ColumnConfig column = new ColumnConfig();  
		    column.setId("date");  
		    column.setHeader("Date");
		    column.setDateTimeFormat(DateTimeFormat.getFormat("dd/MM/yyyy"));
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
					showAddStockSplitDialog();
				}
			});
		    getHeader().addTool(addButton);
		    removeButton = new Button();
			removeButton.addClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					deleteSelectedSplit();
				}
			});
		    removeButton.setIconStyle("remove");
		    getHeader().addTool(removeButton);
		    updateButton = new Button();
		    updateButton.addClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					final StockSplit selected = getSelectedItem();
					if (selected != null) {
						showUpdateStockSplitDialog(selected);
					}
				}
			});
		    updateButton.setIconStyle("update");
		    getHeader().addTool(updateButton);	
		    
		    refreshButton = new Button();
		    refreshButton.addClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					getUiHandlers().loadStockSplits();
				}
			});
		    refreshButton.setIconStyle("refresh");
		    getHeader().addTool(refreshButton);
		    
		    setLayout(new FitLayout());  
		    
		    grid = new Grid<BeanModel>(store, columnModel);  
		    grid.setBorders(true);
		    getAriaSupport().setPresentation(true);  
		    add(grid);  
		}
		
		private void setSplits(ArrayList<StockSplit> splits) {
			List<BeanModel> stockSplitModelList = beanModelFactory.createModel(splits);
			store.removeAll();
			store.add(stockSplitModelList);
		}

		private void remove(StockSplit split) {
			store.remove(beanModelFactory.createModel(split));
		}

		private void add(StockSplit split) {
			store.add(beanModelFactory.createModel(split));
		}

		private void update(StockSplit split) {
			store.update(beanModelFactory.createModel(split));
		}
		
		private StockSplit getSelectedItem() {
			BeanModel selected = grid.getSelectionModel().getSelectedItem();
			if (selected == null) {
				return null;
			}
			return selected.getBean();
		}

		private void deleteSelectedSplit() {
			final StockSplit selected = getSelectedItem();
			if (selected != null) {
				confirmDeleteRequest(new Listener<MessageBoxEvent>() {
					@Override
					public void handleEvent(MessageBoxEvent be) {
						if (be.getButtonClicked().getText().equals(be.getDialog().yesText)) {
							getUiHandlers().delete(selected);
						}
					}
				});
			}
		}

		private void showAddStockSplitDialog() {
			StockSplitEditorPanel editorPanel = new StockSplitEditorPanel();
			EditDialogCallback<StockSplit> callback = new EditDialogCallback<StockSplit>() {
				@Override
				public void commit(StockSplit details) {
					getUiHandlers().add(details);
				}
			};
			EditDialog<StockSplit> editDialog = new EditDialog<StockSplit>(editorPanel, callback);
			editDialog.setTitle("Add");
			editDialog.show();
		}
		
		private void showUpdateStockSplitDialog(final StockSplit selected) {
			final StockSplitEditorPanel editorPanel = new StockSplitEditorPanel();
	    	editorPanel.marshal(selected);
	    	EditDialogCallback<StockSplit> callback = new EditDialogCallback<StockSplit>() {
				@Override
				public void commit(StockSplit details) {
					StockSplit stockSplit = new StockSplit(selected);
					stockSplit.mergeTo(details);
					getUiHandlers().update(stockSplit);
				}
			};
			EditDialog<StockSplit> editDialog = new EditDialog<StockSplit>(editorPanel, callback);
	    	editDialog.setTitle("Update");
	    	editDialog.show();
		}
	}

	@Override
	public void setSplits(ArrayList<StockSplit> splits) {
		stockSplitPanel.setSplits(splits);
	}

	@Override
	public void remove(StockSplit split) {
		stockSplitPanel.remove(split);
	}

	@Override
	public void add(StockSplit split) {
		stockSplitPanel.add(split);
	}

	@Override
	public void update(StockSplit split) {
		stockSplitPanel.update(split);
	}
	
	private class StockSplitEditorPanel extends AbstractEditPanel<StockSplit> {

		private TextField<BigDecimal> factorTextField = new TextField<BigDecimal>();
		private DateField dateField = new DateField();

		private StockSplit data;
		
		@Override  
		protected void onRender(Element parent, int index) {
			super.onRender(parent, index);
			
			setStyleAttribute("backgroundColor", "#FFFFFF");
			setStyleAttribute("paddingLeft", "10px");
			setStyleAttribute("paddingTop", "10px");
			FormLayout layout = new FormLayout();
			layout.setLabelAlign(LabelAlign.LEFT);
			setLayout(layout);

			dateField.getMessages().setBlankText("Must specify a date.");
			dateField.setAllowBlank(false);
			dateField.setFieldLabel("Date");
			add(dateField, new FormData("30%"));

			factorTextField.setValidator(decimalValidator);
			factorTextField.setPropertyEditor(bigDecimalPropertyEditor);
			factorTextField.getMessages().setBlankText("Must specify a factor.");
			factorTextField.setAllowBlank(false);
			factorTextField.setFieldLabel("Factor");
			add(factorTextField, new FormData("30%"));
			
			setSize(285,80);
		}
		@Override
		public StockSplit unmarshal() {
			StockSplit split = new StockSplit(data);
			split.setFactor(factorTextField.getValue());
			split.setDate(dateField.getValue());
			return split;
		}

		@Override
		public void marshal(StockSplit data) {
			this.data = data;
			factorTextField.setValue(data.getFactor());
			dateField.setValue(data.getDate());
		}
		
		@Override
		public boolean isValid() {
			if (!factorTextField.validate()) {
				return false;
			}
			if (!dateField.validate()) {
				return false;
			}
			return true;
		}
		
	}
	
	private class TreasuryHeldStockPanel extends ContentPanel {
		
		private Button addButton, removeButton, updateButton, refreshButton;
		private ListStore<BeanModel> store;
		private Grid<BeanModel> grid;
		private BeanModelFactory beanModelFactory = BeanModelLookup.get().getFactory(TreasuryHeldStock.class);
		
		private TreasuryHeldStockPanel() {
			setHeading("Treasury Held Stock");
			store = Registry.get(Gordon.TREASURY_HELD_STOCK);
			setSize(getPreferredWidth()/2 - 10, getPreferredHeight()/2-80);
		}
		
		@Override
		protected void onRender(Element parent, int pos) {
			super.onRender(parent, pos);
		    List<ColumnConfig> configs = new ArrayList<ColumnConfig>();  
			  
		    ColumnConfig column = new ColumnConfig();  
		    column.setId("date");  
		    column.setHeader("Date");  
		    column.setWidth(277);
		    column.setDateTimeFormat(DateTimeFormat.getFormat("dd/MM/yyyy"));
		    configs.add(column);  
		    
		    column = new ColumnConfig();
		    column.setId("volume");  
		    column.setHeader("Volume");  
		    column.setWidth(277);  
		    configs.add(column);
		    
		    ColumnModel columnModel = new ColumnModel(configs);
		    addButton = new Button();
		    addButton.setIconStyle("add");
		    addButton.addClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					showAddTreasuryHeldStockDialog();
				}
			});
		    getHeader().addTool(addButton);
		    removeButton = new Button();
			removeButton.addClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					deleteSelectedTreasuryHeldStock();
				}
			});
		    removeButton.setIconStyle("remove");
		    getHeader().addTool(removeButton);
		    updateButton = new Button();
		    updateButton.addClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					final TreasuryHeldStock selected = getSelectedItem();
					if (selected != null) {
						showUpdateTreasuryHeldStockDialog(selected);
					}
				}
			});
		    updateButton.setIconStyle("update");
		    getHeader().addTool(updateButton);	
		    
		    refreshButton = new Button();
		    refreshButton.addClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					getUiHandlers().loadStockSplits();
				}
			});
		    refreshButton.setIconStyle("refresh");
		    getHeader().addTool(refreshButton);

		    setLayout(new FitLayout());  
		    
		    grid = new Grid<BeanModel>(store, columnModel);  
		    grid.setBorders(true);
		    getAriaSupport().setPresentation(true);  
		    add(grid);  
		}
		
		private TreasuryHeldStock getSelectedItem() {
			BeanModel selected = grid.getSelectionModel().getSelectedItem();
			if (selected == null) {
				return null;
			}
			return selected.getBean();
		}

		private void deleteSelectedTreasuryHeldStock() {
			final TreasuryHeldStock selected = getSelectedItem();
			if (selected != null) {
				confirmDeleteRequest(new Listener<MessageBoxEvent>() {
					@Override
					public void handleEvent(MessageBoxEvent be) {
						if (be.getButtonClicked().getText().equals(be.getDialog().yesText)) {
							getUiHandlers().delete(selected);
						}
					}
				});
			}
		}

		private void showAddTreasuryHeldStockDialog() {
			TreasuryHeldStockEditorPanel editorPanel = new TreasuryHeldStockEditorPanel();
			EditDialogCallback<TreasuryHeldStock> callback = new EditDialogCallback<TreasuryHeldStock>() {
				@Override
				public void commit(TreasuryHeldStock details) {
					getUiHandlers().add(details);
				}
			};
			EditDialog<TreasuryHeldStock> editDialog = new EditDialog<TreasuryHeldStock>(editorPanel, callback);
			editDialog.setTitle("Add");
			editDialog.show();
		}
		
		private void showUpdateTreasuryHeldStockDialog(final TreasuryHeldStock selected) {
			final TreasuryHeldStockEditorPanel editorPanel = new TreasuryHeldStockEditorPanel();
	    	editorPanel.marshal(selected);
	    	EditDialogCallback<TreasuryHeldStock> callback = new EditDialogCallback<TreasuryHeldStock>() {
				@Override
				public void commit(TreasuryHeldStock details) {
					TreasuryHeldStock stockSplit = new TreasuryHeldStock(selected);
					stockSplit.mergeTo(details);
					getUiHandlers().update(stockSplit);
				}
			};
			EditDialog<TreasuryHeldStock> editDialog = new EditDialog<TreasuryHeldStock>(editorPanel, callback);
	    	editDialog.setTitle("Update");
	    	editDialog.show();
		}
		
		private void setTreasuryHeldStock(ArrayList<TreasuryHeldStock> heldStock) {
			List<BeanModel> heldStockModelList = beanModelFactory.createModel(heldStock);
			store.removeAll();
			store.add(heldStockModelList);
		}

		private void remove(TreasuryHeldStock heldStock) {
			store.remove(beanModelFactory.createModel(heldStock));
		}

		private void add(TreasuryHeldStock heldStock) {
			store.add(beanModelFactory.createModel(heldStock));
		}

		private void update(TreasuryHeldStock heldStock) {
			store.update(beanModelFactory.createModel(heldStock));
		}
		
	}

	private class TreasuryHeldStockEditorPanel extends AbstractEditPanel<TreasuryHeldStock> {

		private TextField<Number> volumeTextField = new TextField<Number>();
		private DateField dateField = new DateField();

		private TreasuryHeldStock data;
		
		@Override  
		protected void onRender(Element parent, int index) {
			super.onRender(parent, index);
			
			setStyleAttribute("backgroundColor", "#FFFFFF");
			setStyleAttribute("paddingLeft", "10px");
			setStyleAttribute("paddingTop", "10px");
			FormLayout layout = new FormLayout();
			layout.setLabelAlign(LabelAlign.LEFT);
			setLayout(layout);

			dateField.getMessages().setBlankText("Must specify a date.");
			dateField.setAllowBlank(false);
			dateField.setFieldLabel("Date");
			add(dateField, new FormData("30%"));

			volumeTextField.setPropertyEditor(new NumberPropertyEditor(Integer.class));
			volumeTextField.setValidator(integerFieldValidator);
			volumeTextField.getMessages().setBlankText("Must specify a volume.");
			volumeTextField.setAllowBlank(false);
			volumeTextField.setFieldLabel("Volume");
			add(volumeTextField, new FormData("30%"));
			
			setSize(285,80);
		}
		
		@Override
		public TreasuryHeldStock unmarshal() {
			TreasuryHeldStock split = new TreasuryHeldStock(data);
			split.setVolume(volumeTextField.getValue().intValue());
			split.setDate(dateField.getValue());
			return split;
		}

		@Override
		public void marshal(TreasuryHeldStock data) {
			this.data = data;
			volumeTextField.setValue(data.getVolume());
			dateField.setValue(data.getDate());
		}
		
		@Override
		public boolean isValid() {
			if (!volumeTextField.validate()) {
				return false;
			}
			if (!dateField.validate()) {
				return false;
			}
			return true;
		}

	}	

	@Override
	public void setTreasuryHeldStock(ArrayList<TreasuryHeldStock> heldStock) {
		treasuryHeldStockPanel.setTreasuryHeldStock(heldStock);
	}

	@Override
	public void remove(TreasuryHeldStock heldStock) {
		treasuryHeldStockPanel.remove(heldStock);
	}

	@Override
	public void add(TreasuryHeldStock heldStock) {
		treasuryHeldStockPanel.add(heldStock);
	}

	@Override
	public void update(TreasuryHeldStock heldStock) {
		treasuryHeldStockPanel.update(heldStock);
	}
	
	private class DividendPanel extends ContentPanel {
		
		private Button addButton, removeButton, updateButton, refreshButton;
		private ListStore<BeanModel> store;
		private Grid<BeanModel> grid;
		private BeanModelFactory beanModelFactory = BeanModelLookup.get().getFactory(Dividend.class);
		
		private DividendPanel() {
			setHeading("Dividends");
			store = Registry.get(Gordon.DIVIDEND);
			setSize(getPreferredWidth()/2 - 10, getPreferredHeight()-155);
		}
		
		@Override
		protected void onRender(Element parent, int pos) {
			super.onRender(parent, pos);
		    List<ColumnConfig> configs = new ArrayList<ColumnConfig>();  
			  
		    ColumnConfig column = new ColumnConfig();  
		    column.setId("announcementDate");  
		    column.setHeader("Announcement Date");  
		    column.setWidth(185);
		    column.setDateTimeFormat(DateTimeFormat.getFormat("dd/MM/yyyy"));
		    configs.add(column);  
		    
		    column = new ColumnConfig();  
		    column.setId("date");  
		    column.setHeader("Issue Date");  
		    column.setWidth(185);
		    column.setDateTimeFormat(DateTimeFormat.getFormat("dd/MM/yyyy"));
		    configs.add(column);
		    
		    column = new ColumnConfig();
		    column.setId("amount");  
		    column.setHeader("Amount");  
		    column.setWidth(185);  
		    configs.add(column);
		    
		    ColumnModel columnModel = new ColumnModel(configs);
		    addButton = new Button();
		    addButton.setIconStyle("add");
		    addButton.addClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					showAddDividendDialog();
				}
			});
		    getHeader().addTool(addButton);
		    removeButton = new Button();
			removeButton.addClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					deleteDividend();
				}
			});
		    removeButton.setIconStyle("remove");
		    getHeader().addTool(removeButton);
		    updateButton = new Button();
		    updateButton.addClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					final Dividend selected = getSelectedItem();
					if (selected != null) {
						showUpdateDividendDialog(selected);
					}
				}
			});
		    updateButton.setIconStyle("update");
		    getHeader().addTool(updateButton);	
		    
		    refreshButton = new Button();
		    refreshButton.addClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					getUiHandlers().loadDividendHistory();
				}
			});
		    refreshButton.setIconStyle("refresh");
		    getHeader().addTool(refreshButton);

		    setLayout(new FitLayout());  
		    
		    grid = new Grid<BeanModel>(store, columnModel);  
		    grid.setBorders(true);
		    getAriaSupport().setPresentation(true);  
		    add(grid);  
		}
		
		private Dividend getSelectedItem() {
			BeanModel selected = grid.getSelectionModel().getSelectedItem();
			if (selected == null) {
				return null;
			}
			return selected.getBean();
		}

		private void deleteDividend() {
			final Dividend selected = getSelectedItem();
			if (selected != null) {
				confirmDeleteRequest(new Listener<MessageBoxEvent>() {
					@Override
					public void handleEvent(MessageBoxEvent be) {
						if (be.getButtonClicked().getText().equals(be.getDialog().yesText)) {
							getUiHandlers().delete(selected);
						}
					}
				});
			}
		}

		private void showAddDividendDialog() {
			DividendEditorPanel editorPanel = new DividendEditorPanel();
			EditDialogCallback<Dividend> callback = new EditDialogCallback<Dividend>() {
				@Override
				public void commit(Dividend details) {
					getUiHandlers().add(details);
				}
			};
			EditDialog<Dividend> editDialog = new EditDialog<Dividend>(editorPanel, callback);
			editDialog.setTitle("Add");
			editDialog.show();
		}
		
		private void showUpdateDividendDialog(final Dividend selected) {
			final DividendEditorPanel editorPanel = new DividendEditorPanel();
	    	editorPanel.marshal(selected);
	    	EditDialogCallback<Dividend> callback = new EditDialogCallback<Dividend>() {
				@Override
				public void commit(Dividend details) {
					Dividend newDividend = new Dividend(selected);
					newDividend.mergeTo(details);
					getUiHandlers().update(newDividend);
				}
			};
			EditDialog<Dividend> editDialog = new EditDialog<Dividend>(editorPanel, callback);
	    	editDialog.setTitle("Update");
	    	editDialog.show();
		}
		
		private void setDividendHistory(ArrayList<Dividend> dividendHistory) {
			List<BeanModel> heldStockModelList = beanModelFactory.createModel(dividendHistory);
			store.removeAll();
			store.add(heldStockModelList);
		}

		private void remove(Dividend dividend) {
			store.remove(beanModelFactory.createModel(dividend));
		}

		private void add(Dividend dividend) {
			store.add(beanModelFactory.createModel(dividend));
		}

		private void update(Dividend dividend) {
			store.update(beanModelFactory.createModel(dividend));
		}
		
	}

	private class DividendEditorPanel extends AbstractEditPanel<Dividend> {

		private DateField announmentDateField = new DateField();
		private DateField dateField = new DateField();
		private TextField<BigDecimal> amountTextField = new TextField<BigDecimal>();

		private Dividend data;
		
		@Override  
		protected void onRender(Element parent, int index) {
			super.onRender(parent, index);
			
			setStyleAttribute("backgroundColor", "#FFFFFF");
			setStyleAttribute("paddingLeft", "10px");
			setStyleAttribute("paddingTop", "10px");
			FormLayout layout = new FormLayout();
			layout.setLabelAlign(LabelAlign.LEFT);
			setLayout(layout);

			announmentDateField.getMessages().setBlankText("Must specify a date.");
			announmentDateField.setAllowBlank(false);
			announmentDateField.setFieldLabel("Announcement Date");
			add(announmentDateField, new FormData("30%"));

			dateField.getMessages().setBlankText("Must specify a date.");
			dateField.setAllowBlank(false);
			dateField.setFieldLabel("Issue Date");
			add(dateField, new FormData("30%"));

			amountTextField.setPropertyEditor(bigDecimalPropertyEditor);
			amountTextField.setValidator(decimalValidator);
			amountTextField.getMessages().setBlankText("Must specify amount.");
			amountTextField.setAllowBlank(false);
			amountTextField.setFieldLabel("Amount");
			add(amountTextField, new FormData("30%"));
			
			setSize(285,110);
		}
		
		@Override
		public Dividend unmarshal() {
			Dividend split = new Dividend(data);
			split.setAmount(amountTextField.getValue());
			split.setDate(dateField.getValue());
			split.setAnnouncementDate(announmentDateField.getValue());
			return split;
		}

		@Override
		public void marshal(Dividend data) {
			this.data = data;
			amountTextField.setValue(data.getAmount());
			dateField.setValue(data.getDate());
			announmentDateField.setValue(data.getAnnouncementDate());
		}
		
		@Override
		public boolean isValid() {
			if (!amountTextField.validate()) {
				return false;
			}
			if (!dateField.validate()) {
				return false;
			}
			if (!announmentDateField.validate()) {
				return false;
			}
			return true;
		}

	}

	@Override
	public void setDividendHistory(ArrayList<Dividend> dividendHistory) {
		dividendPanel.setDividendHistory(dividendHistory);
	}

	@Override
	public void remove(Dividend dividend) {
		dividendPanel.remove(dividend);
	}

	@Override
	public void add(Dividend dividend) {
		dividendPanel.add(dividend);
	}

	@Override
	public void update(Dividend dividend) {
		dividendPanel.update(dividend);
	}	

}

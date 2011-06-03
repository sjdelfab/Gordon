package org.sjd.gordon.client.main;

import java.util.ArrayList;
import java.util.List;

import org.sjd.gordon.client.Gordon;
import org.sjd.gordon.client.gxt.ComboBox;
import org.sjd.gordon.model.Exchange;
import org.sjd.gordon.shared.navigation.StockName;

import com.extjs.gxt.ui.client.Registry;
import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.data.BeanModel;
import com.extjs.gxt.ui.client.data.BeanModelFactory;
import com.extjs.gxt.ui.client.data.BeanModelLookup;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.FormEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionChangedEvent;
import com.extjs.gxt.ui.client.event.SelectionChangedListener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.ComboBox.TriggerAction;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.FileUpload;

public class TradeHistoryImportDialog extends Window {

	private ImportPanel importPanel;
	
	public TradeHistoryImportDialog(TitleStripUIHandler uiHandler) {
		setHeading("Import");
		setTitle("Import");
	    setBodyStyleName("pad-text");
	    setLayout(new FitLayout());
	    importPanel = new ImportPanel(uiHandler);
	    setScrollMode(Scroll.AUTO);  
	    setResizable(true);
	    setSize(350,160);
	}
	
	@Override 
	protected void onRender(Element parent, int pos) {
		super.onRender(parent, pos);
		add(importPanel);		
	}
	
	private class ImportPanel extends FormPanel implements AsyncCallback<ArrayList<StockName>>{
		
		private FileUpload upload;
		private MessageBox progressMessageBox = null;
		private ComboBox<BeanModel> codeComboBox;
		private ComboBox<BeanModel> exchangeComboBox = new ComboBox<BeanModel>();
		private ListStore<BeanModel> stockStore;
		private ListStore<BeanModel> exchangeStore;
		
		private ImportPanel(final TitleStripUIHandler uiHandler) {
			setHeaderVisible(false);
			final Button importButton = new Button("Import");
			importButton.addSelectionListener(new SelectionListener<ButtonEvent>() {
				public void componentSelected(ButtonEvent ce) {
					if (isValid()) {
						importButton.setEnabled(false);
						submit();
					}
				}
			});
	        addButton(importButton);
	        Button cancelButton = new Button("Cancel");
	        cancelButton.addSelectionListener(new SelectionListener<ButtonEvent>() {
				public void componentSelected(ButtonEvent ce) {
					TradeHistoryImportDialog.this.setVisible(false);
				}
			});
	        addButton(cancelButton);
	        
	        addListener(Events.BeforeSubmit, new Listener<FormEvent>() {
				@Override
				public void handleEvent(FormEvent be) {
					progressMessageBox = MessageBox.wait("Import", "Importing trade history", "Importing...");
				}
	        });

	        addListener(Events.Submit, new Listener<FormEvent>() {
				@Override
				public void handleEvent(FormEvent be) {
					if (progressMessageBox != null) {
						progressMessageBox.close();
					}
					importButton.setEnabled(true);
					String result = be.getResultHtml();
					if (result.equals("SUCCESS")) {
						TradeHistoryImportDialog.this.setVisible(false);
					} else {
						MessageBox.alert("Error", "Unable to trade history. See server logs for details.", null);
					}
				}
	        });
	        exchangeStore = Registry.get(Gordon.EXCHANGE_STORE);
	        stockStore = new ListStore<BeanModel>();
	        exchangeComboBox.addSelectionChangedListener(new SelectionChangedListener<BeanModel>() {
				@Override
				public void selectionChanged(SelectionChangedEvent<BeanModel> se) {
					uiHandler.getStocks((Exchange)se.getSelectedItem().getBean(), ImportPanel.this);
				}
			});
		}
		
		@Override  
		protected void onRender(Element parent, int index) {
			super.onRender(parent, index);
	        setAction(GWT.getModuleBaseURL() + "TradeHistoryFileImporter");
	        
	        // Because we're going to add a FileUpload widget, we'll need to set the
	        // form to use the POST method, and multipart MIME encoding.
	        setEncoding(Encoding.MULTIPART);
	        setMethod(Method.POST);
	        
			exchangeComboBox.setEmptyText("Select a exchange...");
			exchangeComboBox.setDisplayField("name");
			exchangeComboBox.setWidth(170);
			exchangeComboBox.setStore(exchangeStore);
			exchangeComboBox.setTypeAhead(true);
			exchangeComboBox.setTriggerAction(TriggerAction.ALL);
			exchangeComboBox.setFireChangeEventOnSetValue(true);
			exchangeComboBox.setName("exchange");
			exchangeComboBox.setFieldLabel("Exchange");
			
			add(exchangeComboBox);
	        
	        codeComboBox = new ComboBox<BeanModel>();
			codeComboBox.setEmptyText("Select a symbol...");
			codeComboBox.setDisplayField("code");
			codeComboBox.setWidth(170);
			codeComboBox.setStore(stockStore);
			codeComboBox.setTypeAhead(true);
			codeComboBox.setTriggerAction(TriggerAction.ALL);
			codeComboBox.setName("stockCode");
			codeComboBox.setFieldLabel("Symbol");
			add(codeComboBox);
			
	        upload = new FileUpload();
	        upload.setName("uploadFormElement");
	        add(upload);
		}
		
		@Override
		public boolean isValid() {
			if (upload.getFilename() == null) {
				return false;
			}
			return super.isValid();
		}

		@Override
		public void onFailure(Throwable cause) {
			// Ignore - Handled by calling callback
		}

		@Override
		public void onSuccess(ArrayList<StockName> stocks) {
			BeanModelFactory beanModelFactory = BeanModelLookup.get().getFactory(StockName.class);
			List<BeanModel> stockModelList = beanModelFactory.createModel(stocks);
			stockStore.removeAll();
			stockStore.add(stockModelList);
		}
	}

}

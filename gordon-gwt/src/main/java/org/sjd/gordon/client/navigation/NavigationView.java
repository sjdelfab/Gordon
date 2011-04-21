package org.sjd.gordon.client.navigation;

import java.util.ArrayList;
import java.util.List;

import org.sjd.gordon.client.Gordon;
import org.sjd.gordon.client.gxt.Button;
import org.sjd.gordon.client.gxt.ComboBox;
import org.sjd.gordon.model.Exchange;
import org.sjd.gordon.model.StockEntity;

import com.extjs.gxt.ui.client.Registry;
import com.extjs.gxt.ui.client.data.BeanModel;
import com.extjs.gxt.ui.client.data.BeanModelFactory;
import com.extjs.gxt.ui.client.data.BeanModelLookup;
import com.extjs.gxt.ui.client.event.SelectionChangedEvent;
import com.extjs.gxt.ui.client.event.SelectionChangedListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.util.Padding;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.form.ComboBox.TriggerAction;
import com.extjs.gxt.ui.client.widget.layout.VBoxLayout;
import com.extjs.gxt.ui.client.widget.layout.VBoxLayout.VBoxLayoutAlign;
import com.extjs.gxt.ui.client.widget.layout.VBoxLayoutData;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.HasValue;

public class NavigationView extends ContentPanel implements NavigationDisplay {

	private Button viewButton = new Button("View");
	private ComboBox<BeanModel> exchangeComboBox = new ComboBox<BeanModel>();
	private ComboBox<BeanModel> codeComboBox;
	private ComboBox<BeanModel> nameComboBox; 
	private ListStore<BeanModel> stockStore;
	private ListStore<BeanModel> exchangeStore;
	
	@Override
	protected void onRender(Element parent, int index) {
		super.onRender(parent, index);
		
		setHeading("Navigation");
		
		VBoxLayout mainLayout = new VBoxLayout();  
        mainLayout.setPadding(new Padding(0));  
        mainLayout.setVBoxLayoutAlign(VBoxLayoutAlign.STRETCH);  
        setLayout(mainLayout);
        
		ContentPanel navigationPanel = new ContentPanel();
		navigationPanel.setCollapsible(false);
		navigationPanel.setHeaderVisible(false);
		VBoxLayout navigationLayout = new VBoxLayout();
		navigationLayout.setPadding(new Padding(5));
		navigationLayout.setVBoxLayoutAlign(VBoxLayoutAlign.LEFT);
		navigationPanel.setLayout(navigationLayout);

		stockStore = Registry.get(Gordon.STOCKS_STORE);
		exchangeStore = Registry.get(Gordon.EXCHANGE_STORE);
		
		VBoxLayoutData vBoxData = new VBoxLayoutData(5, 5, 5, 5);  

		navigationPanel.add(new Html("<b style=\"font-size:13px;font-family:arial\">Exchange:</b>"),vBoxData);
		exchangeComboBox.setEmptyText("Select a exchange...");
		exchangeComboBox.setDisplayField("name");
		exchangeComboBox.setWidth(170);
		exchangeComboBox.setStore(exchangeStore);
		exchangeComboBox.setTypeAhead(true);
		exchangeComboBox.setTriggerAction(TriggerAction.ALL);
		exchangeComboBox.setFireChangeEventOnSetValue(true);
		exchangeComboBox.addSelectionChangedListener(new SelectionChangedListener<BeanModel>() {
			@Override
			public void selectionChanged(SelectionChangedEvent<BeanModel> se) {
				ValueChangeEvent.fire(exchangeComboBox, se.getSelectedItem());
			}
		});
		navigationPanel.add(exchangeComboBox, vBoxData);
		
		navigationPanel.add(new Html("<b style=\"font-size:13px;font-family:arial\">Stock Code:</b>"),vBoxData);
		
		codeComboBox = new ComboBox<BeanModel>();
		codeComboBox.setEmptyText("Select a code...");
		codeComboBox.setDisplayField("code");
		codeComboBox.setWidth(170);
		codeComboBox.setStore(stockStore);
		codeComboBox.setTypeAhead(true);
		codeComboBox.setTriggerAction(TriggerAction.ALL);
		codeComboBox.addSelectionChangedListener(new SelectionChangedListener<BeanModel>() {
			@Override
			public void selectionChanged(SelectionChangedEvent<BeanModel> se) {
				nameComboBox.setValue(se.getSelectedItem()); 
			}
		});
		navigationPanel.add(codeComboBox, vBoxData);

		navigationPanel.add(new Html("<b style=\"font-size:13px;font-family:arial\">Stock Name:</b>"),vBoxData);
		
		nameComboBox = new ComboBox<BeanModel>();
		nameComboBox.setEmptyText("Select a name...");
		nameComboBox.setDisplayField("name");
		nameComboBox.setWidth(170);
		nameComboBox.setStore(stockStore);
		nameComboBox.setTypeAhead(true);
		nameComboBox.setTriggerAction(TriggerAction.ALL);
		nameComboBox.addSelectionChangedListener(new SelectionChangedListener<BeanModel>() {
			@Override
			public void selectionChanged(SelectionChangedEvent<BeanModel> se) {
				codeComboBox.setValue(se.getSelectedItem()); 
			}
		});
		navigationPanel.add(nameComboBox, vBoxData);
		
		navigationPanel.add(viewButton,vBoxData);
		
		vBoxData = new VBoxLayoutData(0, 0, 0, 0);  
	    vBoxData.setFlex(1);
		add(navigationPanel, vBoxData);
	}
	
	public void setStocks(ArrayList<StockEntity> stocks) {
		BeanModelFactory beanModelFactory = BeanModelLookup.get().getFactory(StockEntity.class);
		List<BeanModel> stockModelList = beanModelFactory.createModel(stocks);
		stockStore.add(stockModelList);
	}
	
	public void setExchanges(ArrayList<Exchange> exchanges) {
		BeanModelFactory beanModelFactory = BeanModelLookup.get().getFactory(Exchange.class);
		List<BeanModel> exchangeModelList = beanModelFactory.createModel(exchanges);
		exchangeStore.add(exchangeModelList);
	}
	
	@Override
	public HasClickHandlers getViewHandler() {
		return viewButton;
	}

	@Override
	public HasValue<BeanModel> getStock() {
		return codeComboBox;
	}

	@Override
	public HasValue<BeanModel> getExchange() {
		return exchangeComboBox;
	}

	@Override
	public HasClickHandlers getExchangeHandler() {
		return exchangeComboBox;
	}
}

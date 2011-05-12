package org.sjd.gordon.client.navigation;

import java.util.ArrayList;
import java.util.List;

import org.sjd.gordon.client.Gordon;
import org.sjd.gordon.client.gxt.Button;
import org.sjd.gordon.client.gxt.ComboBox;
import org.sjd.gordon.model.Exchange;
import org.sjd.gordon.shared.navigation.StockName;

import com.extjs.gxt.ui.client.Registry;
import com.extjs.gxt.ui.client.data.BeanModel;
import com.extjs.gxt.ui.client.data.BeanModelFactory;
import com.extjs.gxt.ui.client.data.BeanModelLookup;
import com.extjs.gxt.ui.client.event.SelectionChangedEvent;
import com.extjs.gxt.ui.client.event.SelectionChangedListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.util.Padding;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.form.ComboBox.TriggerAction;
import com.extjs.gxt.ui.client.widget.layout.HBoxLayout;
import com.extjs.gxt.ui.client.widget.layout.HBoxLayout.HBoxLayoutAlign;
import com.extjs.gxt.ui.client.widget.layout.HBoxLayoutData;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.inject.name.Named;
import com.gwtplatform.mvp.client.ViewImpl;

public class NavigationView extends ViewImpl implements NavigationPresenter.NavigationPanelView {

	private Button viewButton = new Button("View");
	private ComboBox<BeanModel> exchangeComboBox = new ComboBox<BeanModel>();
	private ComboBox<BeanModel> codeComboBox;
	private ComboBox<BeanModel> nameComboBox; 
	private ListStore<BeanModel> stockStore;
	private ListStore<BeanModel> exchangeStore;
	
	private LayoutContainer panel;
	@Inject
	@Named("width")
	private int width;

	public NavigationView() {
		panel = new LayoutContainer();
		panel.setWidth(width);
		panel.setHeight(40);
		HBoxLayout layout = new HBoxLayout();  
        layout.setPadding(new Padding(5));  
        layout.setHBoxLayoutAlign(HBoxLayoutAlign.MIDDLE);  
        panel.setLayout(layout); 
        
		stockStore = Registry.get(Gordon.STOCKS_STORE);
		exchangeStore = Registry.get(Gordon.EXCHANGE_STORE);
		
		panel.add(new Html("<b style=\"font-size:13px;font-family:arial\">Exchange:</b>"),new HBoxLayoutData(new Margins(0, 5, 0, 15)));
		
		exchangeComboBox.setEmptyText("Select a exchange...");
		exchangeComboBox.setFieldLabel("Exchange");
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
		panel.add(exchangeComboBox,  new HBoxLayoutData(new Margins(0, 5, 0, 0)));
				
		panel.add(new Html("<b style=\"font-size:13px;font-family:arial\">Stock Code:</b>"),new HBoxLayoutData(new Margins(0, 5, 0, 15)));
		
		codeComboBox = new ComboBox<BeanModel>();
		codeComboBox.setEmptyText("Select a code...");
		codeComboBox.setFieldLabel("Code");
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
		panel.add(codeComboBox,  new HBoxLayoutData(new Margins(0, 5, 0, 0)));
		
		panel.add(new Html("<b style=\"font-size:13px;font-family:arial\">Stock Name:</b>"),new HBoxLayoutData(new Margins(0, 5, 0, 15)));
		
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
		panel.add(nameComboBox,  new HBoxLayoutData(new Margins(0, 5, 0, 0)));
		
		panel.add(viewButton,  new HBoxLayoutData(new Margins(0, 5, 0, 15)));
		
	}
	
	public void setStocks(ArrayList<StockName> stocks) {
		BeanModelFactory beanModelFactory = BeanModelLookup.get().getFactory(StockName.class);
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

	@Override
	public Widget asWidget() {
		return panel;
	}
}

package org.sjd.gordon.client;

import java.util.ArrayList;
import java.util.List;

import org.sjd.gordon.model.StockEntity;

import com.extjs.gxt.ui.client.Registry;
import com.extjs.gxt.ui.client.data.BeanModel;
import com.extjs.gxt.ui.client.data.BeanModelFactory;
import com.extjs.gxt.ui.client.data.BeanModelLookup;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.util.Padding;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.ComboBox.TriggerAction;
import com.extjs.gxt.ui.client.widget.layout.VBoxLayout;
import com.extjs.gxt.ui.client.widget.layout.VBoxLayout.VBoxLayoutAlign;
import com.extjs.gxt.ui.client.widget.layout.VBoxLayoutData;
import com.google.gwt.user.client.Element;

public class NavigationPanel extends LayoutContainer {

	@Override
	protected void onRender(Element parent, int index) {
		super.onRender(parent, index);

		VBoxLayout layout = new VBoxLayout();  
        layout.setPadding(new Padding(5));  
        layout.setVBoxLayoutAlign(VBoxLayoutAlign.LEFT);  
        setLayout(layout);
        
		List<StockEntity> stocks = create();
		BeanModelFactory beanModelFactory = BeanModelLookup.get().getFactory(StockEntity.class);
		List<BeanModel> stockModelList = beanModelFactory.createModel(stocks);
		final ListStore<BeanModel> stockStore = Registry.get(Gordon.STOCKS_STORE);
		stockStore.add(stockModelList);
		
		ComboBox<BeanModel> codeComboBox = new ComboBox<BeanModel>();
		codeComboBox.setEmptyText("Select a code...");
		codeComboBox.setDisplayField("code");
		codeComboBox.setWidth(150);
		codeComboBox.setStore(stockStore);
		codeComboBox.setTypeAhead(true);
		codeComboBox.setTriggerAction(TriggerAction.ALL);
		add(codeComboBox, new VBoxLayoutData(new Margins(5, 5, 5, 5)));
		
		ComboBox<BeanModel> nameComboBox = new ComboBox<BeanModel>();
		nameComboBox.setEmptyText("Select a name...");
		nameComboBox.setDisplayField("name");
		nameComboBox.setWidth(150);
		nameComboBox.setStore(stockStore);
		nameComboBox.setTypeAhead(true);
		nameComboBox.setTriggerAction(TriggerAction.ALL);
		add(nameComboBox, new VBoxLayoutData(new Margins(5, 5, 5, 5)));
		
		
	}

	private List<StockEntity> create() {
		ArrayList<StockEntity> list = new ArrayList<StockEntity>();
		StockEntity stock = new StockEntity();
		stock.setCode("ABC");
		stock.setName("ABC Ltd");
		list.add(stock);
		return list;
	}

}

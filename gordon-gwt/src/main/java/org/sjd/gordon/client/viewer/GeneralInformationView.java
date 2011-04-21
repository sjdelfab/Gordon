package org.sjd.gordon.client.viewer;

import org.sjd.gordon.model.StockEntity;

import com.extjs.gxt.ui.client.Style.LayoutRegion;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.form.FormPanel.LabelAlign;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.layout.BorderLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.extjs.gxt.ui.client.widget.layout.FormData;
import com.extjs.gxt.ui.client.widget.layout.FormLayout;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Element;

public class GeneralInformationView extends LayoutContainer implements GeneralInformationDisplay {

	  private TextField<String> nameTextField = new TextField<String>();
	  private TextField<String> codeTextField = new TextField<String>();
	  private TextField<String> listDateTextField = new TextField<String>();
	  private TextField<String> lastTradeTextField = new TextField<String>();
	  
	  private DateTimeFormat dateFormatter = DateTimeFormat.getFormat("dd/MM/yyyy");
	  
	  @Override  
	  protected void onRender(Element parent, int index) {  
	    super.onRender(parent, index);  
	    setLayout(new BorderLayout());  
	  
	    LayoutContainer main = new LayoutContainer();
	    main.setStyleAttribute("paddingLeft", "10px");
	    main.setStyleAttribute("paddingTop", "10px");
	    FormLayout layout = new FormLayout();  
	    layout.setLabelAlign(LabelAlign.LEFT);
	    main.setLayout(layout);  

	    nameTextField.setFieldLabel("Name");  
	    main.add(nameTextField, new FormData("30%")); 
	    
	    codeTextField.setFieldLabel("Code");  
	    main.add(codeTextField, new FormData("30%"));
	    
	    listDateTextField.setFieldLabel("List Date");
	    listDateTextField.setEnabled(false);
	    main.add(listDateTextField, new FormData("30%"));
	    
	    lastTradeTextField.setEnabled(false);
	    lastTradeTextField.setFieldLabel("Last Trade Date");
	    main.add(lastTradeTextField, new FormData("30%"));
	        
	    add(main,new BorderLayoutData(LayoutRegion.CENTER));  
	  }

	@Override
	public void setStock(StockEntity stock) {
		nameTextField.setValue(stock.getName());
		codeTextField.setValue(stock.getCode());
		if (stock.getListDate() != null) {
		   listDateTextField.setValue(dateFormatter.format(stock.getListDate()));
		}
		if (stock.getLastTradeDate() != null) {
		   lastTradeTextField.setValue(dateFormatter.format(stock.getLastTradeDate()));
		}
	}
	
}

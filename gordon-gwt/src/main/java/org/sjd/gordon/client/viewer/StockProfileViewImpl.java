package org.sjd.gordon.client.viewer;

import org.sjd.gordon.shared.viewer.StockDetails;

import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.form.FormPanel.LabelAlign;
import com.extjs.gxt.ui.client.widget.form.LabelField;
import com.extjs.gxt.ui.client.widget.layout.FormData;
import com.extjs.gxt.ui.client.widget.layout.FormLayout;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.inject.name.Named;
import com.gwtplatform.mvp.client.ViewImpl;

public class StockProfileViewImpl extends ViewImpl implements StockProfilePresenter.StockProfileView {

	private LabelField nameTextField = new LabelField();
	private LabelField codeTextField = new LabelField();
	private LabelField gicsSectorTextField = new LabelField();
	private LabelField gicsIndustryGroupTextField = new LabelField();
	private LabelField listDateTextField = new LabelField();
	private LabelField lastTradeTextField = new LabelField();

	private DateTimeFormat dateFormatter = DateTimeFormat.getFormat("dd/MM/yyyy");

	@Inject
	@Named("width")
	private int width;
	@Inject
	@Named("height")
	private int height;
	private LayoutContainer main;
	
	public StockProfileViewImpl() {
		main = new LayoutContainer();
		main.setStyleAttribute("backgroundColor", "#FFFFFF");
		main.setStyleAttribute("paddingLeft", "10px");
		main.setStyleAttribute("paddingTop", "10px");
		FormLayout layout = new FormLayout();
		layout.setLabelAlign(LabelAlign.LEFT);
		main.setLayout(layout);

		nameTextField.setFieldLabel("Name");
		main.add(nameTextField, new FormData("30%"));

		codeTextField.setFieldLabel("Code");
		main.add(codeTextField, new FormData("30%"));

		gicsSectorTextField.setFieldLabel("GICS Sector");
		main.add(gicsSectorTextField, new FormData("30%"));
		
		gicsIndustryGroupTextField.setFieldLabel("GICS Industry Group");
		main.add(gicsIndustryGroupTextField, new FormData("30%"));
		
		listDateTextField.setFieldLabel("List Date");
		listDateTextField.setEnabled(false);
		main.add(listDateTextField, new FormData("30%"));

		lastTradeTextField.setEnabled(false);
		lastTradeTextField.setFieldLabel("Last Trade Date");
		main.add(lastTradeTextField, new FormData("30%"));
	}

	@Override
	public void setStock(StockDetails stockDetails) {
		nameTextField.setValue(stockDetails.getName());
		codeTextField.setValue(stockDetails.getCode());
		gicsSectorTextField.setValue(stockDetails.getPrimarySectorName());
		gicsIndustryGroupTextField.setValue(stockDetails.getPrimaryIndustryGroupName());
		if (stockDetails.getListDate() != null) {
			listDateTextField.setValue(dateFormatter.format(stockDetails.getListDate()));
		}
		if (stockDetails.getLastTradeDate() != null) {
			lastTradeTextField.setValue(dateFormatter.format(stockDetails.getLastTradeDate()));
		}
	}

	@Override
	public Widget asWidget() {
		main.setSize(width-200, height-50);
		return main;
	}

}

package org.sjd.gordon.client.viewer;

import org.sjd.gordon.shared.viewer.StockDetails;

import com.extjs.gxt.ui.client.Style.LayoutRegion;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.form.FormPanel.LabelAlign;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.layout.BorderLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.extjs.gxt.ui.client.widget.layout.FormData;
import com.extjs.gxt.ui.client.widget.layout.FormLayout;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewImpl;

public class GeneralInformationViewImpl extends ViewImpl implements GeneralInformationPresenter.GeneralInformationView {

	private TextField<String> nameTextField = new TextField<String>();
	private TextField<String> codeTextField = new TextField<String>();
	private TextField<String> listDateTextField = new TextField<String>();
	private TextField<String> lastTradeTextField = new TextField<String>();

	private DateTimeFormat dateFormatter = DateTimeFormat.getFormat("dd/MM/yyyy");

	private LayoutContainer container;

	public GeneralInformationViewImpl() {
		container = new LayoutContainer();
		container.setLayout(new BorderLayout());

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

		container.add(main, new BorderLayoutData(LayoutRegion.CENTER));
	}

	@Override
	public void setStock(StockDetails stockDetails) {
		nameTextField.setValue(stockDetails.getName());
		codeTextField.setValue(stockDetails.getCode());
		if (stockDetails.getListDate() != null) {
			listDateTextField.setValue(dateFormatter.format(stockDetails.getListDate()));
		}
		if (stockDetails.getLastTradeDate() != null) {
			lastTradeTextField.setValue(dateFormatter.format(stockDetails.getLastTradeDate()));
		}
	}

	@Override
	public Widget asWidget() {
		return container;
	}

}

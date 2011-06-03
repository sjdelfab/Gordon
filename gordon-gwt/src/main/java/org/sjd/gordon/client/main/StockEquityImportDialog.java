package org.sjd.gordon.client.main;

import org.sjd.gordon.client.common.ProgressMonitor;

import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.FormEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.FileUpload;


public class StockEquityImportDialog extends Window {

	private ImportPanel importPanel;
	private com.google.gwt.dom.client.Element loadingMessage = ProgressMonitor.getImportMonitor();
	
	public StockEquityImportDialog() {
		setHeading("Import");
		setTitle("Import");
	    setBodyStyleName("pad-text");
	    setLayout(new FitLayout());
	    importPanel = new ImportPanel();
	    setScrollMode(Scroll.AUTO);  
	    setResizable(true);
	    setSize(350,110);
	}
	
	@Override 
	protected void onRender(Element parent, int pos) {
		super.onRender(parent, pos);
		add(importPanel);		
	}
	
	private class ImportPanel extends FormPanel {
		
		private FileUpload upload;
		
		
		private ImportPanel() {
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
					StockEquityImportDialog.this.setVisible(false);
				}
			});
	        addButton(cancelButton);
	        addListener(Events.BeforeSubmit, new Listener<FormEvent>() {
				@Override
				public void handleEvent(FormEvent be) {
					StockEquityImportDialog.this.getElement().appendChild(loadingMessage);
				}
	        });

	        addListener(Events.Submit, new Listener<FormEvent>() {
				@Override
				public void handleEvent(FormEvent be) {
					StockEquityImportDialog.this.getElement().removeChild(loadingMessage);
					importButton.setEnabled(true);
					String result = be.getResultHtml();
					if (result.equals("SUCCESS")) {
						StockEquityImportDialog.this.setVisible(false);
					} else {
						MessageBox.alert("Error", "Unable to import stock equity. See server logs for details.", null);
					}
				}
	        });
		}
		
		@Override  
		protected void onRender(Element parent, int index) {
			super.onRender(parent, index);
	        setAction(GWT.getModuleBaseURL() + "StockEquityFileImporter");
	        
	        // Because we're going to add a FileUpload widget, we'll need to set the
	        // form to use the POST method, and multipart MIME encoding.
	        setEncoding(Encoding.MULTIPART);
	        setMethod(Method.POST);
	        
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
	}
}

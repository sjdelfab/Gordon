package org.sjd.gordon.client.common;

import com.extjs.gxt.ui.client.Style.LayoutRegion;
import com.extjs.gxt.ui.client.Style.Scroll;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.Dialog;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;

public class EditDialog<T> extends Dialog {

	public EditDialog(final AbstractEditPanel<T> editorPanel, final EditDialogCallback<T> callback) {
		setButtons(Dialog.OKCANCEL);  
	    setBodyStyleName("pad-text");
	    BorderLayoutData data = new BorderLayoutData(LayoutRegion.CENTER);
	    
	    add(editorPanel,data);  
	    setScrollMode(Scroll.AUTO);  
	    setHideOnButtonClick(false);
	    getButtonById(Dialog.OK).addSelectionListener(new SelectionListener<ButtonEvent>() {
			@Override
			public void componentSelected(ButtonEvent ce) {
				if (!editorPanel.isValid()) {
					ce.cancelBubble();
					return;
				}
				T details = editorPanel.unmarshal(); 
				setVisible(false);
			    callback.commit(details);
			}
		});
	    getButtonById(Dialog.CANCEL).addSelectionListener(new SelectionListener<ButtonEvent>() {
			@Override
			public void componentSelected(ButtonEvent ce) {
				setVisible(false);
			}
	    });
	    setResizable(false);	
	}
	
}

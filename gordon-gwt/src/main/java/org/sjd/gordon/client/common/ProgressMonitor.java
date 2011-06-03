package org.sjd.gordon.client.common;

import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.DOM;

public class ProgressMonitor {

	public static Element getMonitor() {
		Element loadingMessage = DOM.createDiv();
		loadingMessage.setId("progress_loading");
		Element innerDiv = DOM.createDiv();
		innerDiv.setClassName("progress_loading-indicator");
		Element img = DOM.createImg();
		img.setAttribute("src", "resources/images/default/shared/large-loading.gif");
		img.setAttribute("width","32");
		img.setAttribute("height","32");
		innerDiv.appendChild(img);
		loadingMessage.appendChild(innerDiv);
		return loadingMessage;
	}
	
	public static Element getImportMonitor() {
		Element loadingMessage = DOM.createDiv();
		loadingMessage.setId("progress_importing");
		Element innerDiv = DOM.createDiv();
		innerDiv.setClassName("progress_importing-indicator");
		Element img = DOM.createImg();
		img.setAttribute("src", "resources/images/default/shared/large-loading.gif");
		img.setAttribute("width","32");
		img.setAttribute("height","32");
		innerDiv.appendChild(img);
		loadingMessage.appendChild(innerDiv);
		return loadingMessage;
	}

}

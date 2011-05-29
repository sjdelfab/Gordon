package org.sjd.gordon.client.common;

import com.extjs.gxt.ui.client.widget.LayoutContainer;

public abstract class AbstractEditPanel<T> extends LayoutContainer {
	
	public abstract T unmarshal();
	public abstract void marshal(T data);
	
	public boolean isValid() {
		return true;
	}

}

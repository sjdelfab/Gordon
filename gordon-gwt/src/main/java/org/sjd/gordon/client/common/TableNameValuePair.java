package org.sjd.gordon.client.common;

import com.extjs.gxt.ui.client.data.BaseModel;

public class TableNameValuePair extends BaseModel {

	private static final long serialVersionUID = -9074401690740705950L;
	
	public static final String NAME = "name";
	public static final String VALUE = "value";
	
	private boolean showInfoIcon;
	private Object informationToken;
	private boolean editable;
	private boolean showGraph;
	private boolean canDateRangeable;
	
	public TableNameValuePair(String name, String value) {
		set(NAME,name);
		set(VALUE,value);
	}

	public boolean isEditable() {
		return editable;
	}
	
	public void setEditable(boolean editable) {
		this.editable = editable;
	}
	
	public boolean isShowGraph() {
		return showGraph;
	}
	
	public void setShowGraph(boolean showGraph) {
		this.showGraph = showGraph;
	}
	
	public boolean isShowInfoIcon() {
		return showInfoIcon;
	}
	
	public void setShowInfoIcon(boolean showInfoIcon) {
		this.showInfoIcon = showInfoIcon;
	}
	
	public Object getInformationToken() {
		return informationToken;
	}

	public void setInformationToken(Object informationToken) {
		this.informationToken = informationToken;
	}
	
	public boolean isCanDateRangeable() {
		return canDateRangeable;
	}
	
	public void setCanDateRangeable(boolean canDateRangeable) {
		this.canDateRangeable = canDateRangeable;
	}
}

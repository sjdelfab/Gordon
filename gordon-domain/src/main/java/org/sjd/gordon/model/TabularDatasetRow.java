package org.sjd.gordon.model;

import java.io.Serializable;

public class TabularDatasetRow implements Serializable {

	private static final long serialVersionUID = -7073431680079937274L;
	
	private TabularDatasetElement[] data;
	
	public TabularDatasetRow(int size) {
		this.data = new TabularDatasetElement[size];
	}
	
	public void set(int index, TabularDatasetElement element) {
		data[index] = element;
	}
	
	public TabularDatasetElement get(int index) {
		return data[index];
	}
	
	public int getSize() {
		return data.length;
	}
}

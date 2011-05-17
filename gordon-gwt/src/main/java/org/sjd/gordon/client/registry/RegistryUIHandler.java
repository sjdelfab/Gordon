package org.sjd.gordon.client.registry;

import org.sjd.gordon.shared.viewer.StockDetail;

import com.gwtplatform.mvp.client.UiHandlers;

public interface RegistryUIHandler extends UiHandlers {

	public void delete(StockDetail stock);
	public void add(StockDetail details);
	public void update(StockDetail details);
	
}

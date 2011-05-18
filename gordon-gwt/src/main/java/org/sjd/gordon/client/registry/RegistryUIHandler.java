package org.sjd.gordon.client.registry;

import org.sjd.gordon.shared.viewer.StockDetail;

import com.extjs.gxt.ui.client.data.PagingLoadConfig;
import com.extjs.gxt.ui.client.data.PagingLoadResult;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.gwtplatform.mvp.client.UiHandlers;

public interface RegistryUIHandler extends UiHandlers {

	public void delete(StockDetail stock);
	public void add(StockDetail details);
	public void update(StockDetail details);
	public void load(PagingLoadConfig loadConfig, AsyncCallback<PagingLoadResult<StockDetail>> callback);
}

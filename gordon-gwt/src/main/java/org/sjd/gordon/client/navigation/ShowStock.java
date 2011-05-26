package org.sjd.gordon.client.navigation;

import org.sjd.gordon.shared.navigation.StockName;

import com.gwtplatform.dispatch.annotation.GenEvent;
import com.gwtplatform.dispatch.annotation.Order;

@GenEvent
public class ShowStock {

	@Order(1) StockName stock;
}

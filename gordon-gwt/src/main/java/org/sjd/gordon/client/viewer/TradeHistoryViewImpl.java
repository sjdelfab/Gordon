package org.sjd.gordon.client.viewer;

import java.util.ArrayList;

import org.sjd.gordon.model.StockDayTradeRecord;

import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.layout.CenterLayout;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.visualization.client.AbstractDataTable.ColumnType;
import com.google.gwt.visualization.client.DataTable;
import com.google.gwt.visualization.client.VisualizationUtils;
import com.google.gwt.visualization.client.visualizations.AnnotatedTimeLine;
import com.google.gwt.visualization.client.visualizations.AnnotatedTimeLine.Options;
import com.google.inject.Inject;
import com.google.inject.name.Named;
import com.gwtplatform.mvp.client.ViewImpl;

public class TradeHistoryViewImpl extends ViewImpl implements TradeHistoryPresenter.TradeHistoryView {
	
	@Inject
	@Named("width")
	private int width;
	@Inject
	@Named("height")
	private int height;
	private LayoutContainer container;
	
	public TradeHistoryViewImpl() {
		container = new LayoutContainer();
		container.setLayout(new CenterLayout()); 		
	}

	@Override
	public void setTradeHistory(final ArrayList<StockDayTradeRecord> tradeHistory) {
		Runnable onLoadCallback = new Runnable() {

			@Override
			public void run() {
			    Options options = Options.create();
			    options.setDisplayAnnotations(true);
			    
			    DataTable data = DataTable.create();
			    data.addColumn(ColumnType.DATE, "Date");
			    data.addColumn(ColumnType.NUMBER, "Closed Price");
			    data.addRows(tradeHistory.size());
			    for(int i=0; i < tradeHistory.size(); i++) {
			    	StockDayTradeRecord rec = tradeHistory.get(i);
			    	data.setValue(i, 0, rec.getDate());
			    	data.setValue(i, 1, rec.getClosePrice().doubleValue());
			    }
			    int graphWidth = width-314;
			    int graphHeight = height-136;
			    AnnotatedTimeLine chart = new AnnotatedTimeLine(data, options, graphWidth+ "px", graphHeight + "px");
			    container.add(chart);
			    container.layout(true);
			}
			
		};
		VisualizationUtils.loadVisualizationApi(onLoadCallback, AnnotatedTimeLine.PACKAGE);
	}

	@Override
	public Widget asWidget() {
		container.setSize(width-200, height-50);
		return container;
	}
}

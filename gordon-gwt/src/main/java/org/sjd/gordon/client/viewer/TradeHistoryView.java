package org.sjd.gordon.client.viewer;

import java.util.ArrayList;

import org.sjd.gordon.model.StockDayTradeRecord;

import com.allen_sauer.gwt.log.client.Log;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.layout.FlowLayout;
import com.google.gwt.user.client.Element;
import com.google.gwt.visualization.client.AbstractDataTable.ColumnType;
import com.google.gwt.visualization.client.DataTable;
import com.google.gwt.visualization.client.VisualizationUtils;
import com.google.gwt.visualization.client.visualizations.AnnotatedTimeLine;
import com.google.gwt.visualization.client.visualizations.AnnotatedTimeLine.Options;

public class TradeHistoryView extends LayoutContainer implements TradeHistoryDisplay {
	
	@Override
	protected void onRender(Element parent, int index) {
		super.onRender(parent, index);
		setLayout(new FlowLayout(10)); 		
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
			    	data.setValue(i, 1, rec.getClosePrice());
			    }
			   
			    Log.info("Create chart.");
			    AnnotatedTimeLine chart = new AnnotatedTimeLine(data, options, "1000px", "600px");
			    Log.info("Made chart");
			    add(chart);
			    doLayout(true);
			    Log.info("Added chart.");
			}
			
		};
		
		VisualizationUtils.loadVisualizationApi(onLoadCallback, AnnotatedTimeLine.PACKAGE);
	}
}

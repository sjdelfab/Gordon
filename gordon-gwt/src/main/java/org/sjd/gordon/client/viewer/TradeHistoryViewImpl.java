package org.sjd.gordon.client.viewer;

import java.util.ArrayList;

import org.sjd.gordon.model.StockDayTradeRecord;

import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.layout.CenterLayout;
import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.DOM;
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
	
	private Element loadingMessage;
	
	public TradeHistoryViewImpl() {
		container = new LayoutContainer();
		container.setLayout(new CenterLayout());
		// TODO Put in convenience utility method 
		loadingMessage = DOM.createDiv();
		loadingMessage.setId("progress_loading");
		Element innerDiv = DOM.createDiv();
		innerDiv.setClassName("progress_loading-indicator");
		Element img = DOM.createImg();
		img.setAttribute("src", "resources/images/default/shared/large-loading.gif");
		img.setAttribute("width","32");
		img.setAttribute("height","32");
		innerDiv.appendChild(img);
		loadingMessage.appendChild(innerDiv);
	}

	@Override
	public void setTradeHistory(final ArrayList<StockDayTradeRecord> tradeHistory) {
		container.getElement().appendChild(loadingMessage);
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
			    int graphWidth = width-50;
			    int graphHeight = height-176;
			    AnnotatedTimeLine chart = new AnnotatedTimeLine(data, options, graphWidth+ "px", graphHeight + "px");
			    container.getElement().removeChild(loadingMessage);
			    container.add(chart);
			    container.layout(true);
			}
			
		};
		VisualizationUtils.loadVisualizationApi(onLoadCallback, AnnotatedTimeLine.PACKAGE);
	}

	@Override
	public Widget asWidget() {
		container.setSize(width, height-130);
		return container;
	}
}

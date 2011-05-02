package org.sjd.gordon.server.devhandlers;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.sjd.gordon.model.StockDayTradeRecord;
import org.sjd.gordon.shared.viewer.GetTradeHistory;
import org.sjd.gordon.shared.viewer.GotTradeHistory;

import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

public class GetTradeHistoryDevHandler implements ActionHandler<GetTradeHistory, GotTradeHistory> {

	private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");

	@Override
	public GotTradeHistory execute(GetTradeHistory arg0, ExecutionContext arg1) throws ActionException {
		ArrayList<StockDayTradeRecord> history = new ArrayList<StockDayTradeRecord>();
		StockDayTradeRecord record = new StockDayTradeRecord();
		try {
			record.setClosePrice(10.0);
			record.setDate(dateFormat.parse("01/6/2010"));
			record.setVolume(10000);
			history.add(record);
			record = new StockDayTradeRecord();
			record.setClosePrice(11.0);
			record.setDate(dateFormat.parse("02/6/2010"));
			record.setVolume(20000);
			history.add(record);
			record = new StockDayTradeRecord();
			record.setClosePrice(12.0);
			record.setDate(dateFormat.parse("03/6/2010"));
			record.setVolume(30000);
			history.add(record);
			record = new StockDayTradeRecord();
			record.setClosePrice(13.0);
			record.setDate(dateFormat.parse("04/6/2010"));
			record.setVolume(40000);
			history.add(record);
			record = new StockDayTradeRecord();
			record.setClosePrice(14.0);
			record.setDate(dateFormat.parse("05/6/2010"));
			record.setVolume(50000);
			history.add(record);
		} catch (Exception ex) {
			throw new ActionException(ex);
		}
		return new GotTradeHistory(history);
	}

	@Override
	public Class<GetTradeHistory> getActionType() {
		return GetTradeHistory.class;
	}

	@Override
	public void undo(GetTradeHistory action, GotTradeHistory result, ExecutionContext arg2) throws ActionException {
	}

}

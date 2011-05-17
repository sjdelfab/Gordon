package org.sjd.gordon.server.devhandlers;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.sjd.gordon.model.StockDayTradeRecord;
import org.sjd.gordon.shared.viewer.GetTradeHistoryAction;
import org.sjd.gordon.shared.viewer.GetTradeHistoryResult;

import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

public class GetTradeHistoryDevHandler implements ActionHandler<GetTradeHistoryAction, GetTradeHistoryResult> {

	private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");

	@Override
	public GetTradeHistoryResult execute(GetTradeHistoryAction action, ExecutionContext context) throws ActionException {
		ArrayList<StockDayTradeRecord> history = new ArrayList<StockDayTradeRecord>();
		StockDayTradeRecord record = new StockDayTradeRecord();
		try {
			record.setClosePrice(new BigDecimal("10.0"));
			record.setDate(dateFormat.parse("01/6/2010"));
			record.setVolume(10000);
			history.add(record);
			record = new StockDayTradeRecord();
			record.setClosePrice(new BigDecimal("11.0"));
			record.setDate(dateFormat.parse("02/6/2010"));
			record.setVolume(20000);
			history.add(record);
			record = new StockDayTradeRecord();
			record.setClosePrice(new BigDecimal("12.0"));
			record.setDate(dateFormat.parse("03/6/2010"));
			record.setVolume(30000);
			history.add(record);
			record = new StockDayTradeRecord();
			record.setClosePrice(new BigDecimal("13.0"));
			record.setDate(dateFormat.parse("04/6/2010"));
			record.setVolume(40000);
			history.add(record);
			record = new StockDayTradeRecord();
			record.setClosePrice(new BigDecimal("14.0"));
			record.setDate(dateFormat.parse("05/6/2010"));
			record.setVolume(50000);
			history.add(record);
		} catch (Exception ex) {
			throw new ActionException(ex);
		}
		return new GetTradeHistoryResult(history);
	}

	@Override
	public Class<GetTradeHistoryAction> getActionType() {
		return GetTradeHistoryAction.class;
	}

	@Override
	public void undo(GetTradeHistoryAction action, GetTradeHistoryResult result, ExecutionContext context) throws ActionException {
	}

}

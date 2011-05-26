package org.sjd.gordon.client.viewer;

import org.sjd.gordon.client.AbstractCallback;
import org.sjd.gordon.model.BusinessSummary;
import org.sjd.gordon.shared.viewer.UpdateBusinessSummaryResult;

import com.google.gwt.user.client.rpc.AsyncCallback;

public abstract class UpdateBusinessSummaryCallback extends AbstractCallback implements AsyncCallback<UpdateBusinessSummaryResult> {

	@Override
	public void onSuccess(UpdateBusinessSummaryResult result) {
		updated(result.getUpdatedBusinessSummary());
	}

	public abstract void updated(BusinessSummary summary);
}

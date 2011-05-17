package org.sjd.gordon.client.registry;

import org.sjd.gordon.model.Exchange;

import com.gwtplatform.dispatch.annotation.GenEvent;
import com.gwtplatform.dispatch.annotation.In;

@GenEvent
public class ShowRegistry {

	@In(1) Exchange exchange;
}

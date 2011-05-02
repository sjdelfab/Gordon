package org.sjd.gordon.ejb;

import java.util.List;

import javax.ejb.Local;

import org.sjd.gordon.model.Exchange;

@Local
public interface ExchangeService {

	public Exchange findExchangeById(Integer id);
	
	public Exchange createExchange(Exchange exchange);
	
	public List<Exchange> getExchanges();
}

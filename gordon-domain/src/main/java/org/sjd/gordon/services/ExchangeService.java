package org.sjd.gordon.services;

import java.util.List;

import org.sjd.gordon.model.Exchange;

public interface ExchangeService {

	public Exchange findExchangeById(Integer id);
	
	public Exchange createExchange(Exchange exchange);
	
	public List<Exchange> getExchanges();
	
	public Exchange getExchangeByName(String name);
	public Exchange getExchangeByCode(String code);
	
}

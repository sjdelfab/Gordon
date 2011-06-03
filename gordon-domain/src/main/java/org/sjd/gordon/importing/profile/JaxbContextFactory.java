package org.sjd.gordon.importing.profile;

import java.io.InputStream;
import java.io.Reader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.sjd.gordon.importing.profile.jaxb.StockEquities;

public class JaxbContextFactory {

	private volatile static JaxbContextFactory contextFactoryInstance;
	
	// Can re-use jaxb context in multi threaded environment: https://jaxb.dev.java.net/guide/Performance_and_thread_safety.html
	private JAXBContext jaxbContext;
	
	private JaxbContextFactory() throws JAXBException {
		this.jaxbContext = JAXBContext.newInstance("org.sjd.gordon.importing.profile.jaxb");
	}
	
	protected Unmarshaller getUnmarshaller() throws JAXBException {
		Unmarshaller unMarshaller = jaxbContext.createUnmarshaller();
		return unMarshaller;
	}
	
	public StockEquities unmarshal(InputStream in) throws JAXBException {
		Unmarshaller unMarshaller = getUnmarshaller();
        return (StockEquities) unMarshaller.unmarshal(in);
	}

	public StockEquities unmarshal(Reader reader) throws JAXBException {
		Unmarshaller unMarshaller = getUnmarshaller();
		return (StockEquities) unMarshaller.unmarshal(reader);
	}

	public static JaxbContextFactory getFactory() throws JAXBException {
		if (contextFactoryInstance == null) {
			synchronized (JaxbContextFactory.class) {
				if (contextFactoryInstance == null) {
			       contextFactoryInstance = new JaxbContextFactory();
				}
			}
		}
		return contextFactoryInstance;
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException("Please do not clone this.");
	}

}

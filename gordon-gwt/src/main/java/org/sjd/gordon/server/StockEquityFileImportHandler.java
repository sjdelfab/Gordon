package org.sjd.gordon.server;

import java.io.Reader;
import java.util.Map;

import javax.xml.bind.JAXBException;

import org.sjd.gordon.importing.ImportException;
import org.sjd.gordon.importing.profile.JaxbContextFactory;
import org.sjd.gordon.importing.profile.StockEquityImporterService;
import org.sjd.gordon.importing.profile.jaxb.StockEquities;
import org.sjd.gordon.importing.profile.jaxb.StockEquity;

import com.google.inject.Inject;
import com.google.inject.Provider;

@SuppressWarnings("serial")
public class StockEquityFileImportHandler extends AbstractFileImportHandler {

	@Inject
	private Provider<StockEquityImporterService> importerProvider;
	
	@Override
	protected void importFile(Map<String,String> parameters, Reader reader) throws ImportException {
		try {
			StockEquityImporterService importer = importerProvider.get();
			StockEquities stockEquities = JaxbContextFactory.getFactory().unmarshal(reader);
			for (StockEquity stockEquity : stockEquities.getStockEquity()) {
				importer.importStockEquity(stockEquity);
			}
		} catch (JAXBException cause) {
			throw new ImportException(cause);
		}
	}

}

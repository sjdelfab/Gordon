package org.sjd.gordon.ejb;

import java.sql.Connection;

import javax.sql.DataSource;

import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.DatabaseSequenceFilter;
import org.dbunit.dataset.FilteredDataSet;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.filter.ITableFilter;
import org.dbunit.dataset.filter.IncludeTableFilter;
import org.dbunit.operation.DatabaseOperation;


public abstract class AbstractEJBTest {

	public static void truncateDatabase() throws Exception {
		DataSource dataSource = (DataSource)AllEjbTests.ctx.lookup("jdbc/Gordon");
		Connection sqlConnection = dataSource.getConnection();
		DatabaseConnection connection = new DatabaseConnection(sqlConnection);
		IncludeTableFilter filter = new IncludeTableFilter();       
        filter.includeTable("GICS_*");
        filter.includeTable("EXCHANGE");
        filter.includeTable("STOCK");
        filter.includeTable("UI_*");
        filter.includeTable("STOCK_DAY_TRADE");
        filter.includeTable("COLUMN_DEFINITION");
        filter.includeTable("TABULAR_*");
        filter.includeTable("UNITARY_*");
        filter.includeTable("AUTHENTICATION_USER");
        IDataSet dataset = new FilteredDataSet(filter,connection.createDataSet());
        ITableFilter tableFilter = new DatabaseSequenceFilter(connection);
        IDataSet filteredDataset = new FilteredDataSet(tableFilter,dataset);
        DatabaseOperation.DELETE_ALL.execute(connection, filteredDataset);
	}

}

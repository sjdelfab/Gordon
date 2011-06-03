package org.sjd.gordon.ejb.setup;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.sjd.gordon.model.StockEntity;
import org.sjd.gordon.model.TabularDatasetDefinition;
import org.sjd.gordon.model.TabularDatasetElement;
import org.sjd.gordon.model.TabularDatasetRow;

@Stateless
public class TabularDatasetEJB {

	@PersistenceContext
    private EntityManager em;
	
	public TabularDatasetDefinition addDefinition(TabularDatasetDefinition definition) {
		em.persist(definition);
		return definition;
	}
	
	public TabularDatasetDefinition updateDefinition(TabularDatasetDefinition definition) {
		return em.merge(definition);
	}
	
	public void deleteDefinition(TabularDatasetDefinition definition) {
		em.remove(em.merge(definition));
	}
	
	public List<TabularDatasetDefinition> getDefinitions() {
		String getDefinitions = "SELECT d FROM TabularDatasetDefinition d";
    	return em.createQuery(getDefinitions, TabularDatasetDefinition.class).getResultList();
	}
	
	public TabularDatasetRow addRow(TabularDatasetRow row) {
		for(int i=0; i < row.getSize(); i++) {
			TabularDatasetElement element = row.get(i);
			em.persist(element);
			row.set(i, element);
		}
		return row;
	}
	
	public List<TabularDatasetRow> getData(StockEntity security, TabularDatasetDefinition definition) {
		// TODO: Replace with Native query because inner select sucks - only because the object 
		// relationships aren't there (for performance reasons)
		String getData = "SELECT e FROM TabularDatasetElement e " +
				         "WHERE e.entityId = :entityId AND " +
				                "e.columnDefinition.id IN (SELECT cd.id FROM TabularDatasetDefinition d " +
				                                          "INNER JOIN d.columnDefinitions cd " +
				                                          "WHERE d.id = :definitionId ) " +
				         "ORDER BY e.rowIndex ASC, e.columnDefinition.columnOrder ASC";
		TypedQuery<TabularDatasetElement> query = em.createQuery(getData, TabularDatasetElement.class);
		query.setParameter("entityId", security.getId());
		query.setParameter("definitionId", definition.getId());
		List<TabularDatasetElement> elements = query.getResultList();
		if (elements.isEmpty()) {
			return Collections.emptyList();
		}
		int numberOfColumns = definition.getColumnDefinitions().size();
		List<TabularDatasetRow> data = new ArrayList<TabularDatasetRow>(elements.size()/numberOfColumns);
		TabularDatasetRow row = null;
		int currentColumn = 0;
		for(int i=0; i < elements.size(); i++) {
			if (row == null || i % numberOfColumns == 0) {
				row = new TabularDatasetRow(numberOfColumns);
				currentColumn = 0;
				data.add(row);
			}
			row.set(currentColumn, elements.get(i));
			currentColumn++;
		}
		return data;
	}
	
	public TabularDatasetElement updateElement(TabularDatasetElement element) {
		return em.merge(element);
	}
	
	public void removeRow(Long rowIndex) {
		String deleteRowIndex = "DELETE FROM TabularDatasetElement t WHERE t.rowIndex = :rowIndex";
    	Query query = em.createQuery(deleteRowIndex);
    	query.setParameter("rowIndex", rowIndex);
    	query.executeUpdate();
    	String updateOtherRowIndex = "UPDATE TabularDatasetElement t SET t.rowIndex = t.rowIndex - 1 WHERE t.rowIndex > :rowIndex";
    	query = em.createQuery(updateOtherRowIndex);
    	query.setParameter("rowIndex", rowIndex);
    	query.executeUpdate();
	}
}

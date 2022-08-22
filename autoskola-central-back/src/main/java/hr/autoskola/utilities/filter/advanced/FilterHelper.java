package hr.autoskola.utilities.filter.advanced;

import java.util.Map;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

import hr.autoskola.model.FilterValue;
import hr.autoskola.utilities.filter.advanced.model.FilterFields;

public class FilterHelper {

	public static void sqlFilterQueryUpdate(
			StringBuilder query, 
			MapSqlParameterSource parameters, 
			Map<String, FilterValue> columns, 
			FilterFields options, 
			FilterExportable exportable) {
		if (query == null || parameters == null || options == null
				|| columns == null || columns.keySet() == null || columns.keySet().isEmpty()) return;

		for (Map.Entry<String, FilterValue> column : columns.entrySet()) {
			String columnName = column.getKey();
			FilterValue columnValue = column.getValue();
			
			if (columnValue == null) continue;

			filterStringQueryUpdate(query, parameters, options.getStringColumnsMap(), columnName, columnValue);
			filterNumberQueryUpdate(query, parameters, options.getNumberColumnsMap(), columnName, columnValue);
			filterBooleanQueryUpdate(query, parameters, options.getBooleanColumnsMap(), columnName, columnValue);
			filterListQueryUpdate(query, parameters, options.getListColumnsMap(), columnName, columnValue);
			filterDateQueryUpdate(query, parameters, options.getDateColumnsMap(), columnName, columnValue);
					
			if (exportable != null && options.getCustomColumns() != null && options.getCustomColumns().contains(columnName)) {
				exportable.customFilterFieldsCheck(query, parameters, columnName, columnValue);
			}
		}	
	}
	
	private static void filterStringQueryUpdate(StringBuilder query, MapSqlParameterSource parameters, 
			Map<String, String> mapOfColumns, String columnName, FilterValue columnValue) {
		if (mapOfColumns != null && mapOfColumns.containsKey(columnName) && columnValue.getValue() != null && !columnValue.getValue().isEmpty()) {
			query.append(" AND UPPER(" + mapOfColumns.get(columnName) + ") LIKE :" + columnName + " ");
			parameters.addValue(columnName, "%" + columnValue.getValue().toUpperCase() + "%");
		}
	}
		
	private static void filterNumberQueryUpdate(StringBuilder query, MapSqlParameterSource parameters, 
			Map<String, String> mapOfColumns, String columnName, FilterValue columnValue) {
		if (mapOfColumns != null && mapOfColumns.containsKey(columnName) && columnValue.getMin() != null && columnValue.getMax() != null) {
			query.append(" AND " + mapOfColumns.get(columnName) + " BETWEEN :" + columnName + "Min AND :" + columnName + "Max ");
			parameters.addValue(columnName + "Min", columnValue.getMin());
			parameters.addValue(columnName + "Max", columnValue.getMax());
		}
	}
	
	private static void filterBooleanQueryUpdate(StringBuilder query, MapSqlParameterSource parameters, 
			Map<String, String> mapOfColumns, String columnName, FilterValue columnValue) {
		if (mapOfColumns != null && mapOfColumns.containsKey(columnName) && columnValue.getActive() != null) {
			query.append(" AND " + mapOfColumns.get(columnName) + " = :" + columnName + " ");
			parameters.addValue(columnName, columnValue.getActive() ? "1" : "0");
		}
	}
	
	private static void filterListQueryUpdate(StringBuilder query, MapSqlParameterSource parameters, 
			Map<String, String> mapOfColumns, String columnName, FilterValue columnValue) {
		if (mapOfColumns != null && mapOfColumns.containsKey(columnName) && columnValue.getValues() != null && !columnValue.getValues().isEmpty()) {
			query.append(" AND " + mapOfColumns.get(columnName) + " IN (:" + columnName + ") ");
			parameters.addValue(columnName, columnValue.getValues());
		}
	}
	
	private static void filterDateQueryUpdate(StringBuilder query, MapSqlParameterSource parameters, 
			Map<String, String> mapOfColumns, String columnName, FilterValue columnValue) {
		if (mapOfColumns != null && mapOfColumns.containsKey(columnName) && columnValue.getFrom() != null && columnValue.getTo() != null) {
			query.append(" AND " + mapOfColumns.get(columnName) + " BETWEEN TRUNC(:" + columnName + "From) AND TRUNC(:" + columnName + "To + 1)");
			parameters.addValue(columnName + "From", columnValue.getFrom());
			parameters.addValue(columnName + "To", columnValue.getTo());
		}
	}
}


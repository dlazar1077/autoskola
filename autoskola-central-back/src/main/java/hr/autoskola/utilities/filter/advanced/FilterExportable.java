package hr.autoskola.utilities.filter.advanced;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

import hr.autoskola.model.FilterValue;

public interface FilterExportable {
	
	public void customFilterFieldsCheck(StringBuilder query, MapSqlParameterSource parameters, String columnName, FilterValue columnValue);

}

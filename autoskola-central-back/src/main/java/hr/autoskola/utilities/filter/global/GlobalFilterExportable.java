package hr.autoskola.utilities.filter.global;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

public interface GlobalFilterExportable {
	
	public void customGlobalFilter(StringBuilder query, MapSqlParameterSource parameters, String globalSearch);

}

package hr.autoskola.utilities.filter.global;

import java.util.List;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

public class GlobalFilterHelper {

	public static void sqlGlobalFilterQueryUpdate(
			StringBuilder query, 
			MapSqlParameterSource parameters, 
			String globalSearchString, 
			List<String> allGlobalSearchFields,
			GlobalFilterExportable globalFilterExportable
			) {
		if (query == null || parameters == null 
				|| globalSearchString == null || globalSearchString.isEmpty() 
				|| allGlobalSearchFields == null || allGlobalSearchFields.isEmpty()) return;
		
		query.append(" AND ( 1=0 ");			

		String upperGlobalSearchString = "%" + globalSearchString.toUpperCase() + "%";			
		for (String globalSearchField : allGlobalSearchFields) {
			query.append(" OR UPPER(" + globalSearchField + ") LIKE :upperGlobalSearchString ");
			parameters.addValue("upperGlobalSearchString", upperGlobalSearchString);
		}
		
		if (globalFilterExportable != null) {
			globalFilterExportable.customGlobalFilter(query, parameters, globalSearchString);
		}
		
		query.append(" ) ");
	}
}

package hr.autoskola.utilities.codebooks.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.databind.ObjectMapper;

import hr.autoskola.utilities.codebooks.model.Codebook;

@Repository
public class CodebookRepository {
	
private final JdbcTemplate jdbc;
	
	ObjectMapper objectMapper = new ObjectMapper();
	
	public CodebookRepository(@Qualifier("primaryJdbcTemplate") JdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}
	
	public List<Codebook> getValuesForCodebook(String tableName, String idColumnName, String nameColumnName, String filter, String orderByColumn, String foreignKeyColumnName, String engTranslationColumn, boolean includeOrdinal) {
		StringBuilder query = new StringBuilder();
		
		query.append(" SELECT ");
		query.append( idColumnName + " AS ID, ");
		if(foreignKeyColumnName != null && !foreignKeyColumnName.isEmpty()) {
			query.append( foreignKeyColumnName + " AS FOREIGN_ID, ");
		}
		query.append(nameColumnName + " AS NAME ");
		if (engTranslationColumn != null && !engTranslationColumn.isEmpty()) {
			query.append( ", " + engTranslationColumn + " AS NAME_EN ");
		}		
		if (includeOrdinal) {
			query.append(", ORDINAL ");
		}
		
		query.append(" FROM " + tableName);
		query.append(" WHERE DELETED = 0 ");
		if (filter != null && !filter.isEmpty()) { 
			query.append(filter);
		}
		
		query.append(" ORDER BY " + (orderByColumn != null ? orderByColumn : "ORDINAL"));
		
		return jdbc.query(query.toString(), BeanPropertyRowMapper.newInstance(Codebook.class));
	}

}

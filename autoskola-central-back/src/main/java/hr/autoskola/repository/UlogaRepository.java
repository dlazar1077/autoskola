package hr.autoskola.repository;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import hr.autoskola.model.FilterValue;
import hr.autoskola.model.Uloga;
import hr.autoskola.utilities.filter.advanced.FilterExportable;
import hr.autoskola.utilities.filter.advanced.FilterHelper;
import hr.autoskola.utilities.filter.advanced.model.FilterFields;
import hr.autoskola.utilities.filter.global.GlobalFilterHelper;
import hr.autoskola.utilities.pagination.PageableHelper;

@Repository
public class UlogaRepository implements FilterExportable{
	
	private final NamedParameterJdbcTemplate njdbc;
	
	public UlogaRepository(@Qualifier("namedParameterJdbcTemplate") NamedParameterJdbcTemplate njdbc) {
		this.njdbc = njdbc;
	}
	
	private static final String MAIN_TABLE = "uloge";
	
	private static final String ULOGA_ID = "ULOGA_ID";
	private static final String SIFRA = "SIFRA";
	private static final String NAZIV = "NAZIV";
	private static final String NAZIV_EN = "NAZIV_EN";
	
	private static final String DELETED = "DELETED";
	
	private static final Map<String, String> mapOfStringColumns = Map.of("sifra",
			MAIN_TABLE + "." + SIFRA, "naziv", MAIN_TABLE + "." + NAZIV, "nazivEn", MAIN_TABLE + "." + NAZIV_EN);
	
	private static final FilterFields filterFields = new FilterFields(mapOfStringColumns, null, null, null,
			null, null);
	
	private static final List<String> allGlobalSearchFields = Arrays.asList(
			MAIN_TABLE + "." + SIFRA,
			MAIN_TABLE + "." + NAZIV,
			MAIN_TABLE + "." + NAZIV_EN);
	
	/**
	 * DAO metoda za dohvat svih objekta tipa Uloga
	 * 
	 * @param getAllEntitiesRequest
	 * @return List <Uloga>
	 * 
	 * @author dlazar
	 */
	public List<Uloga> getAllEntities(/*Map<String, FilterValue> filterColumns, String globalSearch, Long currentPage,
		Long pageSize, String sortColumn, String sortDirection*/) {
		StringBuilder query = new StringBuilder();
		MapSqlParameterSource parameters = new MapSqlParameterSource();

		sqlSelectHelper(query);
		sqlJoinHelper(query);
//		GlobalFilterHelper.sqlGlobalFilterQueryUpdate(query, parameters, globalSearch, allGlobalSearchFields, null);
//		FilterHelper.sqlFilterQueryUpdate(query, parameters, filterColumns, filterFields, this);
//		sqlSortHelper(query, sortColumn, sortDirection);
//		PageableHelper.sqlPaginationQueryUpdate(query, parameters, (currentPage - 1) * pageSize, pageSize);

		return njdbc.query(query.toString(), parameters, BeanPropertyRowMapper.newInstance(Uloga.class));
	}
	
	/**
	 * Metoda za spremanje uloge
	 * @param uloga
	 * @return
	 */
	public Long saveUloga(Uloga uloga) {
		StringBuilder query = new StringBuilder();
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		KeyHolder keyHolder = new GeneratedKeyHolder();

		query.append(" INSERT INTO " + MAIN_TABLE);
		query.append(" ( ");
		query.append(SIFRA + " ,");
		query.append(NAZIV + " ,");
		query.append(NAZIV_EN + " ");
		query.append(" ) ");
		query.append(" VALUES ");
		query.append(" ( :sifra, :naziv, :nazivEn) ");

		parameters.addValue("sifra", uloga.getSifra());
		parameters.addValue("naziv", uloga.getNaziv());
		parameters.addValue("nazivEn", uloga.getNazivEn());

		if (njdbc.update(query.toString(), parameters, keyHolder, new String[] { ULOGA_ID }) == 0) {
		}

		return keyHolder.getKey().longValue();
	}
	
	/**
	 * Metoda za ažuriranje uloge
	 * @param uloga
	 * @return
	 */
	public Long updateUloga(Uloga uloga) {
		StringBuilder query = new StringBuilder();
		MapSqlParameterSource parameters = new MapSqlParameterSource();

		query.append(" UPDATE " + MAIN_TABLE);
		query.append(" SET ");
		query.append(SIFRA + " = :sifra ,");
		query.append(NAZIV + " = :naziv ,");
		query.append(NAZIV_EN + " = :nazivEn ");
		query.append("WHERE " + ULOGA_ID + " = :id");

		parameters.addValue("sifra", uloga.getSifra());
		parameters.addValue("naziv", uloga.getNaziv());
		parameters.addValue("nazivEn", uloga.getNazivEn());
		parameters.addValue("id", uloga.getUlogaId());

		Long updated = ((Integer) njdbc.update(query.toString(), parameters)).longValue();
		if (updated == 0) {
		}

		return updated;
	}

	/**
	 * Metoda za brisanje uloge
	 * @param ulogaId
	 * @return
	 */
	public Long deleteUloge(String ulogaId) {
		StringBuilder query = new StringBuilder();
		MapSqlParameterSource parameters = new MapSqlParameterSource();

		query.append(" UPDATE " + MAIN_TABLE);
		query.append(" SET ");
		query.append(DELETED + " = 1 ");
		query.append(" WHERE " + ULOGA_ID + " = :id ");
		
		parameters.addValue("id", ulogaId);

		Long updated = ((Integer) njdbc.update(query.toString(), parameters)).longValue();
		if (updated == 0) {
		}

		return updated;
	}
	
	
	
	// Helper methods
	private void sqlSelectHelper(StringBuilder query) {
		query.append(" SELECT DISTINCT");
		query.append(" " + MAIN_TABLE + "." + ULOGA_ID + " AS " + ULOGA_ID + ",");
		query.append(" " + MAIN_TABLE + "." + SIFRA + " AS " + SIFRA + ",");			
		query.append(" " + MAIN_TABLE + "." + NAZIV + " AS " + NAZIV + ",");
		query.append(" " + MAIN_TABLE + "." + NAZIV_EN + " AS " + NAZIV_EN + " ");
	}
		
	private void sqlJoinHelper(StringBuilder query) {
		query.append(" FROM " + MAIN_TABLE);
		query.append(" WHERE " + MAIN_TABLE + "." + DELETED + " = " + 0 + " ");
	}
			
	private void sqlSortHelper(StringBuilder query, String columnName, String sortDirection) {
		if (columnName != null && sortDirection != null) {
			String direction = sortDirection.equals("Ascending") ? "ASC" : "DESC";
			switch (columnName) {
			case "sifra":
				query.append(" ORDER BY " + SIFRA + " " + direction);
				break;
			case "naziv":
				query.append(" ORDER BY " + NAZIV + " " + direction);
				break;
			case "nazivEn":
				query.append(" ORDER BY " + NAZIV_EN + " " + direction);
				break;
			}
		}
	}
			
	/**
	 * Metoda za dohvaćanje broj redova select upita
	 * @param parentId
	 * @param filterColumns
	 * @param globalSearch
	 * @return
	 */
	public Long getRowCount(String parentId, Map<String, FilterValue> filterColumns, String globalSearch) {
		StringBuilder query = new StringBuilder();
		MapSqlParameterSource parameters = new MapSqlParameterSource();

		query.append("SELECT COUNT(*)  ");
		sqlJoinHelper(query);
		GlobalFilterHelper.sqlGlobalFilterQueryUpdate(query, parameters, globalSearch, allGlobalSearchFields, null);
		FilterHelper.sqlFilterQueryUpdate(query, parameters, filterColumns, filterFields, this);
		query.append(" ");

		return njdbc.queryForObject(query.toString(), parameters, Long.class);
	}

	@Override
	public void customFilterFieldsCheck(StringBuilder query, MapSqlParameterSource parameters, String columnName,
			FilterValue columnValue) {
		// TODO Auto-generated method stub
		
	}

}

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
import hr.autoskola.model.Vozilo;
import hr.autoskola.utilities.filter.advanced.FilterExportable;
import hr.autoskola.utilities.filter.advanced.FilterHelper;
import hr.autoskola.utilities.filter.advanced.model.FilterFields;
import hr.autoskola.utilities.filter.global.GlobalFilterHelper;


@Repository
public class VoziloRepository implements FilterExportable {
	
	private final NamedParameterJdbcTemplate njdbc;
	
	public VoziloRepository(@Qualifier("namedParameterJdbcTemplate") NamedParameterJdbcTemplate njdbc) {
		this.njdbc = njdbc;
	}
	
	private static final String MAIN_TABLE = "vozilo";
	private static final String INSTRUKTOR_VOZILO_TABLE = "instruktor_vozilo";
	
	private static final String VOZILO_ID = "VOZILO_ID";
	private static final String KATEGORIJA_ID = "KATEGORIJA_ID";
	private static final String MARKA = "MARKA_VOZILA";
	private static final String MODEL = "MODEL";
	private static final String REGISTRACIJA = "REGISTRACIJA";
	
	private static final String DELETED = "DELETED";
	
	private static final String INSTRUKTOR_ID = "INSTRUKTOR_ID";
	
	private static final Map<String, String> mapOfStringColumns = Map.of("markaVozila",
			MAIN_TABLE + "." + MARKA, "model", MAIN_TABLE + "." + MODEL);
	
	private static final FilterFields filterFields = new FilterFields(mapOfStringColumns, null, null, null,
			null, null);
	
	private static final List<String> allGlobalSearchFields = Arrays.asList(
			MAIN_TABLE + "." + MARKA, 
			MAIN_TABLE + "." + MODEL);
	
	/**
	 * DAO metoda za dohvat svih objekta tipa Vozilo
	 * 
	 * @param getAllEntitiesRequest
	 * @return List <Vozilo>
	 * 
	 * @author dlazar
	 */
	public List<Vozilo> getAllEntities() {
		StringBuilder query = new StringBuilder();
		MapSqlParameterSource parameters = new MapSqlParameterSource();

		sqlSelectHelper(query);
		sqlJoinHelper(query);

		return njdbc.query(query.toString(), parameters, BeanPropertyRowMapper.newInstance(Vozilo.class));
	}
	
	/**
	 * DAO metoda za dohvat svih objekta tipa Vozilo po instruktor Idu
	 * 
	 * @param getAllEntitiesRequest
	 * @return List <Vozilo>
	 * 
	 * @author dlazar
	 */
	public List<Vozilo> getEntityByInstruktorId(String instruktorId) {
		StringBuilder query = new StringBuilder();
		MapSqlParameterSource parameters = new MapSqlParameterSource();

		sqlSelectHelper(query);
		query.append(" FROM " + MAIN_TABLE + " LEFT JOIN " + INSTRUKTOR_VOZILO_TABLE + " ON ");
		query.append(" " + MAIN_TABLE + "." + VOZILO_ID + " = " + INSTRUKTOR_VOZILO_TABLE + "." + VOZILO_ID + " ");
		query.append(" WHERE " + MAIN_TABLE + "." + DELETED + " = " + 0 + " AND " + INSTRUKTOR_VOZILO_TABLE + "." + INSTRUKTOR_ID + " = " + ":instruktorId ");
		query.append(" AND " + INSTRUKTOR_VOZILO_TABLE + "." + DELETED + " = " + 0 );
		
		parameters.addValue("instruktorId", instruktorId);
		
		return njdbc.query(query.toString(), parameters, BeanPropertyRowMapper.newInstance(Vozilo.class));
	}
	
	/**
	 * DAO metoda za dohvat Vozilo po idu
	 * 
	 * @param 
	 * @return Vozilo
	 * 
	 * @author dlazar
	 */
	public Vozilo getEntityById(String voziloId) {
		StringBuilder query = new StringBuilder();
		MapSqlParameterSource parameters = new MapSqlParameterSource();

		sqlSelectHelper(query);
		sqlJoinHelper(query);
		query.append(" AND " + MAIN_TABLE + "." + VOZILO_ID + " = :voziloId ");
		
		parameters.addValue("voziloId", voziloId);

		List<Vozilo> vozilo = njdbc.query(query.toString(), parameters, BeanPropertyRowMapper.newInstance(Vozilo.class));

		if(vozilo.isEmpty() || vozilo == null) {
			return null;
		}
		
		return vozilo.get(0);
	}
	
	/**
	 * Metoda za spremanje vozila
	 * @param vozilo
	 * @return
	 */
	public Long saveVozilo(Vozilo vozilo) {
		StringBuilder query = new StringBuilder();
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		KeyHolder keyHolder = new GeneratedKeyHolder();

		query.append(" INSERT INTO " + MAIN_TABLE);
		query.append(" ( ");
		query.append(KATEGORIJA_ID + " ,");
		query.append(MARKA + " ,");
		query.append(MODEL + ", ");
		query.append(REGISTRACIJA + " ");
		query.append(" ) ");
		query.append(" VALUES ");
		query.append(" ( :kategorijaId, :marka, :model, :registracija ) ");

		parameters.addValue("kategorijaId", vozilo.getKategorijaId());
		parameters.addValue("marka", vozilo.getMarkaVozila());
		parameters.addValue("model", vozilo.getModel());
		parameters.addValue("registracija", vozilo.getRegistracija());

		if (njdbc.update(query.toString(), parameters, keyHolder, new String[] { VOZILO_ID }) == 0) {
		}

		return keyHolder.getKey().longValue();
	}
	
	/**
	 * Metoda za ažuriranje vozila
	 * @param vozilo
	 * @return
	 */
	public Long updateVozilo(Vozilo vozilo) {
		StringBuilder query = new StringBuilder();
		MapSqlParameterSource parameters = new MapSqlParameterSource();

		query.append(" UPDATE " + MAIN_TABLE);
		query.append(" SET ");
		query.append(KATEGORIJA_ID + " = :kategorijaId ,");
		query.append(MARKA + " = :marka ,");
		query.append(MODEL + " = :model , ");
		query.append(REGISTRACIJA + " = :registracija ");
		query.append("WHERE " + VOZILO_ID + " = :id");

		parameters.addValue("kategorijaId", vozilo.getKategorijaId());
		parameters.addValue("marka", vozilo.getMarkaVozila());
		parameters.addValue("model", vozilo.getModel());
		parameters.addValue("registracija", vozilo.getRegistracija());
		parameters.addValue("id", vozilo.getVoziloId());

		Long updated = ((Integer) njdbc.update(query.toString(), parameters)).longValue();
		if (updated == 0) {
		}

		return updated;
	}

	/**
	 * Metoda za brisanje vozila
	 * @param voziloId
	 * @return
	 */
	public Long deleteVozilo(String voziloId) {
		StringBuilder query = new StringBuilder();
		MapSqlParameterSource parameters = new MapSqlParameterSource();

		query.append(" UPDATE " + MAIN_TABLE);
		query.append(" SET ");
		query.append(DELETED + " = 1 ");
		query.append(" WHERE " + VOZILO_ID + " = :id ");
		
		parameters.addValue("id", voziloId);

		Long updated = ((Integer) njdbc.update(query.toString(), parameters)).longValue();
		if (updated == 0) {
		}

		return updated;
	}
	
	// Helper methods
	private void sqlSelectHelper(StringBuilder query) {
		query.append(" SELECT DISTINCT");
		query.append(" " + MAIN_TABLE + "." + VOZILO_ID + " AS " + VOZILO_ID + ",");
		query.append(" " + MAIN_TABLE + "." + KATEGORIJA_ID + " AS " + KATEGORIJA_ID + ",");
		query.append(" " + MAIN_TABLE + "." + MARKA + " AS " + MARKA + ",");
		query.append(" " + MAIN_TABLE + "." + MODEL + " AS " + MODEL + ", ");
		query.append(" " + MAIN_TABLE + "." + REGISTRACIJA + " AS " + REGISTRACIJA + " ");
	}
	
	private void sqlJoinHelper(StringBuilder query) {
		query.append(" FROM " + MAIN_TABLE);
		query.append(" WHERE " + MAIN_TABLE + "." + DELETED + " = " + 0 + " ");
	}
	
	private void sqlSortHelper(StringBuilder query, String columnName, String sortDirection) {
		if (columnName != null && sortDirection != null) {
			String direction = sortDirection.equals("Ascending") ? "ASC" : "DESC";
			switch (columnName) {
			case "marka":
				query.append(" ORDER BY " + MARKA + " " + direction);
				break;
			case "model":
				query.append(" ORDER BY " + MODEL + " " + direction);
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

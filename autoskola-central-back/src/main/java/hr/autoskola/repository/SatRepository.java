package hr.autoskola.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import hr.autoskola.model.Sat;

@Repository
public class SatRepository {
	
	private final NamedParameterJdbcTemplate njdbc;
	
	public SatRepository(@Qualifier("namedParameterJdbcTemplate") NamedParameterJdbcTemplate njdbc) {
		this.njdbc = njdbc;
	}
	
	private static final String MAIN_TABLE = "sat_voznje";
	private static final String SAT_POLAZNIK_TABLE = "sat_polaznik";
	
	private static final String SAT_POLAZNIK_ID = "SAT_POLAZNIK_ID";
	private static final String SAT_VOZNJE_ID = "SAT_VOZNJE_ID";
	private static final String POLAZNIK_ID = "POLAZNIK_ID";
	private static final String VOZILO_ID = "VOZILO_ID";
	private static final String BROJ_SATA = "BROJ_SATA";
	private static final String OPIS = "OPIS";
	private static final String DATUM = "DATUM";
	
	private static final String DELETED = "DELETED";
	
	/**
	 * DAO metoda za dohvat Satova
	 * 
	 * @param 
	 * @return Sat
	 * 
	 * @author dlazar
	 */
	public List<Sat> getEntitiesByPolaznikId(String polaznikId) {
		StringBuilder query = new StringBuilder();
		MapSqlParameterSource parameters = new MapSqlParameterSource();

		sqlSelectHelper(query);
		sqlJoinHelper(query);
		query.append( " AND " + SAT_POLAZNIK_TABLE + "." + POLAZNIK_ID + " = :polaznikId");
		
		parameters.addValue("polaznikId", polaznikId);

		return njdbc.query(query.toString(), parameters, BeanPropertyRowMapper.newInstance(Sat.class));

	}
	
	/**
	 * Metoda za spremanje sata voznje
	 * @param sat
	 * @return
	 */
	public Long saveSatVoznje(Sat sat) {
		StringBuilder query = new StringBuilder();
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		KeyHolder keyHolder = new GeneratedKeyHolder();

		query.append(" INSERT INTO " + MAIN_TABLE);
		query.append(" ( ");
		query.append(VOZILO_ID + " ,");
		query.append(BROJ_SATA + " ,");
		query.append(OPIS + " ,");
		query.append(DATUM + " ");
		query.append(" ) ");
		query.append(" VALUES ");
		query.append(" ( :voziloId, :brojSata, :opis, :datum) ");

		parameters.addValue("voziloId", sat.getVoziloId());
		parameters.addValue("brojSata", sat.getBrojSata());
		parameters.addValue("opis", sat.getOpis());
		parameters.addValue("datum", sat.getDatum());
		
		if (njdbc.update(query.toString(), parameters, keyHolder, new String[] { SAT_VOZNJE_ID }) == 0) {
		}

		return keyHolder.getKey().longValue();
	}
	
	/**
	 * Metoda za spremanje sata voznje za polaznika
	 * @param sat
	 * @return
	 */
	public Long saveSatPolaznik(String satVoznjeId, String polaznikId) {
		StringBuilder query = new StringBuilder();
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		KeyHolder keyHolder = new GeneratedKeyHolder();

		query.append(" INSERT INTO " + SAT_POLAZNIK_TABLE );
		query.append(" ( ");
		query.append(POLAZNIK_ID + " ,");
		query.append(SAT_VOZNJE_ID + " ");
		query.append(" ) ");
		query.append(" VALUES ");
		query.append(" ( :polaznikId, :satVoznjeId) ");

		parameters.addValue("polaznikId", polaznikId);
		parameters.addValue("satVoznjeId", satVoznjeId);
		
		if (njdbc.update(query.toString(), parameters, keyHolder, new String[] { SAT_POLAZNIK_ID }) == 0) {
		}

		return keyHolder.getKey().longValue();
	}
	
	/**
	 * Metoda za ažuriranje sata
	 * @param sat
	 * @return
	 */
	public Long updateSatVoznje(Sat sat) {
		StringBuilder query = new StringBuilder();
		MapSqlParameterSource parameters = new MapSqlParameterSource();

		query.append(" UPDATE " + MAIN_TABLE);
		query.append(" SET ");
		query.append(VOZILO_ID + " = :voziloId, ");
		query.append(BROJ_SATA + " = :brojSata, ");
		query.append(OPIS + " = :opis, ");
		query.append(DATUM + " = :datum ");
		query.append("WHERE " + SAT_VOZNJE_ID + " = :id");

		parameters.addValue("voziloId", sat.getVoziloId());
		parameters.addValue("brojSata", sat.getBrojSata());
		parameters.addValue("opis", sat.getOpis());
		parameters.addValue("datum", sat.getDatum());
		parameters.addValue("id", sat.getSatVoznjeId());

		Long updated = ((Integer) njdbc.update(query.toString(), parameters)).longValue();
		if (updated == 0) {
		}

		return updated;
	}
	
	/**
	 * Metoda za brisanje sata
	 * @param sat
	 * @return
	 */
	public Long deleteSatVoznje(Sat sat) {
		StringBuilder query = new StringBuilder();
		MapSqlParameterSource parameters = new MapSqlParameterSource();

		query.append(" UPDATE " + MAIN_TABLE);
		query.append(" SET ");
		query.append(DELETED + " = :deleted ");
		query.append("WHERE " + SAT_VOZNJE_ID + " = :id");

		parameters.addValue("deleted", sat.getVoziloId());
		parameters.addValue("id", sat.getSatVoznjeId());

		Long updated = ((Integer) njdbc.update(query.toString(), parameters)).longValue();
		if (updated == 0) {
		}

		return updated;
	}
	
	
	// Helper methods
		private void sqlSelectHelper(StringBuilder query) {
			query.append(" SELECT DISTINCT");
			query.append(" " + MAIN_TABLE + "." + SAT_VOZNJE_ID + " AS " + SAT_VOZNJE_ID + ",");
			query.append(" " + SAT_POLAZNIK_TABLE + "." + POLAZNIK_ID + " AS " + POLAZNIK_ID + ",");	
			query.append(" " + MAIN_TABLE + "." + VOZILO_ID + " AS " + VOZILO_ID + ", ");
			query.append(" " + MAIN_TABLE + "." + BROJ_SATA + " AS " + BROJ_SATA + ", ");
			query.append(" " + MAIN_TABLE + "." + OPIS + " AS " + OPIS + ",");	
			query.append(" " + MAIN_TABLE + "." + DATUM + " AS " + DATUM + " ");
		}
						
		private void sqlJoinHelper(StringBuilder query) {
			query.append(" FROM " + MAIN_TABLE + " LEFT JOIN " + SAT_POLAZNIK_TABLE + " ON ");
			query.append(MAIN_TABLE + "." + SAT_VOZNJE_ID + " = " + SAT_POLAZNIK_TABLE + "." + SAT_VOZNJE_ID + " ");
			query.append(" WHERE " + MAIN_TABLE + "." + DELETED + " = " + 0 + " ");
			query.append(" AND " + SAT_POLAZNIK_TABLE + "." + DELETED + " = " + 0 + " ");
		}

}

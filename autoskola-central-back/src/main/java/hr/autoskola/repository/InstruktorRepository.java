package hr.autoskola.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import hr.autoskola.model.Instruktor;

@Repository
public class InstruktorRepository {
	
	private final NamedParameterJdbcTemplate njdbc;
	
	public InstruktorRepository(@Qualifier("namedParameterJdbcTemplate") NamedParameterJdbcTemplate njdbc) {
		this.njdbc = njdbc;
	}
	
	private static final String MAIN_TABLE = "instruktor";
	private static final String INSTRUKTOR_VOZILO_TABLE = "instruktor_vozilo";
	private static final String VOZILO_TABLE = "vozilo";
	
	private static final String INSTRUKTOR_ID = "INSTRUKTOR_ID";
	private static final String KORISNIK_ID = "KORISNIK_ID";
	private static final String BROJ_SLOBODNIH_MJESTA = "BROJ_SLOBODNIH_MJESTA";
	private static final String DELETED = "DELETED";
	
	private static final String INSTRUKTOR_VOZILO_ID = "INSTRUKTOR_VOZILO_ID";
	private static final String VOZILO_ID = "VOZILO_ID";
	private static final String KATEGORIJA_ID = "KATEGORIJA_ID";
	
	/**
	 * DAO metoda za dohvat svih objekta tipa Instruktor 
	 * 
	 * @param 
	 * @return List <Instruktor>
	 * 
	 * @author dlazar
	 */
	public List<Instruktor> getAllEntities() {
		StringBuilder query = new StringBuilder();
		MapSqlParameterSource parameters = new MapSqlParameterSource();

		sqlSelectHelper(query);
		sqlJoinHelper(query);

		return njdbc.query(query.toString(), parameters, BeanPropertyRowMapper.newInstance(Instruktor.class));
	}
	
	/**
	 * DAO metoda za dohvat Instruktora
	 * 
	 * @param 
	 * @return List <Instruktor>
	 * 
	 * @author dlazar
	 */
	public Instruktor getEntityByKorisnikId(String korisnikId) {
		StringBuilder query = new StringBuilder();
		MapSqlParameterSource parameters = new MapSqlParameterSource();

		sqlSelectHelper(query);
		sqlJoinHelper(query);
		query.append( " AND " + MAIN_TABLE + "." + KORISNIK_ID + " = :korisnikId");
		
		parameters.addValue("korisnikId", korisnikId);
		
		return njdbc.query(query.toString(), parameters, BeanPropertyRowMapper.newInstance(Instruktor.class)).get(0);
	}
	
	/**
	 * DAO metoda za dohvat Instruktora po idu
	 * 
	 * @param instruktorId
	 * @return List <Instruktor>
	 * 
	 * @author dlazar
	 */
	public Instruktor getEntityByInstruktorId(String instruktorId) {
		StringBuilder query = new StringBuilder();
		MapSqlParameterSource parameters = new MapSqlParameterSource();

		sqlSelectHelper(query);
		sqlJoinHelper(query);
		query.append( " AND " + MAIN_TABLE + "." + INSTRUKTOR_ID + " = :instruktorId");
		
		parameters.addValue("instruktorId", instruktorId);
		
		return njdbc.query(query.toString(), parameters, BeanPropertyRowMapper.newInstance(Instruktor.class)).get(0);
	}
	
	/**
	 * DAO metoda za dohvat slobodnih Instruktora
	 * 
	 * @param 
	 * @return List <Instruktor>
	 * 
	 * @author dlazar
	 */
	public List<Instruktor> getAvailableInstructors(String kategorijaId) {
		StringBuilder query = new StringBuilder();
		MapSqlParameterSource parameters = new MapSqlParameterSource();

		query.append(" SELECT DISTINCT");
		query.append(" " + MAIN_TABLE + "." + INSTRUKTOR_ID + " AS " + INSTRUKTOR_ID + ",");
		query.append(" " + MAIN_TABLE + "." + KORISNIK_ID + " AS " + KORISNIK_ID + ",");	
		query.append(" " + MAIN_TABLE + "." + BROJ_SLOBODNIH_MJESTA + " AS " + BROJ_SLOBODNIH_MJESTA + " ");
		
		query.append(" FROM " + MAIN_TABLE + " LEFT JOIN " + INSTRUKTOR_VOZILO_TABLE + " ON ");
		query.append(MAIN_TABLE + "." + INSTRUKTOR_ID + " = " + INSTRUKTOR_VOZILO_TABLE + "." + INSTRUKTOR_ID + " ");
		query.append(" LEFT JOIN " + VOZILO_TABLE + " ON " + INSTRUKTOR_VOZILO_TABLE + "." + VOZILO_ID);
		query.append(" = " + VOZILO_TABLE + "." + VOZILO_ID + " ");
		
		query.append(" WHERE " + MAIN_TABLE + "." + DELETED + " = 0 ");
		query.append(" AND " + INSTRUKTOR_VOZILO_TABLE + "." + DELETED + " = 0 ");
		query.append(" AND " + VOZILO_TABLE + "." + DELETED + " = 0 ");
		query.append(" AND " + VOZILO_TABLE + "." + KATEGORIJA_ID + " = :kategorijaId ");
		query.append(" AND " + MAIN_TABLE + "." + BROJ_SLOBODNIH_MJESTA + " > 0");
		
		parameters.addValue("kategorijaId", kategorijaId);
		
		return njdbc.query(query.toString(), parameters, BeanPropertyRowMapper.newInstance(Instruktor.class));
	}
	
	/**
	 * Metoda za spremanje instruktora
	 * @param instruktor
	 * @return
	 */
	public Long saveInstruktor(Instruktor instruktor) {
		StringBuilder query = new StringBuilder();
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		KeyHolder keyHolder = new GeneratedKeyHolder();

		query.append(" INSERT INTO " + MAIN_TABLE);
		query.append(" ( ");
		query.append(KORISNIK_ID + " ,");
		query.append(BROJ_SLOBODNIH_MJESTA + " ");
		query.append(" ) ");
		query.append(" VALUES ");
		query.append(" ( :korisnikId, :brojSlobodnihMjesta) ");

		parameters.addValue("korisnikId", instruktor.getKorisnikId());
		parameters.addValue("brojSlobodnihMjesta", instruktor.getBrojSlobodnihMjesta());

		if (njdbc.update(query.toString(), parameters, keyHolder, new String[] { INSTRUKTOR_ID }) == 0) {
		}

		return keyHolder.getKey().longValue();
	}
	
	/**
	 * Metoda za ažuriranje instruktora
	 * @param instruktor
	 * @return
	 */
	public Long updateInstruktor(Instruktor instruktor) {
		StringBuilder query = new StringBuilder();
		MapSqlParameterSource parameters = new MapSqlParameterSource();

		query.append(" UPDATE " + MAIN_TABLE);
		query.append(" SET ");
		//query.append(KORISNIK_ID + " = :korisnikId ,");
		query.append(BROJ_SLOBODNIH_MJESTA + " = :brojSlobodnihMjesta ");
		query.append("WHERE " + INSTRUKTOR_ID + " = :id");

		parameters.addValue("korisnikId", instruktor.getKorisnik());
		parameters.addValue("brojSlobodnihMjesta", instruktor.getBrojSlobodnihMjesta());
		parameters.addValue("id", instruktor.getInstruktorId());

		Long updated = ((Integer) njdbc.update(query.toString(), parameters)).longValue();
		if (updated == 0) {
		}

		return updated;
	}

	/**
	 * Metoda za brisanje instruktora
	 * @param instruktorId
	 * @return
	 */
	public Long deleteInstruktor(String instruktorId) {
		StringBuilder query = new StringBuilder();
		MapSqlParameterSource parameters = new MapSqlParameterSource();

		query.append(" UPDATE " + MAIN_TABLE);
		query.append(" SET ");
		query.append(DELETED + " = 1 ");
		query.append(" WHERE " + INSTRUKTOR_ID + " = :id ");
		
		parameters.addValue("id", instruktorId);

		Long updated = ((Integer) njdbc.update(query.toString(), parameters)).longValue();
		if (updated == 0) {
		}

		return updated;
	}
	
	public Long saveVehicleToInstruktor(String vehicleId, String instruktorId) {
		StringBuilder query = new StringBuilder();
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		KeyHolder keyHolder = new GeneratedKeyHolder();

		query.append(" INSERT INTO " + INSTRUKTOR_VOZILO_TABLE);
		query.append(" ( ");
		query.append(INSTRUKTOR_ID + " ,");
		query.append(VOZILO_ID + " ");
		query.append(" ) ");
		query.append(" VALUES ");
		query.append(" ( :instruktorId, :vehicleId) ");

		parameters.addValue("instruktorId", instruktorId);
		parameters.addValue("vehicleId", vehicleId);

		if (njdbc.update(query.toString(), parameters, keyHolder, new String[] { INSTRUKTOR_VOZILO_ID }) == 0) {
		}

		return keyHolder.getKey().longValue();
	}
	
	/**
	 * Metoda za brisanje vozila instruktora
	 * @param instruktorId
	 * @return
	 */
	public Long deleteVozilaInstruktora(String instruktorId) {
		StringBuilder query = new StringBuilder();
		MapSqlParameterSource parameters = new MapSqlParameterSource();

		query.append(" DELETE FROM " + INSTRUKTOR_VOZILO_TABLE);
		query.append(" WHERE " + INSTRUKTOR_ID + " = :id ");
		
		parameters.addValue("id", instruktorId);

		Long updated = ((Integer) njdbc.update(query.toString(), parameters)).longValue();
		if (updated == 0) {
		}

		return updated;
	}
	
	// Helper methods
	private void sqlSelectHelper(StringBuilder query) {
		query.append(" SELECT DISTINCT");
		query.append(" " + MAIN_TABLE + "." + INSTRUKTOR_ID + " AS " + INSTRUKTOR_ID + ",");
		query.append(" " + MAIN_TABLE + "." + KORISNIK_ID + " AS " + KORISNIK_ID + ",");	
		query.append(" " + MAIN_TABLE + "." + BROJ_SLOBODNIH_MJESTA + " AS " + BROJ_SLOBODNIH_MJESTA + " ");
	}
				
	private void sqlJoinHelper(StringBuilder query) {
		query.append(" FROM " + MAIN_TABLE);
		query.append(" WHERE " + MAIN_TABLE + "." + DELETED + " = " + 0 + " ");
	}

}

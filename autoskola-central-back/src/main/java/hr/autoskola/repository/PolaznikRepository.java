package hr.autoskola.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import hr.autoskola.model.Polaznik;

@Repository
public class PolaznikRepository {
	
	private final NamedParameterJdbcTemplate njdbc;
	
	public PolaznikRepository(@Qualifier("namedParameterJdbcTemplate") NamedParameterJdbcTemplate njdbc) {
		this.njdbc = njdbc;
	}
	
	private static final String MAIN_TABLE = "polaznik";
	
	private static final String POLAZNIK_ID = "POLAZNIK_ID";
	private static final String KORISNIK_ID = "KORISNIK_ID";
	private static final String INSTRUKTOR_ID = "INSTRUKTOR_ID";
	private static final String STATUS_POLAZNIKA_ID = "STATUS_POLAZNIKA_ID";
	private static final String ODABRANA_KATEGORIJA_ID = "ODABRANA_KATEGORIJA_ID";
	
	private static final String DELETED = "DELETED";
	
	/**
	 * DAO metoda za dohvat svih objekta tipa Polaznik 
	 * 
	 * @param 
	 * @return List <Polaznik>
	 * 
	 * @author dlazar
	 */
	public List<Polaznik> getAllEntities() {
		StringBuilder query = new StringBuilder();
		MapSqlParameterSource parameters = new MapSqlParameterSource();

		sqlSelectHelper(query);
		sqlJoinHelper(query);

		return njdbc.query(query.toString(), parameters, BeanPropertyRowMapper.newInstance(Polaznik.class));
	}
	
	/**
	 * DAO metoda za dohvat Polaznika
	 * 
	 * @param 
	 * @return Polaznik
	 * 
	 * @author dlazar
	 */
	public Polaznik getEntityByKorisnikId(String korisnikId) {
		StringBuilder query = new StringBuilder();
		MapSqlParameterSource parameters = new MapSqlParameterSource();

		sqlSelectHelper(query);
		sqlJoinHelper(query);
		query.append( " AND " + MAIN_TABLE + "." + KORISNIK_ID + " = :korisnikId");
		
		parameters.addValue("korisnikId", korisnikId);

		List<Polaznik> polaznik = njdbc.query(query.toString(), parameters, BeanPropertyRowMapper.newInstance(Polaznik.class));

		if(polaznik.isEmpty() || polaznik == null) {
			return null;
		}
		
		return polaznik.get(0);
	}
	
	/**
	 * Metoda za spremanje polaznika
	 * @param polaznik
	 * @return
	 */
	public Long savePolaznik(Polaznik polaznik) {
		StringBuilder query = new StringBuilder();
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		KeyHolder keyHolder = new GeneratedKeyHolder();

		query.append(" INSERT INTO " + MAIN_TABLE);
		query.append(" ( ");
		query.append(KORISNIK_ID + " ,");
		query.append(ODABRANA_KATEGORIJA_ID + " ,");
		query.append(STATUS_POLAZNIKA_ID + " ");
		query.append(" ) ");
		query.append(" VALUES ");
		query.append(" ( :korisnikId, :odabranaKategorijaId, :statusPolaznikaId) ");

		parameters.addValue("korisnikId", polaznik.getKorisnikId());
		parameters.addValue("odabranaKategorijaId", polaznik.getOdabranaKategorijaId());
		parameters.addValue("statusPolaznikaId", polaznik.getStatusPolaznikaId());

		if (njdbc.update(query.toString(), parameters, keyHolder, new String[] { POLAZNIK_ID }) == 0) {
		}

		return keyHolder.getKey().longValue();
	}
	
	/**
	 * Metoda za ažuriranje polaznika
	 * @param polaznik
	 * @return
	 */
	public Long updatePolaznik(Polaznik polaznik) {
		StringBuilder query = new StringBuilder();
		MapSqlParameterSource parameters = new MapSqlParameterSource();

		query.append(" UPDATE " + MAIN_TABLE);
		query.append(" SET ");
		query.append(STATUS_POLAZNIKA_ID + " = :statusPolaznikaId, ");
		query.append(INSTRUKTOR_ID + " = :instruktorId ");
		query.append("WHERE " + POLAZNIK_ID + " = :id");

		parameters.addValue("statusPolaznikaId", polaznik.getStatusPolaznikaId());
		parameters.addValue("instruktorId", polaznik.getInstruktorId());
		parameters.addValue("id", polaznik.getPolaznikId());

		Long updated = ((Integer) njdbc.update(query.toString(), parameters)).longValue();
		if (updated == 0) {
		}

		return updated;
	}
	
	/**
	 * Metoda za ažuriranje stusa polaznika
	 * @param korisnikId
	 * @param status
	 * @return
	 */
	public Long updateStatusPolaznik(String korisnikId, String status) {
		StringBuilder query = new StringBuilder();
		MapSqlParameterSource parameters = new MapSqlParameterSource();

		query.append(" UPDATE " + MAIN_TABLE);
		query.append(" SET ");
		query.append(STATUS_POLAZNIKA_ID + " = :statusPolaznikaId ");
		query.append("WHERE " + KORISNIK_ID + " = :id");

		parameters.addValue("statusPolaznikaId",status);
		parameters.addValue("id", korisnikId);

		Long updated = ((Integer) njdbc.update(query.toString(), parameters)).longValue();
		if (updated == 0) {
		}

		return updated;
	}

	/**
	 * Metoda za brisanje polaznika
	 * @param polaznikId
	 * @return
	 */
	public Long deletePolaznik(String polaznikId) {
		StringBuilder query = new StringBuilder();
		MapSqlParameterSource parameters = new MapSqlParameterSource();

		query.append(" UPDATE " + MAIN_TABLE);
		query.append(" SET ");
		query.append(DELETED + " = 1 ");
		query.append(" WHERE " + POLAZNIK_ID + " = :id ");
		
		parameters.addValue("id", polaznikId);

		Long updated = ((Integer) njdbc.update(query.toString(), parameters)).longValue();
		if (updated == 0) {
		}

		return updated;
	}
	
	
	// Helper methods
	private void sqlSelectHelper(StringBuilder query) {
		query.append(" SELECT DISTINCT");
		query.append(" " + MAIN_TABLE + "." + POLAZNIK_ID + " AS " + POLAZNIK_ID + ",");
		query.append(" " + MAIN_TABLE + "." + KORISNIK_ID + " AS " + KORISNIK_ID + ",");	
		query.append(" " + MAIN_TABLE + "." + INSTRUKTOR_ID + " AS " + INSTRUKTOR_ID + ", ");
		query.append(" " + MAIN_TABLE + "." + STATUS_POLAZNIKA_ID + " AS " + STATUS_POLAZNIKA_ID + ",");	
		query.append(" " + MAIN_TABLE + "." + ODABRANA_KATEGORIJA_ID + " AS " + ODABRANA_KATEGORIJA_ID + " ");
	}
					
	private void sqlJoinHelper(StringBuilder query) {
		query.append(" FROM " + MAIN_TABLE);
		query.append(" WHERE " + MAIN_TABLE + "." + DELETED + " = " + 0 + " ");
	}

}

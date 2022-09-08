package hr.autoskola.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import hr.autoskola.model.Ispit;

@Repository
public class IspitRepository {
	
	private final NamedParameterJdbcTemplate njdbc;
	
	public IspitRepository(@Qualifier("namedParameterJdbcTemplate") NamedParameterJdbcTemplate njdbc) {
		this.njdbc = njdbc;
	}
	
	private static final String MAIN_TABLE = "ispit";
	
	private static final String ISPIT_ID = "ISPIT_ID";
	private static final String KORISNIK_ID = "KORISNIK_ID";
	private static final String MAKSIMALNI_BROJ_BODOVA = "MAKSIMALNI_BROJ_BODOVA";
	private static final String OSTVARENI_BROJ_BODOVA = "OSTVARENI_BROJ_BODOVA";
	private static final String STATUS_ISPITA = "STATUS_ISPITA";
	private static final String CREATION_DATE = "CREATION_DATE";
	
	private static final String DELETED = "DELETED";
	
	
	/**
	 * DAO metoda za dohvat svih objekta tipa Pitanje
	 * 
	 * @param 
	 * @return korisnikId
	 * 
	 * @author dlazar
	 */
	public List<Ispit> getAllEntitiesByKorisnikId(String korisnikId) {
		StringBuilder query = new StringBuilder();
		MapSqlParameterSource parameters = new MapSqlParameterSource();

		sqlSelectHelper(query);
		sqlJoinHelper(query);
		query.append(" AND " + MAIN_TABLE + "." + KORISNIK_ID + " = :korisnikId");
		
		parameters.addValue("korisnikId", korisnikId);

		return njdbc.query(query.toString(), parameters, BeanPropertyRowMapper.newInstance(Ispit.class));
	}
	
	/**
	 * DAO metoda za dohvat svih objekta tipa Ispit po id ispita
	 * 
	 * @param 
	 * @return ispitId
	 * 
	 * @author dlazar
	 */
	public Ispit getAllEntitiesByIspitId(String ispitId) {
		StringBuilder query = new StringBuilder();
		MapSqlParameterSource parameters = new MapSqlParameterSource();

		sqlSelectHelper(query);
		sqlJoinHelper(query);
		query.append(" AND " + MAIN_TABLE + "." + ISPIT_ID + " = :ispitId");
		
		parameters.addValue("ispitId", ispitId);

		List<Ispit> ispiti = njdbc.query(query.toString(), parameters, BeanPropertyRowMapper.newInstance(Ispit.class));
		
		if(ispiti.size() > 0) {
			return ispiti.get(0);
		}
		return null;
	}
	
	/**
	 * Metoda za spremanje Ispita
	 * @param ispit
	 * @return
	 */
	public Long saveIspit(Ispit ispit) {
		StringBuilder query = new StringBuilder();
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		KeyHolder keyHolder = new GeneratedKeyHolder();

		query.append(" INSERT INTO " + MAIN_TABLE);
		query.append(" ( ");
		query.append(KORISNIK_ID + " ,");
		query.append(MAKSIMALNI_BROJ_BODOVA + " ,");
		query.append(OSTVARENI_BROJ_BODOVA + " ,");
		query.append(STATUS_ISPITA + " ");
		query.append(" ) ");
		query.append(" VALUES ");
		query.append(" ( :korisnikId, :maksimalniBrojBodova, :ostvarenBrojBodova, :stautsIspita) ");

		parameters.addValue("korisnikId", ispit.getKorisnikId());
		parameters.addValue("maksimalniBrojBodova", ispit.getMaksimalniBrojBodova());
		parameters.addValue("ostvarenBrojBodova", ispit.getOstvareniBrojBodova());
		parameters.addValue("stautsIspita", ispit.getStatusIspita());

		if (njdbc.update(query.toString(), parameters, keyHolder, new String[] { ISPIT_ID }) == 0) {
		}

		return keyHolder.getKey().longValue();
	}
	
	
	// Helper methods
	private void sqlSelectHelper(StringBuilder query) {
		query.append(" SELECT DISTINCT");
		query.append(" " + MAIN_TABLE + "." + ISPIT_ID + " AS " + ISPIT_ID + ",");
		query.append(" " + MAIN_TABLE + "." + KORISNIK_ID + " AS " + KORISNIK_ID + ",");			
		query.append(" " + MAIN_TABLE + "." + MAKSIMALNI_BROJ_BODOVA + " AS " + MAKSIMALNI_BROJ_BODOVA + ",");
		query.append(" " + MAIN_TABLE + "." + OSTVARENI_BROJ_BODOVA + " AS " + OSTVARENI_BROJ_BODOVA + ",");
		query.append(" " + MAIN_TABLE + "." + STATUS_ISPITA + " AS " + STATUS_ISPITA + ", ");
		query.append(" " + MAIN_TABLE + "." + CREATION_DATE + " AS " + CREATION_DATE + "");
	}
				
	private void sqlJoinHelper(StringBuilder query) {
		query.append(" FROM " + MAIN_TABLE);
		query.append(" WHERE " + MAIN_TABLE + "." + DELETED + " = " + 0 + " ");
	}

}

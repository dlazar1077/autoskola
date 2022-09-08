package hr.autoskola.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import hr.autoskola.model.Pitanje;

@Repository
public class PitanjeRepository {
	
	private final NamedParameterJdbcTemplate njdbc;
	
	public PitanjeRepository(@Qualifier("namedParameterJdbcTemplate") NamedParameterJdbcTemplate njdbc) {
		this.njdbc = njdbc;
	}
	
	private static final String MAIN_TABLE = "pitanje";
	private static final String ISPIT_PITANJE_TABLE = "ispit_pitanje";
	
	private static final String PITANJE_ID = "PITANJE_ID";
	private static final String TEKST_PITANJA = "TEKST_PITANJA";
	private static final String SLIKA = "SLIKA";
	private static final String RASKRIZJE = "RASKRIZJE";
	private static final String BROJ_BODOVA = "BROJ_BODOVA";
	
	private static final String ISPIT_PITANJE_ID = "ISPIT_PITANJE_ID";
	private static final String ISPIT_ID = "ISPIT_ID";
	private static final String ODGOVOR = "ODGOVOR";
	
	private static final String DELETED = "DELETED";
	
	
	/**
	 * DAO metoda za dohvat svih objekta tipa Pitanje
	 * 
	 * @param 
	 * @return 
	 * 
	 * @author dlazar
	 */
	public List<Pitanje> getAllEntities() {
		StringBuilder query = new StringBuilder();
		MapSqlParameterSource parameters = new MapSqlParameterSource();

		sqlSelectHelper(query);
		sqlJoinHelper(query);

		return njdbc.query(query.toString(), parameters, BeanPropertyRowMapper.newInstance(Pitanje.class));
	}
	
	/**
	 * DAO metoda za dohvat svih objekta tipa Pitanje za ispit
	 * 
	 * @param numberOfPoints
	 * @param numberOfQuestions
	 * @return 
	 * 
	 * @author dlazar
	 */
	public List<Pitanje> getIspit(int numberOfPoints, int numberOfQuestions) {
		StringBuilder query = new StringBuilder();
		MapSqlParameterSource parameters = new MapSqlParameterSource();

		sqlSelectHelper(query);
		sqlJoinHelper(query);
		query.append(" AND " + MAIN_TABLE + "." + BROJ_BODOVA + " = :brojBodova order by rand() limit :brojPitanja");
		
		parameters.addValue("brojBodova", numberOfPoints);
		parameters.addValue("brojPitanja", numberOfQuestions);

		return njdbc.query(query.toString(), parameters, BeanPropertyRowMapper.newInstance(Pitanje.class));
	}
	
	/**
	 * DAO metoda za dohvat svih objekta tipa Pitanje za ispit
	 * 
	 * @param 
	 * @return ispitId
	 * 
	 * @author dlazar
	 */
	public List<Pitanje> getAllEntitiesByIspitId(String ispitId) {
		StringBuilder query = new StringBuilder();
		MapSqlParameterSource parameters = new MapSqlParameterSource();

		query.append(" SELECT DISTINCT");
		query.append(" " + MAIN_TABLE + "." + PITANJE_ID + " AS " + PITANJE_ID + ",");
		query.append(" " + MAIN_TABLE + "." + TEKST_PITANJA + " AS " + TEKST_PITANJA + ",");			
		query.append(" " + MAIN_TABLE + "." + SLIKA + " AS " + SLIKA + ",");
		query.append(" " + MAIN_TABLE + "." + RASKRIZJE + " AS " + RASKRIZJE + ",");
		query.append(" " + MAIN_TABLE + "." + BROJ_BODOVA + " AS " + BROJ_BODOVA + ", ");
		query.append(" " + ISPIT_PITANJE_TABLE + "." + ODGOVOR + " AS " + ODGOVOR + " ");
		query.append(" FROM " + MAIN_TABLE + " LEFT JOIN " + ISPIT_PITANJE_TABLE + " ON ");
		query.append(MAIN_TABLE + "." + PITANJE_ID + " = " + ISPIT_PITANJE_TABLE + "." + PITANJE_ID + " ");
		
		query.append(" WHERE " + MAIN_TABLE + "." + DELETED + " = " + 0 + " ");
		query.append(" AND " + ISPIT_PITANJE_TABLE + "." + DELETED + " = " + 0 + " ");
		query.append(" AND " + ISPIT_PITANJE_TABLE + "." + ISPIT_ID + " =  :ispitId");
		
		parameters.addValue("ispitId", ispitId);

		return njdbc.query(query.toString(), parameters, BeanPropertyRowMapper.newInstance(Pitanje.class));
	}
	
	/**
	 * Metoda za spremanje pitanja
	 * @param pitanje
	 * @return
	 */
	public Long savePitanje(Pitanje pitanje) {
		StringBuilder query = new StringBuilder();
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		KeyHolder keyHolder = new GeneratedKeyHolder();

		query.append(" INSERT INTO " + MAIN_TABLE);
		query.append(" ( ");
		query.append(TEKST_PITANJA + " ,");
		query.append(SLIKA + " ,");
		query.append(RASKRIZJE + " ,");
		query.append(BROJ_BODOVA + " ");
		query.append(" ) ");
		query.append(" VALUES ");
		query.append(" ( :tekstPitanja, :slika, :raskrizje, :brojBodova) ");

		parameters.addValue("tekstPitanja", pitanje.getTekstPitanja());
		parameters.addValue("slika", pitanje.getSlika());
		parameters.addValue("raskrizje", pitanje.getRaskrizje());
		parameters.addValue("brojBodova", pitanje.getBrojBodova());

		if (njdbc.update(query.toString(), parameters, keyHolder, new String[] { PITANJE_ID }) == 0) {
		}

		return keyHolder.getKey().longValue();
	}
	
	/**
	 * Metoda za spremanje odgovora
	 * @param pitanje
	 * @return
	 */
	public Long savePitanjeOdgovor(Pitanje pitanje, String ispitId) {
		StringBuilder query = new StringBuilder();
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		KeyHolder keyHolder = new GeneratedKeyHolder();

		query.append(" INSERT INTO " + ISPIT_PITANJE_TABLE);
		query.append(" ( ");
		query.append(ISPIT_ID + " ,");
		query.append(PITANJE_ID + " ,");
		query.append(ODGOVOR + " ");
		query.append(" ) ");
		query.append(" VALUES ");
		query.append(" ( :ispitId, :pitanjeId, :odgovor) ");

		parameters.addValue("ispitId", ispitId);
		parameters.addValue("pitanjeId", pitanje.getPitanjeId());
		parameters.addValue("odgovor", pitanje.getOdgovor());

		if (njdbc.update(query.toString(), parameters, keyHolder, new String[] { ISPIT_PITANJE_ID }) == 0) {
		}

		return keyHolder.getKey().longValue();
	}
	
	/**
	 * Metoda za ažuriranje pitanja
	 * @param pitanje
	 * @return
	 */
	public Long updatePitanje(Pitanje pitanje) {
		StringBuilder query = new StringBuilder();
		MapSqlParameterSource parameters = new MapSqlParameterSource();

		query.append(" UPDATE " + MAIN_TABLE);
		query.append(" SET ");
		query.append(TEKST_PITANJA + " = :tekstPitanja ,");
		query.append(SLIKA + " = :slika ,");
		query.append(RASKRIZJE + " = :raskrizje ,");
		query.append(BROJ_BODOVA + " = :brojBodova ");
		query.append("WHERE " + PITANJE_ID + " = :id");

		parameters.addValue("tekstPitanja", pitanje.getTekstPitanja());
		parameters.addValue("slika", pitanje.getSlika());
		parameters.addValue("raskrizje", pitanje.getRaskrizje());
		parameters.addValue("brojBodova", pitanje.getBrojBodova());
		parameters.addValue("id", pitanje.getPitanjeId());

		Long updated = ((Integer) njdbc.update(query.toString(), parameters)).longValue();
		if (updated == 0) {
		}

		return updated;
	}

	/**
	 * Metoda za brisanje pitanja
	 * @param pitanjeId
	 * @return
	 */
	public Long deletePitanje(String pitanjeId) {
		StringBuilder query = new StringBuilder();
		MapSqlParameterSource parameters = new MapSqlParameterSource();

		query.append(" UPDATE " + MAIN_TABLE);
		query.append(" SET ");
		query.append(DELETED + " = 1 ");
		query.append(" WHERE " + PITANJE_ID + " = :id ");
		
		parameters.addValue("id", pitanjeId	);

		Long updated = ((Integer) njdbc.update(query.toString(), parameters)).longValue();
		if (updated == 0) {
		}

		return updated;
	}

	// Helper methods
	private void sqlSelectHelper(StringBuilder query) {
		query.append(" SELECT DISTINCT");
		query.append(" " + MAIN_TABLE + "." + PITANJE_ID + " AS " + PITANJE_ID + ",");
		query.append(" " + MAIN_TABLE + "." + TEKST_PITANJA + " AS " + TEKST_PITANJA + ",");			
		query.append(" " + MAIN_TABLE + "." + SLIKA + " AS " + SLIKA + ",");
		query.append(" " + MAIN_TABLE + "." + RASKRIZJE + " AS " + RASKRIZJE + ",");
		query.append(" " + MAIN_TABLE + "." + BROJ_BODOVA + " AS " + BROJ_BODOVA + " ");
	}
			
	private void sqlJoinHelper(StringBuilder query) {
		query.append(" FROM " + MAIN_TABLE);
		query.append(" WHERE " + MAIN_TABLE + "." + DELETED + " = " + 0 + " ");
	}
}

package hr.autoskola.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import hr.autoskola.model.Odabir;

@Repository
public class OdabirRepository {
	
	private final NamedParameterJdbcTemplate njdbc;
	
	public OdabirRepository(@Qualifier("namedParameterJdbcTemplate") NamedParameterJdbcTemplate njdbc) {
		this.njdbc = njdbc;
	}
	
	private static final String MAIN_TABLE = "odabir";
	
	private static final String ODABIR_ID = "ODABIR_ID";
	private static final String PITANJE_ID = "PITANJE_ID";
	private static final String TEKST = "TEKST";
	private static final String TOCAN_ODGOVOR = "TOCAN_ODGOVOR";
	private static final String SIFRA = "SIFRA";
	
	private static final String DELETED = "DELETED";
	
	/**
	 * DAO metoda za dohvat svih objekta tipa Odabir za odredeni id pitanja
	 * 
	 * @param pitanjeId
	 * @return List <Odabir>
	 * 
	 * @author dlazar
	 */
	public List<Odabir> getEntityById(String pitanjeId) {
		StringBuilder query = new StringBuilder();
		MapSqlParameterSource parameters = new MapSqlParameterSource();

		sqlSelectHelper(query);
		sqlJoinHelper(query);
		query.append(" AND " + MAIN_TABLE + "." + PITANJE_ID + " = :pitanjeId");
		
		parameters.addValue("pitanjeId", pitanjeId);

		return njdbc.query(query.toString(), parameters, BeanPropertyRowMapper.newInstance(Odabir.class));
	}
	
	/**
	 * Metoda za spremanje odbira
	 * @param odabir
	 * @return
	 */
	public Long saveOdabir(Odabir odabir) {
		StringBuilder query = new StringBuilder();
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		KeyHolder keyHolder = new GeneratedKeyHolder();

		query.append(" INSERT INTO " + MAIN_TABLE);
		query.append(" ( ");
		query.append(PITANJE_ID + " ,");
		query.append(TEKST + " ,");
		query.append(TOCAN_ODGOVOR + ", ");
		query.append(DELETED + ", ");
		query.append(SIFRA + " ");
		query.append(" ) ");
		query.append(" VALUES ");
		query.append(" ( :pitanjeId, :tekst, :tocanOdgovor, :deleted, :sifra) ");

		parameters.addValue("pitanjeId", odabir.getPitanjeId());
		parameters.addValue("tekst", odabir.getTekst());
		parameters.addValue("tocanOdgovor", odabir.getTocanOdgovor());
		parameters.addValue("deleted", odabir.getDeleted());
		parameters.addValue("sifra", odabir.getSifra());

		if (njdbc.update(query.toString(), parameters, keyHolder, new String[] { ODABIR_ID }) == 0) {
		}

		return keyHolder.getKey().longValue();
	}
	
	/**
	 * Metoda za ažuriranje odabira
	 * @param odabir
	 * @return
	 */
	public Long updateOdabir(Odabir odabir) {
		StringBuilder query = new StringBuilder();
		MapSqlParameterSource parameters = new MapSqlParameterSource();

		query.append(" UPDATE " + MAIN_TABLE);
		query.append(" SET ");
		query.append(PITANJE_ID + " = :pitanjeId ,");
		query.append(TEKST + " = :tekst ,");
		query.append(DELETED + " = :deleted ,");
		query.append(TOCAN_ODGOVOR + " = :tocanOdgovor ");
		query.append("WHERE " + PITANJE_ID + " = :id " + " AND " + SIFRA +  " = :sifra");

		parameters.addValue("pitanjeId", odabir.getPitanjeId());
		parameters.addValue("tekst", odabir.getTekst());
		parameters.addValue("deleted", odabir.getDeleted());
		parameters.addValue("tocanOdgovor", odabir.getTocanOdgovor());
		parameters.addValue("sifra", odabir.getSifra());
		parameters.addValue("id", odabir.getPitanjeId());

		Long updated = ((Integer) njdbc.update(query.toString(), parameters)).longValue();
		if (updated == 0) {
		}

		return updated;
	}

	/**
	 * Metoda za brisanje odabira
	 * @param odabirId
	 * @return
	 */
	public Long deleteOdabir(String odabirId) {
		StringBuilder query = new StringBuilder();
		MapSqlParameterSource parameters = new MapSqlParameterSource();

		query.append(" UPDATE " + MAIN_TABLE);
		query.append(" SET ");
		query.append(DELETED + " = 1 ");
		query.append(" WHERE " + ODABIR_ID + " = :id ");
		
		parameters.addValue("id", odabirId);

		Long updated = ((Integer) njdbc.update(query.toString(), parameters)).longValue();
		if (updated == 0) {
		}

		return updated;
	}
	
	/**
	 * Metoda za brisanje odabira po id pitanja i sifri
	 * @param pitanjeId, sifra
	 * @return
	 */
	public Long deleteOdabirByPitanjeIdAndSifra(String pitanjeId, String sifra) {
		StringBuilder query = new StringBuilder();
		MapSqlParameterSource parameters = new MapSqlParameterSource();

		query.append(" UPDATE " + MAIN_TABLE);
		query.append(" SET ");
		query.append(DELETED + " = 1 ");
		query.append(" WHERE " + PITANJE_ID + " = :pitanjeId AND "+ SIFRA + " = :sifra");
		
		parameters.addValue("pitanjeId", pitanjeId);
		parameters.addValue("sifra", sifra);

		Long updated = ((Integer) njdbc.update(query.toString(), parameters)).longValue();
		if (updated == 0) {
		}

		return updated;
	}
	
	/**
	 * Metoda za brisanje odabira za uvijek
	 * @param odabirId
	 * @return
	 */
	public Long deleteFroeverOdabir(String odabirId) {
		StringBuilder query = new StringBuilder();
		MapSqlParameterSource parameters = new MapSqlParameterSource();

		query.append(" DELETE FROM " + MAIN_TABLE);
		query.append(" WHERE " + ODABIR_ID + " = :id ");
		
		parameters.addValue("id", odabirId);

		Long updated = ((Integer) njdbc.update(query.toString(), parameters)).longValue();
		if (updated == 0) {
		}

		return updated;
	}
	
	// Helper methods
	private void sqlSelectHelper(StringBuilder query) {
		query.append(" SELECT DISTINCT");
		query.append(" " + MAIN_TABLE + "." + ODABIR_ID + " AS " + ODABIR_ID + ",");
		query.append(" " + MAIN_TABLE + "." + PITANJE_ID + " AS " + PITANJE_ID + ",");			
		query.append(" " + MAIN_TABLE + "." + TEKST + " AS " + TEKST + ",");
		query.append(" " + MAIN_TABLE + "." + SIFRA + " AS " + SIFRA + ",");
		query.append(" " + MAIN_TABLE + "." + TOCAN_ODGOVOR + " AS " + TOCAN_ODGOVOR + " ");
	}
			
	private void sqlJoinHelper(StringBuilder query) {
		query.append(" FROM " + MAIN_TABLE);
		query.append(" WHERE " + MAIN_TABLE + "." + DELETED + " = " + 0 + " ");
	}

}

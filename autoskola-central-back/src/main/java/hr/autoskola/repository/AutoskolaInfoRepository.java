package hr.autoskola.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import hr.autoskola.model.AutoskolaInfo;

@Repository
public class AutoskolaInfoRepository {
	
	private final NamedParameterJdbcTemplate njdbc;
	
	public AutoskolaInfoRepository(@Qualifier("namedParameterJdbcTemplate") NamedParameterJdbcTemplate njdbc) {
		this.njdbc = njdbc;
	}
	
private static final String MAIN_TABLE = "autoskola_info";
	
	private static final String AUTOSKOLA_INFO_ID = "AUTOSKOLA_INFO_ID";
	private static final String SIFRA = "SIFRA";
	private static final String VRIJEDNOST = "VRIJEDNOST";
	
	private static final String DELETED = "DELETED";
	
	/**
	 * DAO metoda za dohvat svih objekta tipa AutoskolaInfo
	 * 
	 * @param getAllEntitiesRequest
	 * @return List <AutoskolaInfo>
	 * 
	 * @author dlazar
	 */
	public List<AutoskolaInfo> getAllEntities() {
		StringBuilder query = new StringBuilder();
		MapSqlParameterSource parameters = new MapSqlParameterSource();

		sqlSelectHelper(query);
		sqlJoinHelper(query);

		return njdbc.query(query.toString(), parameters, BeanPropertyRowMapper.newInstance(AutoskolaInfo.class));
	}
	
	/**
	 * Metoda za spremanje AutoskolaInfo
	 * @param autoskolaInfo
	 * @return
	 */
	public Long saveAutoskolaInfo(AutoskolaInfo autoskolaInfo) {
		StringBuilder query = new StringBuilder();
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		KeyHolder keyHolder = new GeneratedKeyHolder();

		query.append(" INSERT INTO " + MAIN_TABLE);
		query.append(" ( ");
		query.append(SIFRA + " ,");
		query.append(VRIJEDNOST + " ");
		query.append(" ) ");
		query.append(" VALUES ");
		query.append(" ( :sifra, :vrijednost) ");

		parameters.addValue("sifra", autoskolaInfo.getSifra());
		parameters.addValue("vrijednost", autoskolaInfo.getVrijednost());

		if (njdbc.update(query.toString(), parameters, keyHolder, new String[] { AUTOSKOLA_INFO_ID }) == 0) {
		}

		return keyHolder.getKey().longValue();
	}
	
	/**
	 * Metoda za ažuriranje AutoskolaInfo
	 * @param autoskolaInfo
	 * @return
	 */
	public Long updateAutoskolaInfo(AutoskolaInfo autoskolaInfo) {
		StringBuilder query = new StringBuilder();
		MapSqlParameterSource parameters = new MapSqlParameterSource();

		query.append(" UPDATE " + MAIN_TABLE);
		query.append(" SET ");
		query.append(SIFRA + " = :sifra ,");
		query.append(VRIJEDNOST + " = :vrijednost ");
		query.append("WHERE " + AUTOSKOLA_INFO_ID + " = :id");

		parameters.addValue("sifra", autoskolaInfo.getSifra());
		parameters.addValue("vrijednost", autoskolaInfo.getVrijednost());
		parameters.addValue("id", autoskolaInfo.getAutoskolaInfoId());

		Long updated = ((Integer) njdbc.update(query.toString(), parameters)).longValue();
		if (updated == 0) {
		}

		return updated;
	}
	
	/**
	 * Metoda za brisanje AutoskolaInfo
	 * @param autoskolaInfoId
	 * @return
	 */
	public Long deleteAutoskolaInfo(String autoskolaInfoId) {
		StringBuilder query = new StringBuilder();
		MapSqlParameterSource parameters = new MapSqlParameterSource();

		query.append(" UPDATE " + MAIN_TABLE);
		query.append(" SET ");
		query.append(DELETED + " = 1 ");
		query.append(" WHERE " + AUTOSKOLA_INFO_ID + " = :id ");
		
		parameters.addValue("id", autoskolaInfoId);

		Long updated = ((Integer) njdbc.update(query.toString(), parameters)).longValue();
		if (updated == 0) {
		}

		return updated;
	}
	
	// Helper methods
		private void sqlSelectHelper(StringBuilder query) {
			query.append(" SELECT DISTINCT");
			query.append(" " + MAIN_TABLE + "." + AUTOSKOLA_INFO_ID + " AS " + AUTOSKOLA_INFO_ID + ",");
			query.append(" " + MAIN_TABLE + "." + SIFRA + " AS " + SIFRA + ",");			
			query.append(" " + MAIN_TABLE + "." + VRIJEDNOST + " AS " + VRIJEDNOST + " ");
		}
			
		private void sqlJoinHelper(StringBuilder query) {
			query.append(" FROM " + MAIN_TABLE);
			query.append(" WHERE " + MAIN_TABLE + "." + DELETED + " = " + 0 + " ");
		}

}

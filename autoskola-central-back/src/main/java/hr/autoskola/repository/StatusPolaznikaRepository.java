package hr.autoskola.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import hr.autoskola.model.StatusPolaznika;

@Repository
public class StatusPolaznikaRepository {
	
	private final NamedParameterJdbcTemplate njdbc;
	
	public StatusPolaznikaRepository(@Qualifier("namedParameterJdbcTemplate") NamedParameterJdbcTemplate njdbc) {
		this.njdbc = njdbc;
	}
	
	private static final String MAIN_TABLE = "status_polaznika";
	
	private static final String STATUS_POLAZNIKA_ID = "STATUS_POLAZNIKA_ID";
	private static final String SIFRA = "SIFRA";
	private static final String NAZIV = "NAZIV";
	private static final String NAZIV_EN = "NAZIV_EN";
	
	private static final String DELETED = "DELETED";
	
	/**
	 * DAO metoda za dohvat svih objekta tipa StatusPolaznika
	 * 
	 * @param getAllEntitiesRequest
	 * @return List <StatusPolaznika>
	 * 
	 * @author dlazar
	 */
	public List<StatusPolaznika> getAllEntities() {
		StringBuilder query = new StringBuilder();
		MapSqlParameterSource parameters = new MapSqlParameterSource();

		sqlSelectHelper(query);
		sqlJoinHelper(query);

		return njdbc.query(query.toString(), parameters, BeanPropertyRowMapper.newInstance(StatusPolaznika.class));
	}
	
	/**
	 * Metoda za spremanje statusa polaznika
	 * @param statusPolaznika
	 * @return
	 */
	public Long saveStatusPolaznika(StatusPolaznika statusPolaznika) {
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

		parameters.addValue("sifra", statusPolaznika.getSifra());
		parameters.addValue("naziv", statusPolaznika.getNaziv());
		parameters.addValue("nazivEn", statusPolaznika.getNazivEn());

		if (njdbc.update(query.toString(), parameters, keyHolder, new String[] { STATUS_POLAZNIKA_ID }) == 0) {
		}

		return keyHolder.getKey().longValue();
	}
	
	/**
	 * Metoda za ažuriranje statusa polaznika
	 * @param statusPolaznika
	 * @return
	 */
	public Long updateStatusPolaznika(StatusPolaznika statusPolaznika) {
		StringBuilder query = new StringBuilder();
		MapSqlParameterSource parameters = new MapSqlParameterSource();

		query.append(" UPDATE " + MAIN_TABLE);
		query.append(" SET ");
		query.append(SIFRA + " = :sifra ,");
		query.append(NAZIV + " = :naziv ,");
		query.append(NAZIV_EN + " = :nazivEn ");
		query.append("WHERE " + STATUS_POLAZNIKA_ID + " = :id");

		parameters.addValue("sifra", statusPolaznika.getSifra());
		parameters.addValue("naziv", statusPolaznika.getNaziv());
		parameters.addValue("nazivEn", statusPolaznika.getNazivEn());
		parameters.addValue("id", statusPolaznika.getStatusPolaznikaId());

		Long updated = ((Integer) njdbc.update(query.toString(), parameters)).longValue();
		if (updated == 0) {
		}

		return updated;
	}

	/**
	 * Metoda za brisanje statusa polaznika
	 * @param statusPolaznikaId
	 * @return
	 */
	public Long deleteStatusPolaznika(String statusPolaznikaId) {
		StringBuilder query = new StringBuilder();
		MapSqlParameterSource parameters = new MapSqlParameterSource();

		query.append(" UPDATE " + MAIN_TABLE);
		query.append(" SET ");
		query.append(DELETED + " = 1 ");
		query.append(" WHERE " + STATUS_POLAZNIKA_ID + " = :id ");
		
		parameters.addValue("id", statusPolaznikaId);

		Long updated = ((Integer) njdbc.update(query.toString(), parameters)).longValue();
		if (updated == 0) {
		}

		return updated;
	}
	
	
	
	// Helper methods
	private void sqlSelectHelper(StringBuilder query) {
		query.append(" SELECT DISTINCT");
		query.append(" " + MAIN_TABLE + "." + STATUS_POLAZNIKA_ID + " AS " + STATUS_POLAZNIKA_ID + ",");
		query.append(" " + MAIN_TABLE + "." + SIFRA + " AS " + SIFRA + ",");			
		query.append(" " + MAIN_TABLE + "." + NAZIV + " AS " + NAZIV + ",");
		query.append(" " + MAIN_TABLE + "." + NAZIV_EN + " AS " + NAZIV_EN + " ");
	}
		
	private void sqlJoinHelper(StringBuilder query) {
		query.append(" FROM " + MAIN_TABLE);
		query.append(" WHERE " + MAIN_TABLE + "." + DELETED + " = " + 0 + " ");
	}

}

package hr.autoskola.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import hr.autoskola.model.FAQ;

@Repository
public class FAQRepository {
	
	private final NamedParameterJdbcTemplate njdbc;
	
	public FAQRepository(@Qualifier("namedParameterJdbcTemplate") NamedParameterJdbcTemplate njdbc) {
		this.njdbc = njdbc;
	}
	
	private static final String MAIN_TABLE = "faq";
	
	private static final String FAQ_ID = "FAQ_ID";
	private static final String PITANJE = "PITANJE";
	private static final String ODGOVOR = "ODGOVOR";
	
	private static final String DELETED = "DELETED";
	
	/**
	 * DAO metoda za dohvat svih objekta tipa Vozilo
	 * 
	 * @param getAllEntitiesRequest
	 * @return List <FAQ>
	 * 
	 * @author dlazar
	 */
	public List<FAQ> getAllEntities() {
		StringBuilder query = new StringBuilder();
		MapSqlParameterSource parameters = new MapSqlParameterSource();

		sqlSelectHelper(query);
		sqlJoinHelper(query);

		return njdbc.query(query.toString(), parameters, BeanPropertyRowMapper.newInstance(FAQ.class));
	}
	
	/**
	 * Metoda za spremanje faq
	 * @param faq
	 * @return
	 */
	public Long saveFAQ(FAQ faq) {
		StringBuilder query = new StringBuilder();
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		KeyHolder keyHolder = new GeneratedKeyHolder();

		query.append(" INSERT INTO " + MAIN_TABLE);
		query.append(" ( ");
		query.append(PITANJE + " ,");
		query.append(ODGOVOR + " ");
		query.append(" ) ");
		query.append(" VALUES ");
		query.append(" ( :pitanje, :odgovor ) ");

		parameters.addValue("pitanje", faq.getPitanje());
		parameters.addValue("odgovor", faq.getOdgovor());

		if (njdbc.update(query.toString(), parameters, keyHolder, new String[] { FAQ_ID }) == 0) {
		}

		return keyHolder.getKey().longValue();
	}
	
	/**
	 * Metoda za ažuriranje faq
	 * @param faq
	 * @return
	 */
	public Long updateFAQ(FAQ faq) {
		StringBuilder query = new StringBuilder();
		MapSqlParameterSource parameters = new MapSqlParameterSource();

		query.append(" UPDATE " + MAIN_TABLE);
		query.append(" SET ");
		query.append(PITANJE + " = :pitanje ,");
		query.append(ODGOVOR + " = :odgovor ");
		query.append("WHERE " + FAQ_ID + " = :id");

		parameters.addValue("pitanje", faq.getPitanje());
		parameters.addValue("odgovor", faq.getOdgovor());
		parameters.addValue("id", faq.getFaqId());

		Long updated = ((Integer) njdbc.update(query.toString(), parameters)).longValue();
		if (updated == 0) {
		}

		return updated;
	}

	/**
	 * Metoda za brisanje faq
	 * @param faqId
	 * @return
	 */
	public Long deleteFAQ(String faqId) {
		StringBuilder query = new StringBuilder();
		MapSqlParameterSource parameters = new MapSqlParameterSource();

		query.append(" UPDATE " + MAIN_TABLE);
		query.append(" SET ");
		query.append(DELETED + " = 1 ");
		query.append(" WHERE " + FAQ_ID + " = :id ");
		
		parameters.addValue("id", faqId);

		Long updated = ((Integer) njdbc.update(query.toString(), parameters)).longValue();
		if (updated == 0) {
		}

		return updated;
	}
	
	
	// Helper methods
	private void sqlSelectHelper(StringBuilder query) {
		query.append(" SELECT DISTINCT");
		query.append(" " + MAIN_TABLE + "." + FAQ_ID + " AS " + FAQ_ID + ",");
		query.append(" " + MAIN_TABLE + "." + PITANJE + " AS " + PITANJE + ",");
		query.append(" " + MAIN_TABLE + "." + ODGOVOR + " AS " + ODGOVOR + " ");
	}
		
	private void sqlJoinHelper(StringBuilder query) {
		query.append(" FROM " + MAIN_TABLE);
		query.append(" WHERE " + MAIN_TABLE + "." + DELETED + " = " + 0 + " ");
	}

}

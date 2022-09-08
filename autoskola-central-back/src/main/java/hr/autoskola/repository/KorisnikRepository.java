package hr.autoskola.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import hr.autoskola.model.Korisnik;



@Repository
public class KorisnikRepository {
	
	private final JdbcTemplate jdbc;
	private final NamedParameterJdbcTemplate njdbc;
	
	public KorisnikRepository(@Qualifier("primaryJdbcTemplate") JdbcTemplate jdbc, @Qualifier("namedParameterJdbcTemplate") NamedParameterJdbcTemplate njdbc) {
		this.jdbc = jdbc;
		this.njdbc = njdbc;
	}
	
	private static final String TABLE_NAME = "korisnik";
	
	private static final String KORISNIKID = "KORISNIK_ID";
	private static final String ULOGAID = "ULOGA_ID";
	private static final String IME = "IME";
	private static final String PREZIME = "PREZIME";
	private static final String EMAIL = "EMAIL";
	private static final String LOZINKA = "LOZINKA";
	private static final String KORISNICKO_IME = "KORISNICKO_IME";
	private static final String OIB = "OIB";
	
	private static final String DELETED = "DELETED";
	
	public List<Korisnik> getAllEntities() {
		StringBuilder query = new StringBuilder();
		
		query.append("SELECT "
				+ KORISNIKID + ", "
				+ ULOGAID + ", "
				+ IME + ", " 
				+ PREZIME + ", "
				+ EMAIL + ", "
				+ OIB + ", "
				+ KORISNICKO_IME + ", "
				+ LOZINKA + " "
				+ " FROM "
				+ TABLE_NAME
				+ " WHERE " + DELETED + " = " + 0);
		
		return jdbc.query(query.toString(), new EntityRowMapper());
	}
	
	public Korisnik getEntity(String email, String password) {
		List<Korisnik> entities;
		StringBuilder query = new StringBuilder();
		
		query.append("SELECT "
				+ KORISNIKID + ", "
				+ ULOGAID + ", "
				+ IME + ", " 
				+ PREZIME + ", "
				+ EMAIL + ", "
				+ LOZINKA + " "
				+ " FROM "
				+ TABLE_NAME + " "
				+ "WHERE " + EMAIL + " = '"+ email + "' AND " + LOZINKA + " = '" + password + "'");
		
		entities = jdbc.query(query.toString(), new EntityRowMapper());
		
		if(!entities.isEmpty()) {
			return entities.get(0);
		}else {
			return null;
		}
	}
	
	public Korisnik getEntityByKorisnikId(String korisnikId) {
		List<Korisnik> entities;
		StringBuilder query = new StringBuilder();
		
		query.append("SELECT "
				+ KORISNIKID + ", "
				+ ULOGAID + ", "
				+ IME + ", " 
				+ PREZIME + ", "
				+ EMAIL + ", "
				+ OIB + ", "
				+ KORISNICKO_IME + ", "
				+ LOZINKA + " "
				+ " FROM "
				+ TABLE_NAME + " "
				+ "WHERE " + KORISNIKID + " = '"+ korisnikId + "'");
		
		entities = jdbc.query(query.toString(), new EntityRowMapper());
		
		if(!entities.isEmpty()) {
			return entities.get(0);
		}else {
			return null;
		}
	}
	
	/**
	 * Metoda za spremanje korisnika
	 * @param korisnik
	 * @return
	 */
	public Long saveKorisnik(Korisnik korisnik) {
		StringBuilder query = new StringBuilder();
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		KeyHolder keyHolder = new GeneratedKeyHolder();

		query.append(" INSERT INTO " + TABLE_NAME);
		query.append(" ( ");
		query.append(ULOGAID + " ,");
		query.append(PREZIME + " ,");
		query.append(IME + " ,");
		query.append(EMAIL + " ,");
		query.append(OIB + " ,");
		query.append(KORISNICKO_IME + " ,");
		query.append(LOZINKA + " ");
		query.append(" ) ");
		query.append(" VALUES ");
		query.append(" ( :ulogaId, :prezime, :ime, :email, :oib, :korisnickoIme, :lozinka) ");

		parameters.addValue("ulogaId", korisnik.getUlogaId());
		parameters.addValue("prezime", korisnik.getPrezime());
		parameters.addValue("ime", korisnik.getIme());
		parameters.addValue("email", korisnik.getEmail());
		parameters.addValue("oib", korisnik.getOib());
		parameters.addValue("korisnickoIme", korisnik.getKorisnickoIme());
		parameters.addValue("lozinka", korisnik.getLozinka());

		if (njdbc.update(query.toString(), parameters, keyHolder, new String[] { KORISNIKID }) == 0) {
		}

		return keyHolder.getKey().longValue();
	}
	
	/**
	 * Metoda za ažuriranje korisnika
	 * @param korisnik
	 * @return
	 */
	public Long updateKorisnik(Korisnik korisnik) {
		StringBuilder query = new StringBuilder();
		MapSqlParameterSource parameters = new MapSqlParameterSource();

		query.append(" UPDATE " + TABLE_NAME);
		query.append(" SET ");
		query.append(PREZIME + " = :prezime ,");
		query.append(IME + " = :ime ,");
		query.append(EMAIL + " = :email ,");
		query.append(ULOGAID + " = :ulogaId ,");
		query.append(OIB + " = :oib ,");
		if(korisnik.getLozinka() != "") query.append(LOZINKA + " = :lozinka ,");
		query.append(KORISNICKO_IME + " = :korisnickoIme ");
		query.append("WHERE " + KORISNIKID + " = :id");

		parameters.addValue("prezime", korisnik.getPrezime());
		parameters.addValue("ime", korisnik.getIme());
		parameters.addValue("email", korisnik.getEmail());
		parameters.addValue("ulogaId", korisnik.getUlogaId());
		parameters.addValue("oib", korisnik.getOib());
		if(korisnik.getLozinka() != "") parameters.addValue("lozinka", korisnik.getLozinka());
		parameters.addValue("korisnickoIme", korisnik.getKorisnickoIme());
		parameters.addValue("id", korisnik.getKorisnikId());

		Long updated = ((Integer) njdbc.update(query.toString(), parameters)).longValue();
		if (updated == 0) {
		}

		return updated;
	}
	
	/**
	 * Metoda za brisanje korisnika
	 * @param korisnikId
	 * @return
	 */
	public Long deleteKorisnik(String korisnikId) {
		StringBuilder query = new StringBuilder();
		MapSqlParameterSource parameters = new MapSqlParameterSource();

		query.append(" UPDATE " + TABLE_NAME);
		query.append(" SET ");
		query.append(DELETED + " = 1 ");
		query.append("WHERE " + KORISNIKID + " = :id");
		
		parameters.addValue("id", korisnikId);

		Long updated = ((Integer) njdbc.update(query.toString(), parameters)).longValue();
		if (updated == 0) {
		}

		return updated;
	}
	
	private class EntityRowMapper implements RowMapper<Korisnik> {

		@Override
		public Korisnik mapRow(ResultSet rs, int rowNum) throws SQLException {
			Korisnik entity = new Korisnik();
			
			rs.getInt(KORISNIKID);
			entity.setKorisnikId(rs.wasNull() ? null : rs.getInt(KORISNIKID));
			rs.getInt(ULOGAID);
			entity.setUlogaId(rs.wasNull() ? null : rs.getInt(ULOGAID));
			rs.getString(IME);
			entity.setIme(rs.wasNull() ? null : rs.getString(IME));
			rs.getString(PREZIME);
			entity.setPrezime(rs.wasNull() ? null : rs.getString(PREZIME));
			rs.getString(EMAIL);
			entity.setEmail(rs.wasNull() ? null : rs.getString(EMAIL));
			rs.getString(LOZINKA);
			entity.setLozinka(rs.wasNull() ? null : rs.getString(LOZINKA));
			rs.getString(KORISNICKO_IME);
			entity.setKorisnickoIme(rs.wasNull() ? null : rs.getString(KORISNICKO_IME));
			rs.getString(OIB);
			entity.setOib(rs.wasNull() ? null : rs.getString(OIB));
			
			return entity;
		}
		
	}

}

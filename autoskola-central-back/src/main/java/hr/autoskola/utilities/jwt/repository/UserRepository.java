package hr.autoskola.utilities.jwt.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import hr.autoskola.utilities.jwt.model.User;



@Repository
public class UserRepository {
	
	private final JdbcTemplate jdbc;
	private final NamedParameterJdbcTemplate njdbc;
	
	public UserRepository(@Qualifier("primaryJdbcTemplate") JdbcTemplate jdbc,
			@Qualifier("namedParameterJdbcTemplate") NamedParameterJdbcTemplate njdbc) {
		this.jdbc = jdbc;
		this.njdbc = njdbc;
	}
	
	private static final String TABLE_NAME = "korisnik";
	private static final String ROLE_TABLE = "uloge";
	
	private static final String KORISNIKID = "KORISNIK_ID";
	private static final String ULOGAID = "ULOGA_ID";
	private static final String EMAIL = "EMAIL";
	private static final String LOZINKA = "LOZINKA";
	private static final String SIFRA_ULOGE = "SIFRA";
	private static final String KOR_IME = "KORISNICKO_IME";
	private static final String IME = "IME";
	private static final String PREZIME = "PREZIME";
	
	
	public User getEntity(String korIme) {
		List<User> entities;
		StringBuilder query = new StringBuilder();
		
		query.append("SELECT "
				+ KORISNIKID + ", "
				+ TABLE_NAME + "." + ULOGAID + ", "
				+ EMAIL + ", "
				+ LOZINKA + ", "
				+ KOR_IME + ", "
				+ SIFRA_ULOGE + " "
				+ " FROM "
				+ TABLE_NAME + " "
				+ "LEFT JOIN " + ROLE_TABLE + " "
				+ "ON " + TABLE_NAME + "." + ULOGAID + " = " + ROLE_TABLE + "." + ULOGAID + " "
				+ "WHERE " + KOR_IME + " = '"+ korIme + "'");
		
		entities = jdbc.query(query.toString(), new EntityRowMapper());
		
		if(!entities.isEmpty()) {
			return entities.get(0);
		}else {
			return null;
		}
	}
	
	public Boolean existsByEmail(String email) {
		List<User> entities;
		StringBuilder query = new StringBuilder();
		
		query.append("SELECT "
				+ KORISNIKID + ", "
				+ TABLE_NAME + "." + ULOGAID + ", "
				+ EMAIL + ", "
				+ LOZINKA + ", "
				+ KOR_IME + ", "
				+ SIFRA_ULOGE + " "
				+ " FROM "
				+ TABLE_NAME + " "
				+ "LEFT JOIN " + ROLE_TABLE + " "
				+ "ON " + TABLE_NAME + "." + ULOGAID + " = " + ROLE_TABLE + "." + ULOGAID + " "
				+ "WHERE " + EMAIL + " = '"+ email + "'");
		
		entities = jdbc.query(query.toString(), new EntityRowMapper());
		
		if(!entities.isEmpty()) {
			return true;
		}else {
			return false;
		}
	}
	
	public int insertEntity(User user) {
		StringBuilder insertQuery = new StringBuilder();
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		
		insertQuery.append(" INSERT INTO " + TABLE_NAME + "( ");
		insertQuery.append(ULOGAID + ", ");
		insertQuery.append(EMAIL + ", ");
		insertQuery.append(LOZINKA + ", ");
		insertQuery.append(KOR_IME + ", ");
		insertQuery.append(IME + ", ");
		insertQuery.append(PREZIME + ") ");
		insertQuery.append(" VALUES(:ulogaId, :email, :lozinka, :korIme, :ime, :prezime) ");
		
		parameters.addValue("ulogaId", user.getUlogaId());
		parameters.addValue("email", user.getEmail());
		parameters.addValue("lozinka", user.getLozinka());
		parameters.addValue("korIme", user.getKorIme());
		parameters.addValue("ime", user.getIme());
		parameters.addValue("prezime", user.getPrezime());
		
//		if (njdbc.update(insertQuery.toString(), parameters) == 0) {
//			
//		}
		
		return njdbc.update(insertQuery.toString(), parameters);		
	}
	
	/**
	 * Metoda za ažuriranje User
	 * @param user
	 * @return
	 */
	public Long updatePassword(User user) {
		StringBuilder query = new StringBuilder();
		MapSqlParameterSource parameters = new MapSqlParameterSource();

		query.append(" UPDATE " + TABLE_NAME);
		query.append(" SET ");
		query.append(LOZINKA + " = :lozinka ");
		query.append("WHERE " + KORISNIKID + " = :id");

		parameters.addValue("lozinka", user.getLozinka());
		parameters.addValue("id", user.getKorisnikId());

		Long updated = ((Integer) njdbc.update(query.toString(), parameters)).longValue();
		if (updated == 0) {
		}

		return updated;
	}

	
	private class EntityRowMapper implements RowMapper<User> {

		@Override
		public User mapRow(ResultSet rs, int rowNum) throws SQLException {
			User entity = new User();
			
			rs.getLong(KORISNIKID);
			entity.setKorisnikId(rs.wasNull() ? null : rs.getLong(KORISNIKID));
			rs.getLong(ULOGAID);
			entity.setUlogaId(rs.wasNull() ? null : rs.getLong(ULOGAID));
			rs.getString(SIFRA_ULOGE);
			entity.setNazivUloge(rs.wasNull() ? null : rs.getString(SIFRA_ULOGE));
			rs.getString(EMAIL);
			entity.setEmail(rs.wasNull() ? null : rs.getString(EMAIL));
			rs.getString(KOR_IME);
			entity.setKorIme(rs.wasNull() ? null : rs.getString(KOR_IME));
			rs.getString(LOZINKA);
			entity.setLozinka(rs.wasNull() ? null : rs.getString(LOZINKA));
			
			return entity;
		}
		
	}

}

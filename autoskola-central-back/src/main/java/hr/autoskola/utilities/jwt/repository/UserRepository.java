package hr.autoskola.utilities.jwt.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import hr.autoskola.model.Korisnik;
import hr.autoskola.utilities.jwt.model.User;



@Repository
public class UserRepository {
	
	private final JdbcTemplate jdbc;
	
	public UserRepository(@Qualifier("primaryJdbcTemplate") JdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}
	
	private static final String TABLE_NAME = "korisnik";
	private static final String ROLE_TABLE = "uloge";
	
	private static final String KORISNIKID = "KORISNIK_ID";
	private static final String ULOGAID = "ULOGA_ID";
	private static final String EMAIL = "EMAIL";
	private static final String LOZINKA = "LOZINKA";
	private static final String SIFRA_ULOGE = "SIFRA";
	
	
	public User getEntity(String email) {
		List<User> entities;
		StringBuilder query = new StringBuilder();
		
		query.append("SELECT "
				+ KORISNIKID + ", "
				+ TABLE_NAME + "." + ULOGAID + ", "
				+ EMAIL + ", "
				+ LOZINKA + ", "
				+ SIFRA_ULOGE + " "
				+ " FROM "
				+ TABLE_NAME + " "
				+ "LEFT JOIN " + ROLE_TABLE + " "
				+ "ON " + TABLE_NAME + "." + ULOGAID + " = " + ROLE_TABLE + "." + ULOGAID + " "
				+ "WHERE " + EMAIL + " = '"+ email + "'");
		
		entities = jdbc.query(query.toString(), new EntityRowMapper());
		
		if(!entities.isEmpty()) {
			return entities.get(0);
		}else {
			return null;
		}
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
			rs.getString(LOZINKA);
			entity.setLozinka(rs.wasNull() ? null : rs.getString(LOZINKA));
			
			return entity;
		}
		
	}

}

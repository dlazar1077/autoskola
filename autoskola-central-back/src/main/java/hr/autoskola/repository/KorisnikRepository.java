package hr.autoskola.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import hr.autoskola.model.Korisnik;



@Repository
public class KorisnikRepository {
	
	private final JdbcTemplate jdbc;
	
	public KorisnikRepository(@Qualifier("primaryJdbcTemplate") JdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}
	
	private static final String TABLE_NAME = "korisnik";
	
	private static final String KORISNIKID = "KORISNIK_ID";
	private static final String ULOGAID = "ULOGA_ID";
	private static final String IME = "IME";
	private static final String PREZIME = "PREZIME";
	private static final String EMAIL = "EMAIL";
	private static final String LOZINKA = "LOZINKA";
	
	public List<Korisnik> getAllEntities() {
		StringBuilder query = new StringBuilder();
		
		query.append("SELECT "
				+ KORISNIKID + ", "
				+ ULOGAID + ", "
				+ IME + ", " 
				+ PREZIME + ", "
				+ EMAIL + ", "
				+ LOZINKA + " "
				+ " FROM "
				+ TABLE_NAME);
		
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
			
			return entity;
		}
		
	}

}

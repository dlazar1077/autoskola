package hr.autoskola.utilities.jwt.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
 
	private Long korisnikId;
	private Long ulogaId;
	private String nazivUloge;
	private String korIme;
	private String email;
	private String lozinka;
	private String ime;
	private String prezime;
	
}

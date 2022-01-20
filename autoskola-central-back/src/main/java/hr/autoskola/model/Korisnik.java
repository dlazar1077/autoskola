package hr.autoskola.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Korisnik {
	
	private Integer korisnikId;
	private Integer ulogaId;
	private String ime;
	private String prezime;
	private String email;
	private String lozinka;

}

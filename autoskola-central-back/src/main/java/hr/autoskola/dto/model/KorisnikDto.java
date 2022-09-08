package hr.autoskola.dto.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class KorisnikDto {
	
	private Integer korisnikId;
	private Integer ulogaId;
	private String ime;
	private String prezime;
	private String email;
	private String korisnickoIme;
	private String lozinka;
	private String oib;

}

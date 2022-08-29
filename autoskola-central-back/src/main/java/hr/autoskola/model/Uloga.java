package hr.autoskola.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Uloga {
	
	private int ulogaId;
	private String sifra;
	private String naziv;
	private String nazivEn;

}

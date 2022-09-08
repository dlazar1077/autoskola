package hr.autoskola.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Ispit {

	private Integer ispitId;
	private Integer korisnikId;
	private Integer maksimalniBrojBodova;
	private Integer ostvareniBrojBodova;
	private String statusIspita;
	private String creationDate;
	
	private Korisnik korisnik;
	private List<Pitanje> pitanja;
	
}

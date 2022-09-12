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
public class Polaznik {
	
	private Integer polaznikId;
	private Integer korisnikId;
	private Integer instruktorId;
	private Integer statusPolaznikaId;
	private Integer odabranaKategorijaId;
	
	private Korisnik korisnik;
	private Instruktor instruktor;
	private List<Sat> dnevnikVoznje; 

}

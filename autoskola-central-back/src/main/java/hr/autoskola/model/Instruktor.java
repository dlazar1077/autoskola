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
public class Instruktor {
	
	private Integer instruktorId;
	private Integer korisnikId;
	private Integer brojSlobodnihMjesta;
	
	private Korisnik korisnik;
	private List<Vozilo> vozila;

}

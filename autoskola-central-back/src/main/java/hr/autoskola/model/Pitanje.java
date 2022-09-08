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
public class Pitanje {
	
	private Integer pitanjeId;
	private String tekstPitanja;
	private String slika;
	private Integer raskrizje;
	private Integer brojBodova;
	private String odgovor;
	
	private List<Odabir> odabiri;

}

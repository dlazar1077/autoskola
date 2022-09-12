package hr.autoskola.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Sat {

	private Integer satVoznjeId;
	private Integer polaznikId;
	private Integer voziloId;
	private Integer brojSata;
	private String opis; 
	private String datum;
	
	private Polaznik polaznik;
	private Vozilo vozilo;
}

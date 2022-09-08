package hr.autoskola.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Odabir {
	
	private Integer odabirId;
	private Integer pitanjeId;
	private String tekst;
	private Integer tocanOdgovor;
	private String sifra;
	
	private Integer deleted;

}

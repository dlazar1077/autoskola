package hr.autoskola.dto.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OdabirDto {
	
	private Integer odabirId;
	private Integer pitanjeId;
	private String tekst;
	private boolean tocanOdgovor;
	private String sifra;
	
	private Integer deleted;

}

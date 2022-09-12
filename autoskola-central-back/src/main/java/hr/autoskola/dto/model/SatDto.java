package hr.autoskola.dto.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SatDto {

	private Integer satVoznjeId;
	private Integer polaznikId;
	private Integer voziloId;
	private Integer brojSata;
	private String opis; 
	private String datum;
	
	private PolaznikDto polaznik;
	private VoziloDto vozilo;
}

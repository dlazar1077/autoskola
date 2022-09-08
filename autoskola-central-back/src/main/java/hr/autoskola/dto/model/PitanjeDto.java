package hr.autoskola.dto.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PitanjeDto {
	
	private Integer pitanjeId;
	private String tekstPitanja;
	private String slika;
	private boolean raskrizje;
	private Integer brojBodova;
	private String odgovor;

	private List<OdabirDto> odabiri;
}

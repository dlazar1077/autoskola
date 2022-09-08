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
public class InstruktorDto {
	
	private Integer instruktorId;
	private Integer korisnikId;
	private Integer brojSlobodnihMjesta;
	
	private KorisnikDto korisnik;
	private List<VoziloDto> vozila;

}

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
public class IspitDto {

	private Integer ispitId;
	private Integer korisnikId;
	private Integer maksimalniBrojBodova;
	private Integer ostvareniBrojBodova;
	private String statusIspita;
	private String creationDate;
	
	private KorisnikDto korisnik;
	private List<PitanjeDto> pitanja;
	
}

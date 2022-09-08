package hr.autoskola.dto.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PolaznikDto {
	
	private Integer polaznikId;
	private Integer korisnikId;
	private Integer instruktorId;
	private Integer statusPolaznikaId;
	private Integer odabranaKategorijaId;
	
	private KorisnikDto korisnik;
	private InstruktorDto instruktor;

}

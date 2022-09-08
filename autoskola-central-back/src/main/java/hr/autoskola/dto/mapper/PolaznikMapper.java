package hr.autoskola.dto.mapper;

import org.springframework.stereotype.Component;

import hr.autoskola.dto.model.PolaznikDto;
import hr.autoskola.model.Polaznik;

@Component
public class PolaznikMapper {
	
	/**
	  * Metoda koja mapira klasu Polaznik u klasu PolaznikDto
	  * @param polaznik
	  * @return
	  */
	public static PolaznikDto toPolaznikDto (Polaznik polaznik) {
		PolaznikDto returnObject = new PolaznikDto();
		
		returnObject.setPolaznikId(polaznik.getPolaznikId());
		returnObject.setKorisnikId(polaznik.getKorisnikId());
		returnObject.setInstruktorId(polaznik.getInstruktorId());
		returnObject.setStatusPolaznikaId(polaznik.getStatusPolaznikaId());
		returnObject.setOdabranaKategorijaId(polaznik.getOdabranaKategorijaId());
		
		if(polaznik.getKorisnik() != null) {
			returnObject.setKorisnik(KorisnikMapper.toKorisnikDto(polaznik.getKorisnik()));
		}
		
		if(polaznik.getInstruktor() != null) {
			returnObject.setInstruktor(InstruktorMapper.toInstruktorDto(polaznik.getInstruktor()));
		}
		return returnObject;
	}
	
	/**
	  * Metoda koja mapira klasu PolaznikDto u klasu Polaznik
	  * @param polaznikDto
	  * @return
	  */
	public static Polaznik toPolaznik (PolaznikDto polaznikDto) {
		Polaznik returnObject = new Polaznik();
		
		returnObject.setPolaznikId(polaznikDto.getPolaznikId());
		returnObject.setKorisnikId(polaznikDto.getKorisnikId());
		returnObject.setInstruktorId(polaznikDto.getInstruktorId());
		returnObject.setStatusPolaznikaId(polaznikDto.getStatusPolaznikaId());
		returnObject.setOdabranaKategorijaId(polaznikDto.getOdabranaKategorijaId());
		
		if(polaznikDto.getKorisnik() != null) {
			returnObject.setKorisnik(KorisnikMapper.toKorisnik(polaznikDto.getKorisnik()));
		}
		
		if(polaznikDto.getInstruktor() != null) {
			returnObject.setInstruktor(InstruktorMapper.toInstruktor(polaznikDto.getInstruktor()));
		}
		
		return returnObject;
	}

}

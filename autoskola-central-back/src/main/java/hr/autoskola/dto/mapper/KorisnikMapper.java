package hr.autoskola.dto.mapper;

import org.springframework.stereotype.Component;

import hr.autoskola.dto.model.KorisnikDto;
import hr.autoskola.model.Korisnik;

@Component
public class KorisnikMapper {
	
	
	/**
	  * Metoda koja mapira klasu Korisnik u klasu KorisnikDto
	  * @param korinsik
	  * @return
	  */
	public static KorisnikDto toKorisnikDto (Korisnik korisnik) {
		KorisnikDto returnObject = new KorisnikDto();
		
		returnObject.setKorisnikId(korisnik.getKorisnikId());
		returnObject.setUlogaId(korisnik.getUlogaId());
		returnObject.setIme(korisnik.getIme());
		returnObject.setPrezime(korisnik.getPrezime());
		returnObject.setEmail(korisnik.getEmail());
		returnObject.setKorisnickoIme(korisnik.getKorisnickoIme());
		returnObject.setLozinka(korisnik.getLozinka());
		returnObject.setOib(korisnik.getOib());
		
		return returnObject;
	}
	
	/**
	  * Metoda koja mapira klasuKorisnikDto u klasu Korisnik
	  * @param korisnikDto
	  * @return
	  */
	public static Korisnik toKorisnik (KorisnikDto korisnikDto) {
		Korisnik returnObject = new Korisnik();
		
		returnObject.setKorisnikId(korisnikDto.getKorisnikId());
		returnObject.setUlogaId(korisnikDto.getUlogaId());
		returnObject.setIme(korisnikDto.getIme());
		returnObject.setPrezime(korisnikDto.getPrezime());
		returnObject.setEmail(korisnikDto.getEmail());
		returnObject.setKorisnickoIme(korisnikDto.getKorisnickoIme());
		returnObject.setLozinka(korisnikDto.getLozinka());
		returnObject.setOib(korisnikDto.getOib());
		
		return returnObject;
	}

}

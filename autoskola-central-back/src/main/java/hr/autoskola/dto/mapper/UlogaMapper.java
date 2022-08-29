package hr.autoskola.dto.mapper;

import org.springframework.stereotype.Component;

import hr.autoskola.dto.model.UlogaDto;
import hr.autoskola.model.Uloga;

@Component
public class UlogaMapper {
	
	/**
	  * Metoda koja mapira klasu Uloga u klasu UlogaDto
	  * @param uloga
	  * @return
	  */
	public static UlogaDto toUlogaDto (Uloga uloga) {
		UlogaDto returnObject = new UlogaDto();
		
		returnObject.setUlogaId(uloga.getUlogaId());
		returnObject.setSifra(uloga.getSifra());
		returnObject.setNaziv(uloga.getNaziv());
		returnObject.setNazivEn(uloga.getNazivEn());
		
		return returnObject;
	}
	
	/**
	  * Metoda koja mapira klasu UlogaDto u klasu Uloga
	  * @param ulogaDto
	  * @return
	  */
	public static Uloga toUloga (UlogaDto ulogaDto) {
		Uloga returnObject = new Uloga();
		
		returnObject.setUlogaId(ulogaDto.getUlogaId());
		returnObject.setSifra(ulogaDto.getSifra());
		returnObject.setNaziv(ulogaDto.getNaziv());
		returnObject.setNazivEn(ulogaDto.getNazivEn());
		
		return returnObject;
	}

}

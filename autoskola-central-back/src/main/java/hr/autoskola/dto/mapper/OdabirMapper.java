package hr.autoskola.dto.mapper;

import org.springframework.stereotype.Component;

import hr.autoskola.dto.model.OdabirDto;
import hr.autoskola.model.Odabir;

@Component
public class OdabirMapper {
	
	/**
	  * Metoda koja mapira klasu Odabir u klasu OdabirDto
	  * @param odabir
	  * @return
	  */
	public static OdabirDto toOdabirDto (Odabir odabir) {
		OdabirDto returnObject = new OdabirDto();
		
		returnObject.setOdabirId(odabir.getOdabirId());
		returnObject.setPitanjeId(odabir.getPitanjeId());
		returnObject.setTekst(odabir.getTekst());
		returnObject.setTocanOdgovor(odabir.getTocanOdgovor() == 1 ? true : false);
		returnObject.setSifra(odabir.getSifra());
		
		returnObject.setDeleted(odabir.getDeleted());
		
		return returnObject;
	}
	
	/**
	  * Metoda koja mapira klasu OdabirDto u klasu Odabir
	  * @param odabirDto
	  * @return
	  */
	public static Odabir toOdabir (OdabirDto odabirDto) {
		Odabir returnObject = new Odabir();
		
		returnObject.setOdabirId(odabirDto.getOdabirId());
		returnObject.setPitanjeId(odabirDto.getPitanjeId());
		returnObject.setTekst(odabirDto.getTekst());
		returnObject.setTocanOdgovor(odabirDto.isTocanOdgovor() == true ? 1 : 0);
		returnObject.setSifra(odabirDto.getSifra());
		
		returnObject.setDeleted(odabirDto.getDeleted());
		
		return returnObject;
	}

}

package hr.autoskola.dto.mapper;

import org.springframework.stereotype.Component;

import hr.autoskola.dto.model.StatusPolaznikaDto;
import hr.autoskola.model.StatusPolaznika;

@Component
public class StatusPolaznikaMapper {
	
	/**
	  * Metoda koja mapira klasu StatusPolaznika u klasu StatusPolaznikaDto
	  * @param statusPolaznika
	  * @return
	  */
	public static StatusPolaznikaDto toStatusPolaznikaDto (StatusPolaznika statusPolaznika) {
		StatusPolaznikaDto returnObject = new StatusPolaznikaDto();
		
		returnObject.setStatusPolaznikaId(statusPolaznika.getStatusPolaznikaId());
		returnObject.setSifra(statusPolaznika.getSifra());
		returnObject.setNaziv(statusPolaznika.getNaziv());
		returnObject.setNazivEn(statusPolaznika.getNazivEn());
		
		return returnObject;
	}
	
	/**
	  * Metoda koja mapira klasu UlogaDto u klasu Uloga
	  * @param statusPolaznikaDto
	  * @return
	  */
	public static StatusPolaznika toStatusPolaznika (StatusPolaznikaDto statusPolaznikaDto) {
		StatusPolaznika returnObject = new StatusPolaznika();
		
		returnObject.setStatusPolaznikaId(statusPolaznikaDto.getStatusPolaznikaId());
		returnObject.setSifra(statusPolaznikaDto.getSifra());
		returnObject.setNaziv(statusPolaznikaDto.getNaziv());
		returnObject.setNazivEn(statusPolaznikaDto.getNazivEn());
		
		return returnObject;
	}

}

package hr.autoskola.dto.mapper;

import org.springframework.stereotype.Component;

import hr.autoskola.dto.model.VoziloDto;
import hr.autoskola.model.Vozilo;

@Component
public class VoziloMapper {
	 /**
	  * Metoda koja mapira klasu Vozilo u klasu VoziloDto
	  * @param vozilo
	  * @return
	  */
	public static VoziloDto toVoziloDto (Vozilo vozilo) {
		VoziloDto returnObject = new VoziloDto();
		
		returnObject.setVoziloId(vozilo.getVoziloId());
		returnObject.setKategorijaId(vozilo.getKategorijaId());
		returnObject.setMarkaVozila(vozilo.getMarkaVozila());
		returnObject.setModel(vozilo.getModel());
		returnObject.setRegistracija(vozilo.getRegistracija());
		
		return returnObject;
	}
	
	/**
	  * Metoda koja mapira klasu VoziloDto u klasu Vozilo
	  * @param vozilo
	  * @return
	  */
	public static Vozilo toVozilo (VoziloDto voziloDto) {
		Vozilo returnObject = new Vozilo();
		
		returnObject.setVoziloId(voziloDto.getVoziloId());
		returnObject.setKategorijaId(voziloDto.getKategorijaId());
		returnObject.setMarkaVozila(voziloDto.getMarkaVozila());
		returnObject.setModel(voziloDto.getModel());
		returnObject.setRegistracija(voziloDto.getRegistracija());
		
		return returnObject;
	}

}

package hr.autoskola.dto.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import hr.autoskola.dto.model.InstruktorDto;
import hr.autoskola.dto.model.VoziloDto;
import hr.autoskola.model.Instruktor;
import hr.autoskola.model.Vozilo;

@Component
public class InstruktorMapper {
	
	/**
	  * Metoda koja mapira klasu Instruktor u klasu InstruktorDto
	  * @param instruktor
	  * @return
	  */
	public static InstruktorDto toInstruktorDto (Instruktor instruktor) {
		InstruktorDto returnObject = new InstruktorDto();
		
		returnObject.setInstruktorId(instruktor.getInstruktorId());
		returnObject.setKorisnikId(instruktor.getKorisnikId());
		returnObject.setBrojSlobodnihMjesta(instruktor.getBrojSlobodnihMjesta());
		
		if(instruktor.getKorisnik() != null) {
			returnObject.setKorisnik(KorisnikMapper.toKorisnikDto(instruktor.getKorisnik()));
		}
		
		if(instruktor.getVozila() != null) {
			List<VoziloDto> vozilaDto = new ArrayList<VoziloDto>();
			for(Vozilo vozilo : instruktor.getVozila()) {
				vozilaDto.add(VoziloMapper.toVoziloDto(vozilo));
			}
			returnObject.setVozila(vozilaDto);
		}
		
		return returnObject;
	}
	
	/**
	  * Metoda koja mapira klasu InstruktorDto u klasu Instruktor
	  * @param instruktorDto
	  * @return
	  */
	public static Instruktor toInstruktor (InstruktorDto instruktorDto) {
		Instruktor returnObject = new Instruktor();
		
		returnObject.setInstruktorId(instruktorDto.getInstruktorId());
		returnObject.setKorisnikId(instruktorDto.getKorisnikId());
		returnObject.setBrojSlobodnihMjesta(instruktorDto.getBrojSlobodnihMjesta());
		
		if(instruktorDto.getKorisnik() != null) {
			returnObject.setKorisnik(KorisnikMapper.toKorisnik(instruktorDto.getKorisnik()));
		}
		
		if(instruktorDto.getVozila() != null) {
			List<Vozilo> vozila = new ArrayList<Vozilo>();
			for(VoziloDto voziloDto : instruktorDto.getVozila()) {
				vozila.add(VoziloMapper.toVozilo(voziloDto));
			}
			returnObject.setVozila(vozila);
		}
		
		return returnObject;
	}

}

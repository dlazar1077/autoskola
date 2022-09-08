package hr.autoskola.dto.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import hr.autoskola.dto.model.IspitDto;
import hr.autoskola.dto.model.PitanjeDto;
import hr.autoskola.model.Ispit;
import hr.autoskola.model.Pitanje;

@Component
public class IspitMapper {
	
	/**
	  * Metoda koja mapira klasu Ispit u klasu IspitDto
	  * @param ispit
	  * @return
	  */
	public static IspitDto toIspitDto (Ispit ispit) {
		IspitDto returnObject = new IspitDto();
		
		returnObject.setIspitId(ispit.getIspitId());
		returnObject.setKorisnikId(ispit.getKorisnikId());
		returnObject.setMaksimalniBrojBodova(ispit.getMaksimalniBrojBodova());
		returnObject.setOstvareniBrojBodova(ispit.getOstvareniBrojBodova());
		returnObject.setStatusIspita(ispit.getStatusIspita());
		returnObject.setCreationDate(ispit.getCreationDate());
		
		if(ispit.getKorisnik() != null) {
			returnObject.setKorisnik(KorisnikMapper.toKorisnikDto(ispit.getKorisnik()));
		}
		
		if(returnObject.getPitanja() != null) {
			List<PitanjeDto> pitanjeDto = new ArrayList<PitanjeDto>();
			for(Pitanje pitanje: ispit.getPitanja()) {
				pitanjeDto.add( PitanjeMapper.toPitanjeDto(pitanje));
			}
			returnObject.setPitanja(pitanjeDto);
		}
		
		return returnObject;
	}
	
	/**
	  * Metoda koja mapira klasu IspitDto u klasu Ispit
	  * @param ispitDto
	  * @return
	  */
	public static Ispit toIspit (IspitDto ispitDto) {
		Ispit returnObject = new Ispit();
		
		returnObject.setIspitId(ispitDto.getIspitId());
		returnObject.setKorisnikId(ispitDto.getKorisnikId());
		returnObject.setMaksimalniBrojBodova(ispitDto.getMaksimalniBrojBodova());
		returnObject.setOstvareniBrojBodova(ispitDto.getOstvareniBrojBodova());
		returnObject.setStatusIspita(ispitDto.getStatusIspita());
		returnObject.setCreationDate(ispitDto.getCreationDate());
		
		if(ispitDto.getKorisnik() != null) {
			returnObject.setKorisnik(KorisnikMapper.toKorisnik(ispitDto.getKorisnik()));
		}
		
		if(returnObject.getPitanja() != null) {
			List<Pitanje> pitanje = new ArrayList<Pitanje>();
			for(PitanjeDto pitanjeDto: ispitDto.getPitanja()) {
				pitanje.add( PitanjeMapper.toPitanje(pitanjeDto));
			}
			returnObject.setPitanja(pitanje);
		}
		
		return returnObject;
	}

}

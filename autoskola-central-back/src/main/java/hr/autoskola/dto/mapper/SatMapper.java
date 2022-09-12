package hr.autoskola.dto.mapper;

import org.springframework.stereotype.Component;

import hr.autoskola.dto.model.SatDto;
import hr.autoskola.model.Sat;
@Component
public class SatMapper {
	
	/**
	  * Metoda koja mapira klasu Sat u klasu SatDto
	  * @param sat
	  * @return
	  */
	public static SatDto toSatDto (Sat sat) {
		SatDto returnObject = new SatDto();
		
		returnObject.setSatVoznjeId(sat.getSatVoznjeId());
		returnObject.setPolaznikId(sat.getPolaznikId());
		returnObject.setVoziloId(sat.getVoziloId());
		returnObject.setBrojSata(sat.getBrojSata());
		returnObject.setOpis(sat.getOpis());
		returnObject.setDatum(sat.getDatum());
		
		if(sat.getPolaznik() != null) {
			returnObject.setPolaznik(PolaznikMapper.toPolaznikDto(sat.getPolaznik()));
		}
		
		if(sat.getVozilo() != null) {
			returnObject.setVozilo(VoziloMapper.toVoziloDto(sat.getVozilo()));
		}
		return returnObject;
	}
	
	/**
	  * Metoda koja mapira klasu SatDto u klasu Sat
	  * @param satDto
	  * @return
	  */
	public static Sat toSat (SatDto satDto) {
		Sat returnObject = new Sat();
		
		returnObject.setSatVoznjeId(satDto.getSatVoznjeId());
		returnObject.setPolaznikId(satDto.getPolaznikId());
		returnObject.setVoziloId(satDto.getVoziloId());
		returnObject.setBrojSata(satDto.getBrojSata());
		returnObject.setOpis(satDto.getOpis());
		returnObject.setDatum(satDto.getDatum());
		
		if(satDto.getPolaznik() != null) {
			returnObject.setPolaznik(PolaznikMapper.toPolaznik(satDto.getPolaznik()));
		}
		
		if(satDto.getVozilo() != null) {
			returnObject.setVozilo(VoziloMapper.toVozilo(satDto.getVozilo()));
		}
		return returnObject;
	}

}

package hr.autoskola.dto.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import hr.autoskola.dto.model.OdabirDto;
import hr.autoskola.dto.model.PitanjeDto;
import hr.autoskola.model.Odabir;
import hr.autoskola.model.Pitanje;

@Component
public class PitanjeMapper {
	
	/**
	  * Metoda koja mapira klasu Pitanje u klasu PitanjeDto
	  * @param pitanje
	  * @return
	  */
	public static PitanjeDto toPitanjeDto (Pitanje pitanje) {
		PitanjeDto returnObject = new PitanjeDto();
		
		returnObject.setPitanjeId(pitanje.getPitanjeId());
		returnObject.setTekstPitanja(pitanje.getTekstPitanja());
		returnObject.setSlika(pitanje.getSlika());
		returnObject.setRaskrizje(pitanje.getRaskrizje() == 1 ? true : false);
		returnObject.setBrojBodova(pitanje.getBrojBodova());
		returnObject.setOdgovor(pitanje.getOdgovor());
		
//		if(pitanje.getOdgovor() != null) {
//			List<String> odgovorDto = new ArrayList<String>();
//			String[] odgovor = pitanje.getOdgovor().split(",");
//			for(String string: odgovor) {
//				odgovorDto.add(string);
//			}
//			returnObject.setOdgovor(odgovorDto);
//		}
		
		if(returnObject.getOdabiri() != null) {
			List<OdabirDto> odabiriDto = new ArrayList<OdabirDto>();
			for(Odabir odabir: pitanje.getOdabiri()) {
				odabiriDto.add( OdabirMapper.toOdabirDto(odabir));
			}
			returnObject.setOdabiri(odabiriDto);
		}
		
		return returnObject;
	}
	
	/**
	  * Metoda koja mapira klasu OdabirDto u klasu Odabir
	  * @param pitanjeDto
	  * @return
	  */
	public static Pitanje toPitanje (PitanjeDto pitanjeDto) {
		Pitanje returnObject = new Pitanje();
		
		returnObject.setPitanjeId(pitanjeDto.getPitanjeId());
		returnObject.setTekstPitanja(pitanjeDto.getTekstPitanja());
		returnObject.setSlika(pitanjeDto.getSlika());
		returnObject.setRaskrizje(pitanjeDto.isRaskrizje() == true ? 1 : 0);
		returnObject.setBrojBodova(pitanjeDto.getBrojBodova());
		returnObject.setOdgovor(pitanjeDto.getOdgovor());
		
//		if(pitanjeDto.getOdgovor() != null) {
//			String odgovor="";
//			for(String odgovorDto: pitanjeDto.getOdgovor()) {
//				if(pitanjeDto.getOdgovor().indexOf(odgovorDto) == pitanjeDto.getOdgovor().size()-1) {
//					odgovor += odgovorDto;
//				} else {
//					odgovor += odgovorDto + ",";
//				}
//			}
//			returnObject.setOdgovor(odgovor);
//		}
		
		if(returnObject.getOdabiri() != null) {
			List<Odabir> odabiri = new ArrayList<Odabir>();
			for(OdabirDto odabirDto: pitanjeDto.getOdabiri()) {
				odabiri.add( OdabirMapper.toOdabir(odabirDto));
			}
			returnObject.setOdabiri(odabiri);
		}
		
		return returnObject;
	}

}

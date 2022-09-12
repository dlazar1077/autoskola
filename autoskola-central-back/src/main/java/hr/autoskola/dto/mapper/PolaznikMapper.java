package hr.autoskola.dto.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import hr.autoskola.dto.model.PolaznikDto;
import hr.autoskola.dto.model.SatDto;
import hr.autoskola.model.Polaznik;
import hr.autoskola.model.Sat;

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
		
		if(polaznik.getDnevnikVoznje() != null) {
			List<SatDto> denvnikVoznjeDto = new ArrayList<SatDto>();
			for(Sat denvnikVoznje : polaznik.getDnevnikVoznje()) {
				denvnikVoznjeDto.add(SatMapper.toSatDto(denvnikVoznje));
			}
			returnObject.setDnevnikVoznje(denvnikVoznjeDto);
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
		
		if(polaznikDto.getDnevnikVoznje() != null) {
			List<Sat> denvnikVoznje = new ArrayList<Sat>();
			for(SatDto denvnikVoznjeDto : polaznikDto.getDnevnikVoznje()) {
				denvnikVoznje.add(SatMapper.toSat(denvnikVoznjeDto));
			}
			returnObject.setDnevnikVoznje(denvnikVoznje);
		}
		
		return returnObject;
	}

}

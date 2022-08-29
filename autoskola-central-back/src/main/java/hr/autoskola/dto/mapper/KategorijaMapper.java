package hr.autoskola.dto.mapper;

import org.springframework.stereotype.Component;

import hr.autoskola.dto.model.KategorijaDto;
import hr.autoskola.model.Kategorija;

@Component
public class KategorijaMapper {
	
	/**
	  * Metoda koja mapira klasu Kategorija u klasu KategorijaDto
	  * @param kategorija
	  * @return
	  */
	public static KategorijaDto toKategorijaDto (Kategorija kategorija) {
		KategorijaDto returnObject = new KategorijaDto();
		
		returnObject.setKategorijaId(kategorija.getKategorijaId());
		returnObject.setSifra(kategorija.getSifra());
		returnObject.setNaziv(kategorija.getNaziv());
		returnObject.setNazivEn(kategorija.getNazivEn());
		
		return returnObject;
	}
	
	/**
	  * Metoda koja mapira klasu KategorijaDto u klasu Kategorija
	  * @param kategorijaDto
	  * @return
	  */
	public static Kategorija toKategorija (KategorijaDto kategorijaDto) {
		Kategorija returnObject = new Kategorija();
		
		returnObject.setKategorijaId(kategorijaDto.getKategorijaId());
		returnObject.setSifra(kategorijaDto.getSifra());
		returnObject.setNaziv(kategorijaDto.getNaziv());
		returnObject.setNazivEn(kategorijaDto.getNazivEn());
		
		return returnObject;
	}

}

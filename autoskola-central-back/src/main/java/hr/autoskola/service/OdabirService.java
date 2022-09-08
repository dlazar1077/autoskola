package hr.autoskola.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import hr.autoskola.dto.mapper.OdabirMapper;
import hr.autoskola.dto.model.OdabirDto;
import hr.autoskola.dto.model.shared.response.GenericHttpResponse;
import hr.autoskola.model.Odabir;
import hr.autoskola.repository.OdabirRepository;
import hr.autoskola.utilities.response.ResponseMessageEnum;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OdabirService {
	
	private final OdabirRepository odabirRepository;
	
	/**
	 * Servisna metoda za dohvaćanje svih odabira jednog pitanja
	 * @param pitanjeId
	 * @return
	 */
	public List<OdabirDto> getEntityById(String pitanjeId){
		
		List<Odabir> modelEntities=odabirRepository.getEntityById(pitanjeId);
		
		List<OdabirDto> response = modelEntities.stream().map(OdabirMapper::toOdabirDto).collect(Collectors.toList());
		
		return response;
	}
	
	/**
	 * Metoda za dodavanje odabira
	 * @param odabirDto
	 * @return
	 */
	public GenericHttpResponse<Long> saveOdabir(OdabirDto odabirDto) {
		GenericHttpResponse<Long> response;
		Long id = odabirRepository.saveOdabir(OdabirMapper.toOdabir(odabirDto));
		if(id > 0) {
			response = new GenericHttpResponse<>(ResponseMessageEnum.ENTITY_INSERTED);
			response.setData(id);
		}else {
			response = new GenericHttpResponse<>(ResponseMessageEnum.NOTHING_INSERTED);
		}
		return response;		
	}
	
	/**
	 * Servisna metoda za ažuriranje odabira
	 * @param odabirDto
	 * @return
	 */
	public GenericHttpResponse<Long> updateOdabir(OdabirDto odabirDto) {
		GenericHttpResponse<Long> response;
		Long numberOfUpdateRows;
		
		numberOfUpdateRows = odabirRepository.updateOdabir(OdabirMapper.toOdabir(odabirDto));
		response = new GenericHttpResponse<>(numberOfUpdateRows > 0 ? ResponseMessageEnum.ENTITY_UPDATED :ResponseMessageEnum.NOTHING_UPDATED);
		response.setData(numberOfUpdateRows);
		
		return response;		
	}

	/**
	 * Servisna metoda za brisanje odabira
	 * @param id
	 * @return
	 */
	public Long deleteOdabir(String id) {
		return odabirRepository.deleteOdabir(id);
	}
	
	/**
	 * Servisna metoda za brisanje odabira po id pitanja i sifri
	 * @param odabirDto
	 * @return
	 */
	public Long deleteOdabirByPitanjeIdAndSifra(OdabirDto odabirDto) {
		return odabirRepository.deleteOdabirByPitanjeIdAndSifra(odabirDto.getPitanjeId().toString(), odabirDto.getSifra());
	}
	
	/**
	 * Servisna metoda za brisanje odabira za uvijek
	 * @param id
	 * @return
	 */
	public Long deleteFroeverOdabir(OdabirDto[] odabiriDto) {
		Long numberOfUpdateRows = (long) 0;
		for(OdabirDto odabirDto : odabiriDto) {
			Odabir odabir = OdabirMapper.toOdabir(odabirDto);
			odabirRepository.deleteFroeverOdabir(odabir.getOdabirId().toString());
			numberOfUpdateRows++;
		}
		return numberOfUpdateRows;
	}

}

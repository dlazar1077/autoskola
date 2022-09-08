package hr.autoskola.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hr.autoskola.dto.mapper.PitanjeMapper;
import hr.autoskola.dto.model.OdabirDto;
import hr.autoskola.dto.model.PitanjeDto;
import hr.autoskola.dto.model.shared.response.GenericHttpResponse;
import hr.autoskola.model.Pitanje;
import hr.autoskola.repository.PitanjeRepository;
import hr.autoskola.utilities.response.ResponseMessageEnum;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PitanjeService {
	
	private final PitanjeRepository pitanjeRepository;
	private final OdabirService odabirService;
	
	/**
	 * Servisna metoda za dohvaćanje svih pitanja
	 * @param 
	 * @return
	 */
	public List<PitanjeDto> getAllEntities(){
		
		List<Pitanje> modelEntities=pitanjeRepository.getAllEntities();
		
		List<PitanjeDto> response = modelEntities.stream().map(PitanjeMapper::toPitanjeDto).collect(Collectors.toList());
		
		if(response != null) {
			for(PitanjeDto pitanjeDto : response) {
				pitanjeDto.setOdabiri(odabirService.getEntityById(pitanjeDto.getPitanjeId().toString()));
			}
		}
		
		return response;
	}
	
	/**
	 * Servisna metoda za dohvat svih objekta tipa Pitanje za ispit
	 * 
	 * @param numberOfPoints
	 * @param numberOfQuestions
	 * @return 
	 * 
	 * @author dlazar
	 */
	public List<PitanjeDto> getIspit(){
		
		List<Pitanje> modelEntities=  pitanjeRepository.getIspit(7, 4);
		modelEntities.addAll(pitanjeRepository.getIspit(4, 6));
		modelEntities.addAll(pitanjeRepository.getIspit(3, 12));
		modelEntities.addAll(pitanjeRepository.getIspit(2, 16));
		
		List<PitanjeDto> response = modelEntities.stream().map(PitanjeMapper::toPitanjeDto).collect(Collectors.toList());
		
		if(response != null) {
			for(PitanjeDto pitanjeDto : response) {
				pitanjeDto.setOdabiri(odabirService.getEntityById(pitanjeDto.getPitanjeId().toString()));
			}
		}
		
		return response;
	}
	
	/**
	 * Servisna metoda za dohvaćanje svih pitanja za određeni ispit
	 * @param ispitId
	 * @return
	 */
	public List<PitanjeDto> getAllEntitiesByIspitId(String ispitId){
		
		List<Pitanje> modelEntities=pitanjeRepository.getAllEntitiesByIspitId(ispitId);
		
		List<PitanjeDto> response = modelEntities.stream().map(PitanjeMapper::toPitanjeDto).collect(Collectors.toList());
		
		if(response != null) {
			for(PitanjeDto pitanjeDto : response) {
				pitanjeDto.setOdabiri(odabirService.getEntityById(pitanjeDto.getPitanjeId().toString()));
			}
		}
		
		return response;
	}
	
	/**
	 * Metoda za dodavanje pitanja
	 * @param pitanjeDto
	 * @return
	 */
	@Transactional
	public GenericHttpResponse<Long> savePitanje(PitanjeDto pitanjeDto) {
		GenericHttpResponse<Long> response;
		Long id = pitanjeRepository.savePitanje(PitanjeMapper.toPitanje(pitanjeDto));
		if(id > 0) {
			if(pitanjeDto.getOdabiri() != null) {
				for(OdabirDto odabirDto : pitanjeDto.getOdabiri()) {
					odabirDto.setPitanjeId(Integer.parseInt(id.toString())); 
					odabirService.saveOdabir(odabirDto);
				}
			}
			response = new GenericHttpResponse<>(ResponseMessageEnum.ENTITY_INSERTED);
			response.setData(id);
		}else {
			response = new GenericHttpResponse<>(ResponseMessageEnum.NOTHING_INSERTED);
		}
		return response;		
	}
	
	/**
	 * Metoda za dodavanje odgovora
	 * @param pitanjeDto, ispitId
	 * @return
	 */
	@Transactional
	public GenericHttpResponse<Long> savePitanjeOdgovor(PitanjeDto pitanjeDto, String ispitId) {
		GenericHttpResponse<Long> response;
		Long id = pitanjeRepository.savePitanjeOdgovor(PitanjeMapper.toPitanje(pitanjeDto), ispitId);
		if(id > 0) {
			response = new GenericHttpResponse<>(ResponseMessageEnum.ENTITY_INSERTED);
			response.setData(id);
		}else {
			response = new GenericHttpResponse<>(ResponseMessageEnum.NOTHING_INSERTED);
		}
		return response;		
	}
	
	/**
	 * Servisna metoda za ažuriranje pitanja
	 * @param odabipitanjeDtorDto
	 * @return
	 */
	@Transactional
	public GenericHttpResponse<Long> updatePitanje(PitanjeDto pitanjeDto) {
		GenericHttpResponse<Long> response;
		Long numberOfUpdateRows;
		
		numberOfUpdateRows = pitanjeRepository.updatePitanje(PitanjeMapper.toPitanje(pitanjeDto));
		if(pitanjeDto.getOdabiri() != null) {
			for(OdabirDto odabirDto : pitanjeDto.getOdabiri()) {
				if(odabirDto.getTekst() == "") {
					odabirService.deleteOdabirByPitanjeIdAndSifra(odabirDto);
				}else {
					odabirDto.setPitanjeId(pitanjeDto.getPitanjeId());
					odabirService.updateOdabir(odabirDto);
				}
			}
		}
		response = new GenericHttpResponse<>(numberOfUpdateRows > 0 ? ResponseMessageEnum.ENTITY_UPDATED :ResponseMessageEnum.NOTHING_UPDATED);
		response.setData(numberOfUpdateRows);
		
		return response;		
	}

	/**
	 * Servisna metoda za brisanje pitanja
	 * @param id
	 * @return
	 */
	@Transactional
	public Long deletePitanje(PitanjeDto pitanjeDto) {
		Long response = pitanjeRepository.deletePitanje(pitanjeDto.getPitanjeId().toString());
		if(pitanjeDto.getOdabiri() != null) {
			for(OdabirDto odabirDto : pitanjeDto.getOdabiri()) {
				odabirService.deleteOdabir(odabirDto.getOdabirId().toString());
			}
		}
		
		return response;
	}

}

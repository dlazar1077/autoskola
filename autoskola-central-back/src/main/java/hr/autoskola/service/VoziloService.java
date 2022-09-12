package hr.autoskola.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import hr.autoskola.dto.mapper.VoziloMapper;
import hr.autoskola.dto.model.VoziloDto;
import hr.autoskola.dto.model.shared.GetAllEntitiesResponse;
import hr.autoskola.dto.model.shared.response.GenericHttpResponse;
import hr.autoskola.model.Vozilo;
import hr.autoskola.repository.VoziloRepository;
import hr.autoskola.utilities.response.ResponseMessageEnum;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class VoziloService {
	
	private final VoziloRepository voziloRepository;
	
	/**
	 * Servisna metoda za dohvaćanje svih vozila
	 * @param dataRequest
	 * @return
	 */
	public GetAllEntitiesResponse<VoziloDto> getAllEntities(){
		
		List<Vozilo> modelEntities=voziloRepository.getAllEntities();
		
		GetAllEntitiesResponse<VoziloDto> response = new GetAllEntitiesResponse<>();
		response.setTableData(modelEntities.stream().map(VoziloMapper::toVoziloDto).collect(Collectors.toList()));
		//response.setRowCount(rowCount);
		return response;
	}
	
	public GetAllEntitiesResponse<VoziloDto> getEntityByInstruktorId(String instruktorId){
		
		List<Vozilo> modelEntities=voziloRepository.getEntityByInstruktorId(instruktorId);
		
		GetAllEntitiesResponse<VoziloDto> response = new GetAllEntitiesResponse<>();
		response.setTableData(modelEntities.stream().map(VoziloMapper::toVoziloDto).collect(Collectors.toList()));
		return response;
	}
	
	/**
	 * Servisna metoda za dohvaćanje vozila po idu
	 * @param voziloId
	 * @return
	 */
	public VoziloDto getEntityById(String voziloId){
		
		Vozilo modelEntities=voziloRepository.getEntityById(voziloId);
		
		if(modelEntities != null) {

			VoziloDto response = VoziloMapper.toVoziloDto(modelEntities);
			
			return response;
		}
		
		return null;
	}
	
	
	
	/**
	 * Metoda za dodavanje vozila
	 * @param voziloDto
	 * @return
	 */
	public GenericHttpResponse<Long> saveVozilo(VoziloDto voziloDto) {
		GenericHttpResponse<Long> response;
		Long id = voziloRepository.saveVozilo(VoziloMapper.toVozilo(voziloDto));
		if(id > 0) {
			response = new GenericHttpResponse<>(ResponseMessageEnum.ENTITY_INSERTED);
			response.setData(id);
		}else {
			response = new GenericHttpResponse<>(ResponseMessageEnum.NOTHING_INSERTED);
		}
		return response;		
	}
	
	/**
	 * Servisna metoda za ažuriranje vozila
	 * @param voziloDto
	 * @return
	 */
	public GenericHttpResponse<Long> updateVozilo(VoziloDto voziloDto) {
		GenericHttpResponse<Long> response;
		Long numberOfUpdateRows;
		
		numberOfUpdateRows = voziloRepository.updateVozilo(VoziloMapper.toVozilo(voziloDto));
		response = new GenericHttpResponse<>(numberOfUpdateRows > 0 ? ResponseMessageEnum.ENTITY_UPDATED :ResponseMessageEnum.NOTHING_UPDATED);
		response.setData(numberOfUpdateRows);
		
		return response;		
	}

	/**
	 * Servisna metoda za brisanje vozila
	 * @param id
	 * @return
	 */
	public Long deleteVozilo(String id) {
		return voziloRepository.deleteVozilo(id);
	}

}

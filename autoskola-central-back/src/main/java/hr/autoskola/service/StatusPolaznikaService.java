package hr.autoskola.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import hr.autoskola.dto.mapper.StatusPolaznikaMapper;
import hr.autoskola.dto.model.StatusPolaznikaDto;
import hr.autoskola.dto.model.shared.GetAllEntitiesResponse;
import hr.autoskola.dto.model.shared.response.GenericHttpResponse;
import hr.autoskola.model.StatusPolaznika;
import hr.autoskola.repository.StatusPolaznikaRepository;
import hr.autoskola.utilities.codebooks.service.CodebookService;
import hr.autoskola.utilities.response.ResponseMessageEnum;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StatusPolaznikaService {
	
	private final StatusPolaznikaRepository statusPolaznikaRepository;
	private final CodebookService codebookService;
	
	/**
	 * Servisna metoda za dohvaćanje svih statusa polaznika
	 * @param 
	 * @return
	 */
	public GetAllEntitiesResponse<StatusPolaznikaDto> getAllEntities(){
		
		List<StatusPolaznika> modelEntities=statusPolaznikaRepository.getAllEntities();
		
		GetAllEntitiesResponse<StatusPolaznikaDto> response = new GetAllEntitiesResponse<>();
		response.setTableData(modelEntities.stream().map(StatusPolaznikaMapper::toStatusPolaznikaDto).collect(Collectors.toList())); 
		return response;
	}
	
	/**
	 * Metoda za dodavanje statusa polaznika
	 * @param statusPolaznikaDto
	 * @return
	 */
	public GenericHttpResponse<Long> saveStatusPolaznika(StatusPolaznikaDto statusPolaznikaDto) {
		try {
			GenericHttpResponse<Long> response;
			Long id = statusPolaznikaRepository.saveStatusPolaznika(StatusPolaznikaMapper.toStatusPolaznika(statusPolaznikaDto));
			if(id > 0) {
				codebookService.refreshCodebooks();
				response = new GenericHttpResponse<>(ResponseMessageEnum.ENTITY_INSERTED);
				response.setData(id);
			}else {
				response = new GenericHttpResponse<>(ResponseMessageEnum.NOTHING_INSERTED);
			}
			return response;	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new GenericHttpResponse<Long>();
	}
	
	/**
	 * Servisna metoda za ažuriranje statusa polaznika
	 * @param statusPolaznikaDto
	 * @return
	 */
	public GenericHttpResponse<Long> updateStatusPolaznika(StatusPolaznikaDto statusPolaznikaDto) {
		try {
			GenericHttpResponse<Long> response;
			Long numberOfUpdateRows;
			
			numberOfUpdateRows = statusPolaznikaRepository.updateStatusPolaznika(StatusPolaznikaMapper.toStatusPolaznika(statusPolaznikaDto));
			codebookService.refreshCodebooks();
			response = new GenericHttpResponse<>(numberOfUpdateRows > 0 ? ResponseMessageEnum.ENTITY_UPDATED :ResponseMessageEnum.NOTHING_UPDATED);
			response.setData(numberOfUpdateRows);
			
			return response;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new GenericHttpResponse<Long>();
	}

	/**
	 * Servisna metoda za brisanje statusa polaznika
	 * @param id
	 * @return
	 */
	public Long deleteStatusPolaznika(String id) {
		Long response = (long) 0;
		try {
			response = statusPolaznikaRepository.deleteStatusPolaznika(id);
			codebookService.refreshCodebooks();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

}

package hr.autoskola.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import hr.autoskola.dto.mapper.UlogaMapper;
import hr.autoskola.dto.mapper.filter.FilterValueMapper;
import hr.autoskola.dto.model.UlogaDto;
import hr.autoskola.dto.model.shared.GetAllEntitiesRequest;
import hr.autoskola.dto.model.shared.GetAllEntitiesResponse;
import hr.autoskola.dto.model.shared.response.GenericHttpResponse;
import hr.autoskola.model.FilterValue;
import hr.autoskola.model.Uloga;
import hr.autoskola.repository.UlogaRepository;
import hr.autoskola.utilities.codebooks.service.CodebookService;
import hr.autoskola.utilities.response.ResponseMessageEnum;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UlogaService {
	
	private final UlogaRepository ulogaRepository;
	private final CodebookService codebookService;
	
	/**
	 * Servisna metoda za dohvaćanje svih uloga
	 * @param dataRequest
	 * @return
	 */
	public GetAllEntitiesResponse<UlogaDto> getAllEntities(){
		/*Map<String, FilterValue> filterColumns = dataRequest.getFilterColumns() == null ? null :
			dataRequest.getFilterColumns().entrySet().stream()
		    .collect(Collectors.toMap(Map.Entry::getKey, e -> FilterValueMapper.toFilterValue(e.getValue())));

		List<Uloga> modelEntities=ulogaRepository.getAllEntities(filterColumns, dataRequest.getGlobalSearchString(), 
				dataRequest.getCurrentPage(), dataRequest.getPageSize(), dataRequest.getSortColumnName(), dataRequest.getSortDirection());
		Long rowCount = ulogaRepository.getRowCount(dataRequest.getParentId(), filterColumns, dataRequest.getGlobalSearchString());*/
		
		List<Uloga> modelEntities=ulogaRepository.getAllEntities();
		
		GetAllEntitiesResponse<UlogaDto> response = new GetAllEntitiesResponse<>();
		response.setTableData(modelEntities.stream().map(UlogaMapper::toUlogaDto).collect(Collectors.toList()));
		//response.setRowCount(rowCount);
		return response;
	}
	
	/**
	 * Metoda za dodavanje uloge
	 * @param ulogaDto
	 * @return
	 */
	public GenericHttpResponse<Long> saveUloga(UlogaDto ulogaDto) {
		try {
			GenericHttpResponse<Long> response;
			Long id = ulogaRepository.saveUloga(UlogaMapper.toUloga(ulogaDto));
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
	 * Servisna metoda za ažuriranje uloge
	 * @param ulogaDto
	 * @return
	 */
	public GenericHttpResponse<Long> updateUloga(UlogaDto ulogaDto) {
		try {
			GenericHttpResponse<Long> response;
			Long numberOfUpdateRows;
			
			numberOfUpdateRows = ulogaRepository.updateUloga(UlogaMapper.toUloga(ulogaDto));
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
	 * Servisna metoda za brisanje uloge
	 * @param id
	 * @return
	 */
	public Long deleteUloge(String id) {
		Long response = (long) 0;
		try {
			response = ulogaRepository.deleteUloge(id);
			codebookService.refreshCodebooks();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

}

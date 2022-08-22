package hr.autoskola.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import hr.autoskola.dto.mapper.VoziloMapper;
import hr.autoskola.dto.mapper.filter.FilterValueMapper;
import hr.autoskola.dto.model.VoziloDto;
import hr.autoskola.dto.model.shared.GetAllEntitiesRequest;
import hr.autoskola.dto.model.shared.GetAllEntitiesResponse;
import hr.autoskola.dto.model.shared.response.GenericHttpResponse;
import hr.autoskola.model.FilterValue;
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
	public GetAllEntitiesResponse<VoziloDto> getAllEntities(GetAllEntitiesRequest dataRequest){
		Map<String, FilterValue> filterColumns = dataRequest.getFilterColumns() == null ? null :
			dataRequest.getFilterColumns().entrySet().stream()
		    .collect(Collectors.toMap(Map.Entry::getKey, e -> FilterValueMapper.toFilterValue(e.getValue())));

		List<Vozilo> modelEntities=voziloRepository.getAllEntities(filterColumns, dataRequest.getGlobalSearchString(), 
				dataRequest.getCurrentPage(), dataRequest.getPageSize(), dataRequest.getSortColumnName(), dataRequest.getSortDirection());
		Long rowCount = voziloRepository.getRowCount(dataRequest.getParentId(), filterColumns, dataRequest.getGlobalSearchString());
		
		GetAllEntitiesResponse<VoziloDto> response = new GetAllEntitiesResponse<>();
		response.setTableData(modelEntities.stream().map(VoziloMapper::toVoziloDto).collect(Collectors.toList()));
		response.setRowCount(rowCount);
		return response;
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
	 * @param executorDto
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

package hr.autoskola.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import hr.autoskola.dto.mapper.KategorijaMapper;
import hr.autoskola.dto.mapper.filter.FilterValueMapper;
import hr.autoskola.dto.model.KategorijaDto;
import hr.autoskola.dto.model.shared.GetAllEntitiesRequest;
import hr.autoskola.dto.model.shared.GetAllEntitiesResponse;
import hr.autoskola.dto.model.shared.response.GenericHttpResponse;
import hr.autoskola.model.FilterValue;
import hr.autoskola.model.Kategorija;
import hr.autoskola.repository.KategorijaRepository;
import hr.autoskola.utilities.response.ResponseMessageEnum;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class KategorijaService {

	private final KategorijaRepository kategorijaRepository;
	
	/**
	 * Servisna metoda za dohvaćanje svih kategorija
	 * @param dataRequest
	 * @return
	 */
	public GetAllEntitiesResponse<KategorijaDto> getAllEntities(){
		/*Map<String, FilterValue> filterColumns = dataRequest.getFilterColumns() == null ? null :
			dataRequest.getFilterColumns().entrySet().stream()
		    .collect(Collectors.toMap(Map.Entry::getKey, e -> FilterValueMapper.toFilterValue(e.getValue())));*/

		/*List<Kategorija> modelEntities=kategorijaRepository.getAllEntities(filterColumns, dataRequest.getGlobalSearchString(), 
				dataRequest.getCurrentPage(), dataRequest.getPageSize(), dataRequest.getSortColumnName(), dataRequest.getSortDirection());
		Long rowCount = kategorijaRepository.getRowCount(dataRequest.getParentId(), filterColumns, dataRequest.getGlobalSearchString());*/
		List<Kategorija> modelEntities=kategorijaRepository.getAllEntities();
		GetAllEntitiesResponse<KategorijaDto> response = new GetAllEntitiesResponse<>();
		response.setTableData(modelEntities.stream().map(KategorijaMapper::toKategorijaDto).collect(Collectors.toList()));
		//response.setRowCount(rowCount);
		return response;
	}
	
	/**
	 * Metoda za dodavanje kategorije
	 * @param kategorijaDto
	 * @return
	 */
	public GenericHttpResponse<Long> saveKategorija(KategorijaDto kategorijaDto) {
		GenericHttpResponse<Long> response;
		Long id = kategorijaRepository.saveKategorija(KategorijaMapper.toKategorija(kategorijaDto));
		if(id > 0) {
			response = new GenericHttpResponse<>(ResponseMessageEnum.ENTITY_INSERTED);
			response.setData(id);
		}else {
			response = new GenericHttpResponse<>(ResponseMessageEnum.NOTHING_INSERTED);
		}
		return response;		
	}
	
	/**
	 * Servisna metoda za ažuriranje kategorije
	 * @param kategorijaDto
	 * @return
	 */
	public GenericHttpResponse<Long> updateKategorija(KategorijaDto kategorijaDto) {
		GenericHttpResponse<Long> response;
		Long numberOfUpdateRows;
		
		numberOfUpdateRows = kategorijaRepository.updateKategorija(KategorijaMapper.toKategorija(kategorijaDto));
		response = new GenericHttpResponse<>(numberOfUpdateRows > 0 ? ResponseMessageEnum.ENTITY_UPDATED :ResponseMessageEnum.NOTHING_UPDATED);
		response.setData(numberOfUpdateRows);
		
		return response;		
	}

	/**
	 * Servisna metoda za brisanje kategorije
	 * @param id
	 * @return
	 */
	public Long deleteKategorija(String id) {
		return kategorijaRepository.deleteKategorija(id);
	}
}

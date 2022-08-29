package hr.autoskola.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import hr.autoskola.dto.mapper.AutoskolaInfoMapper;
import hr.autoskola.dto.model.AutoskolaInfoDto;
import hr.autoskola.dto.model.shared.response.GenericHttpResponse;
import hr.autoskola.model.AutoskolaInfo;
import hr.autoskola.repository.AutoskolaInfoRepository;
import hr.autoskola.utilities.response.ResponseMessageEnum;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AutoskolaInfoService {
	
	
	private final AutoskolaInfoRepository autoskolaInfoRepository;
	
	/**
	 * Servisna metoda za dohvaćanje svih informacija o autoskoli
	 * @param 
	 * @return
	 */
	public List<AutoskolaInfoDto> getAllEntities(){
		
		List<AutoskolaInfo> modelEntities=autoskolaInfoRepository.getAllEntities();
		
		List<AutoskolaInfoDto> response = modelEntities.stream().map(AutoskolaInfoMapper::toAutoskolaInfoDto).collect(Collectors.toList());
		
		return response;
	}
	
	/**
	 * Metoda za dodavanje informacija o autoskoli
	 * @param autoskolaInfoDto
	 * @return
	 */
	public GenericHttpResponse<Long> saveAutoskolaInfo(AutoskolaInfoDto autoskolaInfoDto) {
		GenericHttpResponse<Long> response;
		Long id = autoskolaInfoRepository.saveAutoskolaInfo(AutoskolaInfoMapper.toAutoskolaInfo(autoskolaInfoDto));
		if(id > 0) {
			response = new GenericHttpResponse<>(ResponseMessageEnum.ENTITY_INSERTED);
			response.setData(id);
		}else {
			response = new GenericHttpResponse<>(ResponseMessageEnum.NOTHING_INSERTED);
		}
		return response;		
	}
	
	/**
	 * Servisna metoda za ažuriranje informacija o autoskoli
	 * @param autoskolaInfoDto
	 * @return
	 */
	public GenericHttpResponse<Long> updateAutoskolaInfo(AutoskolaInfoDto autoskolaInfoDto) {
		GenericHttpResponse<Long> response;
		Long numberOfUpdateRows;
		
		numberOfUpdateRows = autoskolaInfoRepository.updateAutoskolaInfo(AutoskolaInfoMapper.toAutoskolaInfo(autoskolaInfoDto));
		response = new GenericHttpResponse<>(numberOfUpdateRows > 0 ? ResponseMessageEnum.ENTITY_UPDATED :ResponseMessageEnum.NOTHING_UPDATED);
		response.setData(numberOfUpdateRows);
		
		return response;		
	}

	/**
	 * Servisna metoda za brisanje informacije o autoskoli
	 * @param id
	 * @return
	 */
	public Long deleteAutoskolaInfo(String id) {
		return autoskolaInfoRepository.deleteAutoskolaInfo(id);
	}

}

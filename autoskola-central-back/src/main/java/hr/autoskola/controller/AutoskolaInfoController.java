package hr.autoskola.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import hr.autoskola.dto.model.AutoskolaInfoDto;
import hr.autoskola.dto.model.shared.response.GenericHttpResponse;
import hr.autoskola.service.AutoskolaInfoService;
import hr.autoskola.utilities.response.ResponseMessageEnum;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class AutoskolaInfoController {
	
	private final AutoskolaInfoService autoskolaInfoService;
	
	@GetMapping(value = "/api/autoskolaInfo")
	public ResponseEntity<List<AutoskolaInfoDto>> getAutoskolaInfo() {
		List<AutoskolaInfoDto> autoskolaInfo = autoskolaInfoService.getAllEntities();
		return new ResponseEntity<>(autoskolaInfo, HttpStatus.OK);
	}
	
	@PostMapping(value="/api/insertAutoskolaInfo")
	public GenericHttpResponse<Long> saveAutoskolaInfo(@RequestBody AutoskolaInfoDto autoskolaInfoDto) {
		return autoskolaInfoService.saveAutoskolaInfo(autoskolaInfoDto);
	}
	
	@PutMapping(value="/api/updateAutoskolaInfo")
	public GenericHttpResponse<Long> updateAutoskolaInfo(@RequestBody AutoskolaInfoDto autoskolaInfoDto) {
		return autoskolaInfoService.updateAutoskolaInfo(autoskolaInfoDto);
	}
	
	@PutMapping(value="/api/deleteAutoskolaInfo")
	public GenericHttpResponse<Long> deleteAutoskolaInfo(@RequestBody AutoskolaInfoDto autoskolaInfoDto) {
		Long numberOfUpdatedEntities = autoskolaInfoService.deleteAutoskolaInfo(String.valueOf(autoskolaInfoDto.getAutoskolaInfoId()));
		GenericHttpResponse<Long> result = new GenericHttpResponse<>(numberOfUpdatedEntities > 0 ? ResponseMessageEnum.ENTITY_UPDATED : ResponseMessageEnum.NOTHING_UPDATED);
		result.setData(numberOfUpdatedEntities);
		return result;
	}

}

package hr.autoskola.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import hr.autoskola.dto.model.VoziloDto;
import hr.autoskola.dto.model.shared.GetAllEntitiesRequest;
import hr.autoskola.dto.model.shared.GetAllEntitiesResponse;
import hr.autoskola.dto.model.shared.response.GenericHttpResponse;
import hr.autoskola.service.VoziloService;
import hr.autoskola.utilities.response.ResponseMessageEnum;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class VoziloController {

	private final VoziloService voziloService;
	
	@PostMapping(value = "/api/test/vozila")
	public ResponseEntity<GetAllEntitiesResponse<VoziloDto>> getExecutors(@RequestBody GetAllEntitiesRequest dataRequest) {
		GetAllEntitiesResponse<VoziloDto> executors = voziloService.getAllEntities(dataRequest);
		return new ResponseEntity<>(executors, HttpStatus.OK);
	}
	
	@PostMapping(value="/api/test/insertVozilo")
	public GenericHttpResponse<Long> saveVozilo(@RequestBody VoziloDto voziloDto) {
		return voziloService.saveVozilo(voziloDto);
	}
	
	@PostMapping(value="/api/test/updateVozilo")
	public GenericHttpResponse<Long> updateVozilo(@RequestBody VoziloDto voziloDto) {
		return voziloService.updateVozilo(voziloDto);
	}
	
	@PostMapping(value="/api/test/deleteVozilo")
	public GenericHttpResponse<Long> deleteVozilo(@RequestBody VoziloDto voziloDto, @RequestHeader HttpHeaders headers) {
		Long numberOfUpdatedEntities = voziloService.deleteVozilo(voziloDto.getVoziloId().toString());
		GenericHttpResponse<Long> result = new GenericHttpResponse<>(numberOfUpdatedEntities > 0 ? ResponseMessageEnum.ENTITY_UPDATED : ResponseMessageEnum.NOTHING_UPDATED);
		result.setData(numberOfUpdatedEntities);
		return result;
	}
}

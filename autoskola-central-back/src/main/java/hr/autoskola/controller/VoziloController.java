package hr.autoskola.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import hr.autoskola.dto.model.VoziloDto;
import hr.autoskola.dto.model.shared.GetAllEntitiesResponse;
import hr.autoskola.dto.model.shared.response.GenericHttpResponse;
import hr.autoskola.service.VoziloService;
import hr.autoskola.utilities.response.ResponseMessageEnum;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class VoziloController {

	private final VoziloService voziloService;
	
	@GetMapping(value = "/api/vozila")
	public ResponseEntity<GetAllEntitiesResponse<VoziloDto>> getVozila() {
		GetAllEntitiesResponse<VoziloDto> vozila = voziloService.getAllEntities();
		return new ResponseEntity<>(vozila, HttpStatus.OK);
	}
	
	@GetMapping(value = "/api/vozilaInstruktora")
	public ResponseEntity<GetAllEntitiesResponse<VoziloDto>> getEntityByInstruktorId(@RequestParam("id") String id) {
		GetAllEntitiesResponse<VoziloDto> vozila = voziloService.getEntityByInstruktorId(id);
		return new ResponseEntity<>(vozila, HttpStatus.OK);
	}
	
	@PostMapping(value="/api/insertVozilo")
	public GenericHttpResponse<Long> saveVozilo(@RequestBody VoziloDto voziloDto) {
		return voziloService.saveVozilo(voziloDto);
	}
	
	@PutMapping(value="/api/updateVozilo")
	public GenericHttpResponse<Long> updateVozilo(@RequestBody VoziloDto voziloDto) {
		return voziloService.updateVozilo(voziloDto);
	}
	
	@PutMapping(value="/api/deleteVozilo")
	public GenericHttpResponse<Long> deleteVozilo(@RequestBody VoziloDto voziloDto, @RequestHeader HttpHeaders headers) {
		Long numberOfUpdatedEntities = voziloService.deleteVozilo(voziloDto.getVoziloId().toString());
		GenericHttpResponse<Long> result = new GenericHttpResponse<>(numberOfUpdatedEntities > 0 ? ResponseMessageEnum.ENTITY_UPDATED : ResponseMessageEnum.NOTHING_UPDATED);
		result.setData(numberOfUpdatedEntities);
		return result;
	}
}

package hr.autoskola.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import hr.autoskola.dto.model.UlogaDto;
import hr.autoskola.dto.model.shared.GetAllEntitiesRequest;
import hr.autoskola.dto.model.shared.GetAllEntitiesResponse;
import hr.autoskola.dto.model.shared.response.GenericHttpResponse;
import hr.autoskola.service.UlogaService;
import hr.autoskola.utilities.response.ResponseMessageEnum;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class UlogaController {
	
	private final UlogaService ulogaService;
	
	@GetMapping(value = "/api/uloge")
	public ResponseEntity<GetAllEntitiesResponse<UlogaDto>> getUloge() {
		GetAllEntitiesResponse<UlogaDto> uloge = ulogaService.getAllEntities();
		return new ResponseEntity<>(uloge, HttpStatus.OK);
	}
	
	@PostMapping(value="/api/insertUloge")
	public GenericHttpResponse<Long> saveUloga(@RequestBody UlogaDto ulogaDto) {
		return ulogaService.saveUloga(ulogaDto);
	}
	
	@PutMapping(value="/api/updateUloge")
	public GenericHttpResponse<Long> updateUloga(@RequestBody UlogaDto ulogaDto) {
		return ulogaService.updateUloga(ulogaDto);
	}
	
	@PutMapping(value="/api/deleteUloge")
	public GenericHttpResponse<Long> deleteUloge(@RequestBody UlogaDto ulogaDto, @RequestHeader HttpHeaders headers) {
		Long numberOfUpdatedEntities = ulogaService.deleteUloge(String.valueOf(ulogaDto.getUlogaId()));
		GenericHttpResponse<Long> result = new GenericHttpResponse<>(numberOfUpdatedEntities > 0 ? ResponseMessageEnum.ENTITY_UPDATED : ResponseMessageEnum.NOTHING_UPDATED);
		result.setData(numberOfUpdatedEntities);
		return result;
	}

}

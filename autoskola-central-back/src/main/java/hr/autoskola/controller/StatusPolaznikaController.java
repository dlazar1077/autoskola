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

import hr.autoskola.dto.model.StatusPolaznikaDto;
import hr.autoskola.dto.model.shared.GetAllEntitiesResponse;
import hr.autoskola.dto.model.shared.response.GenericHttpResponse;
import hr.autoskola.service.StatusPolaznikaService;
import hr.autoskola.utilities.response.ResponseMessageEnum;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class StatusPolaznikaController {
	
	private final StatusPolaznikaService statusPolaznikaService;
	
	@GetMapping(value = "/api/statusiPolaznika")
	public ResponseEntity<GetAllEntitiesResponse<StatusPolaznikaDto>> getStatusPolaznika() {
		GetAllEntitiesResponse<StatusPolaznikaDto> uloge = statusPolaznikaService.getAllEntities();
		return new ResponseEntity<>(uloge, HttpStatus.OK);
	}
	
	@PostMapping(value="/api/insertStatusPolaznika")
	public GenericHttpResponse<Long> saveStatusPolaznika(@RequestBody StatusPolaznikaDto statusPolaznikaDto) {
		return statusPolaznikaService.saveStatusPolaznika(statusPolaznikaDto);
	}
	
	@PutMapping(value="/api/updateStatusPolaznika")
	public GenericHttpResponse<Long> updateStatusPolaznika(@RequestBody StatusPolaznikaDto statusPolaznikaDto) {
		return statusPolaznikaService.updateStatusPolaznika(statusPolaznikaDto);
	}
	
	@PutMapping(value="/api/deleteStatusPolaznika")
	public GenericHttpResponse<Long> deleteStatusPolaznika(@RequestBody StatusPolaznikaDto statusPolaznikaDto, @RequestHeader HttpHeaders headers) {
		Long numberOfUpdatedEntities = statusPolaznikaService.deleteStatusPolaznika(String.valueOf(statusPolaznikaDto.getStatusPolaznikaId()));
		GenericHttpResponse<Long> result = new GenericHttpResponse<>(numberOfUpdatedEntities > 0 ? ResponseMessageEnum.ENTITY_UPDATED : ResponseMessageEnum.NOTHING_UPDATED);
		result.setData(numberOfUpdatedEntities);
		return result;
	}

}

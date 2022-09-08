package hr.autoskola.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import hr.autoskola.dto.model.OdabirDto;
import hr.autoskola.dto.model.shared.response.GenericHttpResponse;
import hr.autoskola.service.OdabirService;
import hr.autoskola.utilities.response.ResponseMessageEnum;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class OdabirController {
	
	private final OdabirService odabirService;
	
	@PostMapping(value="/api/insertOdabir")
	public GenericHttpResponse<Long> saveOdabir(@RequestBody OdabirDto odabirDto) {
		return odabirService.saveOdabir(odabirDto);
	}
	
	@PutMapping(value="/api/deleteOdabir")
	public GenericHttpResponse<Long> deleteOdabir(@RequestBody OdabirDto odabirDto) {
		Long numberOfUpdatedEntities = odabirService.deleteOdabir(odabirDto.getOdabirId().toString());
		GenericHttpResponse<Long> result = new GenericHttpResponse<>(numberOfUpdatedEntities > 0 ? ResponseMessageEnum.ENTITY_UPDATED : ResponseMessageEnum.NOTHING_UPDATED);
		result.setData(numberOfUpdatedEntities);
		return result;
	}
	
	@DeleteMapping(value="/api/deleteAllOdabir")
	public GenericHttpResponse<Long> deleteAllOdabir(@RequestBody OdabirDto[] odabiriDto) {
		Long numberOfUpdatedEntities = odabirService.deleteFroeverOdabir(odabiriDto);
		GenericHttpResponse<Long> result = new GenericHttpResponse<>(numberOfUpdatedEntities > 0 ? ResponseMessageEnum.ENTITY_UPDATED : ResponseMessageEnum.NOTHING_UPDATED);
		result.setData(numberOfUpdatedEntities);
		return result;
	}

}

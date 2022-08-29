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

import hr.autoskola.dto.model.KategorijaDto;
import hr.autoskola.dto.model.shared.GetAllEntitiesRequest;
import hr.autoskola.dto.model.shared.GetAllEntitiesResponse;
import hr.autoskola.dto.model.shared.response.GenericHttpResponse;
import hr.autoskola.service.KategorijaService;
import hr.autoskola.utilities.response.ResponseMessageEnum;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class KategorijaController {
	
	private final KategorijaService kategorijaService;
	
	@GetMapping(value = "/api/kategorije")
	public ResponseEntity<GetAllEntitiesResponse<KategorijaDto>> getKategorije() {
		GetAllEntitiesResponse<KategorijaDto> kategorije = kategorijaService.getAllEntities();
		return new ResponseEntity<>(kategorije, HttpStatus.OK);
	}
	
	@PostMapping(value="/api/insertKategorija")
	public GenericHttpResponse<Long> saveKategorija(@RequestBody KategorijaDto kategorijaDto) {
		return kategorijaService.saveKategorija(kategorijaDto);
	}
	
	@PutMapping(value="/api/updateKategorija")
	public GenericHttpResponse<Long> updateKategorija(@RequestBody KategorijaDto kategorijaDto) {
		return kategorijaService.updateKategorija(kategorijaDto);
	}
	
	@PutMapping(value="/api/deleteKategorija")
	public GenericHttpResponse<Long> deleteKategorija(@RequestBody KategorijaDto kategorijaDto) {
		Long numberOfUpdatedEntities = kategorijaService.deleteKategorija(String.valueOf(kategorijaDto.getKategorijaId()));
		GenericHttpResponse<Long> result = new GenericHttpResponse<>(numberOfUpdatedEntities > 0 ? ResponseMessageEnum.ENTITY_UPDATED : ResponseMessageEnum.NOTHING_UPDATED);
		result.setData(numberOfUpdatedEntities);
		return result;
	}

}

package hr.autoskola.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import hr.autoskola.dto.model.SatDto;
import hr.autoskola.dto.model.shared.response.GenericHttpResponse;
import hr.autoskola.service.SatService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class SatController {
	
	private final SatService satService;
	
	@GetMapping(value = "/api/satoviPolaznika")
	public ResponseEntity<List<SatDto>> getEntityByKorisnikId(@RequestParam("id") String id) {
		List<SatDto> satDto = satService.getEntitiesByPolaznikId(id);
		return new ResponseEntity<>(satDto, HttpStatus.OK);
	}
	
	@PostMapping(value="/api/insertSatVoznje")
	public GenericHttpResponse<Long> saveSatVoznje(@RequestBody SatDto satDto) {
		return satService.saveSatVoznje(satDto);
	}
	
	@PutMapping(value="/api/updateSatVoznje")
	public GenericHttpResponse<Long> updateSatVoznje(@RequestBody SatDto satDto) {
		return satService.updateSatVoznje(satDto);
	}
	
	@PutMapping(value="/api/deleteSatVoznje")
	public GenericHttpResponse<Long> deleteSatVoznje(@RequestBody SatDto satDto) {
		return satService.deleteSatVoznje(satDto);
	}

}

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

import hr.autoskola.dto.model.PolaznikDto;
import hr.autoskola.dto.model.shared.response.GenericHttpResponse;
import hr.autoskola.service.PolaznikService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class PolaznikController {
	
	private final PolaznikService polaznikService;
	
	@GetMapping(value = "/api/polaznici")
	public ResponseEntity<List<PolaznikDto>> getAllEntities() {
		List<PolaznikDto> polaznici = polaznikService.getAllEntities();
		return new ResponseEntity<>(polaznici, HttpStatus.OK);
	}
	
	@GetMapping(value = "/api/polaznik")
	public ResponseEntity<PolaznikDto> getEntityByKorisnikId(@RequestParam("id") String id) {
		PolaznikDto polaznik = polaznikService.getEntityByKorisnikId(id);
		return new ResponseEntity<>(polaznik, HttpStatus.OK);
	}
	
	@GetMapping(value = "/api/polazniciInstruktora")
	public ResponseEntity<List<PolaznikDto>> getEntitiesByInstruktorId(@RequestParam("id") String id) {
		List<PolaznikDto> polaznici = polaznikService.getEntitiesByInstruktorId(id);
		return new ResponseEntity<>(polaznici, HttpStatus.OK);
	}
	
	@PostMapping(value="/api/insertPolaznik")
	public GenericHttpResponse<Long> insertPolaznik(@RequestBody PolaznikDto polaznikDto) {
		return polaznikService.savePolaznik(polaznikDto);
	}
	
	@PutMapping(value="/api/updatePolaznik")
	public GenericHttpResponse<Long> updatePolaznik(@RequestBody PolaznikDto polaznikDto) {
		return polaznikService.updatePolaznik(polaznikDto);
	}
	
	@PutMapping(value="/api/deletePolaznik")
	public GenericHttpResponse<Long> deletePolaznik(@RequestBody PolaznikDto polaznikDto) {
		return polaznikService.deletePolaznik(polaznikDto);
	}

}

package hr.autoskola.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import hr.autoskola.dto.model.IspitDto;
import hr.autoskola.dto.model.shared.response.GenericHttpResponse;
import hr.autoskola.service.IspitService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class IspitController {
	
	private final IspitService ispitService;	
	
	@GetMapping(value = "/api/ispitiKorisnika")
	public ResponseEntity<List<IspitDto>> getAllEntitiesByKorisnikId(@RequestParam("id") String korisnikId) {
		List<IspitDto> ispiti = ispitService.getAllEntitiesByKorisnikId(korisnikId);
		return new ResponseEntity<>(ispiti, HttpStatus.OK);
	}
	
	@GetMapping(value = "/api/ispitById")
	public ResponseEntity<IspitDto> getAllEntitiesByIspitId(@RequestParam("id") String ispitId) {
		IspitDto ispiti = ispitService.getAllEntitiesByIspitId(ispitId);
		return new ResponseEntity<>(ispiti, HttpStatus.OK);
	}
	
	@PostMapping(value="/api/insertIspit")
	public GenericHttpResponse<Long> saveIspit(@RequestBody IspitDto ispitDto) {
		return ispitService.saveIspit(ispitDto);
	}

}

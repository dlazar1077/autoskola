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

import hr.autoskola.dto.model.InstruktorDto;
import hr.autoskola.dto.model.shared.response.GenericHttpResponse;
import hr.autoskola.service.InstruktorService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class InstruktorController {

	private final InstruktorService instruktorService;
	
	@GetMapping(value = "/api/instruktori")
	public ResponseEntity<List<InstruktorDto>> getAllEntities() {
		List<InstruktorDto> instruktori = instruktorService.getAllEntities();
		return new ResponseEntity<>(instruktori, HttpStatus.OK);
	}
	
	@GetMapping(value = "/api/instruktor")
	public ResponseEntity<InstruktorDto> getEntityByKorisnikId(@RequestParam("id") String id) {
		InstruktorDto instruktori = instruktorService.getEntityByKorisnikId(id);
		return new ResponseEntity<>(instruktori, HttpStatus.OK);
	}
	
	@GetMapping(value = "/api/slobodniInstruktori")
	public ResponseEntity<List<InstruktorDto>> getAvailableInstructors(@RequestParam("id") String id) {
		List<InstruktorDto> instruktori = instruktorService.getAvailableInstructors(id);
		return new ResponseEntity<>(instruktori, HttpStatus.OK);
	}
	
	@PostMapping(value="/api/insertInstruktor")
	public GenericHttpResponse<Long> saveInstruktor(@RequestBody InstruktorDto instruktorDto) {
		return instruktorService.saveInstruktor(instruktorDto);
	}
	
	@PutMapping(value="/api/updateInstruktor")
	public GenericHttpResponse<Long> updateInstruktor(@RequestBody InstruktorDto instruktorDto) {
		return instruktorService.updateInstruktor(instruktorDto);
	}
	
	@PutMapping(value="/api/deleteInstruktor")
	public GenericHttpResponse<Long> deleteInstruktor(@RequestBody InstruktorDto instruktorDto) {
		return instruktorService.deleteInstruktor(instruktorDto);
	}
	
	
}

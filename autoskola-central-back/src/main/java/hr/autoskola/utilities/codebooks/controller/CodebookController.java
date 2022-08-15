package hr.autoskola.utilities.codebooks.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import hr.autoskola.utilities.codebooks.dto.model.CodebookDto;
import hr.autoskola.utilities.codebooks.service.CodebookService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class CodebookController {
	
	private final CodebookService service;
	
	@GetMapping(value = "/api/getCodebooks")
	public ResponseEntity<Map<String, List<CodebookDto>>> getEntity(@RequestParam(name = "names") List<String> names) {
		return new ResponseEntity<>(service.getCodebooks(names), HttpStatus.OK);
	}

	@GetMapping(value = "/api/refreshCodebooks")
	public ResponseEntity<Void> refreshCodebooks() {
		try {
			service.refreshCodebooks();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

}

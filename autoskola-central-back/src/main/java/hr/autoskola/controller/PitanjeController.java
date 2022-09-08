package hr.autoskola.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import hr.autoskola.dto.model.PitanjeDto;
import hr.autoskola.dto.model.shared.response.GenericHttpResponse;
import hr.autoskola.service.PitanjeService;
import hr.autoskola.utilities.response.ResponseMessageEnum;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class PitanjeController {
	
	private final PitanjeService pitanjeService;
	
	@GetMapping(value = "/api/pitanja")
	public ResponseEntity<List<PitanjeDto>> getAutoskolaInfo() {
		List<PitanjeDto> pitanja = pitanjeService.getAllEntities();
		return new ResponseEntity<>(pitanja, HttpStatus.OK);
	}
	
	@GetMapping(value = "/api/generirajIspit")
	public ResponseEntity<List<PitanjeDto>> getIspit() {
		List<PitanjeDto> pitanja = pitanjeService.getIspit();
		return new ResponseEntity<>(pitanja, HttpStatus.OK);
	}
	
	@PostMapping(value="/api/insertPitanje")
	public GenericHttpResponse<Long> savePitanje(@RequestBody PitanjeDto pitanjeDto) {
		return pitanjeService.savePitanje(pitanjeDto);
	}
	
	@PutMapping(value="/api/updatePitanje")
	public GenericHttpResponse<Long> updatePitanje(@RequestBody PitanjeDto pitanjeDto) {
		return pitanjeService.updatePitanje(pitanjeDto);
	}
	
	@PutMapping(value="/api/deletePitanje")
	public GenericHttpResponse<Long> deletePitanje(@RequestBody PitanjeDto pitanjeDto) {
		Long numberOfUpdatedEntities = pitanjeService.deletePitanje(pitanjeDto);
		GenericHttpResponse<Long> result = new GenericHttpResponse<>(numberOfUpdatedEntities > 0 ? ResponseMessageEnum.ENTITY_UPDATED : ResponseMessageEnum.NOTHING_UPDATED);
		result.setData(numberOfUpdatedEntities);
		return result;
	}

}

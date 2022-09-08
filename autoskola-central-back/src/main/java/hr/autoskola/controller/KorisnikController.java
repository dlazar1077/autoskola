package hr.autoskola.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import hr.autoskola.dto.model.KorisnikDto;
import hr.autoskola.dto.model.shared.response.GenericHttpResponse;
import hr.autoskola.service.KorisnikService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class KorisnikController {

	private final KorisnikService korisnikService;
	
	@GetMapping(value = "/api/korisnik")
	public KorisnikDto getKorisnikById(@RequestParam("id") String id) {
		KorisnikDto korisnik = korisnikService.getEntityByKorisnikId(id);
		return korisnik;
	}
	
	@PutMapping(value="/api/updateKorisnik")
	public GenericHttpResponse<Long> updateKorisnik(@RequestBody KorisnikDto korisnikDto) {
		return korisnikService.updateKorisnik(korisnikDto);
	}
	
	
}
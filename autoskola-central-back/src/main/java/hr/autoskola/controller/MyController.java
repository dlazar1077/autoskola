package hr.autoskola.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import hr.autoskola.model.Korisnik;
import hr.autoskola.service.KorisnikService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class MyController {
	
	private final KorisnikService service;
	
	@GetMapping("/test-docker")
	public String getData() {
		return "in docker tutorial project of play java - Radi!";
	}
	
	@CrossOrigin
	@GetMapping("/getData")
	public List<Korisnik> getKorisnik() {
		return service.getAllEntities();
	}
	
	@GetMapping("/login")
	public Korisnik getEntity(@RequestParam(name = "email") String email,@RequestParam(name = "pass") String password){
		return service.getEntity(email, password);
	}

}

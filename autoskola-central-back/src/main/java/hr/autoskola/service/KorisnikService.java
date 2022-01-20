package hr.autoskola.service;

import java.util.List;

import org.springframework.stereotype.Service;

import hr.autoskola.model.Korisnik;
import hr.autoskola.repository.KorisnikRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class KorisnikService {
	
	private final KorisnikRepository repository;
	
	public List<Korisnik> getAllEntities() {
		List<Korisnik> modelEntities = repository.getAllEntities();
		return modelEntities;
	}
	
	public Korisnik getEntity(String email, String password) {
		return repository.getEntity(email, password);
	}

}

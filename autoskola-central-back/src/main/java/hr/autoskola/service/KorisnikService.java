package hr.autoskola.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import hr.autoskola.dto.mapper.KorisnikMapper;
import hr.autoskola.dto.model.KorisnikDto;
import hr.autoskola.dto.model.shared.response.GenericHttpResponse;
import hr.autoskola.model.Korisnik;
import hr.autoskola.repository.KorisnikRepository;
import hr.autoskola.utilities.response.ResponseMessageEnum;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class KorisnikService {
	
	@Autowired
	PasswordEncoder encoder;
	
	private final KorisnikRepository repository;
	
	public List<Korisnik> getAllEntities() {
		List<Korisnik> modelEntities = repository.getAllEntities();
		return modelEntities;
	}
	
	public Korisnik getEntity(String email, String password) {
		return repository.getEntity(email, password);
	}
	
	public KorisnikDto getEntityByKorisnikId(String korisnikId) {
		Korisnik modelEntity=repository.getEntityByKorisnikId(korisnikId);
		
		KorisnikDto response = KorisnikMapper.toKorisnikDto(modelEntity);
		
		return response;
	}
	
	/**
	 * Metoda za dodavanje korisnika
	 * @param korisnikDto
	 * @return
	 */
	public GenericHttpResponse<Long> saveKorisnik(KorisnikDto korisnikDto) {
		GenericHttpResponse<Long> response;
		korisnikDto.setLozinka(encoder.encode(korisnikDto.getLozinka()));
		Long id = repository.saveKorisnik(KorisnikMapper.toKorisnik(korisnikDto));
		if(id > 0) {
			response = new GenericHttpResponse<>(ResponseMessageEnum.ENTITY_INSERTED);
			response.setData(id);
		}else {
			response = new GenericHttpResponse<>(ResponseMessageEnum.NOTHING_INSERTED);
		}
		return response;		
	}
	
	/**
	 * Metoda za ažutiranje korisnika
	 * @param korisnikDto
	 * @return
	 */
	public GenericHttpResponse<Long> updateKorisnik(KorisnikDto korisnikDto) {
		GenericHttpResponse<Long> response;
		if(korisnikDto.getLozinka() != "") korisnikDto.setLozinka(encoder.encode(korisnikDto.getLozinka()));
		Long id = repository.updateKorisnik(KorisnikMapper.toKorisnik(korisnikDto));
		if(id > 0) {
			response = new GenericHttpResponse<>(ResponseMessageEnum.ENTITY_INSERTED);
			response.setData(id);
		}else {
			response = new GenericHttpResponse<>(ResponseMessageEnum.NOTHING_INSERTED);
		}
		return response;		
	}
	
	/**
	 * Metoda za brisanje korisnika
	 * @param korisnikDto
	 * @return
	 */
	public GenericHttpResponse<Long> deleteKorisnik(String korisnikId) {
		GenericHttpResponse<Long> response;
		Long id = repository.deleteKorisnik(korisnikId);
		if(id > 0) {
			response = new GenericHttpResponse<>(ResponseMessageEnum.ENTITY_INSERTED);
			response.setData(id);
		}else {
			response = new GenericHttpResponse<>(ResponseMessageEnum.NOTHING_INSERTED);
		}
		return response;		
	}

}

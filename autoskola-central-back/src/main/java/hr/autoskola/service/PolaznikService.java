package hr.autoskola.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hr.autoskola.dto.mapper.PolaznikMapper;
import hr.autoskola.dto.model.PolaznikDto;
import hr.autoskola.dto.model.shared.response.GenericHttpResponse;
import hr.autoskola.model.Polaznik;
import hr.autoskola.repository.PolaznikRepository;
import hr.autoskola.utilities.response.ResponseMessageEnum;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PolaznikService {
	
	private final PolaznikRepository polaznikRepository;
	private final InstruktorService instruktorService;
	private final KorisnikService korisnikService;
	private final SatService satService;
	
	/**
	 * Servisna metoda za dohvaćanje svih polaznika
	 * @param 
	 * @return
	 */
	public List<PolaznikDto> getAllEntities(){
		
		List<Polaznik> modelEntities=polaznikRepository.getAllEntities();
		
		List<PolaznikDto> response = modelEntities.stream().map(PolaznikMapper::toPolaznikDto).collect(Collectors.toList());
		

		for(PolaznikDto polaznikDto : response) {
			polaznikDto.setKorisnik(korisnikService.getEntityByKorisnikId(polaznikDto.getKorisnikId().toString()));
			if(polaznikDto.getKorisnikId() != null ) polaznikDto.setInstruktor(instruktorService.getEntityByKorisnikId(polaznikDto.getInstruktorId().toString()));
			polaznikDto.setDnevnikVoznje(satService.getEntitiesByPolaznikId(polaznikDto.getPolaznikId().toString()));
		}
		
		return response;
	}
	
	/**
	 * Servisna metoda za dohvaćanje svih polaznika
	 * @param polaznikId
	 * @return
	 */
	public PolaznikDto getEntityById(String polaznikId){
		
		Polaznik modelEntities=polaznikRepository.getEntityById(polaznikId);
		
		if(modelEntities != null) {

			PolaznikDto response = PolaznikMapper.toPolaznikDto(modelEntities);
			
			if(response.getKorisnikId() != null) response.setKorisnik(korisnikService.getEntityByKorisnikId(response.getKorisnikId().toString()));
			if(response.getInstruktorId() != null) response.setInstruktor(instruktorService.getEntityByInstruktorId(response.getInstruktorId().toString()));
			response.setDnevnikVoznje(satService.getEntitiesByPolaznikId(response.getPolaznikId().toString()));
			
			return response;
		}
		
		return null;
	}
	
	/**
	 * Servisna metoda za dohvaćanje svih polaznika
	 * @param korisnikId
	 * @return
	 */
	public PolaznikDto getEntityByKorisnikId(String korisnikId){
		
		Polaznik modelEntities=polaznikRepository.getEntityByKorisnikId(korisnikId);
		
		if(modelEntities != null) {

			PolaznikDto response = PolaznikMapper.toPolaznikDto(modelEntities);
			
			if(response.getKorisnikId() != null) response.setKorisnik(korisnikService.getEntityByKorisnikId(response.getKorisnikId().toString()));
			if(response.getInstruktorId() != null) response.setInstruktor(instruktorService.getEntityByInstruktorId(response.getInstruktorId().toString()));
			response.setDnevnikVoznje(satService.getEntitiesByPolaznikId(response.getPolaznikId().toString()));
			
			return response;
		}
		
		return null;
	}
	
	/**
	 * Servisna metoda za dohvaćanje svih polaznika za instruktora
	 * @param instruktorId
	 * @return
	 */
	public List<PolaznikDto> getEntitiesByInstruktorId(String instruktorId){
		
		List<Polaznik> modelEntities=polaznikRepository.getEntitiesByInstruktorId(instruktorId);
		
		List<PolaznikDto> response = modelEntities.stream().map(PolaznikMapper::toPolaznikDto).collect(Collectors.toList());
		

		for(PolaznikDto polaznikDto : response) {
			polaznikDto.setKorisnik(korisnikService.getEntityByKorisnikId(polaznikDto.getKorisnikId().toString()));
			//if(polaznikDto.getKorisnikId() != null ) polaznikDto.setInstruktor(instruktorService.getEntityByKorisnikId(polaznikDto.getInstruktorId().toString()));
			polaznikDto.setDnevnikVoznje(satService.getEntitiesByPolaznikId(polaznikDto.getPolaznikId().toString()));
		}
		
		return response;
	}
	
	/**
	 * Metoda za dodavanje polaznika
	 * @param polaznikDto
	 * @return
	 */
	@Transactional
	public GenericHttpResponse<Long> savePolaznik(PolaznikDto polaznikDto) {
		GenericHttpResponse<Long> response;
		
		Long id = polaznikRepository.savePolaznik(PolaznikMapper.toPolaznik(polaznikDto));
		if(id > 0) {
			response = new GenericHttpResponse<>(ResponseMessageEnum.ENTITY_INSERTED);
			response.setData(id);
		}else {
			response = new GenericHttpResponse<>(ResponseMessageEnum.NOTHING_INSERTED);
		}
		return response;		
	}
	
	/**
	 * Metoda za ažuriranje polaznika
	 * @param polaznikDto
	 * @return
	 */
	@Transactional
	public GenericHttpResponse<Long> updatePolaznik(PolaznikDto polaznikDto) {
		GenericHttpResponse<Long> response;
		Long id = polaznikRepository.updatePolaznik(PolaznikMapper.toPolaznik(polaznikDto));
		if(id > 0) {
			if(polaznikDto.getKorisnik() != null) {
				korisnikService.updateKorisnik(polaznikDto.getKorisnik());
			}
			
			response = new GenericHttpResponse<>(ResponseMessageEnum.ENTITY_INSERTED);
			response.setData(id);
		}else {
			response = new GenericHttpResponse<>(ResponseMessageEnum.NOTHING_INSERTED);
		}
		return response;		
	}
	
	/**
	 * Metoda za ažuriranje stusa polaznika
	 * @param korisnikId
	 * @param status
	 * @return
	 */
	public GenericHttpResponse<Long> updateStatusPolaznik(String korisnikId, String status) {
		GenericHttpResponse<Long> response;
		Long id = polaznikRepository.updateStatusPolaznik(korisnikId, status);
		
		if(id > 0) {
			response = new GenericHttpResponse<>(ResponseMessageEnum.ENTITY_INSERTED);
			response.setData(id);
		}else {
			response = new GenericHttpResponse<>(ResponseMessageEnum.NOTHING_INSERTED);
		}
		return response;
	}
	
	/**
	 * Metoda za brisanje polaznika
	 * @param polaznikDto
	 * @return
	 */
	@Transactional
	public GenericHttpResponse<Long> deletePolaznik(PolaznikDto polaznikDto) {
		GenericHttpResponse<Long> response;
		Long id = polaznikRepository.deletePolaznik(PolaznikMapper.toPolaznik(polaznikDto).getPolaznikId().toString());
		if(id > 0) {
			korisnikService.deleteKorisnik(polaznikDto.getKorisnik().getKorisnikId().toString());
			
			response = new GenericHttpResponse<>(ResponseMessageEnum.ENTITY_INSERTED);
			response.setData(id);
		}else {
			response = new GenericHttpResponse<>(ResponseMessageEnum.NOTHING_INSERTED);
		}
		return response;		
	}

}

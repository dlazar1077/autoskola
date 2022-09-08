package hr.autoskola.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hr.autoskola.dto.mapper.InstruktorMapper;
import hr.autoskola.dto.model.InstruktorDto;
import hr.autoskola.dto.model.VoziloDto;
import hr.autoskola.dto.model.shared.response.GenericHttpResponse;
import hr.autoskola.model.Instruktor;
import hr.autoskola.repository.InstruktorRepository;
import hr.autoskola.utilities.response.ResponseMessageEnum;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InstruktorService {
	
	private final InstruktorRepository instruktorRepository;
	private final VoziloService voziloService;
	private final KorisnikService korisnikService;
	
	/**
	 * Servisna metoda za dohvaćanje svih instruktora
	 * @param 
	 * @return
	 */
	public List<InstruktorDto> getAllEntities(){
		
		List<Instruktor> modelEntities=instruktorRepository.getAllEntities();
		
		List<InstruktorDto> response = modelEntities.stream().map(InstruktorMapper::toInstruktorDto).collect(Collectors.toList());
		

		for(InstruktorDto instruktorDto : response) {
			instruktorDto.setKorisnik(korisnikService.getEntityByKorisnikId(instruktorDto.getKorisnikId().toString()));
			instruktorDto.setVozila(voziloService.getEntityByInstruktorId(instruktorDto.getInstruktorId().toString()).getTableData());
		}
		
		return response;
	}
	
	/**
	 * Servisna metoda za dohvaćanje svih instruktora
	 * @param 
	 * @return
	 */
	public InstruktorDto getEntityByKorisnikId(String korisnikId){
		
		Instruktor modelEntities=instruktorRepository.getEntityByKorisnikId(korisnikId);
		
		InstruktorDto response = InstruktorMapper.toInstruktorDto(modelEntities);
		

		response.setKorisnik(korisnikService.getEntityByKorisnikId(response.getKorisnikId().toString()));
		response.setVozila(voziloService.getEntityByInstruktorId(response.getInstruktorId().toString()).getTableData());
		
		return response;
	}
	
	/**
	 * Servisna metoda za dohvaćanje svih instruktora
	 * @param 
	 * @return
	 */
	public InstruktorDto getEntityByInstruktorId(String instruktorId){
		
		Instruktor modelEntities=instruktorRepository.getEntityByInstruktorId(instruktorId);
		
		InstruktorDto response = InstruktorMapper.toInstruktorDto(modelEntities);
		

		response.setKorisnik(korisnikService.getEntityByKorisnikId(response.getKorisnikId().toString()));
		response.setVozila(voziloService.getEntityByInstruktorId(response.getInstruktorId().toString()).getTableData());
		
		return response;
	}
	
	/**
	 * Servisna metoda za dohvat slobodnih Instruktora
	 * 
	 * @param kategorijaId
	 * @return List <InstruktorDto>
	 * 
	 * @author dlazar
	 */
	public List<InstruktorDto> getAvailableInstructors(String kategorijaId) {
		List<Instruktor> modelEntities=instruktorRepository.getAvailableInstructors(kategorijaId);
		
		List<InstruktorDto> response = modelEntities.stream().map(InstruktorMapper::toInstruktorDto).collect(Collectors.toList());
		

		for(InstruktorDto instruktorDto : response) {
			instruktorDto.setKorisnik(korisnikService.getEntityByKorisnikId(instruktorDto.getKorisnikId().toString()));
			//instruktorDto.setVozila(voziloService.getEntityByInstruktorId(instruktorDto.getInstruktorId().toString()).getTableData());
		}
		
		return response;
	}
	
	/**
	 * Metoda za dodavanje instruktora
	 * @param instruktorDto
	 * @return
	 */
	@Transactional
	public GenericHttpResponse<Long> saveInstruktor(InstruktorDto instruktorDto) {
		GenericHttpResponse<Long> response;
		Long idKorisnik = korisnikService.saveKorisnik(instruktorDto.getKorisnik()).getData();
		instruktorDto.setKorisnikId(Integer.parseInt(idKorisnik.toString()));
		
		Long id = instruktorRepository.saveInstruktor(InstruktorMapper.toInstruktor(instruktorDto));
		if(id > 0) {
			if(instruktorDto.getVozila() != null) {
				for(VoziloDto voziloDto : instruktorDto.getVozila()) {
					instruktorRepository.saveVehicleToInstruktor(voziloDto.getVoziloId().toString(), id.toString());
				}
			}
			
			response = new GenericHttpResponse<>(ResponseMessageEnum.ENTITY_INSERTED);
			response.setData(id);
		}else {
			response = new GenericHttpResponse<>(ResponseMessageEnum.NOTHING_INSERTED);
		}
		return response;		
	}
	
	/**
	 * Metoda za ažuriranje instruktora
	 * @param instruktorDto
	 * @return
	 */
	@Transactional
	public GenericHttpResponse<Long> updateInstruktor(InstruktorDto instruktorDto) {
		GenericHttpResponse<Long> response;
		Long id = instruktorRepository.updateInstruktor(InstruktorMapper.toInstruktor(instruktorDto));
		if(id > 0) {
			korisnikService.updateKorisnik(instruktorDto.getKorisnik());
			instruktorRepository.deleteVozilaInstruktora(instruktorDto.getInstruktorId().toString());
			if(instruktorDto.getVozila() != null) {
				for(VoziloDto voziloDto : instruktorDto.getVozila()) {
					instruktorRepository.saveVehicleToInstruktor(voziloDto.getVoziloId().toString(), instruktorDto.getInstruktorId().toString());
				}
			}
			
			response = new GenericHttpResponse<>(ResponseMessageEnum.ENTITY_INSERTED);
			response.setData(id);
		}else {
			response = new GenericHttpResponse<>(ResponseMessageEnum.NOTHING_INSERTED);
		}
		return response;		
	}
	
	/**
	 * Metoda za brisanje instruktora
	 * @param instruktorDto
	 * @return
	 */
	@Transactional
	public GenericHttpResponse<Long> deleteInstruktor(InstruktorDto instruktorDto) {
		GenericHttpResponse<Long> response;
		Long id = instruktorRepository.deleteInstruktor(InstruktorMapper.toInstruktor(instruktorDto).getInstruktorId().toString());
		if(id > 0) {
			korisnikService.deleteKorisnik(instruktorDto.getKorisnik().getKorisnikId().toString());
			instruktorRepository.deleteVozilaInstruktora(instruktorDto.getInstruktorId().toString());
			
			response = new GenericHttpResponse<>(ResponseMessageEnum.ENTITY_INSERTED);
			response.setData(id);
		}else {
			response = new GenericHttpResponse<>(ResponseMessageEnum.NOTHING_INSERTED);
		}
		return response;		
	}

}

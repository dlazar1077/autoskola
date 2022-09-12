package hr.autoskola.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hr.autoskola.dto.mapper.IspitMapper;
import hr.autoskola.dto.model.IspitDto;
import hr.autoskola.dto.model.PitanjeDto;
import hr.autoskola.dto.model.shared.response.GenericHttpResponse;
import hr.autoskola.model.Ispit;
import hr.autoskola.repository.IspitRepository;
import hr.autoskola.utilities.codebooks.dto.model.CodebookDto;
import hr.autoskola.utilities.codebooks.service.CodebookService;
import hr.autoskola.utilities.response.ResponseMessageEnum;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class IspitService {
	
	private final IspitRepository ispitRepository;
	private final PitanjeService pitanjeService;
	private final KorisnikService korisnikService;
	private final PolaznikService polaznikService;
	private final CodebookService codebookService;
	
	/**
	 * Servisna metoda za dohvaćanje svih ispita prema korisnik id
	 * @param korisnikId
	 * @return
	 */
	public List<IspitDto> getAllEntitiesByKorisnikId(String korisnikId){
		
		List<Ispit> modelEntities=ispitRepository.getAllEntitiesByKorisnikId(korisnikId);
		
		List<IspitDto> response = modelEntities.stream().map(IspitMapper::toIspitDto).collect(Collectors.toList());
		
		if(response != null) {
			for(IspitDto ispitDto : response) {
				ispitDto.setKorisnik(korisnikService.getEntityByKorisnikId(ispitDto.getKorisnikId().toString()));
//				ispitDto.setPitanja(pitanjeService.getAllEntitiesByIspitId(ispitDto.getIspitId().toString()));
			}
		}
		
		return response;
	}
	
	/**
	 * Servisna metoda za dohvaćanje svih ispita prema ispit id
	 * @param ispitId
	 * @return
	 */
	public IspitDto getAllEntitiesByIspitId(String ispitId){
		
		Ispit modelEntities=ispitRepository.getAllEntitiesByIspitId(ispitId);
		
		IspitDto response =IspitMapper.toIspitDto(modelEntities);
		
		if(response != null) {
			response.setKorisnik(korisnikService.getEntityByKorisnikId(response.getKorisnikId().toString()));
			response.setPitanja(pitanjeService.getAllEntitiesByIspitId(response.getIspitId().toString()));
		}
		
		return response;
	}
	
	/**
	 * Metoda za dodavanje ispita
	 * @param ispitDto
	 * @return
	 */
	@Transactional
	public GenericHttpResponse<Long> saveIspit(IspitDto ispitDto) {
		GenericHttpResponse<Long> response;
		Long id = ispitRepository.saveIspit(IspitMapper.toIspit(ispitDto));
		if(id > 0) {
			if(ispitDto.getPitanja() != null) {
				for(PitanjeDto pitanjeDto : ispitDto.getPitanja()) {
					pitanjeService.savePitanjeOdgovor(pitanjeDto, id.toString());
				}
			}
			if(ispitDto.getStatusIspita().contains("PROLAZ")) {
				List<String> trazeniCodebook = new ArrayList<String>();
				trazeniCodebook.add("statusPolaznika");
				Map<String, List<CodebookDto>> codebook = codebookService.getCodebooks(trazeniCodebook);
				List<CodebookDto> statusPolaznika = codebook.get("statusPolaznika");
				for(CodebookDto code : statusPolaznika) {
					if(code.getName().toLowerCase().equals("POLOŽIO TEST".toLowerCase())) {
						polaznikService.updateStatusPolaznik(ispitDto.getKorisnikId().toString(), code.getId());
						break;
					}
				}
			}
//		System.out.println(ispitDto.getStatusIspita());
//		if(ispitDto.getStatusIspita().contains("PROLAZ")) {
//			List<String> trazeniCodebook = new ArrayList<String>();
//			trazeniCodebook.add("statusPolaznika");
//			Map<String, List<CodebookDto>> codebook = codebookService.getCodebooks(trazeniCodebook);
//			List<CodebookDto> statusPolaznika = codebook.get("statusPolaznika");
//			for(CodebookDto code : statusPolaznika) {
//				System.out.println(code.getName().toLowerCase() + " - " + "POLOŽIO TEST".toLowerCase() );
//				if(code.getName().toLowerCase().equals("".toLowerCase())) {
//					System.out.println("UPISAN JJJJJJJEEEEEEEEE");
//					polaznikService.updateStatusPolaznik(ispitDto.getKorisnikId().toString(), code.getId());
//					break;
//				}
//			}
			response = new GenericHttpResponse<>(ResponseMessageEnum.ENTITY_INSERTED);
			//response.setData(id);
		}else {
			response = new GenericHttpResponse<>(ResponseMessageEnum.NOTHING_INSERTED);
		}
		return response;		
	}

}

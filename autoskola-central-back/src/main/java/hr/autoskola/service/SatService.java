package hr.autoskola.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hr.autoskola.dto.mapper.SatMapper;
import hr.autoskola.dto.model.SatDto;
import hr.autoskola.dto.model.shared.response.GenericHttpResponse;
import hr.autoskola.model.Sat;
import hr.autoskola.repository.SatRepository;
import hr.autoskola.utilities.response.ResponseMessageEnum;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SatService {
	
	private final SatRepository satRepository;
	private final VoziloService voziloService;
//	private final PolaznikService polaznikService;
	
	/**
	 * Servisna metoda za dohvaćanje svih satova jednog polaznika
	 * @param polaznikId
	 * @return
	 */
	public List<SatDto> getEntitiesByPolaznikId(String polaznikId){
		
		List<Sat> modelEntities=satRepository.getEntitiesByPolaznikId(polaznikId);
		
		if(modelEntities != null) {

			List<SatDto> response = modelEntities.stream().map(SatMapper::toSatDto).collect(Collectors.toList());
			
			for(SatDto satDto : response) {
				if(satDto.getVoziloId() != null) satDto.setVozilo(voziloService.getEntityById(satDto.getVoziloId().toString()));
//				if(satDto.getPolaznikId() != null) satDto.setPolaznik(polaznikService.getEntityById(satDto.getPolaznikId().toString()));
			}
			
			return response;
		}
		
		return null;
	}
	
	/**
	 * Metoda za dodavanje sata voznje
	 * @param satDto
	 * @return
	 */
	@Transactional
	public GenericHttpResponse<Long> saveSatVoznje(SatDto satDto) {
		GenericHttpResponse<Long> response;
		
		Long id = satRepository.saveSatVoznje(SatMapper.toSat(satDto));
		if(id > 0) {
			satRepository.saveSatPolaznik(id.toString(), satDto.getPolaznikId().toString());
			response = new GenericHttpResponse<>(ResponseMessageEnum.ENTITY_INSERTED);
			response.setData(id);
		}else {
			response = new GenericHttpResponse<>(ResponseMessageEnum.NOTHING_INSERTED);
		}
		return response;		
	}
	
	/**
	 * Metoda za ažuriranje sata
	 * @param satDto
	 * @return
	 */
	public GenericHttpResponse<Long> updateSatVoznje(SatDto satDto) {
		GenericHttpResponse<Long> response;
		Long id = satRepository.updateSatVoznje(SatMapper.toSat(satDto));
		if(id > 0) {
			response = new GenericHttpResponse<>(ResponseMessageEnum.ENTITY_INSERTED);
			response.setData(id);
		}else {
			response = new GenericHttpResponse<>(ResponseMessageEnum.NOTHING_INSERTED);
		}
		return response;		
	}
	
	/**
	 * Metoda za brisanje sata
	 * @param satDto
	 * @return
	 */
	public GenericHttpResponse<Long> deleteSatVoznje(SatDto satDto) {
		GenericHttpResponse<Long> response;
		Long id = satRepository.deleteSatVoznje(SatMapper.toSat(satDto));
		if(id > 0) {
			response = new GenericHttpResponse<>(ResponseMessageEnum.ENTITY_INSERTED);
			response.setData(id);
		}else {
			response = new GenericHttpResponse<>(ResponseMessageEnum.NOTHING_INSERTED);
		}
		return response;		
	}

}

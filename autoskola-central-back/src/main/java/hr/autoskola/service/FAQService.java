package hr.autoskola.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import hr.autoskola.dto.mapper.FAQMapper;
import hr.autoskola.dto.model.FAQDto;
import hr.autoskola.dto.model.shared.response.GenericHttpResponse;
import hr.autoskola.model.FAQ;
import hr.autoskola.repository.FAQRepository;
import hr.autoskola.utilities.response.ResponseMessageEnum;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FAQService {
	
	private final FAQRepository faqRepository;
	
	/**
	 * Servisna metoda za dohvaćanje svih faq
	 * 
	 * @return
	 */
	public List<FAQDto> getAllEntities(){
		
		List<FAQ> modelEntities=faqRepository.getAllEntities();
		
		List<FAQDto> response = modelEntities.stream().map(FAQMapper::toFAQDto).collect(Collectors.toList());
		
		return response;
	}
	

	/**
	 * Metoda za dodavanje faq
	 * @param faqDto
	 * @return
	 */
	public GenericHttpResponse<Long> saveFAQ(FAQDto faqDto) {
		GenericHttpResponse<Long> response;
		Long id = faqRepository.saveFAQ(FAQMapper.toFAQ(faqDto));
		if(id > 0) {
			response = new GenericHttpResponse<>(ResponseMessageEnum.ENTITY_INSERTED);
			response.setData(id);
		}else {
			response = new GenericHttpResponse<>(ResponseMessageEnum.NOTHING_INSERTED);
		}
		return response;		
	}
	
	/**
	 * Servisna metoda za ažuriranje faq
	 * @param faqDto
	 * @return
	 */
	public GenericHttpResponse<Long> updateFAQ(FAQDto faqDto) {
		GenericHttpResponse<Long> response;
		Long numberOfUpdateRows;
		
		numberOfUpdateRows = faqRepository.updateFAQ(FAQMapper.toFAQ(faqDto));
		response = new GenericHttpResponse<>(numberOfUpdateRows > 0 ? ResponseMessageEnum.ENTITY_UPDATED :ResponseMessageEnum.NOTHING_UPDATED);
		response.setData(numberOfUpdateRows);
		
		return response;		
	}

	/**
	 * Servisna metoda za brisanje faq
	 * @param id
	 * @return
	 */
	public Long deleteFAQ(String id) {
		return faqRepository.deleteFAQ(id);
	}

}

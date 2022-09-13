package hr.autoskola.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import hr.autoskola.dto.model.FAQDto;
import hr.autoskola.dto.model.shared.response.GenericHttpResponse;
import hr.autoskola.service.FAQService;
import hr.autoskola.utilities.response.ResponseMessageEnum;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class FAQController {
	
	private final FAQService faqService;
	
	@GetMapping(value = "/api/FAQ")
	public ResponseEntity<List<FAQDto>> getAutoskolaInfo() {
		List<FAQDto> faq = faqService.getAllEntities();
		return new ResponseEntity<>(faq, HttpStatus.OK);
	}
	
	@PostMapping(value="/api/insertFAQ")
	public GenericHttpResponse<Long> saveFAQ(@RequestBody FAQDto faqDto) {
		return faqService.saveFAQ(faqDto);
	}
	
	@PutMapping(value="/api/updateFAQ")
	public GenericHttpResponse<Long> updateFAQ(@RequestBody FAQDto faqDto) {
		return faqService.updateFAQ(faqDto);
	}
	
	@PutMapping(value="/api/deleteFAQ")
	public GenericHttpResponse<Long> deleteFAQ(@RequestBody FAQDto faqDto) {
		Long numberOfUpdatedEntities = faqService.deleteFAQ(String.valueOf(faqDto.getFaqId()));
		GenericHttpResponse<Long> result = new GenericHttpResponse<>(numberOfUpdatedEntities > 0 ? ResponseMessageEnum.ENTITY_UPDATED : ResponseMessageEnum.NOTHING_UPDATED);
		result.setData(numberOfUpdatedEntities);
		return result;
	}

}

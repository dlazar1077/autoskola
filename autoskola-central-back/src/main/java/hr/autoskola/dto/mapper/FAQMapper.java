package hr.autoskola.dto.mapper;

import org.springframework.stereotype.Component;

import hr.autoskola.dto.model.FAQDto;
import hr.autoskola.model.FAQ;

@Component
public class FAQMapper {
	
	/**
	  * Metoda koja mapira klasu FAQ u klasu FAQDto
	  * @param faq
	  * @return
	  */
	public static FAQDto toFAQDto (FAQ faq) {
		FAQDto returnObject = new FAQDto();
		
		returnObject.setFaqId(faq.getFaqId());;
		returnObject.setPitanje(faq.getPitanje());
		returnObject.setOdgovor(faq.getOdgovor());
		
		return returnObject;
	}
	
	/**
	  * Metoda koja mapira klasu FAQDto u klasu FAQ
	  * @param faqDto
	  * @return
	  */
	public static FAQ toFAQ (FAQDto faqDto) {
		FAQ returnObject = new FAQ();
		
		returnObject.setFaqId(faqDto.getFaqId());;
		returnObject.setPitanje(faqDto.getPitanje());
		returnObject.setOdgovor(faqDto.getOdgovor());
		
		return returnObject;
	}

}

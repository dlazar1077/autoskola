package hr.autoskola.utilities.codebooks;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import hr.autoskola.utilities.codebooks.service.CodebookService;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CodebookRefreshedListener implements ApplicationListener<ContextRefreshedEvent > {

	private final CodebookService codebookService;
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent  event) {
		try {
			codebookService.refreshCodebooks();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

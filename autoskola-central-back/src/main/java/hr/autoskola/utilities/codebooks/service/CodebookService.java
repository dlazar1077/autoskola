package hr.autoskola.utilities.codebooks.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import hr.autoskola.utilities.codebooks.constants.CodebookConstants;
import hr.autoskola.utilities.codebooks.dto.mapper.CodebookMapper;
import hr.autoskola.utilities.codebooks.dto.model.CodebookDto;
import hr.autoskola.utilities.codebooks.enums.CodebookNameEnum;
import hr.autoskola.utilities.codebooks.model.Codebook;
import hr.autoskola.utilities.codebooks.repository.CodebookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class CodebookService {

	private final CodebookRepository codebookRepository;
	
	public Map<String, CodebookDto> getCodebookById(String name) {
		return CodebookConstants.codebooksById.get(name);
	}
	
	public Map<String, List<CodebookDto>> getCodebooks(List<String> names) {
		Map<String, List<CodebookDto>> codebooks = new HashMap<>();
		for (String name : names) {
			List<CodebookDto> codebookValues = CodebookConstants.codebooks.get(name);
			if (codebookValues != null) {
				codebooks.put(name, codebookValues);
			}
		}
		return codebooks;
	}
	
	public void refreshCodebook(CodebookNameEnum codebookName) {
		List<Codebook> codebook = codebookRepository.getValuesForCodebook(
				codebookName.getTableName(), codebookName.getIdColumnName(), codebookName.getNameColumnName(),
				codebookName.getWhereCondition(), codebookName.getOrderByColumn(), codebookName.getForeignKeyColumnName(), 
				codebookName.getEngTranslationColumn(), codebookName.isIncludeOrdinal());
		if (codebook != null) {
			CodebookConstants.codebooks.put(codebookName.name(), 
					codebook.stream().map(CodebookMapper::toCodebookDto).collect(Collectors.toList()));
		}
	}
	
	public void refreshCodebooks() throws Exception {
		CodebookConstants.codebooks = new ConcurrentHashMap<>();
		
		try {
			List<CompletableFuture<Void>> completableFutures = new ArrayList<>();
			
//			completableFutures.add(CompletableFuture.runAsync(() -> refreshCodebook(CodebookNameEnum.modifierPeriod))); DBCHANGE
//			completableFutures.add(CompletableFuture.runAsync(() -> refreshCodebook(CodebookNameEnum.salesChannelProductStatus))); //DBCHANGE
			
			// metoda za blokiranje
			CompletableFuture.allOf(completableFutures.toArray(new CompletableFuture[completableFutures.size()])).join();
		} catch (Exception e) {
			log.error("", e);
		}
				
		createCodebooksById();
	}
	
	private void createCodebooksById () {
		CodebookConstants.codebooksById = new HashMap<>();
		for (String key : CodebookConstants.codebooks.keySet()) {
			Map<String, CodebookDto> codebookByIdMap = new HashMap<>();
			for (CodebookDto entry : CodebookConstants.codebooks.get(key)) {
				codebookByIdMap.put(entry.getId(), entry);
			}
			CodebookConstants.codebooksById.put(key, codebookByIdMap);
	    }
	}
}

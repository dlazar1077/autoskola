package hr.autoskola.utilities.codebooks.dto.mapper;

import org.springframework.stereotype.Component;

import hr.autoskola.utilities.codebooks.dto.model.CodebookDto;
import hr.autoskola.utilities.codebooks.model.Codebook;

@Component
public class CodebookMapper {

	public static CodebookDto toCodebookDto(Codebook codebook) {
		CodebookDto returnObject = new CodebookDto();
		
		returnObject.setId(codebook.getId());
		returnObject.setName(codebook.getName());
		returnObject.setNameEn(codebook.getNameEn());
		returnObject.setForeignKey(codebook.getForeignId());
		
		return returnObject;
	}
}

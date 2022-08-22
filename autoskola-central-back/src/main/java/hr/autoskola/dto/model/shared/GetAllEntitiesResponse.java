package hr.autoskola.dto.model.shared;

import java.util.List;
import java.util.Map;

import hr.autoskola.utilities.codebooks.dto.model.CodebookDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class GetAllEntitiesResponse<T> {
	
	Long rowCount;
	List<T> tableData;
	Map<String, List<CodebookDto>> codebooks;
	
}
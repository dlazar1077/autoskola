package hr.autoskola.dto.model.shared;

import java.util.Map;

import hr.autoskola.dto.model.filter.FilterValueDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class GetAllEntitiesRequest {

	private boolean getCodebooks;
	private String parentId;
	private Map<String, Integer> miscParameters;
	private Long currentPage;
	private Long pageSize;
	private String sortDirection;
	private String sortColumnName;
	private String globalSearchString;
	private Map<String, FilterValueDto> filterColumns;

}

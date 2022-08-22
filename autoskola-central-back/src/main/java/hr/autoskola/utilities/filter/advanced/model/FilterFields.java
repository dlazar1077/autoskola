package hr.autoskola.utilities.filter.advanced.model;

import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FilterFields {

	private Map<String, String> stringColumnsMap;
	private Map<String, String> numberColumnsMap;
	private Map<String, String> booleanColumnsMap;
	private Map<String, String> listColumnsMap;
	private Map<String, String> dateColumnsMap;

	private List<String> customColumns;
}


package hr.autoskola.dto.model.filter;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class FilterValueDto {

	// Input type
	private String value;
	// List type
	private List<String> values;
	// Boolean type
	private Boolean active;
	// Number type
	private BigDecimal min;
	private BigDecimal max;
	// Date type
	private Date from;
	private Date to;
}

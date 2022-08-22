package hr.autoskola.dto.mapper.filter;

import org.springframework.stereotype.Component;

import hr.autoskola.dto.model.filter.FilterValueDto;
import hr.autoskola.model.FilterValue;


@Component
public class FilterValueMapper {

	public static FilterValueDto toFilterValueDto(FilterValue filterValue) {
		FilterValueDto returnObject = new FilterValueDto();
		
		returnObject.setValue(filterValue.getValue());
		returnObject.setValues(filterValue.getValues());
		returnObject.setActive(filterValue.getActive());
		returnObject.setMin(filterValue.getMin());
		returnObject.setMax(filterValue.getMax());
		returnObject.setFrom(filterValue.getFrom());
		returnObject.setTo(filterValue.getTo());		
		
		return returnObject;
	}
	
	public static FilterValue toFilterValue(FilterValueDto filterValueDto) {
		FilterValue returnObject = new FilterValue();

		returnObject.setValue(filterValueDto.getValue());
		returnObject.setValues(filterValueDto.getValues());
		returnObject.setActive(filterValueDto.getActive());
		returnObject.setMin(filterValueDto.getMin());
		returnObject.setMax(filterValueDto.getMax());
		returnObject.setFrom(filterValueDto.getFrom());
		returnObject.setTo(filterValueDto.getTo());
		
		return returnObject;
	}
}

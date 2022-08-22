package hr.autoskola.utilities.pagination;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

import hr.autoskola.dto.model.shared.GetAllEntitiesRequest;


public class PageableHelper {

	public static Pageable createPageable(GetAllEntitiesRequest getTableDataRequest) {
		String springDirection = "ASC";
		Sort sortObject = null;
		Pageable pageable = PageRequest.of(getTableDataRequest.getCurrentPage().intValue() -1, getTableDataRequest.getPageSize().intValue());
		if (getTableDataRequest.getSortDirection() != null) {
			switch (getTableDataRequest.getSortDirection()) {
			case "Ascending":
				springDirection = "ASC";
				break;
			case "Descending":
				springDirection = "DESC";
				break;
			}
		}
		if (getTableDataRequest.getSortColumnName() != null) {
			sortObject = Sort.by(Sort.Direction.fromString(springDirection), getTableDataRequest.getSortColumnName());
		}
		if (sortObject != null) {
			pageable = PageRequest.of(getTableDataRequest.getCurrentPage().intValue() -1, getTableDataRequest.getPageSize().intValue(), sortObject);
		}
		return pageable;
	}

	/**
	 * Map column names in Sort class into different column names
	 * @param sort
	 * @param columnNames Map<String, String> with value mapping
	 * @return new Sort
	 */
	public static Sort mapSortColumnNames(Sort sort, final Map<String, String> columnNames) {
		List<Order> orders = sort.stream()
			.map(order -> new Sort.Order(order.getDirection(), columnNames.get(order.getProperty())))
			.collect(Collectors.toList());
		Sort sortDbColumns = Sort.by(orders);
		return sortDbColumns;
	}

	public static void sqlPaginationQueryUpdate(StringBuilder query, MapSqlParameterSource parameters, Long offset, Long pageSize) {
		if (query == null || parameters == null || pageSize == 0) return;
		
		//query.append(" OFFSET :offset ROWS FETCH FIRST :pageSize ROWS ONLY");
		query.append(" LIMIT :offset , :pageSize ");
		parameters.addValue("offset", offset);
		parameters.addValue("pageSize", pageSize);
	}
}

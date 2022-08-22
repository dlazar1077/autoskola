package hr.autoskola.utilities.codebooks.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum CodebookNameEnum {
	
	uloge("uloge", "ULOGA_ID", "NAZIV", null, null, null, "NAZIV_EN", false),
	kategorije("kategorije", "KATEGORIJA_ID", "NAZIV", null, null, null, "NAZIV_EN", false);
	
	@Getter private String tableName;
	@Getter private String idColumnName;
	@Getter private String nameColumnName;
	@Getter private String whereCondition;
	@Getter private String orderByColumn;
	@Getter private String foreignKeyColumnName;
	@Getter private String engTranslationColumn;
	@Getter private boolean includeOrdinal = true;

}

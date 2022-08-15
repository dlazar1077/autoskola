package hr.autoskola.utilities.codebooks.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum CodebookNameEnum {
	
	country("GEN_CBK_COUNTRY", "CBK_COUNTRY_ID", "NAME", null, "CODE_LT3", null, "NAME_EN", true);
	
	@Getter private String tableName;
	@Getter private String idColumnName;
	@Getter private String nameColumnName;
	@Getter private String whereCondition;
	@Getter private String orderByColumn;
	@Getter private String foreignKeyColumnName;
	@Getter private String engTranslationColumn;
	@Getter private boolean includeOrdinal = true;

}

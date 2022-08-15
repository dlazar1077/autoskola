package hr.autoskola.utilities.codebooks.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Codebook {
	
	String id;
	String name;
	String nameEn;
	String foreignId;
	Long ordinal;

}

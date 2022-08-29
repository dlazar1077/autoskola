package hr.autoskola.dto.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class KategorijaDto {
	
	private int kategorijaId;
	private String sifra;
	private String naziv;
	private String nazivEn;

}

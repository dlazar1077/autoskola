package hr.autoskola.dto.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class VoziloDto {

	private Integer voziloId;
	private Integer kategorijaId;
	private String markaVozila;
	private String model;
}

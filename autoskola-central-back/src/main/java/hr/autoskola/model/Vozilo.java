package hr.autoskola.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Vozilo {

	private Integer voziloId;
	private Integer kategorijaId;
	private String markaVozila;
	private String model;
}

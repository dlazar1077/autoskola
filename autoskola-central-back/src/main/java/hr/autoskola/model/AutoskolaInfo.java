package hr.autoskola.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AutoskolaInfo {
	
	private int autoskolaInfoId;
	private String sifra;
	private String vrijednost;

}

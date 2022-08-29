package hr.autoskola.dto.mapper;

import org.springframework.stereotype.Component;

import hr.autoskola.dto.model.AutoskolaInfoDto;
import hr.autoskola.model.AutoskolaInfo;

@Component
public class AutoskolaInfoMapper {
	
	/**
	  * Metoda koja mapira klasu AutoskolaInfo u klasu AutoskolaInfoaDto
	  * @param autoskolaInfo
	  * @return
	  */
	public static AutoskolaInfoDto toAutoskolaInfoDto (AutoskolaInfo autoskolaInfo) {
		AutoskolaInfoDto returnObject = new AutoskolaInfoDto();
		
		returnObject.setAutoskolaInfoId(autoskolaInfo.getAutoskolaInfoId());
		returnObject.setSifra(autoskolaInfo.getSifra());
		returnObject.setVrijednost(autoskolaInfo.getVrijednost());
		
		return returnObject;
	}
	
	/**
	  * Metoda koja mapira klasu AutoskolaInfoDto u klasu AutoskolaInfo
	  * @param autoskolaInfoDto
	  * @return
	  */
	public static AutoskolaInfo toAutoskolaInfo (AutoskolaInfoDto autoskolaInfoDto) {
		AutoskolaInfo returnObject = new AutoskolaInfo();
		
		returnObject.setAutoskolaInfoId(autoskolaInfoDto.getAutoskolaInfoId());
		returnObject.setSifra(autoskolaInfoDto.getSifra());
		returnObject.setVrijednost(autoskolaInfoDto.getVrijednost());
		
		return returnObject;
	}

}

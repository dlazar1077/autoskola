package hr.autoskola.dto.model.shared.response;

import org.springframework.http.HttpStatus;

import hr.autoskola.utilities.response.ResponseMessageEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class GenericHttpResponse<T> {
	
	private HttpStatus status;
	private T data;
	private String messageCode;
	private boolean valid;
	
	public GenericHttpResponse(ResponseMessageEnum messageEnum) {
		this.status = messageEnum.getStatus();
		this.messageCode = messageEnum.getMessageCode();
		this.valid = messageEnum.isValid();
	}
	
}

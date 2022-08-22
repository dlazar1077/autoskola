package hr.autoskola.utilities.response;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public enum ResponseMessageEnum {

	IVR_NUMBER_EXISTS("IVR_NUMBER_EXISTS", false, HttpStatus.CONFLICT),
	CALL_GROUP_EXISTS("CALL_GROUP_EXISTS", false, HttpStatus.CONFLICT),
	ENTITY_UPDATED("ENTITY_UPDATED", true, HttpStatus.OK),
	ENTITY_INSERTED("ENTITY_INSERTED", true, HttpStatus.OK),
	FETCHED_ENTITIES("ENTITIES_ARE_FETCHED", true, HttpStatus.OK),
	JOB_DONE("JOB_DONE", true, HttpStatus.OK),
	NOTHING_UPDATED("NOTHING_UPDATED", false, HttpStatus.BAD_GATEWAY),
	NOTHING_INSERTED("NOTHING_INSERTED", false, HttpStatus.BAD_GATEWAY),
	ENTITY_FETCHED("ENTITY_FETCHED", true, HttpStatus.OK),
	ACTIVE_EXECUTOR_EXIST("ACTIVE_EXECUTOR_EXIST", false, HttpStatus.CONFLICT),
	EXECUTOR_EXIST_ON_ASSOCIATE("EXECUTOR_EXIST_ON_ASSOCIATE", false , HttpStatus.CONFLICT);
	
	public final String messageCode;
	public final boolean valid;
	public final HttpStatus status;
	
	private ResponseMessageEnum(String code, boolean valid, HttpStatus status) {
		this.messageCode = code;
		this.valid = valid;
		this.status = status;
	}
	
	
}

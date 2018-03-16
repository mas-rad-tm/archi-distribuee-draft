package ch.globaz.avs4.affiliationsservice.infrastructure.dto;

import lombok.Getter;

@Getter
public class RequestErrorDto {

	private String messgae;
	private String statusCode;

	public RequestErrorDto() {}

	public RequestErrorDto(String message, String statusCode){
		this.messgae = message;
		this.statusCode = statusCode;
	}


}

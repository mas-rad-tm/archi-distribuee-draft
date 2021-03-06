package ch.globaz.avs4.zuulapigateway.application.dto;


import ch.globaz.avs4.zuulapigateway.application.dto.localdate.LocalDateSerializer;
import ch.globaz.avs4.zuulapigateway.application.dto.localdate.LocalDateDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.ToString;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@ToString
public class DossierDto {

	private String numero;
	private Long affilieId;

	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	private LocalDate dateEnregistrement;


	public DossierDto(){}


	private DossierDto(String numero, Long affilieId, String dateEnregistrement){

		this.numero = numero;
		this.affilieId = affilieId;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

		this.dateEnregistrement = LocalDate.parse(dateEnregistrement,formatter);
	}


	public String getNumero() {
		return numero;
	}

	public LocalDate getDateEnregistrement() {
		return dateEnregistrement;
	}

	public Long getAffilieId () {
		return affilieId;
	}

	public static DossierDto from(String numero, Long affilieId, String dateEnregistrement) {

		return new DossierDto(numero,affilieId,dateEnregistrement);

	}


}

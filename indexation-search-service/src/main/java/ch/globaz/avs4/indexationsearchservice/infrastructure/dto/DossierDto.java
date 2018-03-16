package ch.globaz.avs4.indexationsearchservice.infrastructure.dto;

import ch.globaz.avs4.indexationsearchservice.infrastructure.repository.models.dossier.DossierStatus;
import ch.globaz.avs4.indexationsearchservice.infrastructure.repository.models.localdate.LocalDateDeserializer;
import ch.globaz.avs4.indexationsearchservice.infrastructure.repository.models.localdate.LocalDateSerializer;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;
import lombok.ToString;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@ToString
@Getter
public class DossierDto {

	private String identifiant;
	private Long affilieId;

	@JsonProperty("id")
	private Long technicalId;

	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	private LocalDate dateEnregistrement;

	private DossierStatus status;


	public DossierDto(){}


	private DossierDto(Long id, String numero, Long affilieId, String dateEnregistrement, DossierStatus status){

		this.identifiant = numero;
		this.affilieId = affilieId;
		this.technicalId = id;
		this.status = status;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

		this.dateEnregistrement = LocalDate.parse(dateEnregistrement,formatter);
	}




}

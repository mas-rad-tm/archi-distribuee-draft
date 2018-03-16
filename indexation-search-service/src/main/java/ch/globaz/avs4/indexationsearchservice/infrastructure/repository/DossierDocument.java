package ch.globaz.avs4.indexationsearchservice.infrastructure.repository;


import ch.globaz.avs4.indexationsearchservice.infrastructure.jms.event.DossierCreeEvent;
import ch.globaz.avs4.indexationsearchservice.infrastructure.repository.models.localdate.LocalDateDeserializer;
import ch.globaz.avs4.indexationsearchservice.infrastructure.repository.models.localdate.LocalDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
@Getter
@Document(indexName = "dossier", type = "avs")
public class DossierDocument {



	private Long affilieId;
	private String identifiant;
	@Id
	private Long id;

	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	private LocalDate dateEnregistrement;


	public DossierDocument(){}


	public DossierDocument(Long id, String identifiant, Long affilieId, String dateEnregistrement){

		this.id = id;
		this.affilieId = affilieId;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

		this.dateEnregistrement = LocalDate.parse(dateEnregistrement,formatter);
		this.identifiant = identifiant;
	}



	public static DossierDocument fromEvent(DossierCreeEvent dossierEvent){

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
		return new DossierDocument(dossierEvent.getId(),
				dossierEvent.getIdentifiant(),
				dossierEvent.getAffilieId(),
				dossierEvent.getDateEnregistrement());
	}
}

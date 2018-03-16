package ch.globaz.avs4.indexationsearchservice.infrastructure.dto;


public class DossierEtendueDto {

	private DossierDto dossierDto;
	private PersonnesPhysiqueDto personne;

	public DossierEtendueDto() {}

	public DossierEtendueDto(DossierDto dossierDto, PersonnesPhysiqueDto personne){
		this.dossierDto = dossierDto;
		this.personne = personne;
	}

	public DossierDto getDossier () {
		return dossierDto;
	}

	public PersonnesPhysiqueDto getPersonne () {
		return personne;
	}


}

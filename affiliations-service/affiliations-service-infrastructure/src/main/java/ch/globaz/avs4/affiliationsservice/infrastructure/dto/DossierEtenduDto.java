package ch.globaz.avs4.affiliationsservice.infrastructure.dto;


import org.springframework.hateoas.ResourceSupport;

public class DossierEtenduDto extends ResourceSupport{

	private DossierDto dossier;
	private PersonnesPhysiqueDto requerant;

	public DossierEtenduDto() {}

	public DossierEtenduDto(DossierDto dossier, PersonnesPhysiqueDto requerant){
		this.dossier = dossier;
		this.requerant = requerant;
	}

	public DossierDto getDossier() {
		return dossier;
	}

	public PersonnesPhysiqueDto getRequerant() {
		return requerant;
	}


}

package ch.globaz.avs4.indexationsearchservice.infrastructure.jms.event;



import ch.globaz.avs4.indexationsearchservice.infrastructure.repository.models.ValueObject;
import ch.globaz.avs4.indexationsearchservice.infrastructure.repository.models.dossier.Dossier;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@EqualsAndHashCode
@ToString
@Getter
public class DossierCreeEvent implements ValueObject<DossierCreeEvent> {

    private String identifiant;
    private String dateEnregistrement;
    private Long affilieId;
    private String status;
    private Long id;

    public DossierCreeEvent(Long id, String identifiant, String dateEnregistrement, Long affilieId, String status) {
        this.identifiant = identifiant;
        this.dateEnregistrement = dateEnregistrement;
        this.affilieId = affilieId;
        this.status = status;
        this.id = id;
    }

    public DossierCreeEvent(){}


    @Override
    public boolean sameValueAs(DossierCreeEvent other) {
        return this.equals(other);

    }
    public static DossierCreeEvent fromEntity(Dossier dossier) {
        return new DossierCreeEvent(dossier.getId(),
                dossier.getIdentifiant().identifiant(),dossier
                .getDateEnregistrementAsString(),
                dossier
                .affilieId(),
                dossier.status().toString());
    }

}

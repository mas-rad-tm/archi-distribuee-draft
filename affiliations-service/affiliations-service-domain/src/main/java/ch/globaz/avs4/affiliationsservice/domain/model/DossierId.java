package ch.globaz.avs4.affiliationsservice.domain.model;

import ch.globaz.avs4.affiliationsservice.domain.ValueObject;
import lombok.Getter;
import lombok.ToString;


import java.util.UUID;

@ToString
@Getter
public class DossierId implements ValueObject<DossierId> {


    private String identifiant;

    public DossierId(String identifiant) {
        this.identifiant = identifiant;
    }

    public static DossierId aleatoire () {
        return new DossierId(UUID.randomUUID().toString());
    }

    public String identifiant() {
        return identifiant;
    }

    @Override
    public boolean sameValueAs(DossierId other) {
        return this.equals(other);
    }

    DossierId(){}
}

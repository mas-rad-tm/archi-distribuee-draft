package ch.globaz.avs4.personnes.domain.model;

import ch.globaz.avs4.personnes.domain.ValueObject;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.util.Objects;


@EqualsAndHashCode
@Getter
public class NSS implements ValueObject<NSS>{

    NSS () {}


    private String nss;

    public NSS(String nss){

        Objects.requireNonNull(nss, "Le nss ne peut pas eter null");
        this.nss = nss;
    }

    public String nss(){
        return nss;
    }

    @Override
    public boolean sameValueAs(NSS other) {
        return this.equals(other);
    }
}

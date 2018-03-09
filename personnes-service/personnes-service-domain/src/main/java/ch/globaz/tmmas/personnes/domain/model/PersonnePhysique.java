package ch.globaz.tmmas.personnes.domain.model;

import lombok.ToString;

import java.time.LocalDate;

/**
 * Created by seb on .
 * <p>
 * ${VERSION}
 */
@ToString
public class PersonnePhysique {

    private String nom;
    private String prenom;
    private LocalDate dateNaissance;
    private NSS nss;
    private PersonnePhysiqueId identifiant;

    private PersonnePhysique(String nom, String prenom, LocalDate dateNaissance, NSS nss) {
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
        this.nss = nss;
    }

    public PersonnePhysique(Long id,String nom, String prenom, LocalDate dateNaissance, NSS nss) {
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
        this.nss = nss;
        this.id = id;
    }

    public String nom() {
        return nom;
    }

    public String prenom() {
        return prenom;
    }

    public LocalDate dateNaissance() {
        return dateNaissance;
    }

    public NSS nss () {
        return nss;
    }

   // public Long getId () {
      //  return id;
   // }

    public static PersonnePhysique builder(String nom, String prenom, LocalDate dateNaissance, NSS nss) {
        return new PersonnePhysique(nom,prenom,dateNaissance,nss);
    }

    //hibernate
    private Long id;

    PersonnePhysique(){}
}

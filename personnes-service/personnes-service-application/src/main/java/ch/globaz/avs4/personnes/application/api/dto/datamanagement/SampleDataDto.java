package ch.globaz.avs4.personnes.application.api.dto.datamanagement;

public class SampleDataDto {

    private String nbValeurs;

    public SampleDataDto () {}

    public SampleDataDto (String nbValeurs) {
        this.nbValeurs = nbValeurs;
    }

    public String getNbValeurs() {
        return nbValeurs;
    }
}

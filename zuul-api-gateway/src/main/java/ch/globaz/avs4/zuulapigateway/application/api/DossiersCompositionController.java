package ch.globaz.avs4.zuulapigateway.application.api;

import ch.globaz.avs4.zuulapigateway.application.datamanagement.SampleDataDto;
import ch.globaz.avs4.zuulapigateway.application.dto.DossierDto;
import ch.globaz.avs4.zuulapigateway.application.service.DossierService;
import ch.globaz.avs4.zuulapigateway.application.service.PersonneService;
import ch.globaz.avs4.zuulapigateway.application.dto.PersonnesPhysiqueDto;
import com.github.javafaker.Faker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping("/dossiers")
public class DossiersCompositionController {

    private static final Logger LOGGER = LoggerFactory.getLogger(DossiersCompositionController.class);

    @Autowired
    PersonneService personnePhysiqueService;

    @Autowired
    DossierService dossierService;

    static final Faker faker = new Faker(new Locale("fr"));

    @RequestMapping(value = "/sample",method = RequestMethod.POST)
    public ResponseEntity<String> createDossiersSampleData(@RequestBody SampleDataDto dto) {
        LOGGER.debug("createPerson data samples(), {} elements to generate", dto.getNbValeurs());

        //récupération du nombre de valeurs à générer
        Integer nbDossiersToGenerate = Integer.valueOf(dto.getNbValeurs());
        //récupérationd personnes physiques
        ResponseEntity<List<PersonnesPhysiqueDto>> personnesPhysiques = personnePhysiqueService.getPersonnesPhysique();

        Integer nbOfPersonnesPhysique = personnesPhysiques.getBody().size();

        //plafin nbRentes a geerer
        Integer nbDossiersToGenereateEffective = (nbDossiersToGenerate < nbOfPersonnesPhysique) ? nbDossiersToGenerate :
                nbOfPersonnesPhysique;

        LOGGER.debug("createPerson data samples(), found {} personnesPhysique, so {} dossiers will be generated ",
                nbOfPersonnesPhysique, nbOfPersonnesPhysique);

        //iteration sur les personnes, et generation des rentes
        personnesPhysiques.getBody().subList(0, nbDossiersToGenereateEffective).stream().forEach(personne -> {

            DossierDto dossierDto = DossierDto.from(getNumero(),personne.getId(),"11.11.2111");

            dossierService.sauveDossier(dossierDto);

        });

        return ResponseEntity.ok().body("OK Created");
    }

    private String getNumero () {
        return String.valueOf(faker.number().numberBetween(10000,99999));
    }

}

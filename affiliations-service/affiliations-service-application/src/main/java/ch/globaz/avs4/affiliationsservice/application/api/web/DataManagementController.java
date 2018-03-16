package ch.globaz.avs4.affiliationsservice.application.api.web;


import ch.globaz.avs4.affiliationsservice.application.service.DossierService;
import ch.globaz.avs4.affiliationsservice.domain.model.Dossier;
import ch.globaz.avs4.affiliationsservice.infrastructure.dto.datamanagement.SampleDataDto;
import ch.globaz.avs4.affiliationsservice.infrastructure.dto.PersonnesPhysiqueDto;
import ch.globaz.avs4.affiliationsservice.infrastructure.service.FeignPersonnesPhysiqueService;
import ch.globaz.avs4.affiliationsservice.infrastructure.service.RestTemplatePersonnesPhysiqueService;
import com.github.javafaker.Faker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping("/data")
public class DataManagementController {

    private static final Logger LOGGER = LoggerFactory.getLogger(DataManagementController.class);

    @Autowired
    DossierService dossierService;


    @Autowired
    FeignPersonnesPhysiqueService feignPersonnePhysiqueService;

    @Autowired
    RestTemplatePersonnesPhysiqueService restTemplatePersonnesPhysiqueService;



    static final Faker faker = new Faker(new Locale("fr"));
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    DateFormat df = new SimpleDateFormat("dd.MM.yyyy");

    @RequestMapping(value = "/sample",method = RequestMethod.POST)
    public ResponseEntity<String> createDossiersSampleData(@RequestBody SampleDataDto dto){
        LOGGER.debug("createPerson data samples(), {} elements to generate",dto.getNbValeurs());

        //récupération du nombre de valeurs à générer
        Integer nbDossiersToGenerate = Integer.valueOf(dto.getNbValeurs());
        //récupérationd personnes physiques
        List<PersonnesPhysiqueDto> personnesPhysiques  = feignPersonnePhysiqueService.getPersonnesPhysique().getBody();
        Integer nbOfPersonnesPhysique = personnesPhysiques.size();

        //plafin nbRentes a geerer
        Integer nbDossiersToGenereateEffective = (nbDossiersToGenerate < nbOfPersonnesPhysique) ? nbDossiersToGenerate :
                nbOfPersonnesPhysique;

        LOGGER.debug("createPerson data samples(), found {} personnesPhysique, so {} dossiers will be generated ",
                nbOfPersonnesPhysique,nbOfPersonnesPhysique);

        //iteration sur les personnes, et generation des rentes
        personnesPhysiques.subList(0,nbDossiersToGenereateEffective).stream().forEach(personne -> {

            Dossier dossier = Dossier.builder(personne.getTechnicalId(),LocalDate.now());

            dossierService.sauve(dossier);

        });


        return new ResponseEntity<String>("OK,  "+ nbDossiersToGenereateEffective +" dossiers créés",HttpStatus
                .CREATED);
    }

    @RequestMapping(value = "/sample-rt",method = RequestMethod.POST)
    public ResponseEntity<String> createDossiersSampleDataWithRestTemplate(@RequestBody SampleDataDto dto){
        LOGGER.debug("createPerson data samples(), {} elements to generate",dto.getNbValeurs());

        //récupération du nombre de valeurs à générer
        Integer nbDossiersToGenerate = Integer.valueOf(dto.getNbValeurs());
        //récupérationd personnes physiques
        List<PersonnesPhysiqueDto> personnesPhysiques  = restTemplatePersonnesPhysiqueService.getPersonnesPhysique();
        Integer nbOfPersonnesPhysique = personnesPhysiques.size();

        //plafin nbRentes a geerer
        Integer nbDossiersToGenereateEffective = (nbDossiersToGenerate < nbOfPersonnesPhysique) ? nbDossiersToGenerate :
                nbOfPersonnesPhysique;

        LOGGER.debug("createPerson data samples(), found {} personnesPhysique, so {} dossiers will be generated ",
                nbOfPersonnesPhysique,nbOfPersonnesPhysique);

        //iteration sur les personnes, et generation des rentes
        personnesPhysiques.subList(0,nbDossiersToGenereateEffective).stream().forEach(personne -> {

            Dossier dossier = Dossier.builder(personne.getTechnicalId(),LocalDate.now());

            dossierService.sauve(dossier);

        });


        return new ResponseEntity<String>("OK,  "+ nbDossiersToGenereateEffective +" dossiers créés",HttpStatus
                .CREATED);
    }



    private String getNumero () {
        return String.valueOf(faker.number().numberBetween(10000,99999));
    }

    private Long getAffilieId () {
        return Long.valueOf(faker.number().numberBetween(1,10000));
    }

    private LocalDate getDateEnregistrement () {

        Date date = faker.date().birthday(0,1);

        return LocalDate.parse(df.format(date),formatter);
    }



}

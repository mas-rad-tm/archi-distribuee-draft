package ch.globaz.avs4.indexationsearchservice.infrastructure.jms;

import ch.globaz.avs4.indexationsearchservice.infrastructure.jms.event.DossierCreeEvent;
import ch.globaz.avs4.indexationsearchservice.infrastructure.repository.DossierDocument;
import ch.globaz.avs4.indexationsearchservice.infrastructure.repository.PersonnePhysiqueIndexRepository;
import ch.globaz.avs4.indexationsearchservice.infrastructure.jms.event.PersonnesPhysiqueCreeEvent;
import ch.globaz.avs4.indexationsearchservice.infrastructure.repository.DossierIndexRepository;
import ch.globaz.avs4.indexationsearchservice.infrastructure.repository.PersonnePhysiqueDocument;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;

import javax.jms.JMSException;
import java.io.IOException;


public class IndexationListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(IndexationListener.class);


    @Autowired
    PersonnePhysiqueIndexRepository personnePhysiqueIndexRepository;

    @Autowired
    DossierIndexRepository dossierIndexRepository;

    @Autowired
    ObjectMapper mapper;

    @JmsListener(destination = "affiliations.q",containerFactory = "jmsListenerContainerFactory")
    public void onDossierMessage(String dossier) {

        LOGGER.info("received message='{}'", dossier);

        try {

            DossierCreeEvent event = mapper.readValue(dossier,DossierCreeEvent.class);

            dossierIndexRepository.save(DossierDocument.fromEvent(event));

            LOGGER.info("Successfully indexed'{}'", dossier);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @JmsListener(destination = "personnes.q",containerFactory = "jmsListenerContainerFactory")
    public void onPersonneMessage(String personne) {

        LOGGER.info("received message='{}'", personne);

        try {

            PersonnesPhysiqueCreeEvent event = mapper.readValue(personne,PersonnesPhysiqueCreeEvent.class);

            personnePhysiqueIndexRepository.save(PersonnePhysiqueDocument.fromEvent(event));

            LOGGER.info("Successfully indexed'{}'", personne);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @JmsListener(id="dlq.q",destination = "DLQ.*.*",containerFactory = "jmsListenerContainerFactory")
    public void onMessageDLQ(javax.jms.Message message) throws JMSException {

        LOGGER.info("received message FROM DLQ ='{}'", message);

        LOGGER.info(message.getJMSDestination().toString());


    }

}

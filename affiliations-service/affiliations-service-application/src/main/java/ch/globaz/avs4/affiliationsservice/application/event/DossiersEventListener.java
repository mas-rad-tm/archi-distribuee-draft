package ch.globaz.avs4.affiliationsservice.application.event;



import ch.globaz.avs4.affiliationsservice.domain.notification.NotificationService;
import ch.globaz.avs4.affiliationsservice.domain.event.DossierCreeEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class DossiersEventListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(DossiersEventListener.class);

    @Autowired
    NotificationService notificationService;

    @Autowired
    ObjectMapper mapper;

    @EventListener
    void dossierCreeEvent(DossierCreeEvent event) throws JsonProcessingException {

        LOGGER.info("DossierCree Event {}",event);

        notificationService.notify(mapper.writeValueAsString(event));

    }
}

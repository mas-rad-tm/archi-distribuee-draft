package ch.globaz.avs4.affiliationsservice.infrastructure.jms;


import ch.globaz.avs4.affiliationsservice.domain.notification.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MessageService implements NotificationService {

    @Autowired
    MessageSender jmsSender;

    @Override
    public void notify(String msg) {
        jmsSender.sendMessage(msg);
    }
}

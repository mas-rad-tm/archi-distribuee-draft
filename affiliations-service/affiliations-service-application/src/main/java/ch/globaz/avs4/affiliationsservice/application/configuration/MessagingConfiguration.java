package ch.globaz.avs4.affiliationsservice.application.configuration;



import ch.globaz.avs4.affiliationsservice.infrastructure.jms.MessageSender;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.jms.core.JmsTemplate;

import java.util.Arrays;

@Configuration
public class MessagingConfiguration {

    @Value("${activemq.broker-url}")
    private String brokerUrl;

    private static final String AFFILIATIONS_QUEUE = "affiliations.q";

    @Bean
    public ActiveMQConnectionFactory connectionFactory(){
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
        connectionFactory.setBrokerURL(brokerUrl);
        connectionFactory.setTrustedPackages(Arrays.asList("com.websystique.springmvc"));
       // connectionFactory.setRedeliveryPolicy(redeliveryPolicy());
        return connectionFactory;
    }

    @Bean
    public CachingConnectionFactory cachingConnectionFactory(){
        return new CachingConnectionFactory(connectionFactory());
    }



    @Bean
    public JmsTemplate jmsTemplate(){
        JmsTemplate template = new JmsTemplate();
        template.setConnectionFactory(cachingConnectionFactory());
        template.setDefaultDestinationName(AFFILIATIONS_QUEUE);
        return template;
    }


    @Bean
    public MessageSender sender() {
        return new MessageSender();
    }
}

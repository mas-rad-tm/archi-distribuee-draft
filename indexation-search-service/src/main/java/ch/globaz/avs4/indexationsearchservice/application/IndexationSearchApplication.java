package ch.globaz.avs4.indexationsearchservice.application;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jms.JmsAutoConfiguration;
import org.springframework.boot.autoconfigure.jms.activemq.ActiveMQAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.jms.config.JmsListenerEndpointRegistry;


@SpringBootApplication
@EnableAutoConfiguration(exclude = {ActiveMQAutoConfiguration.class, JmsAutoConfiguration.class})
@EnableDiscoveryClient
@ComponentScan(basePackages = "ch.globaz.avs4.indexationsearchservice")
public class IndexationSearchApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(IndexationSearchApplication.class);

    @Autowired
    private ApplicationContext appContext;




    public static void main(String[] args) {
        SpringApplication.run(IndexationSearchApplication.class);
    }

    @EventListener
    public void onApplicationEvent(ContextRefreshedEvent event) {

        LOGGER.info("APPLICATION STARTED EVENT");

       // JmsListenerEndpointRegistry customRegistry =
        //        appContext.getBean("jmsListenerContainerFactory", JmsListenerEndpointRegistry.class);

        JmsListenerEndpointRegistry registry = appContext.getBean(org.springframework.jms.config.JmsListenerEndpointRegistry
                .class);

        registry.getListenerContainer("dlq.q").stop();

        //registry.stop();
        //MessageListenerContainer dlqListenerContainer = customRegistry.getListenerContainer("dlq-listener");
        //customRegistry.stop();

        LOGGER.info("STOPPING DLQ LISTENER");
    }
}

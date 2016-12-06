package fr.osb.smartf.agent.dispatcher.main.config;


import fr.osb.smartf.agent.dispatcher.jms.MessageListener;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.RedeliveryPolicy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.listener.SimpleMessageListenerContainer;
import org.springframework.jms.listener.adapter.MessageListenerAdapter;
import org.springframework.util.ErrorHandler;

import javax.jms.ConnectionFactory;
import javax.jms.Session;

/**
 * Created by mpaltanea on 01.07.2016.
 */
@Configuration
@EnableJms
public class ActiveMQConfig {

    private static final Logger LOG = LoggerFactory.getLogger(ActiveMQConfig.class);

    @Value("${activemq.host}")
    private String host;

    @Value("${activemq.user}")
    private String user;

    @Value("${activemq.pass}")
    private String pass;

    @Value("${activemq.queue}")
    private String queue;

    @Value("${activemq.redeliveryDelay}")
    private int redeliveryDelay;

    @Value("${activemq.maxRedeliveries}")
    private int maxRedeliveries;

    @Bean
    public ConnectionFactory connectionFactory() {

        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(
                user,
                pass,
                host);

        RedeliveryPolicy redeliveryPolicy = new RedeliveryPolicy();
        redeliveryPolicy.setRedeliveryDelay(redeliveryDelay);
        redeliveryPolicy.setMaximumRedeliveries(maxRedeliveries);
        activeMQConnectionFactory.setRedeliveryPolicy(redeliveryPolicy);

        return new CachingConnectionFactory(activeMQConnectionFactory);
    }

    @Bean
    public MessageListenerAdapter receiver(MessageListener messageListener) {
        return new MessageListenerAdapter(messageListener) {{
            setMessageConverter(null);
            setDefaultListenerMethod("receiveMessage");
        }};
    }

    @Bean
    public ErrorHandler errorHandler() {
        return e -> LOG.error("rollback message");
    }


    @Bean
    public SimpleMessageListenerContainer container(
            final MessageListenerAdapter messageListenerAdapter,
            final ConnectionFactory connectionFactory,
            final ErrorHandler errorHandler
    ) {

        return new SimpleMessageListenerContainer() {
            {
                setConnectionFactory(connectionFactory);
                setMessageListener(messageListenerAdapter);
                setDestinationName(queue);
                setErrorHandler(errorHandler);
                setSessionAcknowledgeMode(Session.CLIENT_ACKNOWLEDGE); //javax.jms.Session#CLIENT_ACKNOWLEDGE
            }
        };
    }

    @Bean
    JmsTemplate jmsTemplate(ConnectionFactory connectionFactory) {
        return new JmsTemplate(connectionFactory);
    }

    /*@Bean
    public Queue queue() {
        return new ActiveMQQueue("events");
    }*/

}

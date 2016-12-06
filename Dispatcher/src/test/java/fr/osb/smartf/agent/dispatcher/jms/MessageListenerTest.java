package fr.osb.smartf.agent.dispatcher.jms;

import fr.osb.smartf.agent.dispatcher.TestContextConfiguration;
import org.apache.activemq.command.ActiveMQQueue;
import org.junit.After;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.OutputCapture;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.jms.*;
import java.util.Enumeration;
/**
 * Created by mpaltanea on 22.07.2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = TestContextConfiguration.class)
@WebIntegrationTest("server.port:0")
//@WebAppConfiguration
public class MessageListenerTest {

    @Autowired
    ConnectionFactory connectionFactory;

    @Value("${activemq.queue}")
    private String queue;

    @Rule
    public OutputCapture outputCapture = new OutputCapture();

    @Test
    public void testInvalidMessage() throws Exception {
        JmsTemplate jmsTemplate = new JmsTemplate(connectionFactory);
        jmsTemplate.send( new ActiveMQQueue(queue), new MessageCreator() {
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage("this is not a valid message");
            }});

        this.outputCapture.notify();
        Thread.sleep(2000L);
        Assert.assertTrue(this.outputCapture.toString().contains("invalid message"));
    };

    @Test
    public void testNoAvailableAgents() throws Exception {
        JmsTemplate jmsTemplate = new JmsTemplate(connectionFactory);
        jmsTemplate.send( new ActiveMQQueue(queue), new MessageCreator() {
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage("{\"fileName\":\"foo.txt\", \"importType\":\"builtPatrimony\"}");
            }});

        Thread.sleep(5000L);
        Assert.assertTrue(this.outputCapture.toString().contains("No instances found"));
    };

    @After
    public void tearDown() {
        clearQueue();
    }

    private void clearQueue() {
        try {
            Connection con = connectionFactory.createConnection();

            Session session = con.createSession(false, 1);
            Queue destination = session.createQueue(queue);

            QueueBrowser browser = session.createBrowser(destination);

            Enumeration<?> enum1 = browser.getEnumeration();

            while (enum1.hasMoreElements()) {
                TextMessage msg = (TextMessage) enum1.nextElement();
                System.out.println(msg.getText());
                msg.acknowledge();
                MessageConsumer consumer = session.createConsumer(destination, "id='" +   msg.getStringProperty("id") + "'");
                consumer.receive();
            }
        } catch (JMSException jmsEx) {

        }
    }



}


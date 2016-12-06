package fr.osb.smartf.agent.dispatcher.jms;


import fr.osb.smartf.agent.dispatcher.controller.ImportFileFormat;
import fr.osb.smartf.agent.dispatcher.controller.dto.ImportFileParams;
import fr.osb.smartf.agent.dispatcher.controller.dto.WorkerType;
import fr.osb.smartf.agent.dispatcher.exception.types.TechnicalException;
import fr.osb.smartf.agent.dispatcher.service.AgentService;
import fr.osb.smartf.agent.dispatcher.service.response.WorkerInfo;
import org.apache.activemq.command.ActiveMQTextMessage;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by mpaltanea on 27.06.2016.
 */

@Component
public class MessageListener {
    private static final Logger LOG = LoggerFactory.getLogger(MessageListener.class);

    private static int i = 0;

    private final AgentService agentService;

    @Autowired
    public MessageListener(AgentService agentService) {
        this.agentService = agentService;
    }

    public void receiveMessage(final Message message) throws JMSException {
        String msgText = ((ActiveMQTextMessage) message).getText();
        LOG.info("received message: " + msgText);

        try {
            ImportFileParams params = new ObjectMapper().readValue(msgText, ImportFileParams.class);
            synchronized (this) {
                WorkerInfo activeAgent = agentService.getActiveWorker(WorkerType.importer);

                //only acknowledge message if available workers found
                message.acknowledge();
                importFile(activeAgent, params);
            }
        } catch (TechnicalException techEx) {
            throw new JMSException(techEx.getMessage());
        } catch (JsonMappingException jmEx) {
            LOG.error("invalid message: " + jmEx.getMessage());
        } catch (JsonParseException jpEx) {
            LOG.error("invalid message: " + jpEx.getMessage());
        } catch (IOException ioEx) {
            LOG.error("invalid message: " + ioEx.getMessage());
        }

    }

    private void importFile(WorkerInfo activeAgent, ImportFileParams param) throws JMSException  {
        ImportFileFormat fileExtension = ImportFileFormat.getExtensionType(param.getFileName());
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        executorService.shutdown();
        LOG.info("Start import file " + param.getFileName());
        agentService.delegate(activeAgent, WorkerType.importer, param.getImportType() + "/" + fileExtension.getMethodName(), param, MediaType.APPLICATION_JSON);
        LOG.info("Started import file " + param.getFileName());

    }
}

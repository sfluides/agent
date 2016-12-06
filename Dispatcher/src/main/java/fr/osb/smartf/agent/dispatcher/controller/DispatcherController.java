package fr.osb.smartf.agent.dispatcher.controller;

import fr.osb.smartf.agent.dispatcher.controller.dto.ImportFileParams;
import fr.osb.smartf.agent.dispatcher.controller.dto.ImportType;
import fr.osb.smartf.agent.dispatcher.controller.dto.ImportWsParams;
import fr.osb.smartf.agent.dispatcher.controller.dto.WorkerType;
import fr.osb.smartf.agent.dispatcher.exception.types.TechnicalException;
import fr.osb.smartf.agent.dispatcher.service.AgentService;
import fr.osb.smartf.agent.dispatcher.service.response.WorkerInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.core.Response;
import java.util.List;

//import org.springframework.jms.annotation.JmsListener;

/**
 * Created by mpaltanea on 11.04.2016.
 */

@RestController
public class DispatcherController /*implements DependencyWatcherListener*/ {

    private static final Logger LOG = LoggerFactory.getLogger(DispatcherController.class);
    private final AgentService agentService;

    @Autowired
    public DispatcherController(AgentService agentService) {
        this.agentService = agentService;
    }

    @RequestMapping("/delegateImportFile")
    public String delegateImportFile(
            @RequestBody ImportFileParams params) {
        WorkerInfo activeWorker = agentService.getActiveWorker(WorkerType.importer);
        importFile(activeWorker, params);
        return Response.ok().build().toString();
    }

    private void importFile(WorkerInfo activeAgent, ImportFileParams param) throws TechnicalException {
        ImportFileFormat fileExtension = ImportFileFormat.getExtensionType(param.getFileName());
        agentService.delegate(activeAgent, WorkerType.importer, param.getImportType() + "/" + fileExtension.getMethodName(), param, MediaType.APPLICATION_JSON);
    }

    @RequestMapping("/delegateImportRawDataFromWs")
    public String delegateImportRawDataFromWs(
            @RequestBody ImportWsParams param) {
        WorkerInfo activeWorker = agentService.getActiveWorker(WorkerType.importer);
        return agentService.delegate(activeWorker, WorkerType.importer, param.getImportType().name() + "/importRawDataFromWs", param, MediaType.APPLICATION_JSON).getBody();
    }

    @RequestMapping(value = "/delegateImportRawDataFromJson/{importType}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.POST
    )
    public String delegateImportRawDataFromJson(@RequestBody String rawData,
                                                @PathVariable("importType") ImportType importType) {
        WorkerInfo activeWorker = agentService.getActiveWorker(WorkerType.importer);
        return agentService.delegate(activeWorker, WorkerType.importer, importType.name() + "/importJson", rawData, MediaType.APPLICATION_JSON).getBody();
    }

    @RequestMapping(value = "/delegateImportRawDataFromXml/{importType}",
            produces = MediaType.APPLICATION_XML_VALUE,
            consumes = MediaType.APPLICATION_XML_VALUE
    )
    public String delegateImportRawDataFromXml(
            @RequestBody String rawData,
            @PathVariable("importType") ImportType importType) {
        WorkerInfo activeWorker = agentService.getActiveWorker(WorkerType.importer);
        return agentService.delegate(activeWorker, WorkerType.importer, importType.name() + "/importXml", rawData, MediaType.APPLICATION_XML).getBody();
    }

    @RequestMapping(value = "/delegateCalcumConsumption",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public String delegateCalcumConsumption(
            @RequestBody String rawData) {
        WorkerInfo activeWorker = agentService.getActiveWorker(WorkerType.importer);
        return agentService.delegate(activeWorker, WorkerType.processor, "/process/calculateConsumption", rawData, MediaType.APPLICATION_JSON).getBody();
    }

    /**
     * get the list of workers registered in Zookeeper
     * the WorkerInfo object contains info about the worker's address and status
     *
     * @return List of WorkerInfo
     */
    @RequestMapping(value = "/agents",
            produces = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.GET
    )
    public List<WorkerInfo> agents() {
        List<WorkerInfo> agents = agentService.getWorkers(WorkerType.importer);
        return agents;
    }

    /**
     * get status of external resources of agents
     */
    @RequestMapping(value = "/externalResources",
            produces = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.GET)
    public String externalResources() {
        return agentService.getExternalResources();
    }

}

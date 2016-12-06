package fr.osb.smartf.agent.worker.controller;

import fr.osb.smartf.agent.worker.controller.dto.ImportWsParams;
import fr.osb.smartf.agent.worker.io.RestConsumerToMongoService;
import fr.osb.smartf.agent.worker.service.response.ResourcesInfo;
import fr.osb.smartf.agent.worker.service.response.WorkerInfo;
import fr.osb.smartf.agent.worker.service.util.PingService;
import fr.osb.smartf.agent.worker.service.util.ResourcesStatusService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.core.Response;

/**
 * Created by mpaltanea on 07.04.2016.
 */

@RestController
public class WorkerController {

    private static final Logger LOG = LoggerFactory.getLogger(WorkerController.class);

    private final DiscoveryClient discovery;
    private final PingService pingService;
    private final RestConsumerToMongoService restConsumerServiceMongo;
    private final ResourcesStatusService resourcesStatusService;

    private static final int DEFAULT_NR_THREADS = 20;
    private static final int DEFAULT_CHUNK_SIZE = 10000;

    @Value("${import.basedir}")
    private String importDir;

    @Autowired
    public WorkerController(DiscoveryClient discovery,
                            PingService pingService,
                            RestConsumerToMongoService restConsummerServiceMongo,
                            ResourcesStatusService resourcesStatusService) {
        this.discovery = discovery;
        this.pingService = pingService;
        this.restConsumerServiceMongo = restConsummerServiceMongo;
        this.resourcesStatusService = resourcesStatusService;
    }

    @ApiOperation(value = "External resources status",
            notes = "External resources status",
            response = String.class)
    @ResponseStatus(value = HttpStatus.OK)
    @RequestMapping(value = "/resourcesStatus",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResourcesInfo resourcesInfo() {
        return resourcesStatusService.getWorkerResourcesInfo();
    }

    @ApiOperation(value = "Ping service for testing application deploy",
            notes = "Ping service for testing application deploy",
            response = String.class)
    @ApiResponses(value = {
            @ApiResponse(code = 401, message = "Error based on request!"),
            @ApiResponse(code = 500, message = "Error based on internal server behavior!")
    })
    @ResponseStatus(value = HttpStatus.OK)
    @RequestMapping(value = "/ping",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public WorkerInfo ping() {
        WorkerInfo workerInfo = pingService.getWorkerStatus();
        LOG.info("Called ping() service. STATUT: " + workerInfo.getStatus());
        return workerInfo;
    }

    @RequestMapping(value = "/hi",
            method = RequestMethod.GET,
            produces = "application/json")
    public String hi() {
        LOG.info("Called hi() service.");
        return "Hello from " + this.discovery.getLocalServiceInstance();
    }

//    @RequestMapping(value = "/importRawDataFromCsv", method = RequestMethod.POST, produces =
//            {MediaType.APPLICATION_JSON_VALUE})
//    public Response importRawDataFromCsv(@RequestBody ImportCsvInput input,
//                                         @RequestHeader(value = "Authorization") String authorizationHeader) {
//
//        LOG.info("Called importRawDataFromCsv(" + input.toString() + ") service.");
//
//        fileParserServiceMongo.setFile(new File(importDir + "/" +
//                input.getImportType() + "/" +
//                input.getFileName()));
//        fileParserServiceMongo.setImportType(input.getImportType() + "");
//
//        try {
//            fileParserServiceMongo.processAll(DEFAULT_NR_THREADS, DEFAULT_CHUNK_SIZE);
//
//        } catch (InvalidPathException service) {
//            LOG.error(service.getMessage());
//            return Response.status(Response.Status.BAD_REQUEST).build();
//        } catch (IOException service) {
//            LOG.error(service.getMessage());
//            return Response.status(Response.Status.BAD_REQUEST).build();
//        } catch (InterruptedException service) {
//            LOG.error(service.getMessage());
//            return Response.status(Response.Status.BAD_REQUEST).build();
//        } catch (ExecutionException service) {
//            LOG.error(service.getMessage());
//            return Response.status(Response.Status.BAD_REQUEST).build();
//        }
//
//        return Response.ok().build();
//    }

    @RequestMapping(value = "/importRawDataFromWs", method = RequestMethod.POST, produces =
            {MediaType.APPLICATION_JSON_VALUE})
    public Response importRawDataFromWs(@RequestBody ImportWsParams params,
                                        @RequestHeader(value = "Authorization") String authorizationHeader) {

        LOG.info("Called importRawDataFromWS(" + params.toString() + ") service.");
        restConsumerServiceMongo.setImportType(params.getImportType());
        restConsumerServiceMongo.makeRequest(params.getUrl());
        return Response.ok().build();
    }

//    @RequestMapping(value = "/importRawDataFromJson", method = RequestMethod.POST,
//            consumes = {MediaType.APPLICATION_JSON_VALUE},
//            produces = {MediaType.APPLICATION_JSON_VALUE}
//    )
//    public Response importRawDataFromJson(@RequestBody ImportJsonInput input) {
//
//        LOG.info("Called importRawDataFromJson(" + input.toString() + ") service.");
//
//        return Response.ok().build();
//    }


//    @RequestMapping(value = "/importRawDataFromXml", method = RequestMethod.POST,
//            consumes = {MediaType.APPLICATION_XML_VALUE},
//            produces = {MediaType.APPLICATION_JSON_VALUE}
//    )
//    public Response importRawDataFromXml(@RequestBody ImportXmlInput xml) {
//
//        LOG.info("Called importRawDataFromXml(" + xml.toString() + ") service.");
//
//        return Response.ok().build();
//    }
}

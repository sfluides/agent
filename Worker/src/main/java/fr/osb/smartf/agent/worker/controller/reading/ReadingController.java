package fr.osb.smartf.agent.worker.controller.reading;

import fr.osb.smartf.agent.worker.controller.dto.ImportFileParams;
import fr.osb.smartf.agent.worker.service.LaunchJobService;
import fr.osb.smartf.agent.worker.service.ReadingService;
import fr.osb.smartf.agent.worker.service.dto.ReadingDTO;
import fr.osb.smartf.agent.worker.service.dto.ReadingDTOList;
import fr.osb.smartf.agent.worker.service.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

import static fr.osb.smartf.agent.worker.batch.reading.ImportReadingJobConfig.*;

/**
 * Created by mpaltanea on 09.06.2016.
 */
@RestController
@RequestMapping("/reading")
public class ReadingController {

    private static final Logger LOG = LoggerFactory.getLogger(ReadingController.class);

    private final ReadingService readingService;
    private final WorkerStatusService workerStatusService;
    private final TreatmentService treatmentService;
    private final LaunchJobService launchJobService;

    @Autowired
    public ReadingController(ReadingService readingService,
                             WorkerStatusService workerStatusService,
                             TreatmentService treatmentService,
                             LaunchJobService launchJobService) {
        this.readingService = readingService;
        this.workerStatusService = workerStatusService;
        this.treatmentService = treatmentService;
        this.launchJobService = launchJobService;
    }

    /**
     * Import readings with json content in request body
     *
     * @param list of ReadingDTO
     * @return HTTP response
     */
    @RequestMapping(value = "/importJson",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON},
            consumes = {MediaType.APPLICATION_JSON}
    )
    public Response importJson(@RequestBody List<ReadingDTO> list) {
        LOG.info("Called importReadingFromJson");
        workerStatusService.updateWorkerStatus(WorkerStatus.busy);
        String idTreatment = treatmentService.registerTreatment(ImportType.reading, ImportFormat.REST_JSON, null);
        readingService.save(list);
        treatmentService.finalizeTreatment(idTreatment, list.size(), ExitStatus.COMPLETED);
        workerStatusService.updateWorkerStatus(WorkerStatus.available);
        LOG.info("ReadingDTO@List saved in DB");
        return Response.ok().build();
    }

    /**
     * Import readings with xml content in request body
     *
     * @param readingsListWrapper of OrganizationDTO
     * @return HTTP response
     */
    @RequestMapping(value = "/importXml",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_XML},
            consumes = {MediaType.APPLICATION_XML}
    )
    public Response importXml(@RequestBody ReadingDTOList readingsListWrapper) {
        LOG.info("Called importOrganizationFromJson");
        workerStatusService.updateWorkerStatus(WorkerStatus.busy);
        String idTreatment = treatmentService.registerTreatment(ImportType.reading, ImportFormat.REST_XML, null);
        readingService.save(readingsListWrapper.getReadings());
        treatmentService.finalizeTreatment(idTreatment, readingsListWrapper.getReadings().size(), ExitStatus.COMPLETED);
        workerStatusService.updateWorkerStatus(WorkerStatus.available);
        LOG.info("OrganizationDTO@List saved in DB");
        return Response.ok().build();
    }

    /**
     * Import readings xml file
     *
     * @param importFileParams{importType, fileName}
     * @return HTTP response
     */
    @RequestMapping(value = "/importXmlFile",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON},
            consumes = {MediaType.APPLICATION_JSON}
    )
    public Response importXmlFile(@RequestBody ImportFileParams importFileParams) {
        try {
            launchJobService.launchJob(IMPORT_READING_XML, ImportType.reading, ImportFormat.FILE_XML, importFileParams);
        } catch (Exception e) {
            LOG.error(e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
        return Response.ok().build();
    }

    /**
     * Import readings excel file
     *
     * @param importFileParams{importType, fileName}
     * @return HTTP response
     */
    @RequestMapping(value = "/importExcelFile",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON},
            consumes = {MediaType.APPLICATION_JSON}
    )
    public Response importExcelFile(@RequestBody ImportFileParams importFileParams) {
        try {
            launchJobService.launchJob(IMPORT_READING_EXCEL, ImportType.reading, ImportFormat.FILE_XLS, importFileParams);
        } catch (Exception e) {
            LOG.error(e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
        return Response.ok().build();
    }


    /**
     * Import readings csv file
     *
     * @param importFileParams{importType, fileName}
     * @return HTTP response
     */
    @RequestMapping(value = "/importCsvFile",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON},
            consumes = {MediaType.APPLICATION_JSON}
    )
    public Response importCsvFile(@RequestBody ImportFileParams importFileParams) {
        try {
            launchJobService.launchJob(IMPORT_READING_CSV, ImportType.reading, ImportFormat.FILE_CSV, importFileParams);
        } catch (Exception e) {
            return Response.serverError().build();
        }
        return Response.ok().build();
    }
}

package fr.osb.smartf.agent.worker.controller.organization;

import fr.osb.smartf.agent.worker.controller.dto.ImportFileParams;
import fr.osb.smartf.agent.worker.service.LaunchJobService;
import fr.osb.smartf.agent.worker.service.OrganizationService;
import fr.osb.smartf.agent.worker.service.dto.OrganizationDTO;
import fr.osb.smartf.agent.worker.service.dto.OrganizationsDTOList;
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

import static fr.osb.smartf.agent.worker.batch.organization.ImportOrganizationJobConfig.*;


/**
 * Created by szagoret on 01.06.2016.
 */
@RestController
@RequestMapping("/organization")
public class OrganizationController {

    private static final Logger LOG = LoggerFactory.getLogger(OrganizationController.class);

    private final OrganizationService organizationService;
    private LaunchJobService launchJobService;
    private WorkerStatusService workerStatusService;
    private TreatmentService treatmentService;

    @Autowired
    public OrganizationController(OrganizationService organizationService,
                                  LaunchJobService launchJobService,
                                  WorkerStatusService workerStatusService,
                                  TreatmentService treatmentService) {
        this.organizationService = organizationService;
        this.launchJobService = launchJobService;
        this.workerStatusService = workerStatusService;
        this.treatmentService = treatmentService;
    }

    /**
     * Import organizations with json content in request body
     *
     * @param list of OrganizationDTO
     * @return HTTP response
     */
    @RequestMapping(value = "/importJson",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON},
            consumes = {MediaType.APPLICATION_JSON}
    )
    public Response importJson(@RequestBody List<OrganizationDTO> list) {
        LOG.info("Called importOrganizationFromJson");
        workerStatusService.updateWorkerStatus(WorkerStatus.busy);
        String idTreatment = treatmentService.registerTreatment(ImportType.organization, ImportFormat.REST_JSON, null);
        organizationService.save(list);
        treatmentService.finalizeTreatment(idTreatment, list.size(), ExitStatus.COMPLETED);
        workerStatusService.updateWorkerStatus(WorkerStatus.available);
        LOG.info("OrganizationDTO@List saved in DB");
        return Response.ok().build();
    }

    /**
     * Import organizations with xml content in request body
     *
     * @param organizationsListWrapper of OrganizationDTO
     * @return HTTP response
     */
    @RequestMapping(value = "/importXml",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON},
            consumes = {MediaType.APPLICATION_XML}
    )
    public Response importXml(@RequestBody OrganizationsDTOList organizationsListWrapper) {
        LOG.info("Called importOrganizationFromJson");
        workerStatusService.updateWorkerStatus(WorkerStatus.busy);
        String idTreatment = treatmentService.registerTreatment(
                ImportType.organization, ImportFormat.REST_XML, null);
        organizationService.save(organizationsListWrapper.getOrganizations());
        treatmentService.finalizeTreatment(idTreatment, organizationsListWrapper.getOrganizations().size(), ExitStatus.COMPLETED);
        workerStatusService.updateWorkerStatus(WorkerStatus.available);
        LOG.info("OrganizationDTO@List saved in DB");
        return Response.ok().build();
    }


    /**
     * Import organizations xml file
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
            launchJobService.launchJob(IMPORT_ORGANIZATION_XML, ImportType.organization, ImportFormat.FILE_XML, importFileParams);
        } catch (Exception e) {
            LOG.error(e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
        return Response.ok().build();
    }

    /**
     * Import organizations excel file
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
            launchJobService.launchJob(IMPORT_ORGANIZATION_EXCEL, ImportType.organization, ImportFormat.FILE_XLS, importFileParams);
        } catch (Exception e) {
            LOG.error(e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
        return Response.ok().build();
    }


    /**
     * Import organizations csv file
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
            launchJobService.launchJob(IMPORT_ORGANIZATION_CSV, ImportType.organization, ImportFormat.FILE_CSV, importFileParams);
        } catch (Exception e) {
            LOG.error(e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
        return Response.ok().build();
    }


}

package fr.osb.smartf.agent.worker.controller.patrimony;

import fr.osb.smartf.agent.worker.controller.dto.ImportFileParams;
import fr.osb.smartf.agent.worker.service.LaunchJobService;
import fr.osb.smartf.agent.worker.service.util.ImportFormat;
import fr.osb.smartf.agent.worker.service.util.ImportType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static fr.osb.smartf.agent.worker.batch.patrimony.ImportPatrimonyJobConfig.IMPORT_BUILT_PATRIMONY_XML;

/**
 * Created by mpaltanea on 13.07.2016.
 */
@RestController
@RequestMapping("/builtPatrimony")
public class BuiltPatrimonyController {

    private static final Logger LOG = LoggerFactory.getLogger(BuiltPatrimonyController.class);

    private final LaunchJobService launchJobService;

    @Autowired
    public BuiltPatrimonyController(LaunchJobService launchJobService) {
        this.launchJobService = launchJobService;
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
            launchJobService.launchJob(IMPORT_BUILT_PATRIMONY_XML, ImportType.builtPatrimony, ImportFormat.FILE_XML, importFileParams);
        } catch (Exception e) {
            LOG.error(e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
        return Response.ok().build();
    }


}

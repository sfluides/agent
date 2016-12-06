package fr.osb.smartf.agent.worker.controller;

import fr.osb.smartf.agent.worker.service.LaunchJobService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.core.Response;

import static fr.osb.smartf.agent.worker.batch.reading.CalculateConsumptionJobConfig.CALCUL_CONSUM;

/**
 * Created by mpaltanea on 10.06.2016.
 */

@RestController
@RequestMapping("/process")
public class ProcessorController {
    private static final Logger LOG = LoggerFactory.getLogger(ProcessorController.class);

    @Autowired
    LaunchJobService launchJobService;

    @RequestMapping(value = "/calculateConsumption",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public Response calculateConsumption() {
        try {
            launchJobService.launchJob(CALCUL_CONSUM);
        } catch (Exception e) {
            LOG.error(e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
        return Response.ok().build();
    }
}

package fr.osb.smartf.agent.worker.soap.endpoints;

/*import agent.smartf.osb.fr.worker.schema.site.InsertSiteRequest;
import agent.smartf.osb.fr.worker.schema.site.InsertSiteResponse;
import agent.smartf.osb.fr.worker.schema.site.Response;
import agent.smartf.osb.fr.worker.schema.site.SiteInput;*/

import org.springframework.ws.server.endpoint.annotation.Endpoint;

/**
 * Created by mpaltanea on 20.05.2016.
 */

@Endpoint
public class InsertSiteEndpoint {

    /*private static final Logger LOG = LoggerFactory.getLogger(InsertSiteEndpoint.class);

    SiteRepository siteRepository;

    @Autowired
    public InsertSiteEndpoint(SiteRepository siteRepository) {
        this.siteRepository = siteRepository;
    }

    @PayloadRoot(namespace = "http://fr.osb.smartf.agent/worker/schema/site", localPart = "insertSiteRequest")
    @ResponsePayload
    public InsertSiteResponse getCityByCode(@RequestPayload InsertSiteRequest request) {
        LOG.info("entering endpoint");

        Site site = new Site();
        SiteInput input = request.getSite();
        site.setName(input.getName());
        site.setTown(input.getTown());
        site.setAddress(input.getAddress());

        siteRepository.insert(site);

        InsertSiteResponse insertSiteResponse = new InsertSiteResponse();
        insertSiteResponse.setResponse(new Response());

        return insertSiteResponse;
    }*/
}

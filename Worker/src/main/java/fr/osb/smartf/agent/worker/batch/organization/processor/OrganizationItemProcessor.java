package fr.osb.smartf.agent.worker.batch.organization.processor;

import fr.osb.smartf.agent.worker.mongo.model.Organization;
import fr.osb.smartf.agent.worker.service.dto.OrganizationDTO;
import fr.osb.smartf.agent.worker.service.dto.OrganizationDTOMapper;
import org.springframework.batch.item.ItemProcessor;

/**
 * Created by szagoret on 18.07.2016.
 */
public class OrganizationItemProcessor implements ItemProcessor<OrganizationDTO, Organization> {

    @Override
    public Organization process(OrganizationDTO organizationDTO) throws Exception {
        return OrganizationDTOMapper.toOrganization(organizationDTO);
    }
}

package fr.osb.smartf.agent.worker.batch.organization;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * Created by szagoret on 06.06.2016.
 */
@Configuration
@ImportResource({
        "classpath:batch/organization/ImportOrganizationSupport.xml",
        "classpath:batch/organization/ImportOrganizationCsvJob.xml",
        "classpath:batch/organization/ImportOrganizationExcelJob.xml",
        "classpath:batch/organization/ImportOrganizationXmlJob.xml"
})
public class ImportOrganizationJobConfig {

    /**
     * Names of imported jobs
     */
    public static final String IMPORT_ORGANIZATION_CSV = "importOrganizationCSVJob";
    public static final String IMPORT_ORGANIZATION_EXCEL = "importOrganizationExcelJob";
    public static final String IMPORT_ORGANIZATION_XML = "importOrganizationXmlJob";

}

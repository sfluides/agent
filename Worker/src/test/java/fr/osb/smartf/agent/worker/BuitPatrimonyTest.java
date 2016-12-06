package fr.osb.smartf.agent.worker;

import com.couchbase.client.java.bucket.BucketManager;
import fr.osb.smartf.agent.worker.service.dto.patrimony.BuildingDTO;
import fr.osb.smartf.agent.worker.service.dto.patrimony.HousingDTO;
import fr.osb.smartf.agent.worker.service.dto.patrimony.LocalDTO;
import fr.osb.smartf.agent.worker.service.dto.patrimony.ResidenceDTO;
import fr.osb.smartf.agent.worker.service.util.ImportFormat;
import fr.osb.smartf.agent.worker.service.util.ImportType;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.data.couchbase.core.CouchbaseTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;

/**
 * Created by mpaltanea on 28.07.2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = TestContextConfiguration.class)
@WebIntegrationTest("server.port:0")
/*@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
                        "classpath:/test-config.xml",
                        "classpath:batch/batch-config.xml",
                        "classpath:worker/worker-config.xml",
                        "classpath:batch/patrimony/ImportPatrimonyXmlJob.xml",
                        "classpath:batch/AbstractParentJob.xml"

                        })*/
public class BuitPatrimonyTest {

    private static final Logger LOG = LoggerFactory.getLogger(BuitPatrimonyTest.class);
    private static final String builtPatrimonyTestFile = "BuiltPatrimonyTestFile.xml";

    @Autowired
    private ApplicationContext context;

    @Autowired
    @Qualifier("jobLauncherBuiltPatrimonyTestUtils")
    private JobLauncherTestUtils jobLauncherTestUtils;

    @Autowired
    private CouchbaseTemplate couchbaseTemplate;

    @Value("${import.basedir}")
    private String importDir;

    @Before
    public void setUp() {
        LOG.debug("copy file to import dir");
        String destination = importDir + "\\" + ImportType.builtPatrimony + "\\" + builtPatrimonyTestFile;
        Resource resource = context.getResource("classpath:"+builtPatrimonyTestFile);
        try {
            Files.copy(resource.getInputStream(), Paths.get(destination), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ioEx) {
            LOG.error("error copying file: "+ ioEx.getMessage());
        }
    }

    @Test
    public void testImportBuildPatrimony() throws Exception{

        JobParameters jobParameters = new JobParametersBuilder()
                .addDate("date", new Date())
                .addString("importType", ImportType.builtPatrimony.name())
                .addString("importFormat", ImportFormat.FILE_XML.name())
                .addString("fileName", builtPatrimonyTestFile)
                .addString("pathToFile", importDir + "\\" + ImportType.builtPatrimony.name() +
                        "\\" + builtPatrimonyTestFile).toJobParameters();

        JobExecution exec = jobLauncherTestUtils
                .launchStep("importBuiltPatrimonyXmlStep", jobParameters);

        int count = 0;
        while(exec.isRunning() && count <= 100){
            Thread.sleep(100);
            count++;
        }

        Assert.assertEquals(BatchStatus.COMPLETED, exec.getStatus());

        ResidenceDTO residence = couchbaseTemplate.findById("7574", ResidenceDTO.class);
        Assert.assertNotNull(residence);
        Assert.assertEquals("France", residence.getCountry());

        BuildingDTO building = couchbaseTemplate.findById("27398", BuildingDTO.class);
        Assert.assertNotNull(residence);
        Assert.assertEquals("MARCQ EN BAROEUL / 1", building.getName());

        HousingDTO housing = couchbaseTemplate.findById("340239", HousingDTO.class);
        Assert.assertNotNull(housing);
        Assert.assertEquals("M.MME DEGUINES Michael", housing.getTenantLastName());

        LocalDTO local = couchbaseTemplate.findById("1061409", LocalDTO.class);
        Assert.assertNotNull(local);
        Assert.assertEquals("COMPTEUR GENERAL", local.getTenantLastName());
    }

    @After
    public void tearDown() {
        LOG.debug("flush couchbase bucket");
        BucketManager bucketManager = couchbaseTemplate.getCouchbaseBucket().bucketManager();
        bucketManager.flush();
    }

}

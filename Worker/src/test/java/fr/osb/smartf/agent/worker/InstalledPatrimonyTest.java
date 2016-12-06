package fr.osb.smartf.agent.worker;

import com.couchbase.client.java.bucket.BucketManager;
import fr.osb.smartf.agent.worker.service.dto.patrimony.CounterDTO;
import fr.osb.smartf.agent.worker.service.dto.patrimony.CountingPointDTO;
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
public class InstalledPatrimonyTest {

    private static final Logger LOG = LoggerFactory.getLogger(InstalledPatrimonyTest.class);
    private static final String installedPatrimonyTestFile = "InstalledPatrimonyTestFile.xml";

    @Autowired
    private ApplicationContext context;

    @Autowired
    @Qualifier("jobLauncherInstalledPatrimonyTestUtils")
    private JobLauncherTestUtils jobLauncherTestUtils;

    /*@Autowired
    private JobRepository jobRepository;

    @Autowired
    private JobLauncher jobLauncher;*/

    @Autowired
    private CouchbaseTemplate couchbaseTemplate;

    @Value("${import.basedir}")
    private String importDir;

    @Before
    public void setUp() {
        LOG.debug("copy file to import dir");
        String destination = importDir + "\\" + ImportType.installedPatrimony + "\\" + installedPatrimonyTestFile;
        Resource resource = context.getResource("classpath:"+installedPatrimonyTestFile);
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
                .addString("importType", ImportType.installedPatrimony.name())
                .addString("importFormat", ImportFormat.FILE_XML.name())
                .addString("fileName", installedPatrimonyTestFile)
                .addString("pathToFile", importDir + "\\" + ImportType.installedPatrimony.name() +
                        "\\" + installedPatrimonyTestFile).toJobParameters();

        JobExecution exec = jobLauncherTestUtils
                .launchStep("importInstalledPatrimonyXmlStep", jobParameters);

        int count = 0;
        while(exec.isRunning() && count <= 100){
            Thread.sleep(100);
            count++;
        }

        Assert.assertEquals(BatchStatus.COMPLETED, exec.getStatus());

        ResidenceDTO residence = couchbaseTemplate.findById("29008", ResidenceDTO.class);
        Assert.assertNotNull(residence);
        Assert.assertEquals("France", residence.getCountry());

        CountingPointDTO countingPoint = couchbaseTemplate.findById("1963931", CountingPointDTO.class);
        Assert.assertNotNull(countingPoint);
        Assert.assertEquals("1109206", countingPoint.getAlimentation());

        CounterDTO counter = couchbaseTemplate.findById("2597456", CounterDTO.class);
        Assert.assertNotNull(counter);
        Assert.assertEquals("ALTAIR", counter.getModel());
    }

    @After
    public void tearDown() {
        LOG.debug("flush couchbase bucket");
        BucketManager bucketManager = couchbaseTemplate.getCouchbaseBucket().bucketManager();
        bucketManager.flush();
    }

}

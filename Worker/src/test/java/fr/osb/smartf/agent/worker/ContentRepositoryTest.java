package fr.osb.smartf.agent.worker;

import fr.osb.smartf.agent.worker.service.ContentRepositoryService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by mpaltanea on 04.08.2016.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = TestContextConfiguration.class)
@WebIntegrationTest("server.port:0")
public class ContentRepositoryTest {

    private static final String gedUploadTestFile = "test.json";

    @Autowired
    private ApplicationContext context;

    @Autowired
    ContentRepositoryService contentRepositoryService;

    @Before
    public void setUp() {
        Resource resource = context.getResource("classpath:"+gedUploadTestFile);
        contentRepositoryService.uploadResource(resource, "test", gedUploadTestFile);
    }

    @Test
    public void testUploadResource() throws Exception {
        String content = contentRepositoryService.getResource("test/" + gedUploadTestFile);
        Assert.assertTrue(content.contains("RENNES-LE-CHATEAU"));
    };

    @After
    public void tearDown() {
        contentRepositoryService.deleteResource("test");
    }

}

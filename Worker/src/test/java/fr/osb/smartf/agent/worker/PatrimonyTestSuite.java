package fr.osb.smartf.agent.worker;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;

/**
 * Created by mpaltanea on 29.07.2016.
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({BuitPatrimonyTest.class, InstalledPatrimonyTest.class})
@ContextConfiguration
public class PatrimonyTestSuite {

    private static final Logger LOG = LoggerFactory.getLogger(BuitPatrimonyTest.class);


    @BeforeClass
    public static void setUp() {
    }

    // cannot load couchbase template beacause method is static -> needs workaround
    @AfterClass
    public static void tearDown() {
    }

}

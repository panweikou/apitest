package O2O.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import O2O.repository.O2OAddressRepository;
import O2O.util.AllureReport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.*;

import java.io.IOException;
import java.util.Date;

/**
 * @Author: Sean Pan
 * @ClassName E2E_Run
 * @Description TODO
 * @date 2021/11/28 15:34
 * @Version 1.0
 */

@CucumberOptions(
        glue = {
                "O2O.cucumber.stepDefinition",
                "O2O.util",
        },
        plugin = {"pretty", "html:target/regression-test-report", "pretty","html:target/cucumber/index.html", "junit:target/cucumber/junit/cucumber.xml","json:target/cucumber/json/cucumber.json"},
        tags = "",
        dryRun = false,
        features = {
                "classpath:Features/001_O2OAddressLifecycle.feature",
        }

)
public class E2E_Run extends AbstractTestNGCucumberTests {
        private static boolean loaded = false;
        private static final Logger logger = LoggerFactory.getLogger(E2E_Run.class);

        @BeforeTest
        public static void deleteReport() {
                logger.info("Delete previous test report before test");
                AllureReport.deleteFolderOrFile("target/allure-report");
        }

        @AfterTest
        public static void generateReport() throws IOException {
                AllureReport.generateAllureReport();
        }

        @Override
        @DataProvider(parallel = true)  //if don't want to run in parallel , set parallel = false;
        public Object[][] scenarios() {return super.scenarios();}

        @BeforeSuite
        public void beforeSuite() {
                logger.info("============O2ORegressionTest Suite START at {} =========", new Date());
                if (loaded) {
                        logger.info("reusing existing test data");
                }
                else {
                        try {
                                logger.info("loading test data for regression test");
                                //load input login data
                                O2OAddressRepository.instance().appendAllJsonOfO2OLogin("src/test/resources/testData/O2O/input/login");
                                //load input address data
                                O2OAddressRepository.instance().appendAllJsonOfO2OAddress("src/test/resources/testData/O2O/input/address");
                                //load expected output address data
                                O2OAddressRepository.instance().appendAllCsvOfO2OAddress("src/test/resources/testData/O2O/output/address");
                                loaded = true;
                        }
                        catch (Exception e) {
                                logger.error("failed to load test data", e);
                        }
                }
        }

        @AfterSuite
        public void afterSuite(){logger.info("=====O2ORegression Suite END at {} ======", new Date());}

}

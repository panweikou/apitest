package O2O.util;

import O2O.repository.O2OContext;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.asserts.SoftAssert;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author: Sean_Pan
 * @ClassName Hook
 * @Description TODO
 * @date 11/29/2021 4:33 PM
 * @Version 1.0
 */
public class Hook {
    private static final Logger logger = LoggerFactory.getLogger(Hook.class);
    SoftAssert softAssert;
    private static final Map<String, O2OContext> o2OContextMap = new ConcurrentHashMap<>();

    public Hook(SoftAssert softAssert) {
        logger.info("created " + this.getClass().getCanonicalName());
        this.softAssert = softAssert;
    }

    public static Map<String, O2OContext> getO2OContextHap() { return o2OContextMap ;}

    @Before
    public void scenarioBegin(Scenario scenario) {
        softAssert = new SoftAssert();
        String threadName = scenario.getId();
        Thread.currentThread().setName(threadName);
        logger.info("Hook current thread name : {}", Thread.currentThread().getName());
    }

    @After
    public void scenarioEnd() {
            logger.info( "Hook current thread id : {} ", Thread.currentThread().getId());
        }
    }
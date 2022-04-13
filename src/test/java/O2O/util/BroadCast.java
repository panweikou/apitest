package O2O.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

/**
 * @Author: Sean Pan
 * @ClassName BroadCast
 * @Description TODO
 * @date 2021/11/28 17:23
 * @Version 1.0
 */
public class BroadCast implements ITestListener {
    private static final Logger logger = LoggerFactory.getLogger(BroadCast.class);


    @Override
    public void onTestStart(ITestResult iTestResult) {}

    @Override
    public void onTestSuccess(ITestResult iTestResult) {}

    @Override
    public void onTestFailure(ITestResult iTestRessult) {}

    @Override
    public void onTestSkipped(ITestResult iTestResult) {}

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {}

    @Override
    public void onStart(ITestContext itestContext) {}

    @Override
    public void onFinish(ITestContext itestContext) {
       // try{
       //     CloseableHttpClient sender = HttpClients.createMinimal();
       //     HttpGet request = new HttpGet("http://127.0.0.1:8080");
       //     sender.execute(request);
       //     logger.info("Test complete notification has been send.");
       //     sender.close();
       //
       // } catch (IOException e) {
       //     e.printStackTrace();
       // }
    }


}

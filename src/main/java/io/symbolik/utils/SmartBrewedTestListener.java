package io.symbolik.utils;

import io.gurock.testrail.APIClient;
import io.gurock.testrail.APIException;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.testng.*;

import javax.validation.constraints.AssertFalse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

/**
 * Custom listener to send updates to testrail based on the results of provided tests.
 *
 * @author joshuatfarrell
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class SmartBrewedTestListener extends TestListenerAdapter implements ISuiteListener {
    private final Map<String, Object> results = new HashMap<>();
    final Map<String, Object> data = new HashMap<>();
    private int totalElapsedTime = 0;
    private String suiteId;
    private String testId;
    private String testName;
    private APIClient client;
    private String comment = "";

    @Override
    public void onConfigurationFailure(ITestResult itr) {
        System.out.println(itr.getThrowable().getMessage());
    }

    @Override
    public void onTestFailure(final ITestResult testResult) {
        String[] message = testResult.getThrowable().getMessage().split("\n");
        System.out.println(message[0]);

        results.put(testResult.getName(), "Failure - " + message[0]);
    }

    @Override
    public void onTestSkipped(final ITestResult testResult) {
        String[] message = testResult.getThrowable().getMessage().split("\n");
        System.out.println(message[0]);

        results.put(testResult.getName(), "Skipped - " + message[0]);
    }

    @Override
    public void onTestSuccess(final ITestResult testResult) {
        results.put(testResult.getName(), "Success");

        results.put("elapsedTime", ((testResult.getEndMillis() / 1000) - (testResult.getStartMillis() / 1000)) +
                Integer.parseInt(results.getOrDefault("elapsedTime", 0).toString()));
    }

    /**
     * This will run once at the start a the test suite
     *
     * @param suite
     */
    @Override
    public void onStart(ISuite suite) {
        System.out.println("Suite started");
    }

    /**
     * This will run every time a test is started
     *
     * @param testContext
     */
    @Override
    public void onStart(final ITestContext testContext){
        results.clear();
    }

    /**
     * This will run every time a test is finished
     * @param testContext
     */
    @Override
    public void onFinish(final ITestContext testContext) {
        int status = 1;

        for (Map.Entry<String, Object> result : results.entrySet()) {
            comment += result.getKey() + ": " + result.getValue() + "\n";

            if (result.getValue().toString().contains("Failure") && status != 5) {
                status = 5;
            }
        }

        data.put("test_id", testId);
        data.put("status_id", status);
        data.put("comment", comment);
        totalElapsedTime += Integer.parseInt(results.get("elapsedTime").toString());

        try {
            client.sendPost("add_result/" + testId, data);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (APIException e) {
            e.printStackTrace();
        }

        System.out.println("Done");
    }

    /**
     * This will run once the entire suite is finished and then send the results back to TestRail
     *
     * @param suite
     */
    @Override
    public void onFinish(final ISuite suite) {
        System.out.println("Suite finished");
    }
}

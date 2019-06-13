package io.symbolik.utils;

import io.gurock.testrail.APIClient;
import io.gurock.testrail.APIException;
import lombok.Data;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Data
public class SmartBrewedTestListener extends TestListenerAdapter {
    private final Map<String, Object> results = new HashMap<>();
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
    }

    @Override
    public void onFinish(final ITestContext testContext) {
        final Map<String, Object> data = new HashMap<>();
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
        data.put("elapsed", "30s");

        try {
            client.sendPost("add_result/" + testId, data);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (APIException e) {
            e.printStackTrace();
        }

        System.out.println("Done");
    }
}

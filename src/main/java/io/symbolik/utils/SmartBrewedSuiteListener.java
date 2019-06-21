package io.symbolik.utils;

import io.gurock.testrail.APIException;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class SmartBrewedSuiteListener implements ISuiteListener {


    @Override
    public void onStart(ISuite suite) {
        System.out.println("Suite started");
    }

    @Override
    public void onFinish(final ISuite suite) {
        System.out.println("Suite finished");
/*
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
        data.put("elapsed", results.get("elapsedTime") + "s");

        try {
            client.sendPost("add_result/" + testId, data);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (APIException e) {
            e.printStackTrace();
        }

        System.out.println("Done");
        */
    }
}

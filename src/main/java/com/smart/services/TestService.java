package com.smart.services;

import com.gurock.testrail.APIClient;
import com.gurock.testrail.APIException;
import com.selenium.utils.SmartBrewedTestListener;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;
import org.testng.TestNG;
import org.testng.log4testng.Logger;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Example of a {@link Service} class.
 */
@Service
public class TestService {
    private static final Logger LOGGER = Logger.getLogger(TestService.class);
    private static final APIClient CLIENT = new APIClient("https://sheknows.testrail.net/");

    private final String user = "joshua.farrell+1@sheknows.com";
    private final String password = "Qwerty@1";

    public void runById(final long runId, final String suiteName) {
        CLIENT.setUser(user);
        CLIENT.setPassword(password);

        JSONArray response = new JSONArray();

        // Get tests that have status: Untested, Retest, Failed; and are related to the test run
        try {
            response = (JSONArray) CLIENT.sendGet("get_tests/" + runId + "&status_id=3,4,5&custom_automation=3");
        } catch (IOException | APIException e) {
            LOGGER.info(e);
        }

        // Lets iterate through the tests, capture the id and add the test to the suite
        for (int i = 0; i < response.size(); i++) {
            final List<XmlSuite> suites = new ArrayList<>();
            final XmlSuite suite = new XmlSuite();
            final Map<String, String> params = new HashMap<>();
            final XmlTest xmlTest = new XmlTest(suite);
            final TestNG testNg = new TestNG();

            List<XmlClass> classes = new ArrayList<>();
            suite.setName(suiteName);

            params.put("local", "true");
            params.put("browser", "firefox");

            suite.setParameters(params);

            final JSONObject test = (JSONObject) response.get(i);

            XmlClass xmlClass = new XmlClass((String) test.get("custom_package"));

            classes.add(xmlClass);

            xmlTest.setName("TmpTest");
            xmlTest.setXmlClasses(classes);

            suites.add(suite);

            testNg.setXmlSuites(suites);

            System.out.println(test.get("id") + ": " + test.get("title"));

            SmartBrewedTestListener listener = new SmartBrewedTestListener();

            listener.setSuiteId(String.valueOf(runId));
            listener.setTestName((String)test.get("title"));
            listener.setTestId(String.valueOf(test.get("id")));
            listener.setClient(CLIENT);

            testNg.addListener(listener);

            testNg.run();
        }








    }

}

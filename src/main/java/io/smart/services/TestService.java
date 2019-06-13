package io.smart.services;

import io.gurock.testrail.APIClient;
import io.gurock.testrail.APIException;
import io.symbolik.utils.SmartBrewedTestListener;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;
import org.testng.ITestNGListener;
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
    private static final APIClient CLIENT = new APIClient("https://smartbrewed.testrail.io/");

    private final String user = "joshuatfarrell@gmail.com";
    private final String password = "DeMark123";


    public void runById(final long runId, final String suiteName) {
        CLIENT.setUser(user);
        CLIENT.setPassword(password);

        JSONArray response = new JSONArray();

        // Get tests that have status: Untested, Retest, Failed; and are related to the test run
        try {
            response = (JSONArray) CLIENT.sendGet("get_tests/" + runId + "&custom_automation_type=2");
        } catch (IOException | APIException e) {
            LOGGER.info(e);
        }

        System.out.println("Responses: " + response.size());
        // Lets iterate through the tests, capture the id and add the test to the suite
        for (int i = 0; i < response.size(); i++) {
            final JSONObject test = (JSONObject) response.get(i);
            if (Integer.parseInt(test.get("custom_automation_type").toString()) == 2) {
                final List<XmlSuite> suites = new ArrayList<>();
                final XmlSuite suite = new XmlSuite();
                final Map<String, String> params = new HashMap<>();
                final XmlTest xmlTest = new XmlTest(suite);
                final TestNG testNg = new TestNG();

                List<XmlClass> classes = new ArrayList<>();
                suite.setName(suiteName);

                params.put("local", "true");
                params.put("browser", "chrome");

                suite.setParameters(params);

                XmlClass xmlClass = new XmlClass((String) test.get("custom_package"));

                classes.add(xmlClass);

                xmlTest.setName("LoginTest");
                xmlTest.setXmlClasses(classes);

                suites.add(suite);

                testNg.setXmlSuites(suites);

                SmartBrewedTestListener listener = new SmartBrewedTestListener();

                listener.setSuiteId(String.valueOf(runId));
                listener.setTestName((String) test.get("title"));
                listener.setTestId(String.valueOf(test.get("id")));
                listener.setClient(CLIENT);

                testNg.addListener((ITestNGListener) listener);

                testNg.run();
            }
        }


    }

}

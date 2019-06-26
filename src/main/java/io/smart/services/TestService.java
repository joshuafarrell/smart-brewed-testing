package io.smart.services;

import io.gurock.testrail.APIClient;
import io.gurock.testrail.APIException;
import io.symbolik.utils.SmartBrewedSuiteListener;
import io.symbolik.utils.SmartBrewedTestListener;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;
import org.testng.ISuite;
import org.testng.ISuiteListener;
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
        String browser = "firefox";
        final List<XmlSuite> suites = new ArrayList<>();
        final TestNG testNg = new TestNG();
        final XmlSuite suite = new XmlSuite();
        final SmartBrewedTestListener listener = new SmartBrewedTestListener();
        final SmartBrewedSuiteListener suiteListener = new SmartBrewedSuiteListener();

        CLIENT.setUser(user);
        CLIENT.setPassword(password);

        JSONArray testsResponse = new JSONArray();
        JSONObject testRunResponse = new JSONObject();
        // Get tests that have status: Untested, Retest, Failed; and are related to the testrail runId
        try {
            testsResponse = (JSONArray) CLIENT.sendGet("get_tests/" + runId + "&status_id=1,2,3,4,5");
            testRunResponse = (JSONObject) CLIENT.sendGet("get_run/" + runId);

            browser = testRunResponse.get("config").toString().toLowerCase();
        } catch (IOException | APIException e) {
            LOGGER.info(e);
        }

        suite.setName(suiteName);
        suite.setFileName(suiteName);

        Map<String, String> params = new HashMap<>();

        params.put("local", "true");
        params.put("browser", browser);

        // Lets iterate through the tests, capture the id and add the test to the suite
        for (int i = 0; i < testsResponse.size(); i++) {
            List<XmlClass> classes = new ArrayList<>();
            final JSONObject test = (JSONObject) testsResponse.get(i);

            if (Integer.parseInt(test.get("custom_automation_type").toString()) == 2) {
                XmlTest xmlTest = new XmlTest(suite);
                xmlTest.setParameters(params);

                XmlClass xmlClass = new XmlClass((String) test.get("custom_package"));

                xmlClass.setName((String) test.get("custom_package"));
                classes.add(xmlClass);

                xmlTest.setName((String) test.get("title"));
                xmlTest.setXmlClasses(classes);

                listener.setTestName((String) test.get("title"));
                listener.setTestId(String.valueOf(test.get("id")));
            }
        }

        suite.setParallel(XmlSuite.ParallelMode.TESTS);
        suites.add(suite);

        listener.setSuiteId(String.valueOf(runId));
        listener.setClient(CLIENT);

        testNg.setXmlSuites(suites);
        testNg.addListener((ITestNGListener) listener);

        if (!suites.isEmpty()) {
            testNg.run();
        } else {
            System.out.println("No tests to execute");
        }
    }

}

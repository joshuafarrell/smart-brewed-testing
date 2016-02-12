package com.gurock.testrail;

import org.json.simple.JSONObject;
import org.testng.log4testng.Logger;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class APIAction {
    private static final Logger LOGGER = Logger.getLogger(APIAction.class);

    APIClient client = new APIClient("https://sheknows.testrail.net/");
    String user = "joshua.farrell+1@sheknows.com";
    String password = "Qwerty@1";

    public APIAction() {
        client.setUser(user);
        client.setPassword(password);
    }

    public int convertStatus(int testNGStatus) {
        switch (testNGStatus) {
            case 2:
                return 5;
            case 3:
                return 2;
            default:
                return 1;
        }

    }

    public void addResultForRun(int runId, int runTestId, int status, String testSuite, String testName) {
        final Map<String, Object> data = new HashMap<>();
        data.put("status_id", convertStatus(status));
        data.put("comment", testSuite + "." + testName);
        data.put("run_id", runTestId);

        try {
            client.sendPost("add_results/" + runId, data);
        } catch (IOException | APIException e) {
            LOGGER.info(e);
        }
    }

    public int addRun(int projId, List cases) {
        final Map<String, Object> data = new HashMap<>();

        data.put("suite_id", 15);
        data.put("name", "AutomateIt run");
        data.put("assignedto_id", 1);
        data.put("include_all", false);
        data.put("case_ids", cases);

        try {
            JSONObject response = (JSONObject) client.sendPost("add_run/" + projId, data);
            return Integer.parseInt(response.get("id").toString());
        } catch (IOException | APIException e) {
            LOGGER.info(e);
        }

        return 0;
    }
}

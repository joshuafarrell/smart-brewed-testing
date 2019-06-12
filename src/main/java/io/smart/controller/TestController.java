package io.smart.controller;

import io.smart.services.TestService;
import org.jsondoc.core.annotation.ApiMethod;
import org.jsondoc.core.annotation.ApiPathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;
import org.testng.log4testng.Logger;

/**
 * Example of an API endpoint
 * Class contains all necessary methods
 *
 * @author Joshua Farrell
 */
@Async
@RestController
public class TestController {
    private static final Logger LOGGER = Logger.getLogger(TestController.class);

    @Autowired
    private TestService testService;

    @CrossOrigin
    @ApiMethod(description = "Gets tests associated with test run and executes them.",
            id = "find-tests-by-id")
    @RequestMapping(method = RequestMethod.GET, value = "/{runId}/{suiteName}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public void getByRunId(@PathVariable("runId")
                           @ApiPathParam(name = "ID", description = "Identifier of Test Run", format = "\\d+")
                           final long runId,
                           @PathVariable("suiteName")
                           @ApiPathParam(name = "Suite Name", description = "Name of the test suite",
                                   format = "^\\w{1,255}$")
                           final String suiteName) {
        testService.runById(runId, suiteName);
    }

}

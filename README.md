# Smart Brewed Testing

## Why?

We needed a way to bridge our automated tests with manual testing that occurs through Gurock's TestRail.

This sets up a spring service that TestRail then talks to through a custom ui-script added to the test run dashboard. A
user can mix together manual and automated tests to create a run, click a button and continue to work while this service
runs the automated tests and updates the associated statuses in TestRail.

## Integrating with TestRail
The API option will need to be enabled in TestRail.

The following ui-script should be added, the `^runs/view` will apply this button to the toolbar when viewing a test run.

```javascript
name: AutoTests
description: Execute automated tests
author: Joshua Farrell
version: 1.0
includes: ^runs/view
excludes:

js:
$(document).ready(

	function() {
	    var uri = "http://localhost:8080/" + uiscripts.context.run.id + "/" + uiscripts.context.suite.name;
	    var runAutomation = $('<a id="run-auto" class="toolbar-button toolbar-button-last content-header-button" href=' + uri + ' target="_blank"}>Run Automation</a>');

	    var toolbar = $('<div class="toolbar content-header-toolbar"></div>');
	    	toolbar.append(runAutomation);
	    $("#content-header .content-header-inner").prepend(toolbar);
	}
);

css:
div.some-class {
}
```

## Initial Setup for Framework
You will need to change a few fields to match where your selenium grid runs and where your TestRail instance lives.

## Running the service
The easiest way to run this service is through IntelliJ setup to run Spring. The default location will be
localhost:8080.

Example request from TestRail to this service would be
`http://localhost:8080/:runId/:suiteName`

This will grab all associated tests for the provided runId and filter through to find the tests that are flagged as
automated. It will then run through these tests and update the statuses for the associated testCaseId.

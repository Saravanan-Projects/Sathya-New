$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("file:/G:/Sathya_Automation/src/test/resources/testcases/airconditioner/Clicking_Each_Product.feature");
formatter.feature({
  "name": "Clicking_Each_Product",
  "description": "",
  "keyword": "Feature",
  "tags": [
    {
      "name": "@Project_Name__Sathya_Automation"
    },
    {
      "name": "@Tester_Name__Saravanan_A"
    },
    {
      "name": "@Module_Name__Airconditioner"
    },
    {
      "name": "@Sample_1"
    }
  ]
});
formatter.scenario({
  "name": "Clicking_Each_Product",
  "description": "",
  "keyword": "Scenario",
  "tags": [
    {
      "name": "@Project_Name__Sathya_Automation"
    },
    {
      "name": "@Tester_Name__Saravanan_A"
    },
    {
      "name": "@Module_Name__Airconditioner"
    },
    {
      "name": "@Sample_1"
    }
  ]
});
formatter.before({
  "status": "passed"
});
formatter.step({
  "name": "I set my testcase id as \u0027TC_1\u0027",
  "keyword": "Given "
});
formatter.match({
  "location": "Sathya_Common_Steps.setTestIDAs(String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I am Logged into Sathya Application",
  "keyword": "Given "
});
formatter.match({
  "location": "Sathya_Common_Steps.Sathya_Login()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I read the file as following details",
  "rows": [
    {
      "cells": [
        "FILE_NAME",
        "Sample"
      ]
    },
    {
      "cells": [
        "FILE_TYPE",
        "TEXT"
      ]
    }
  ],
  "keyword": "And "
});
formatter.match({
  "location": "Sathya_Common_Steps.readFromFile(DataTable)"
});
formatter.result({
  "status": "passed"
});
formatter.after({
  "status": "passed"
});
});
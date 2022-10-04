$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("G:/Sathya_Automation/src/test/resources/testcases/airconditioner/Clicking_Each_Product.feature");
formatter.feature({
  "line": 4,
  "name": "Clicking_Each_Product",
  "description": "",
  "id": "clicking-each-product",
  "keyword": "Feature",
  "tags": [
    {
      "line": 1,
      "name": "@Project_Name__Sathya_Automation"
    },
    {
      "line": 2,
      "name": "@Module_Name__Airconditioner"
    },
    {
      "line": 3,
      "name": "@Sample_1"
    }
  ]
});
formatter.before({
  "duration": 88183558,
  "status": "passed"
});
formatter.scenario({
  "line": 6,
  "name": "Clicking_Each_Product",
  "description": "",
  "id": "clicking-each-product;clicking-each-product",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "comments": [
    {
      "line": 8,
      "value": "#Testcase_Name: Entering into application with username and password"
    }
  ],
  "line": 9,
  "name": "I set my testcase id as \u0027TC_1\u0027",
  "keyword": "Given "
});
formatter.step({
  "line": 10,
  "name": "I am Logged into Sathya Application",
  "keyword": "Given "
});
formatter.step({
  "line": 11,
  "name": "I enter username and password and log in account",
  "keyword": "And "
});
formatter.match({
  "arguments": [
    {
      "val": "TC_1",
      "offset": 25
    }
  ],
  "location": "Sathya_Common_Steps.setTestIDAs(String)"
});
formatter.result({
  "duration": 262116562,
  "status": "passed"
});
formatter.match({
  "location": "Sathya_Common_Steps.Sathya_Login()"
});
formatter.result({
  "duration": 16180488127,
  "status": "passed"
});
formatter.match({
  "location": "Sathya_Common_Steps.enterUsrNmePwd()"
});
formatter.result({
  "duration": 4277420628,
  "status": "passed"
});
formatter.after({
  "duration": 4826624567,
  "status": "passed"
});
});
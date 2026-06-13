package com.omrbranch.stepdefinition;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Assert;

import com.omrbranch.endpoints.Endpoints;
import com.omrbranch.globaldata.GlobalData;
import com.omrbranch.pojo.postmanbasicauthlogin.PostmanBasicAuthLogin_Output_Pojo;
import com.omrbranch.utility.BaseClass;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;



public class TC001_LoginStep extends BaseClass {
Response response;
@Given("User adds required login headers")
public void user_adds_required_login_headers() {
initRequest();
addHeader("accept", "application/json");

}

@When("User adds basic authentication for login")
public void user_adds_basic_authentication_for_login() throws FileNotFoundException, IOException {
addBasicAuth(getPropertyFilevalue("username"), getPropertyFilevalue("password"));

}

@When("User sends {string} request to the login endpoint")
public void user_sends_request_to_the_login_endpoint(String type) {
response = sendRequest(type, Endpoints.POSTMANBASICAUTHLOGIN);
int statusCode = getStatusCode(response);
GlobalData.getGlobalDataInstance().setStatusCode(statusCode);
System.out.println(statusCode);
getResponseBody(response);
}



@Then("User should verify the login response body contains firstName {string} and save the logtoken")
public void user_should_verify_the_login_response_body_contains_first_name_and_save_the_logtoken(String expFirstName) {

PostmanBasicAuthLogin_Output_Pojo postmanBasicAuthLogin_Output_Pojo = response.as(PostmanBasicAuthLogin_Output_Pojo.class);
String logtoken = postmanBasicAuthLogin_Output_Pojo.getData().getLogtoken();
GlobalData.getGlobalDataInstance().setLogtoken(logtoken);
String actFirst_name = postmanBasicAuthLogin_Output_Pojo.getData().getFirst_name();
Assert.assertEquals("Verify Login Endpoint first name", expFirstName,actFirst_name);
}
}

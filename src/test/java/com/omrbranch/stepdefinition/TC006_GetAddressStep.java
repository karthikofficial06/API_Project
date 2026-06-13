package com.omrbranch.stepdefinition;

import org.junit.Assert;

import com.omrbranch.endpoints.Endpoints;
import com.omrbranch.pojo.getUserAddress.GetUserAddress_Output_Pojo;
import com.omrbranch.utility.BaseClass;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

public class TC006_GetAddressStep extends BaseClass {
	Response response;

	@Given("User adds headers and bearer authorization for accessing get address endpoints")
	public void user_adds_headers_and_bearer_authorization_for_accessing_get_address_endpoints() {

		initRequest();

		addHeader("accept", "application/json");
		addHeader("Authorization",
				"Bearer " + com.omrbranch.globaldata.GlobalData
						.getGlobalDataInstance()
						.getLogtoken());
	}

	@When("User sends {string} request to GetUserAddress endpoint")
	public void user_sends_request_to_get_user_address_endpoint(String type) {

		response = sendRequest(type, Endpoints.GETUSERADDRESS);
		int statusCode = getStatusCode(response);
		com.omrbranch.globaldata.GlobalData
				.getGlobalDataInstance()
				.setStatusCode(statusCode);
		getResponseBody(response);
	}

	@Then("User should verify the GetUserAddress response message matches {string}")
	public void user_should_verify_the_get_user_address_response_message_matches(
			String expMessage) {
		GetUserAddress_Output_Pojo outputPojo =
				response.as(GetUserAddress_Output_Pojo.class);
		Assert.assertEquals(
				"Verify Get Address Message",
				expMessage,
				outputPojo.getMessage());
	}
}
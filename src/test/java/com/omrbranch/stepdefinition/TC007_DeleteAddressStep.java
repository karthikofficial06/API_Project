package com.omrbranch.stepdefinition;

import org.junit.Assert;

import com.omrbranch.endpoints.Endpoints;
import com.omrbranch.globaldata.GlobalData;
import com.omrbranch.pojo.deleteaddress.DeleteAddress_Input_Pojo;
import com.omrbranch.pojo.deleteaddress.DeleteAddress_Output_Pojo;
import com.omrbranch.utility.BaseClass;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

public class TC007_DeleteAddressStep extends BaseClass {
	Response response;

	@When("User adds request body with address id")
	public void user_adds_request_body_with_address_id() {

		int addressId =
				GlobalData.getGlobalDataInstance().getAddressId();
		DeleteAddress_Input_Pojo inputPojo =
				new DeleteAddress_Input_Pojo(addressId);
		addPayload(inputPojo);
	}

	@When("User sends {string} request to DeleteAddress endpoint")
	public void user_sends_request_to_delete_address_endpoint(String type) {

		response = sendRequest(type, Endpoints.DELETEADDRESS);
		int statusCode = getStatusCode(response);
		GlobalData.getGlobalDataInstance().setStatusCode(statusCode);
		getResponseBody(response);
	}

	@Then("User should verify the DeleteAddress response message matches {string}")
	public void user_should_verify_the_delete_address_response_message_matches(
			String expMessage) {
		
		DeleteAddress_Output_Pojo outputPojo =
				response.as(DeleteAddress_Output_Pojo.class);
		Assert.assertEquals(
				"Verify Delete Address Message",
				expMessage,
				outputPojo.getMessage());
	}
}
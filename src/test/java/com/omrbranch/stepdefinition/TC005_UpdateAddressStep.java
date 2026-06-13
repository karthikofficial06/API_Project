package com.omrbranch.stepdefinition;

import org.junit.Assert;

import com.omrbranch.endpoints.Endpoints;
import com.omrbranch.globaldata.GlobalData;
import com.omrbranch.pojo.updateuseraddress.UpdateUserAddress_Input_Pojo;
import com.omrbranch.pojo.updateuseraddress.UpdateUserAddress_Output_Pojo;
import com.omrbranch.utility.BaseClass;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

public class TC005_UpdateAddressStep extends BaseClass {
	Response response;

	@When("User adds request body to update address {string},{string},{string},{string},{string},{string},{string},{string},{string},{string}")
	public void user_adds_request_body_to_update_address(
			String firstName,
			String lastName,
			String mobile,
			String apartment,
			String state,
			String city,
			String country,
			String zipcode,
			String address,
			String addressType) {

		int addressId = GlobalData.getGlobalDataInstance().getAddressId();

		UpdateUserAddress_Input_Pojo inputPojo =
				new UpdateUserAddress_Input_Pojo(
						addressId,
						firstName,
						lastName,
						mobile,
						apartment,
						Integer.parseInt(state),
						Integer.parseInt(city),
						Integer.parseInt(country),
						zipcode,
						address,
						addressType);

		addPayload(inputPojo);
	}

	@When("User sends {string} request to updateUserAddress endpoint")
	public void user_sends_request_to_update_user_address_endpoint(String type) {

		response = sendRequest(type, Endpoints.UPDATEUSERADDRESS);
		int statusCode = getStatusCode(response);
		GlobalData.getGlobalDataInstance().setStatusCode(statusCode);
		getResponseBody(response);
	}

	@Then("User should verify the updateUserAddress response message matches {string}")
	public void user_should_verify_the_update_user_address_response_message_matches(
			String expMessage) {
		UpdateUserAddress_Output_Pojo outputPojo =
				response.as(UpdateUserAddress_Output_Pojo.class);
		Assert.assertEquals(
				"Verify Update Address Message",
				expMessage,
				outputPojo.getMessage());
	}
}
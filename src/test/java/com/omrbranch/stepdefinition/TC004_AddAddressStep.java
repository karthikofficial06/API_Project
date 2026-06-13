package com.omrbranch.stepdefinition;

import org.junit.Assert;

import com.omrbranch.endpoints.Endpoints;
import com.omrbranch.globaldata.GlobalData;
import com.omrbranch.pojo.addaddress.AddUserAddress_Input_Pojo;
import com.omrbranch.pojo.addaddress.AddUserAddress_Output_Pojo;
import com.omrbranch.utility.BaseClass;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

public class TC004_AddAddressStep extends BaseClass {
	Response response;

	@Given("User adds headers and bearer authorization for accessing address endpoints")
	public void user_adds_headers_and_bearer_authorization_for_accessing_address_endpoints() {

		initRequest();
		addHeader("accept", "application/json");
		addHeader("Content-Type", "application/json");
		addHeader("Authorization",
				"Bearer " + GlobalData.getGlobalDataInstance().getLogtoken());
	}

	@When("User adds request body for add new address {string},{string},{string},{string},{string},{string},{string},{string},{string},{string}")
	public void user_adds_request_body_for_add_new_address(
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

		AddUserAddress_Input_Pojo inputPojo =
				new AddUserAddress_Input_Pojo(
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

	@Then("User sends {string} request to addUserAddress endpoint")
	public void user_sends_request_to_add_user_address_endpoint(String type) {

		response = sendRequest(type, Endpoints.ADDUSERADDRESS);
		int statusCode = getStatusCode(response);
		GlobalData.getGlobalDataInstance().setStatusCode(statusCode);
		getResponseBody(response);
	}

	@Then("User should verify the addUserAddress response message matches {string} and save the address id")
	public void user_should_verify_the_add_user_address_response_message_matches_and_save_the_address_id(
	        String expMessage) {

	    AddUserAddress_Output_Pojo outputPojo =
	            response.as(AddUserAddress_Output_Pojo.class);

	    Assert.assertEquals(
	            "Verify Add Address Message",
	            expMessage,
	            outputPojo.getMessage());

	    int addressId = outputPojo.getAddress_id();

	    GlobalData.getGlobalDataInstance().setAddressId(addressId);

	    System.out.println("Address Id : " + addressId);
	}
}
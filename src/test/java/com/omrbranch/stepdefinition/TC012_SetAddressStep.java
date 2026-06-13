package com.omrbranch.stepdefinition;

import com.omrbranch.endpoints.Endpoints;
import com.omrbranch.globaldata.GlobalData;
import com.omrbranch.pojo.setaddress.SetAddress_Input_Pojo;
import com.omrbranch.pojo.setaddress.SetAddress_Output_Pojo;
import com.omrbranch.utility.BaseClass;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import junit.framework.Assert;

public class TC012_SetAddressStep extends BaseClass{

	@Given("User sets bearer authorization for SetAddress endpoint")
	public void user_sets_bearer_authorization_for_set_address_endpoint() {

	    initRequest();

	    addHeader("accept", "application/json");
	    addHeader("Content-Type", "application/json");
	    addHeader("Authorization",
	            "Bearer " + GlobalData.getGlobalDataInstance().getLogtoken());
	}

	@When("User sets request body with saved address ID and cart ID")
	public void user_sets_request_body_with_saved_address_id_and_cart_id() {

	    String addressId = String.valueOf(
	            GlobalData.getGlobalDataInstance().getAddressId());
	    System.out.println("Saved Address Id = " + GlobalData.getGlobalDataInstance().getAddressId());

	    String cartId =
	            GlobalData.getGlobalDataInstance().getCartId();

	    System.out.println("Address Id = " + addressId);
	    System.out.println("Cart Id = " + cartId);

	    SetAddress_Input_Pojo inputPojo =
	            new SetAddress_Input_Pojo(addressId, cartId);

	    addPayload(inputPojo);
	}

	@When("User sends {string} request to the SetAddress endpoint")
	public void user_sends_request_to_the_set_address_endpoint(String type) {

	    response = sendRequest(type, Endpoints.SETADDRESS);

	    int statusCode = getStatusCode(response);
	    GlobalData.getGlobalDataInstance().setStatusCode(statusCode);

	    getResponseBody(response);
	}

	@Then("Verify the SetAddress response message is {string}")
	public void verify_the_set_address_response_message_is(String expMessage) {

	    SetAddress_Output_Pojo outputPojo =
	            response.as(SetAddress_Output_Pojo.class);

	    Assert.assertEquals(
	            "Verify Set Address Message",
	            expMessage,
	            outputPojo.getMessage());
	}
}
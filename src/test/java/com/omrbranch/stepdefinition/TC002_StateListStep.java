package com.omrbranch.stepdefinition;

import org.junit.Assert;

import com.omrbranch.endpoints.Endpoints;
import com.omrbranch.globaldata.GlobalData;
import com.omrbranch.pojo.statelist.Datum;
import com.omrbranch.pojo.statelist.stateList_Output_Pojo;
import com.omrbranch.utility.BaseClass;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

public class TC002_StateListStep extends BaseClass {
	Response response;

	@Given("User adds headers for StateList")
	public void user_adds_headers_for_state_list() {
		initRequest();
		addHeader("accept", "application/json");
	}

	@When("User sends {string} request to StateList endpoint")
	public void user_sends_request_to_state_list_endpoint(String type) {

		response = sendRequest(type, Endpoints.STATELIST);
		int statusCode = getStatusCode(response);
		GlobalData.getGlobalDataInstance().setStatusCode(statusCode);
		getResponseBody(response);
	}

	@Then("User should verify the stateList response message matches {string} and save the state id")
	public void user_should_verify_the_state_list_response_message_matches_and_save_the_state_id(
			String expStateName) {
		stateList_Output_Pojo outputPojo = response.as(stateList_Output_Pojo.class);

		for (Datum datum : outputPojo.getData()) {
			if (datum.getName().equals(expStateName)) {
				Assert.assertEquals("Verify State Name", expStateName, datum.getName());
				int stateId = datum.getId();
				GlobalData.getGlobalDataInstance().setStateId(stateId);
				System.out.println("State Id : " + stateId);
				break;
			}
		}
	}
}
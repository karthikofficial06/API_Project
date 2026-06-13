package com.omrbranch.stepdefinition;

import org.junit.Assert;

import com.omrbranch.endpoints.Endpoints;
import com.omrbranch.globaldata.GlobalData;
import com.omrbranch.pojo.citylist.CityList;
import com.omrbranch.pojo.citylist.CityList_Input_Pojo;
import com.omrbranch.pojo.citylist.CityList_Output_Pojo;
import com.omrbranch.utility.BaseClass;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

public class TC003_CityListStep extends BaseClass {
	Response response;

	@Given("User adds headers for CityList")
	public void user_adds_headers_for_city_list() {

		initRequest();
		addHeader("accept", "application/json");
		addHeader("Content-Type", "application/json");
	}

	@When("User adds request body with state id for city list")
	public void user_adds_request_body_with_state_id_for_city_list() {

		String stateId = String.valueOf(GlobalData.getGlobalDataInstance().getStateId());
		CityList_Input_Pojo inputPojo = new CityList_Input_Pojo(stateId);
		addPayload(inputPojo);
	}

	@When("User sends {string} request to CityList endpoint")
	public void user_sends_request_to_city_list_endpoint(String type) {

		response = sendRequest(type, Endpoints.CITYLIST);
		int statusCode = getStatusCode(response);
		GlobalData.getGlobalDataInstance().setStatusCode(statusCode);
		getResponseBody(response);
	}

	@Then("User should verify the cityList response message matches {string} and save the city id")
	public void user_should_verify_the_city_list_response_message_matches_and_save_the_city_id(String expCityName) {

		CityList_Output_Pojo outputPojo = response.as(CityList_Output_Pojo.class);
		for (CityList city : outputPojo.getData()) {
			if (city.getName().equals(expCityName)) {
				Assert.assertEquals("Verify City Name", expCityName, city.getName());
				GlobalData.getGlobalDataInstance().setCityId(city.getId());
				System.out.println("City Id : " + city.getId());
				break;
			}
		}
	}
}
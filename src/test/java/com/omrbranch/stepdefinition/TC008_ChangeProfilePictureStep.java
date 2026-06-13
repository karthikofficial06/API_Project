package com.omrbranch.stepdefinition;

import java.io.File;

import org.junit.Assert;

import com.omrbranch.endpoints.Endpoints;
import com.omrbranch.globaldata.GlobalData;
import com.omrbranch.pojo.changeprofilepic.ChangeProfilePic_Output_Pojo;
import com.omrbranch.utility.BaseClass;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

public class TC008_ChangeProfilePictureStep extends BaseClass {
	Response response;

	@Given("User sets bearer authorization using the saved logtoken for profile picture endpoint")
	public void user_sets_bearer_authorization_using_the_saved_logtoken_for_profile_picture_endpoint() {

		initRequest();
		addHeader("accept", "application/json");
		addHeader("Authorization",
				"Bearer " + GlobalData.getGlobalDataInstance().getLogtoken());
	}

	@Given("User sets multipart request body with valid image file for profile update")
	public void user_sets_multipart_request_body_with_valid_image_file_for_profile_update() {

		File file = new File(
				getProjectPath() + "\\src\\test\\resources\\Profile\\66e1accb-777c-4cd9-b4f5-fbeeea174291.png");
		addMultiPart("profile_picture", file);
	}

	@When("User sends {string} request to the ChangeProfile endpoint")
	public void user_sends_request_to_the_change_profile_endpoint(String type) {

		response = sendRequest(type, Endpoints.CHANGEPROFILE);
		int statusCode = getStatusCode(response);
		GlobalData.getGlobalDataInstance().setStatusCode(statusCode);
		getResponseBody(response);
	}

	@Then("User should verify the response message is {string}")
	public void user_should_verify_the_response_message_is(String expMessage) {

		ChangeProfilePic_Output_Pojo outputPojo =
				response.as(ChangeProfilePic_Output_Pojo.class);
		Assert.assertEquals(
				"Verify Profile Update Message",
				expMessage,
				outputPojo.getMessage());
	}
}
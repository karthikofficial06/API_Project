package com.omrbranch.stepdefinition;

import org.junit.Assert;

import com.omrbranch.endpoints.Endpoints;
import com.omrbranch.globaldata.GlobalData;
import com.omrbranch.pojo.clearcart.ClearCart_Output_Pojo;
import com.omrbranch.utility.BaseClass;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

public class TC009_ClearCartStep extends BaseClass {
    Response response;

    @Given("User sets bearer authorization for ClearCart endpoint")
    public void user_sets_bearer_authorization_for_clear_cart_endpoint() {

        initRequest();
        addHeader("accept", "application/json");
        addHeader("Authorization",
                "Bearer " + GlobalData.getGlobalDataInstance().getLogtoken());
    }

    @When("User sends {string} request to the ClearCart endpoint")
    public void user_sends_request_to_the_clear_cart_endpoint(String type) {

        response = sendRequest(type, Endpoints.CLEARCART);
        int statusCode = getStatusCode(response);
        GlobalData.getGlobalDataInstance().setStatusCode(statusCode);

        getResponseBody(response);
    }

    @Then("Verify the ClearCart response message is {string}")
    public void verify_the_clear_cart_response_message_is(String expMessage) {

        ClearCart_Output_Pojo outputPojo =
                response.as(ClearCart_Output_Pojo.class);

        String actualMessage = outputPojo.getMessage();

        Assert.assertTrue(
            actualMessage.equals("Currently Your Cart is empty")
            || actualMessage.equals("Cart has been cleared.")
        );
    }
}

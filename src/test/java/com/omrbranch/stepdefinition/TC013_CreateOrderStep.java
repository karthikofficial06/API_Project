package com.omrbranch.stepdefinition;

	import org.junit.Assert;

	import com.omrbranch.createorder.pojo.CreateOrder_Input_Pojo;
	import com.omrbranch.createorder.pojo.CreateOrder_Output_Pojo;
	import com.omrbranch.endpoints.Endpoints;
	import com.omrbranch.globaldata.GlobalData;
	import com.omrbranch.utility.BaseClass;

	import io.cucumber.java.en.Given;
	import io.cucumber.java.en.Then;
	import io.cucumber.java.en.When;
	import io.restassured.response.Response;

	public class TC013_CreateOrderStep extends BaseClass {

		Response response;

		@Given("User sets bearer authorization for CreateOrder endpoint")
		public void user_sets_bearer_authorization_for_create_order_endpoint() {

			initRequest();

			addHeader("accept", "application/json");
			addHeader("Content-Type", "application/json");
			addHeader("Authorization",
					"Bearer " + GlobalData.getGlobalDataInstance().getLogtoken());
		}

		@When("User sets request body to create order with payment details {string}, {string}, {string}, {string}, {string}, {string}")
		public void user_sets_request_body_to_create_order_with_payment_details(
				String paymentMethod,
				String cardNo,
				String cardType,
				String year,
				String month,
				String cvv) {

			CreateOrder_Input_Pojo inputPojo =  new CreateOrder_Input_Pojo(
			                "debit_card",
			                cardNo,
			                paymentMethod, // visa
			                year,
			                month,
			                cvv);

			addPayload(inputPojo);
		}

		@When("User sends {string} request to the CreateOrder endpoint")
		public void user_sends_request_to_the_create_order_endpoint(String type) {

		    response = sendRequest(type, Endpoints.CREATEORDER);
		    response.prettyPrint();   // Add this line

		    int statusCode = getStatusCode(response);
		    GlobalData.getGlobalDataInstance().setStatusCode(statusCode);

		    getResponseBody(response);
		}

		@Then("Verify the CreateOrder response message is {string}")
		public void verify_the_create_order_response_message_is(String expMessage) {

			CreateOrder_Output_Pojo outputPojo =
					response.as(CreateOrder_Output_Pojo.class);

			Assert.assertEquals(
					"Verify Create Order Message",
					expMessage,
					outputPojo.getMessage());

			System.out.println("Order Id : " + outputPojo.getOrder_id());
		}
	}

package com.omrbranch.stepdefinition;

	import org.junit.Assert;

import com.omrbranch.addtocart.AddToCart_Input_Pojo;
import com.omrbranch.addtocart.AddToCart_Output_Pojo;
import com.omrbranch.endpoints.Endpoints;
	import com.omrbranch.globaldata.GlobalData;
import com.omrbranch.pojo.getcart.GetCart_Output_Pojo;
import com.omrbranch.pojo.getsearchresult.GetSearchResult_Input_Pojo;
	import com.omrbranch.pojo.getsearchresult.GetSearchResult_Output_Pojo;
	import com.omrbranch.utility.BaseClass;

	import io.cucumber.java.en.Given;
	import io.cucumber.java.en.Then;
	import io.cucumber.java.en.When;
	import io.restassured.response.Response;

	public class TC011_CartStep extends BaseClass {
		Response response;
		
//GET SEARCH RESULT=====================================================================================
		
		@Given("User sets bearer authorization for GetSearchProductList endpoint")
		public void user_sets_bearer_authorization_for_get_search_product_list_endpoint() {

			initRequest();
			addHeader("accept", "application/json");
			addHeader("Content-Type", "application/json");
			addHeader("Authorization",
					"Bearer " + GlobalData.getGlobalDataInstance().getLogtoken());
		}

		@When("User sets request body with saved product ID")
		public void user_sets_request_body_with_saved_product_id() {

			String categoryId = GlobalData.getGlobalDataInstance().getCategoryId();
			String productId = GlobalData.getGlobalDataInstance().getProductId();
			GetSearchResult_Input_Pojo inputPojo = new GetSearchResult_Input_Pojo(categoryId,productId,
					"product");
			addPayload(inputPojo);
		}

		@When("User sends {string} request to the GetSearchProductList endpoint")
		public void user_sends_request_to_the_get_search_product_list_endpoint(String type) {

			response = sendRequest(type, Endpoints.GETSEARCHRESULT);
			int statusCode = getStatusCode(response);
			GlobalData.getGlobalDataInstance().setStatusCode(statusCode);
			getResponseBody(response);
		}

		@Then("Verify the response includes product with specification {string} and save the variant ID")
		public void verify_the_response_includes_product_with_specification_and_save_the_variant_id(
				String expSpecification) {

			GetSearchResult_Output_Pojo outputPojo = response.as(GetSearchResult_Output_Pojo.class);
			String actSpecification =
					outputPojo.getData()
					.get(0)
					.getVariations()
					.get(1)
					.getSpecifications();
			Assert.assertEquals("Verify Product Specification",expSpecification,actSpecification);
			String variantId = String.valueOf(outputPojo.getData()
					.get(0)
					.getVariations()
					.get(1)
					.getId());		
			GlobalData.getGlobalDataInstance().setVariantId(variantId);
			System.out.println("Variant Id : " + variantId);
		}
		
		//ADD TO CART ========================================================================================

		@Given("User sets bearer authorization for AddToCart endpoint")
		public void user_sets_bearer_authorization_for_add_to_cart_endpoint() {

			initRequest();

			addHeader("accept", "application/json");
			addHeader("Content-Type", "application/json");
			addHeader("Authorization","Bearer " + GlobalData.getGlobalDataInstance().getLogtoken());
		}

		@When("User sets request body using saved variant ID")
		public void user_sets_request_body_using_saved_variant_id() {

			String productId = GlobalData.getGlobalDataInstance().getProductId();
			String variantId = GlobalData.getGlobalDataInstance().getVariantId();
			AddToCart_Input_Pojo inputPojo = new AddToCart_Input_Pojo(productId,variantId,"plus");
			addPayload(inputPojo);
		}

		@When("User sends {string} request to the AddToCart endpoint")
		public void user_sends_request_to_the_add_to_cart_endpoint(String type) {

			response = sendRequest(type, Endpoints.ADDTOCART);
			int statusCode = getStatusCode(response);
			GlobalData.getGlobalDataInstance().setStatusCode(statusCode);
			getResponseBody(response);
		}

		@Then("Verify the AddToCart response message is {string}")
		public void verify_the_add_to_cart_response_message_is(
				String expMessage) {

			AddToCart_Output_Pojo outputPojo = response.as(AddToCart_Output_Pojo.class);
			Assert.assertEquals("Verify Add To Cart Message",expMessage,outputPojo.getMessage());
			System.out.println("Cart Count : "+ outputPojo.getCart_count());
		}
		
		// GET CART ITEMS===============================================================================
		
		@Given("User sets bearer authorization for GetCart endpoint")
		public void user_sets_bearer_authorization_for_get_cart_endpoint() {

			initRequest();
			addHeader("accept", "application/json");
			addHeader("Authorization","Bearer " + GlobalData.getGlobalDataInstance().getLogtoken());
		}

		@When("User sends {string} request to the GetCart endpoint")
		public void user_sends_request_to_the_get_cart_endpoint(String type) {

			response = sendRequest(type, Endpoints.GETCARTITEMS);
			int statusCode = getStatusCode(response);
			GlobalData.getGlobalDataInstance().setStatusCode(statusCode);
			getResponseBody(response);
		}

		@Then("Verify the GetCart response message is {string} Save the cart ID")
		public void verify_the_get_cart_response_message_is_save_the_cart_id(String expMessage) {

			GetCart_Output_Pojo outputPojo = response.as(GetCart_Output_Pojo.class);
			Assert.assertEquals("Verify GetCart Message",expMessage, outputPojo.getMessage());
					
			String cartId = String.valueOf(outputPojo.getData().get(0).getCart_id());
			GlobalData.getGlobalDataInstance().setCartId(cartId);
			System.out.println("Cart Id : " + cartId);
		}
	}

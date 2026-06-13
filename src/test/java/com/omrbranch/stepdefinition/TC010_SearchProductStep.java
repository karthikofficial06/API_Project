package com.omrbranch.stepdefinition;

import org.junit.Assert;

import com.omrbranch.endpoints.Endpoints;
import com.omrbranch.globaldata.GlobalData;
import com.omrbranch.pojo.searchproduct.SearchProduct_Input_Pojo;
import com.omrbranch.pojo.searchproduct.SearchProduct_Output_Pojo;
import com.omrbranch.utility.BaseClass;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

public class TC010_SearchProductStep extends BaseClass {
	Response response;

	@Given("User sets headers for SearchProduct")
	public void user_sets_headers_for_search_product() {

		initRequest();
		addHeader("accept", "application/json");
		addHeader("Content-Type", "application/json");
	}

	@When("User sets request body to search for product {string}")
	public void user_sets_request_body_to_search_for_product(String productName) {

		SearchProduct_Input_Pojo inputPojo = new SearchProduct_Input_Pojo(productName);
		addPayload(inputPojo);
	}

	@When("User sends {string} request to the SearchProduct endpoint")
	public void user_sends_request_to_the_search_product_endpoint(String type) {

		response = sendRequest(type, Endpoints.SEARCHPRODUCT);
		int statusCode = getStatusCode(response);
		GlobalData.getGlobalDataInstance().setStatusCode(statusCode);
		getResponseBody(response);
	}

	@Then("Verify the search result includes product name {string} and Save the category ID and product ID from response")
	public void verify_the_search_result_includes_product_name_and_save_the_category_id_and_product_id_from_response(
			String expProductName) {

		SearchProduct_Output_Pojo outputPojo = response.as(SearchProduct_Output_Pojo.class);
		String actProductName = outputPojo.getData().get(0).getText();
				
		Assert.assertEquals("Verify Product Name",expProductName,actProductName);
		int categoryId = outputPojo.getData().get(0).getCategory_id();
		int productId = outputPojo.getData().get(0).getId();
		GlobalData.getGlobalDataInstance().setCategoryId(String.valueOf(categoryId));
		GlobalData.getGlobalDataInstance().setProductId(String.valueOf(productId));
				
		System.out.println("Category Id : " + categoryId);
		System.out.println("Product Id : " + productId);
	}
}
Feature: Change User Profile Picture via API
  
  This feature handles updating a user's profile picture.
  The user must first be authenticated to access the profile update endpoint.

  @ProfileLogin
  Scenario: Get User logtoken from login endpoint
    Given User adds required login headers
    When User adds basic authentication for login
    And User sends "POST" request to the login endpoint
    Then User should verify the status code is 200
    And User should verify the login response body contains firstName "Karthikeyan" and save the logtoken

  @Profilev
  Scenario: Change user profile picture
    Given User sets bearer authorization using the saved logtoken for profile picture endpoint
    And User sets multipart request body with valid image file for profile update
    When User sends "POST" request to the ChangeProfile endpoint
    Then User should verify the status code is 200
    And User should verify the response message is "Profile updated Successfully"
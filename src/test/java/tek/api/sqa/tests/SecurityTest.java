package tek.api.sqa.tests;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class SecurityTest extends APITestConfig {
	
	@Test
	public void testGenerateTokenValidUser() {
		
		//First Step to set Base URL done at BeforeMethod Annotation 
		// 2nd prepare Request.
		//Create Request Body.
		//Option 1) Creating a Map
		//Option 2) Create and Encapsulated object
		//Option 3) String as JSON Object. (Not Recommended) 
		Map<String, String> tokenRequestBody = new HashMap<>();
		tokenRequestBody.put("username", "supervisor");
		tokenRequestBody.put("password", "tek_supervisor");
		//Given prepare Request.
		RequestSpecification request = RestAssured.given().body(tokenRequestBody);
		//Set Content type
		request.contentType(ContentType.JSON);
		//When sending request to end-point
		Response response = request.when().post("/api/token");
		//Optional to print response in console.
		response.prettyPrint();
		Assert.assertEquals(response.getStatusCode(), 200);
		//Assert token is not null.
		//Using jsonPath we can get value of any entity in response.
		String generatedToken = response.jsonPath().get("token");
		Assert.assertNotNull(generatedToken);

	}
	
	//Create Test to generate token with Invalid Username.
		//Assert errorMessage "User not found" 
		@Test(dataProvider = "invalidTokenData")
		public void tokenWithInvalidDataTest(String username, String password,
				
				String expectedErrorMessager){
			
			Map<String, String> requestBody = new HashMap<>();
			requestBody.put("username", "WrongUserName");
			requestBody.put("password", "tek_supervisor");
			RequestSpecification request = RestAssured.given().body(requestBody);
			Response response = request.when().post("/api/token");
			response.prettyPrint();
			Assert.assertEquals(response.getStatusCode(), 400);
			String errorMessage = response.jsonPath().getString("errorMessage");
			Assert.assertEquals(errorMessage, "User not found"); 
						
		}
		private Object[][] invalidTokenData() {
			Object[][] data = {
					{"WrongUser" , "tek_supervisor" , "User not found"},
					{"supervisor", "WrongPssword" , "Password Not Matched"}
			};
			return data; 
			}
		
}

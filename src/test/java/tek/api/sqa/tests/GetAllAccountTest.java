package tek.api.sqa.tests;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import tek.api.sqa.base.APITestConfig;

public class GetAllAccountTest extends APITestConfig {
	
	@Test
	public void getAllAccountTest() {
		
		//prepare the request.
		String token = getValidToken();
		RequestSpecification request = RestAssured.given();
		request.header("Authorization", "Bearer " + token); 
		request.when().get(EndPoints.Get-All_Accounts.getValue());
		
	}
	
	

}

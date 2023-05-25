package tek.api.sqa.base;

import com.aventstack.extentreports.testng.listener.ExtentITestListenerAdapter;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners({ExtentITestListenerAdapter.class})
public class APITestConfig extends BaseConfig {

    @BeforeMethod
    public void setupApiTest() {
    	//First Step Setup BaseURL to RestAssured
        System.out.println("Setting up Test");
        RestAssured.baseURI = getBaseUrl();
    }
    
    public String getValidToken() {
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
}

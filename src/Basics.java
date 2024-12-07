import io.restassured.RestAssured;
import io.restassured.matcher.ResponseAwareMatcher;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import repackage.Repackage;
import org.json.JSONArray;

import com.aventstack.extentreports.util.Assert;

import static org.hamcrest.Matchers.equalTo;
import static org.testng.Assert.assertEquals;

import javax.print.DocFlavor.STRING;

import Files.ReUsableMethod;
import Files.payLoads;

import static io.restassured.RestAssured.*;

public class Basics {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// given> all input details like parameter, header
		// when > submit the API> Resources.http method
		// then> validate the response
		/*
		 * RestAssured.baseURI = "https://rahulshettyacademy.com";
		 * given().log().all().queryParam("key",
		 * "qaclick123").header("Content-Type","application/json")
		 * .body(payLoads.addPlace()) //directly also we can add body here present in
		 * addPlace method .when().post("maps/api/place/add/json")
		 * .then().log().all().assertThat().statusCode(200).body("scope",
		 * equalTo("APP")).header("server", "Apache/2.4.52 (Ubuntu)");
		 */

		// above will give response

		// following code to copy any value from repsons and update it
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		String response = given().log().all().
				queryParam("key", "qaclick123").
				header("Content-Type", "application/json")
				.body(payLoads.addPlace()) // directly also we can add body here present in addPlace method
				.when().post("maps/api/place/add/json")
				.then().assertThat().statusCode(200) // without log we are not be see response
				.body("scope", equalTo("APP")).header("server", "Apache/2.4.52 (Ubuntu)").extract().response()
				.asString();
		System.out.println(response);         // this will give json body in repsonse

		JsonPath js = new JsonPath(response); // JsonPath class // for parsing json
		String placeId = js.get("place_id");
		System.out.println(placeId);

		// update above place
		String newAddress = "70 winter walk, USA11";
		given().log().all().queryParam("key", "qaclick123")
						.header("Content-Type", "application/json")
				.body("{\r\n"
						+ "\"place_id\":\""+placeId+"\",\r\n"
						+ "\"address\":\""+newAddress+"\",\r\n"
						+ "\"key\":\"qaclick123\"\r\n"
						+ "}")
				.when().put("maps/api/place/update/json")
				.then().assertThat().log().all().statusCode(200)
				.body("msg", equalTo("Address successfully updated"));

		// get place and validate
		String getPlaceHolder = given().log().all()
				.queryParam("place_id", placeId)
				.queryParam("key", "qaclick123")
				.when().get("maps/api/place/get/json")
				.then().log().all().assertThat().statusCode(200).extract().asString();

		JsonPath js1 = ReUsableMethod.rawToJson(getPlaceHolder);
		String actualAddress = js1.getString("address");
		System.out.println(actualAddress);

		// we are out of rest as not used here given when then // let us use cucumber junit or testng to user assert
		assertEquals(actualAddress, newAddress);
		
	}

}

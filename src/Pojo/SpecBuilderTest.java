package Pojo;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.List;

import io.restassured.matcher.ResponseAwareMatcher;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;

public class SpecBuilderTest {
	public static void main(String[] args) {
		addPlace p = new addPlace();
		p.setAccuracy(50);
		p.setAddress("29, side lay address");
		p.setLanguage("French-IN");
		p.setPhone_number("483934");
		p.setWebsite("http://google.com");
		p.setName("frontline hous");

		List<String> myList = new ArrayList<String>();
		myList.add("shoe park");
		myList.add("shop");
		p.setTypes(myList);
		Location l = new Location();
		l.setLat(-38.873);
		l.setLng(33.425);
		p.setLocation(l);

		RequestSpecification req = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com/")
				.addQueryParam("key", "qaclic123").setContentType(ContentType.JSON).build();
		ResponseSpecification resspec= new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		
		RequestSpecification res = given().spec(req).body(p);
		String response = res.when().post("maps/api/place/add/json")
				.then()
				.spec(resspec).extract()
				.response().asString();
		System.out.println(response);
	}
}

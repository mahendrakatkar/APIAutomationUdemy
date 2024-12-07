package Pojo;
import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.List;

import io.restassured.matcher.ResponseAwareMatcher;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
public class serializeTest {
public static void main(String[] args) {
	addPlace p= new addPlace();
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
	
	
	RestAssured.baseURI = "https://rahulshettyacademy.com";
	String responseString = given().log().all().
			queryParam("key", "qaclick123")
			.body(p)
			.when().post("maps/api/place/add/json")
			.then().assertThat().statusCode(200).extract().response().asString();
System.out.println(responseString);
}
}

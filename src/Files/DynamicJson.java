package Files;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;


public class DynamicJson {

/*	@Test
	public void addBook() {
		RestAssured.baseURI="https://rahulshettyacademy.com";
		String response= given()
		.header("Content-Type","application/json").body(payLoads.addBook())
		.when().post("/Library/Addbook.php").
		then().assertThat().log().all().statusCode(200)
		.extract().asString();
		
		JsonPath js=ReUsableMethod.rawToJson(response);
		String id= js.get("ID");
	System.out.println(id);	*/
	

	@Test(dataProvider = "BookData")
	public void addBook(String isbn, String aisle) 
	{
		RestAssured.baseURI="https://rahulshettyacademy.com";
		String res= given()
		.header("Content-Type","application/json").body(payLoads.addBook(isbn,aisle))
		.when().post("/Library/Addbook.php").
		then().assertThat().log().all().statusCode(200)
		.extract().asString();
		
		JsonPath js=ReUsableMethod.rawToJson(res);
		String id= js.get("ID");
	System.out.println(id);	
	}
	
	@DataProvider(name="BookData")
	public Object[][] getData() {
		return new Object[][] {{"ais","8371"},{"aisl","8372"},{"aisle","8373"}};
	
	
	}
	
}

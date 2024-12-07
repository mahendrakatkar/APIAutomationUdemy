package Pojo;


	import com.aventstack.extentreports.gherkin.model.Given;
	import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.apache.xmlbeans.impl.xb.xsdschema.ListDocument.List;
import org.testng.Assert;

import io.restassured.RestAssured;
	import io.restassured.path.json.JsonPath;
	public class oAuthTest2pojo {
	public static void main(String[] args) {
		String[] courseTitles= {"Selenium Webdriver Java","Cypress","Protractor"};
		
		String response= given()
		.formParam("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
		.formParam("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
		.formParam("grant_type", "client_credentials")
		.formParam("scope", "trust")
		.when().log().all()
		.post("https://rahulshettyacademy.com/oauthapi/oauth2/resourceOwner/token")
		.asString();
		System.out.println(response);
		
		JsonPath jsonpath= new JsonPath(response);
		String accessToken=jsonpath.getString("access_token");
		
		GetCourses gc= given()
		.queryParam("access_token", accessToken)
		.when()
		.log().all()
		.get("https://rahulshettyacademy.com/oauthapi/getCourseDetails")
		 .as(GetCourses.class);     // removed .asString(); and added parent class
		System.out.println(gc.getLinkedIn());
		System.out.println(gc.getInstructor());
		System.out.println(gc.getCourses().getApi().get(1).getCourseTitle());
		
		java.util.List<api> getapiCourses=gc.getCourses().getApi();
		for(int i=0;i<getapiCourses.size();i++) {
		if(	getapiCourses.get(i).getCourseTitle().equalsIgnoreCase("SoapUI Webservices testing")){
		System.out.println("SoapUI Webservices testing price is= "+getapiCourses.get(i).getPrice());
		}
		}
		
		ArrayList<String> a= new ArrayList<String>();
		
		
				java.util.List<webAutomation> w=gc.getCourses().getWebAutomation();
		for(int i=0;i<w.size();i++) {
			System.out.println(w.get(i).getCourseTitle());  //for printing courses from  WebAutomation
			
			//for comparison
			a.add(w.get(i).getCourseTitle());   
			
		}
		// for comparing 2 array> converted to arraylist and checked 2 arraylis
		java.util.List<String>ExpectedResult= Arrays.asList(courseTitles);
		Assert.assertEquals(a.equals(ExpectedResult), true);
		}
	}


import com.aventstack.extentreports.gherkin.model.Given;
import static io.restassured.RestAssured.*;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
public class oAuthTest {
public static void main(String[] args) {
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
	
	String response2= given()
	.queryParam("access_token", accessToken)
	.when()
	.log().all()
	.get("https://rahulshettyacademy.com/oauthapi/getCourseDetails")
	.asString();
	System.out.println(response2);
}
}

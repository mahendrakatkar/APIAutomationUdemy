import com.aventstack.extentreports.gherkin.model.Given;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

import java.io.File;

public class BugTestJira {
public static void main(String[] args) {
	RestAssured.baseURI = "https://mahendrakumarkatkar.atlassian.net";
String createIssueResponse= given().
header("Content-Type","application/json").
header("Authorization","Basic bWFoZW5kcmFrdW1hcmthdGthckBnbWFpbC5jb206QVRBVFQzeEZmR0YwRi11ZVROekNsTDlDOTlWcnFnT1pOYjZVaVRGSWNpdC1RcXpBYjFWbEY1WWFvRTlNYnBYZjlDbVdDek9MNkVQWEEzSF9VcnhUazNGSkJrMzFubUhGS3gwU1lFUXN0OV83TVNBb1pQUHhnYkZScG9uRmxUSm9kdkQ2a3FWT1E3azNuNWNYYWVFZjRabUNWcTJHemRtQ2g3Wm92bnZ1QXR0STZMeDA4eXFPY0k4PUE3MjhGQTRB")
.body("{\r\n"
		+ "    \"fields\": {\r\n"
		+ "       \"project\":\r\n"
		+ "       {\r\n"
		+ "          \"key\": \"SCRUM\"\r\n"
		+ "       },\r\n"
		+ "       \"summary\": \"attachment from selenium.\",\r\n"
		+ "       \"issuetype\": {\r\n"
		+ "          \"name\": \"Bug\"\r\n"
		+ "       }\r\n"
		+ "   }\r\n"
				+ "}")
		.log().all()
		.post("rest/api/3/issue")
		.then().log().all().assertThat().statusCode(201).extract().response().asString();
		JsonPath js= new JsonPath(createIssueResponse);
		String issueId=js.getString("id");
		System.out.println(issueId);

		// add attachments
		given()
		.pathParam("idkey", issueId)
		.header("X-Atlassian-Token","no-check")
		.header("Authorization","Basic bWFoZW5kcmFrdW1hcmthdGthckBnbWFpbC5jb206QVRBVFQzeEZmR0YwRi11ZVROekNsTDlDOTlWcnFnT1pOYjZVaVRGSWNpdC1RcXpBYjFWbEY1WWFvRTlNYnBYZjlDbVdDek9MNkVQWEEzSF9VcnhUazNGSkJrMzFubUhGS3gwU1lFUXN0OV83TVNBb1pQUHhnYkZScG9uRmxUSm9kdkQ2a3FWT1E3azNuNWNYYWVFZjRabUNWcTJHemRtQ2g3Wm92bnZ1QXR0STZMeDA4eXFPY0k4PUE3MjhGQTRB")
		.multiPart("file", new File("C:\\Users\\Mahendrakumar_Katkar\\Desktop\\Capture.JPG")).log().all()
		.post("/rest/api/3/issue/{idkey}/attachments")
		.then().log().all().assertThat().statusCode(200);
}
}

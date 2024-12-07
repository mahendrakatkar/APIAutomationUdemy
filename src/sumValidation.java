import org.testng.Assert;
import org.testng.annotations.Test;

import Files.payLoads;
import io.restassured.path.json.JsonPath;

public class sumValidation {

	// total purchase amount validation
	@Test
	public void sumOfCourses() {
		int sum=0;
		JsonPath js = new JsonPath(payLoads.coursePrice());
		int count = js.getInt("courses.size()");
		for(int i=0;i<count;i++) {
		int price=	js.get("courses["+i+"].price");
		int copies=	js.get("courses["+i+"].copies");
		int ammount= price*copies;
		System.out.println(ammount);
		sum= sum+ammount;
		System.out.println(sum);
		
		}int purAmount=js.get("dashboard.purchaseAmount");
		Assert.assertEquals(sum, purAmount,"purchase ammount is not matched" );
		
	}
}

import org.apache.poi.hssf.record.PageBreakRecord.Break;

import Files.payLoads;
import groovy.transform.ToString;
import io.restassured.path.json.JsonPath;

public class complexJsonParse {
	public static void main(String[] args) {
		JsonPath js = new JsonPath(payLoads.coursePrice());
		// use json editor for payload so that it will structure
		// count of course
		int count = js.getInt("courses.size()");
		System.out.println(count);

		// print purchase amount
		int price = js.getInt("dashboard.purchaseAmount");
		System.out.println(price);

//first title of course
		String firstTitle=js.get("courses[0].title");
		System.out.println(firstTitle);
		
		// All title and prices
		for(int i=0;i<count;i++) {
			String courseTitle=js.get("courses["+i+"].title");
			System.out.println(js.get("courses["+i+"].price").toString());
			System.out.println(courseTitle);
		}
		
		//print no. of copies sold for RPA
		for(int i=0;i<count;i++) {
			String courseTitle=js.get("courses["+i+"].title");
			if(courseTitle.equalsIgnoreCase("RPA")) {
			int copiesCount =js.get("courses["+i+"].copies");
			System.out.println("Copies sold by RPA ARE " +copiesCount);
			break;
			}
		}
		
		
		
		
	}
}

package Pojo;

public class GetCourses {
	
	//for POJO classes variables are mandatorily private as below 
	////dont need to write getter setter method manually>>> imp imp> use shortcut >alt+shift+s
	private String url;
	private String services;
	private String expertise;
	private	Courses Courses;           // changed to courses after creating courses class as  
	private String instructor;
	private String linkedIn;
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getServices() {
		return services;
	}
	public void setServices(String services) {
		this.services = services;
	}
	public String getExpertise() {
		return expertise;
	}
	public void setExpertise(String expertise) {
		this.expertise = expertise;
	}
	public Pojo.Courses getCourses() {
		return Courses;
	}
	public void setCourses(Pojo.Courses courses) {
		Courses = courses;
	}
	public String getInstructor() {
		return instructor;
	}
	public void setInstructor(String instructor) {
		this.instructor = instructor;
	}
	public String getLinkedIn() {
		return linkedIn;
	}
	public void setLinkedIn(String linkedIn) {
		this.linkedIn = linkedIn;
	}
	
	
	
}

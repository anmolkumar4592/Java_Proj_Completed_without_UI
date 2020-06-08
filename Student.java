package service;

public class Student {
private String name;
private String usn;
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getUsn() {
	return usn;
}
public void setUsn(String usn) {
	this.usn = usn;
}

Student (String name, String usn){
	this.name = name ; 
	this.usn = usn ;
	
}
}

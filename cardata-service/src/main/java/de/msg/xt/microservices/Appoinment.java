package de.msg.xt.microservices;
/**
 * Appointment Data 
 * 
 * @author Michael Schäfer 
 * 
 * */

public class Appoinment {

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Appoinment(String name) {
		super();
		this.name = name;
	}
	
	public Appoinment() {
		super();
	}
	
	@Override
	public String toString() {
		return "Appoinment [name=" + name + "]";
	}
	
}

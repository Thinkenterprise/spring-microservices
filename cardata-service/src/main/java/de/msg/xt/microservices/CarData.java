package de.msg.xt.microservices;

public class CarData {

	
	private int id;
	private long value;
	
	public CarData(int id, long value) {
		super();
		this.id = id;
		this.value = value;
	}
	public CarData() {
		super();
		
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public long getValue() {
		return value;
	}
	public void setValue(long value) {
		this.value = value;
	}
	
	@Override
	public String toString() {
		return "CarData [id=" + id + ", value=" + value + "]";
	}
	
	
}

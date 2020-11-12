package DTO;

public class Car{
	public Car() {
	}
	public Car(String name, int minSpeed, int maxSpeed) {
		this.name = name;
		this.minSpeed = minSpeed;
		this.maxSpeed = maxSpeed;
	}
	private String name;
	private int minSpeed;
	private int maxSpeed;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getMinSpeed() {
		return minSpeed;
	}
	public void setMinSpeed(int minSpeed) {
		this.minSpeed = minSpeed;
	}
	public int getMaxSpeed() {
		return maxSpeed;
	}
	public void setMaxSpeed(int maxSpeed) {
		this.maxSpeed = maxSpeed;
	}
}
package DTO;

public class Car{
	public Car() {
	}
	public Car(String name, int minSpeed, int maxSpeed) {
		setName(name);
		setMinSpeed(minSpeed);
		setMaxSpeed(maxSpeed);
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
		if(minSpeed>0 && minSpeed<getMaxSpeed()) {
			this.minSpeed = minSpeed;
		}else {
			this.minSpeed = 1;
		}
	}
	public int getMaxSpeed() {
		return maxSpeed;
	}
	public void setMaxSpeed(int maxSpeed) {
		if(maxSpeed>0 && maxSpeed>getMinSpeed()) {
			this.maxSpeed = maxSpeed;
		}else {
			this.maxSpeed = 2;
		}
	}
}
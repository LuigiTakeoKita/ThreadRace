package DTO;
import java.util.Random;

public class RaceCar extends Car implements Runnable{
	public RaceCar() {
		super();
	}
	public RaceCar(Car car) {
		super(car.getName(), car.getMinSpeed(), car.getMaxSpeed());
	}
	public RaceCar(String name, int minSpeed, int maxSpeed, int[] track, int laps) {
		super(name, minSpeed, maxSpeed);
		this.track = track;
		this.laps = laps;
	}
	private int[] track;
	private int laps;
	public int[] getTrack() {
		return track;
	}
	public void setTrack(int[] track) {
		this.track = track;
	}
	public int getLaps() {
		return laps;
	}
	public void setLaps(int laps) {
		this.laps = laps;
	}
	@Override
	public void run() {
		Random random = new Random();
		boolean running = true;
		int speed = 0;
		int currentLap = 1;
		int sector = 1;
		while(running) {
			speed = random.nextInt((getMaxSpeed() - getMinSpeed()) + 1) + getMinSpeed();
			System.out.println("| Car: "+getName()+" | Lap: "+currentLap+" | Sector: "+sector+" | Max Speed on this Sector: "+getTrack()[sector-1]+" | Speed: "+speed+" |");
			if(speed > getTrack()[sector-1]) { // caso a velocidade seja maior q o valor max do setor o carro irá bater e será recolocado na pista
				try {
					System.out.println("Respawning car");
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			sector++;
			if(sector > getTrack().length) {
				sector = 1;
				currentLap++;
			}
			if(currentLap > getLaps()) {
				break; // win
			}
			Thread.currentThread();
			Thread.yield();
		}
		System.out.println(getName()+ " Finished");
	}
}
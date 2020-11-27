package DTO;
import java.util.Random;

public class RaceCar extends Car implements Runnable{
	public RaceCar() {
		super();
	}
	public RaceCar(Car car) {
		super(car.getName(), car.getMinSpeed(), car.getMaxSpeed());
	}
	public RaceCar(String name, int minSpeed, int maxSpeed, int[] track, int laps, int replaceTime, RaceListener raceListener) {
		super(name, minSpeed, maxSpeed);
		this.track = track;
		this.laps = laps;
		this.replaceTime = replaceTime;
		this.raceListener = raceListener;
	}
	private int[] track;
	private int laps;
	private int replaceTime;
	private RaceListener raceListener;
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
	public int getReplaceTime() {
		return replaceTime;
	}
	public void setReplaceTime(int replaceTime) {
		this.replaceTime = replaceTime;
	}
	public RaceListener getRaceListener() {
		return raceListener;
	}
	public void setRaceListener(RaceListener raceListener) {
		this.raceListener = raceListener;
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
			// Caso a velocidade seja maior que o valor máximo do setor o carro irá bater e será recolocado na pista
			if(speed > getTrack()[sector-1]) { 
				try {
					System.out.println("Replacing Car: "+getName());
					Thread.sleep(getReplaceTime());
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
				break; // Finalizou a corrida
			}
			Thread.currentThread();
			Thread.yield();
		}
		System.out.println(getName()+ " Finished");
		getRaceListener().RaceFinished(getName());
	}
}
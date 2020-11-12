package DTO;

public class Race {
	public Race(Car[] cars, int[] track, int laps) {
		super();
		this.cars = cars;
		this.track = track;
		this.laps = laps;
	}
	private Car[] cars;
	private int[] track;
	private int laps; 
	public Car[] getCars() {
		return cars;
	}
	public void setCars(Car[] cars) {
		this.cars = cars;
	}
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
	public String startRace() {
		String winner = "";
		Thread[] carsT = new Thread[getCars().length];
		for (int i = 0; i < getCars().length; i++) {
			RaceCar c = new RaceCar(getCars()[i]);
			c.setTrack(getTrack());
			c.setLaps(getLaps());
			carsT[i] = new Thread(c);
		}
		for (Thread c : carsT) {
			c.start();
		}
		try {
			for (int i = 0; i < carsT.length; i++) {
				carsT[i].join();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return winner;
	}
}
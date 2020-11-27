package DTO;

public class Race implements RaceListener{
	public Race(Car[] cars, int[] track, int laps, int replaceTime) {
		super();
		this.cars = cars;
		this.track = track;
		this.laps = laps;
		this.replaceTime = replaceTime;
		this.finishedPos = new String[cars.length];
	}
	private Car[] cars;
	private int[] track;
	private int laps;
	private int replaceTime;
	private String[] finishedPos;
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
	public int getReplaceTime() {
		return replaceTime;
	}
	public void setReplaceTime(int replaceTime) {
		this.replaceTime = replaceTime;
	}
	public String[] getFinishedPos() {
		return finishedPos;
	}
	public void setFinishedPos(String[] finishedPos) {
		this.finishedPos = finishedPos;
	}
	public void setFinishedPos(String car, int pos) {
		this.finishedPos[pos] = car;
	}
	public String[] startRace() {
		Thread[] carsT = new Thread[getCars().length];
		for (int i = 0; i < getCars().length; i++) { // Criação das Threads
			RaceCar c = new RaceCar(getCars()[i]);
			c.setTrack(getTrack());
			c.setLaps(getLaps());
			c.setReplaceTime(getReplaceTime());
			c.setRaceListener(this);
			carsT[i] = new Thread(c);
		}
		System.out.println("Starting Race");
		for (Thread c : carsT) { // Iniciação das Threads
			c.start();
		}
		try {
			for (int i = 0; i < carsT.length; i++) {
				carsT[i].join();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return getFinishedPos();
	}
	public synchronized void RaceFinished(String car) {
		for (int i = 0; i < getCars().length; i++) {
			if(getFinishedPos()[i]  == null) {
				setFinishedPos(car, i);
				break;
			}
		}
	}
}
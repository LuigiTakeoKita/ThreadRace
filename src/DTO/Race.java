package DTO;

public class Race implements RaceListener{
	public Race(Car[] cars, int[] track, int laps) {
		super();
		this.cars = cars;
		this.track = track;
		this.laps = laps;
	}
	private Car[] cars;
	private int[] track;
	private int laps;
	private String winner;
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
	public String getWinner() {
		return winner;
	}
	public void setWinner(String winner) {
		this.winner = winner;
	}
	public String startRace() {
		Thread[] carsT = new Thread[getCars().length];
		for (int i = 0; i < getCars().length; i++) { // Criação das Threads
			RaceCar c = new RaceCar(getCars()[i]);
			c.setTrack(getTrack());
			c.setLaps(getLaps());
			c.setRaceListener(this);
			carsT[i] = new Thread(c);
		}
		setWinner("");
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
		return getWinner();
	}
	@Override
	public void RaceFinished(String car) {
		synchronized (this) {
			if(getWinner().equals("")) {
				setWinner(car);
			}
		}
	}
}
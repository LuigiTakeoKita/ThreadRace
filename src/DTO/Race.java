package DTO;

public class Race implements RaceListener{
	public Race(Car[] cars, int[] track, int laps, int replaceTime, int lapsToPitstop) {
		super();
		setCars(cars);
		setTrack(track);
		setLaps(laps);
		setReplaceTime(replaceTime);
		setLapstoPitstop(lapsToPitstop);
	}
	private Car[] cars;
	private int[] track;
	private int laps;
	private int replaceTime;
	private int lapstoPitstop;
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
		boolean flag = true;
		for (int i : track) {
			if(i<0) {
				flag = false;
				this.track = new int[] {6,2,6,2};
				break;
			}
		}
		if (flag) {
			this.track = track;
		}
	}
	public int getLaps() {
		return laps;
	}
	public void setLaps(int laps) {
		if(laps>0) {
			this.laps = laps;
		}else {
			this.laps = 1;
		}
	}
	public int getReplaceTime() {
		return replaceTime;
	}
	public void setReplaceTime(int replaceTime) {
		if(replaceTime>0) {
			this.replaceTime = replaceTime;
		}else {
			this.replaceTime = 1;
		}
	}
	public int getLapstoPitstop() {
		return lapstoPitstop;
	}
	public void setLapstoPitstop(int lapstoPitstop) {
		if(lapstoPitstop>0) {
			this.lapstoPitstop = lapstoPitstop;
		}else {
			this.lapstoPitstop = 1;
		}
	}
	public String[] getFinishedPos() {
		return finishedPos;
	}
	public void setFinishedPos(String[] finishedPos) {
		this.finishedPos = finishedPos;
	}
	public void setFinishedPos(String car, int pos) {
		if(pos>=0 && pos<this.finishedPos.length) {
			this.finishedPos[pos] = car;
		}
	}
	public String[] startRace() {
		setFinishedPos(new String[getCars().length]);
		Thread[] carsT = new Thread[getCars().length];
		for (int i = 0; i < getCars().length; i++) { // Criação das Threads
			RaceCar c = new RaceCar(getCars()[i]);
			c.setRace(getTrack(), getLaps(), getReplaceTime(), getLapstoPitstop(), this);
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
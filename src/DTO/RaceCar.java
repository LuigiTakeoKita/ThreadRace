package DTO;
import java.util.Random;

public class RaceCar extends Car implements Runnable{
	public RaceCar() {
		super();
	}
	public RaceCar(Car car) {
		super(car.getName(), car.getMinSpeed(), car.getMaxSpeed());
	}
	private int[] track;
	private int laps;
	private int replaceTime;
	private int lapsToPitstop;
	private RaceListener raceListener;
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
	public int getLapsToPitstop() {
		return lapsToPitstop;
	}
	public void setLapsToPitstop(int lapsToPitstop) {
		if(lapsToPitstop>0) {
			this.lapsToPitstop = lapsToPitstop;
		}else {
			this.lapsToPitstop = 1;
		}
	}
	public RaceListener getRaceListener() {
		return raceListener;
	}
	public void setRaceListener(RaceListener raceListener) {
		this.raceListener = raceListener;
	}
	public void setRace(int[] track, int laps, int replaceTime, int lapsToPitstop, RaceListener raceListener) {
		setTrack(track);
		setLaps(laps);
		setReplaceTime(replaceTime);
		setLapsToPitstop(lapsToPitstop);
		setRaceListener(raceListener);
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
				if(currentLap%getLapsToPitstop() == 0) {
					int pitstopTime = random.nextInt(2000+1) + 1000;
					try {
						System.out.println("Pitstop Car: "+getName());
						Thread.sleep(pitstopTime);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
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
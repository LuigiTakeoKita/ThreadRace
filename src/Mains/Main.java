package Mains;
import DTO.Car;
import DTO.Race;

public class Main {
	public static void main(String[] args) {
		Car car1 = new Car("A", 1, 5);
		Car car2 = new Car("B", 1, 5);
		Car car3 = new Car("C", 1, 5);
		Car car4 = new Car("D", 1, 5);
		Car car5 = new Car("E", 1, 5);
		Car[] cars = {car1, car2, car3, car4, car5};
		// Cada número representa a velocidade máxima por setor da pista
		int[] track = {6,2,6,2};
		int laps = 5;
		int replacetime = 1000;
		// O pitstop acontece após conpletar a um determinado número de voltas
		int lapsToPitstop = 2; 
		Race race = new Race(cars, track, laps, replacetime, lapsToPitstop);
		String[] winner = race.startRace();
		for (int i = 0; i < winner.length; i++) {
			System.out.println((i+1)+" - "+winner[i]);
		}
		System.out.println("The Winner is: "+winner[0]);
	}
}
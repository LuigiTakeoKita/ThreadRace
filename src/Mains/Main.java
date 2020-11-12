package Mains;
import DTO.Car;
import DTO.Race;

public class Main {
	public static void main(String[] args) {
		Car car1 = new Car("A", 1, 5);
		Car car2 = new Car("B", 1, 5);
		Car[] cars = {car1, car2};
		int[] track = {6,2,6,2}; // Cada número representa a velocidade máxima por setor da pista 
		int laps = 3;
		Race race = new Race(cars, track, laps);
		String winner = race.startRace();
		System.out.println("The Winner is: "+winner);
	}
}
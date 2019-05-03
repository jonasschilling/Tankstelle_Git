package tanken_UI_test;

//import java.util.ArrayList;
import java.util.Scanner;

public class Test01 {

	public static void main(String[] args) {

		FuelPump pump1 = new FuelPump(1);
		FuelPump pump2 = new FuelPump(2);
		FuelPump pump3 = new FuelPump(3);
		FuelPump pump4 = new FuelPump(4);
		FuelPump pump5 = new FuelPump(5);

		Scanner scanner = new Scanner(System.in);

		System.out.print("1 - Tanken \n2 - Kraftstoffbestellung");
		int choice = scanner.nextInt();

		switch (choice) {
		case 1:
			System.out.print("Bitte geben sie die Nummer der Zapfsäule ein: ");
			int number = scanner.nextInt();

			switch (number) {
			case 1:
				pump1.refuel();
				break;
			case 2:
				pump2.refuel();
				break;
			case 3:
				pump3.refuel();
				break;
			case 4:
				pump4.refuel();
				break;
			case 5:
				pump5.refuel();
				break;
			default:
				System.out.println("Die Zapfsäule mit der Nummer '" + number + "' existiert nicht.");
				break;
			}
			break;

		case 2:
			System.out.println("coming soon");
			break;
		}

		scanner.close();

	}
}

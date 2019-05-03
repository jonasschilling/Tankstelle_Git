package tanken_test;

//import java.util.ArrayList;
import java.util.Scanner;

public class Test01 {

	public static void main(String[] args) {

//		Tank tank1 = new Tank("Super", 12000.0f);
//		Tank tank2 = new Tank("Diesel", 8000.0f);

//		ArrayList fuelPumps = new ArrayList<>();
//		
//		fuelPumps.add(new FuelPump(1));
//		fuelPumps.add(new FuelPump(2));
//		fuelPumps.add(new FuelPump(3));
//		fuelPumps.add(new FuelPump(4));
//		fuelPumps.add(new FuelPump(5));

		FuelPump pump1 = new FuelPump(1);
		FuelPump pump2 = new FuelPump(2);
		FuelPump pump3 = new FuelPump(3);
		FuelPump pump4 = new FuelPump(4);
		FuelPump pump5 = new FuelPump(5);

		Scanner scanner = new Scanner(System.in);

//
//		boolean loop = false;
//		float litres;
//
//		do {
//
//			boolean refuelLoop = true;
//			
//			System.out.print("Was möchten Sie tanken? \n 1 - Super \n 2 - Diesel ");
//			int tank = scanner.nextInt();															// ERSETZEN MIT GUI
//			
//			switch(tank) {
//				case 1:
//					System.out.println("Füllstand: " + tank1.getAbsFuelLevel() + "/" + tank1.getCapacity() + " ("
//							+ tank1.getRelFuelLevel() + "%)");
//	
//					while (refuelLoop) {
//						System.out.print("Wie viel Liter möchten Sie tanken? ");
//						litres = scanner.nextFloat();												// SIMULATION
//						if (litres > tank1.getAbsFuelLevel()) {
//							System.out.println("Es ist nicht genügend Kraftstoff vorhanden. Bitte Eingabe anpassen.");
//							refuelLoop = true;
//						} else {
//							pump1.setLitresRefueled(litres);
//							refuelLoop = false;
//						}
//					}
//					;
//	
//					tank1.decreaseFuelLevel(pump1.getLitresRefueled()); // TANKEN
//					System.out.println("Füllstand: " + tank1.getAbsFuelLevel() + "/" + tank1.getCapacity() + " ("
//							+ tank1.getRelFuelLevel() + "%)");
//	
//					System.out.print("Möchten Sie nochmal tanken? ");
//					loop = scanner.nextBoolean();
//					System.out.println();
//					
//					break;
//					
//				case 2:
//					System.out.println("Füllstand: " + tank2.getAbsFuelLevel() + "/" + tank2.getCapacity() + " ("
//							+ tank2.getRelFuelLevel() + "%)");
//	
//					while (refuelLoop) {
//						System.out.print("Wie viel Liter möchten Sie tanken? ");
//						litres = scanner.nextFloat();
//						if (litres > tank2.getAbsFuelLevel()) {
//							System.out.println("Es ist nicht genügend Kraftstoff vorhanden. Bitte Eingabe anpassen.");
//							refuelLoop = true;
//						} else {
//							pump1.setLitresRefueled(litres);
//							refuelLoop = false;
//						}
//					}
//					;
//	
//					tank2.decreaseFuelLevel(pump1.getLitresRefueled()); // TANKEN
//					System.out.println("Füllstand: " + tank2.getAbsFuelLevel() + "/" + tank2.getCapacity() + " ("
//							+ tank2.getRelFuelLevel() + "%)");
//	
//					System.out.print("Möchten Sie nochmal tanken? ");
//					loop = scanner.nextBoolean();
//					System.out.println();
//					
//					break;
//					
//			}
//			
//
//		} while (loop);
//
//		scanner.close();

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

		Printer printer = new Printer();
		printer.print();

		scanner.close();

	}
}

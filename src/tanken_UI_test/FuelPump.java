package tanken_UI_test;

import java.util.Scanner;

public class FuelPump {
	
	Tank tank1 = new Tank("Super", 12000.0f);
	Tank tank2 = new Tank("Diesel", 8000.0f);
	
	private float litresRefueled;
	private int id;

	public FuelPump(int id) {
		this.id = id;
	}
	
	public void refuel() {
		
		Scanner scanner = new Scanner(System.in);

		boolean loop = false;
		float litres;

		do {

			boolean refuelLoop = true;
			
			System.out.print("Was möchten Sie tanken? \n 1 - Super \n 2 - Diesel ");
			int tank = scanner.nextInt();															// ERSETZEN MIT GUI
			
			switch(tank) {
				case 1:
					System.out.println("Füllstand: " + tank1.getAbsFuelLevel() + "/" + tank1.getCapacity() + " ("
							+ tank1.getRelFuelLevel() + "%)");
	
					while (refuelLoop) {
						System.out.print("Wie viel Liter möchten Sie tanken? ");
						litres = scanner.nextFloat();												// SIMULATION
						if (litres > tank1.getAbsFuelLevel()) {
							System.out.println("Es ist nicht genügend Kraftstoff vorhanden. Bitte Eingabe anpassen.");
							refuelLoop = true;
						} else {
							setLitresRefueled(litres);
							refuelLoop = false;
						}
					}
					;
	
					tank1.decreaseFuelLevel(getLitresRefueled()); // TANKEN
					System.out.println("Füllstand: " + tank1.getAbsFuelLevel() + "/" + tank1.getCapacity() + " ("
							+ tank1.getRelFuelLevel() + "%)");
	
					System.out.print("Möchten Sie nochmal tanken? ");
					loop = scanner.nextBoolean();
					System.out.println();
					
					break;
					
				case 2:
					System.out.println("Füllstand: " + tank2.getAbsFuelLevel() + "/" + tank2.getCapacity() + " ("
							+ tank2.getRelFuelLevel() + "%)");
	
					while (refuelLoop) {
						System.out.print("Wie viel Liter möchten Sie tanken? ");
						litres = scanner.nextFloat();
						if (litres > tank2.getAbsFuelLevel()) {
							System.out.println("Es ist nicht genügend Kraftstoff vorhanden. Bitte Eingabe anpassen.");
							refuelLoop = true;
						} else {
							setLitresRefueled(litres);
							refuelLoop = false;
						}
					}
					;
	
					tank2.decreaseFuelLevel(getLitresRefueled()); // TANKEN
					System.out.println("Füllstand: " + tank2.getAbsFuelLevel() + "/" + tank2.getCapacity() + " ("
							+ tank2.getRelFuelLevel() + "%)");
	
					System.out.print("Möchten Sie nochmal tanken? ");
					loop = scanner.nextBoolean();
					System.out.println();
					
					break;
					
			}
			

		} while (loop);
		
		scanner.close();
		
	}
	
	public float getLitresRefueled() {
		return litresRefueled;
	}

	public void setLitresRefueled(float litresRefueled) {
		this.litresRefueled = litresRefueled;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	

}

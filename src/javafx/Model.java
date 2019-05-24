package javafx;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Model {

	static Model instance;

	Tank superTank = new Tank("Super", 1200.0f);
	Tank dieselTank = new Tank("Diesel", 800.0f);

	private String newFuelLevel;
	private float superProgress;
	

	public Model() {

	}

	public static Model getInstance() {
		if (Model.instance == null) {
			Model.instance = new Model();
		}
		return Model.instance;
	}
	
	public Tank getTank(String description) {
		if(description.equals("Super")) {
			return superTank;
		} else {
			return dieselTank;
		}
	}

	public void writeSuperPrice(String newPrice) {
		File file = new File("C:/Users/d074024/Desktop/resources/PrinterTest/SuperPreis.txt");
		FileWriter fw = null;
		BufferedWriter bw = null;

		try {
			fw = new FileWriter(file);
			bw = new BufferedWriter(fw);
			bw.write(newPrice);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (bw != null) {
				try {
					bw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public String readPrice(String tankDescription) {
		File file = new File("C:/Users/d074024/Desktop/resources/PrinterTest/" + tankDescription + "Preis.txt");
		String newPrice = null;
		try (FileReader fr = new FileReader(file); BufferedReader br = new BufferedReader(fr)) {
			String line;
			while ((line = br.readLine()) != null) {
				newPrice = line;
			}
		} catch (IOException e) {
		e.printStackTrace();
		}
		return newPrice;
	}
	
	public void writeFuelLevel(String tankDescription, String newFuelLevel) {
		File file = new File("C:/Users/d074024/Desktop/resources/PrinterTest/FuelLevels/" + tankDescription + "Level.txt");
		FileWriter fw = null;
		BufferedWriter bw = null;

		try {
			fw = new FileWriter(file);
			bw = new BufferedWriter(fw);
			bw.write(newFuelLevel);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (bw != null) {
				try {
					bw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public String readFuelLevel(String tankDescription) {
		File file = new File("C:/Users/d074024/Desktop/resources/PrinterTest/FuelLevels/" + tankDescription + "Level.txt");
		try (FileReader fr = new FileReader(file); BufferedReader br = new BufferedReader(fr)) {
			String line;
			while ((line = br.readLine()) != null) {
				newFuelLevel = line;
			}
		} catch (IOException e) {
		e.printStackTrace();
		}
		return newFuelLevel;
	}
	
	public float getSuperProgress() {
		superProgress = Float.valueOf(readFuelLevel("Super")) / superTank.getCapacity();
		return superProgress;
	}
	

}

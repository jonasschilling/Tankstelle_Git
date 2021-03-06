package javafx;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author Jonas Schilling, Patrick Berlet
 *
 */
public class TankModel {

	private static TankModel instance;

	Tank superTank = new Tank("Super", 12000.0f);
	Tank dieselTank = new Tank("Diesel", 8000.0f);
	

	private String newFuelLevel;
	

	public TankModel() {

	}

	public static TankModel getInstance() {
		if (TankModel.instance == null) {
			TankModel.instance = new TankModel();
		}
		return TankModel.instance;
	}
	
	public Tank getTank(String description) {
		if(description.equals("Super")) {
			return superTank;
		} else {
			return dieselTank;
		}
	}

	// Erzeugt Textdatei mit Verkaufspreis fuer die jeweilige Kraftstoffart
	public void writePrice(String tankDescription, String newPrice) {
		File file = new File("src/javafx/resources/Preise/" + tankDescription + "Preis.txt");
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
	
	// Liest Preis der jeweiligen Kraftstoffart aus Textdatei aus
	public String readPrice(String tankDescription) {
		File file = new File("src/javafx/resources/Preise/" + tankDescription + "Preis.txt");
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
	
	// Erzeugt Textdatei mit Fuellstand fuer die jeweilige Kraftstoffart
	public void writeFuelLevel(String tankDescription, String newFuelLevel) {
		File file = new File("src/javafx/resources/" + tankDescription + "Level.txt");
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
	
	// Liest Fuellstand der jeweiligen Kraftstoffart aus Textdatei aus
	public String readFuelLevel(String tankDescription) {
		File file = new File("src/javafx/resources/" + tankDescription + "Level.txt");
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
	
	public float getProgress(Tank tank) {
		float progress = Float.valueOf(readFuelLevel(tank.getDescription())) / tank.getCapacity();
		return progress;
	}
	
	
	
}

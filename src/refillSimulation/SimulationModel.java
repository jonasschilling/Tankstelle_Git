package refillSimulation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class SimulationModel {
	
	private static SimulationModel instance;
	
	String amountRefilled;
	
	public static SimulationModel getInstance() {
		if (SimulationModel.instance == null) {
			SimulationModel.instance = new SimulationModel();
		}
		return SimulationModel.instance;
	}
	
	public SimulationModel() {
		
	}
	
	public void writePumpData(String pumpNr, float amountRefilled) {
		File file = new File("src/javafx/resources/PumpData/pumpData" + pumpNr + ".txt");
		FileWriter fw = null;
		BufferedWriter bw = null;

		try {
			fw = new FileWriter(file);
			bw = new BufferedWriter(fw);
			bw.write(String.valueOf(amountRefilled));
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
	
	public String readPumpData(int pumpNr) {
		File file = new File("src/javafx/resources/PumpData/pumpData" + pumpNr + ".txt");
		try (FileReader fr = new FileReader(file); BufferedReader br = new BufferedReader(fr)) {
			String line;
			while ((line = br.readLine()) != null) {
				amountRefilled = line;
			}
		} catch (IOException e) {
		e.printStackTrace();
		}
		return amountRefilled;
	}
	
}

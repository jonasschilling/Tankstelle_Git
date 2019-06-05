package refillSimulation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javafx.Employee;
import javafx.EmployeeController;

public class SimulationModel {

	private static SimulationModel instance;

	String amountRefilled;

	private String gasKind, readAmount, readPriceComp;

	public static SimulationModel getInstance() {
		if (SimulationModel.instance == null) {
			SimulationModel.instance = new SimulationModel();
		}
		return SimulationModel.instance;
	}

	public SimulationModel() {

	}

	public void writePumpData(String pumpNr, String gasKind, float amountRefilled, float priceComp) {
		File file = new File("src/javafx/resources/PumpData/pumpData" + pumpNr + ".txt");
		FileWriter fw = null;
		BufferedWriter bw = null;
		try {
			fw = new FileWriter(file);
			bw = new BufferedWriter(fw);
			bw.write(gasKind + ";" + String.valueOf(amountRefilled) + ";" + String.valueOf(priceComp) + ";");
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

	public void readPumpData(int pumpNr) throws IOException {
		File file = new File("src/javafx/resources/PumpData/pumpData" + pumpNr + ".txt");
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		String line;
		while ((line = br.readLine()) != null) {
			String[] parts = line.split(";");
			setGasKind(parts[0]);
			setReadAmount(parts[1]);
			setReadPriceComp(parts[2]);
		}
		br.close();
	}

	public void setGasKind(String gasKind) {
		this.gasKind = gasKind;
	}

	public String getGasKind() {
		return gasKind;
	}

	public String getReadAmount() {
		return readAmount;
	}

	public void setReadAmount(String readAmount) {
		this.readAmount = readAmount;
	}

	public String getReadPriceComp() {
		return readPriceComp;
	}

	public void setReadPriceComp(String readPriceComp) {
		this.readPriceComp = readPriceComp;
	}

}

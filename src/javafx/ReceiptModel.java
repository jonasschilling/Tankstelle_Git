package javafx;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import refillSimulation.SimulationModel;

public class ReceiptModel {
	private static String path;
	private static ReceiptModel instance;
	private static int noReceipts;
	private float liter;
	private int numWodka;
	private int numFilip;
	private int numJupiter;
	private int numBull;
	private int numPizza;
	private float total;
	private static ArrayList<Receipt> receipts = new ArrayList<Receipt>();
	SalesModel salesModel = SalesModel.getInstance();
	SimulationModel simulationModel = SimulationModel.getInstance();
	TankModel tankModel = TankModel.getInstance();

	public static ReceiptModel getInstance() {
		if (ReceiptModel.instance == null) {
			ReceiptModel.instance = new ReceiptModel();
		}
		return ReceiptModel.instance;
	}

	public void setPath(String path) {
		ReceiptModel.path = path;
	}

	public String generateText() {
		ArrayList<String> receiptArray = new ArrayList<String>();
		String line;
		try (BufferedReader br = new BufferedReader(new FileReader(path))) {
			while ((line = br.readLine()) != null) {
				receiptArray.add(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		String text = "";
		for (String receiptLine : receiptArray) {
			text += receiptLine + "\n";
		}
		return text;
	}

	public void writeReceipt() {
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter df = DateTimeFormatter.ofPattern("dd.MM.yyyy kk:mm");
		String date = now.format(df);
		noReceipts++;
		File file = new File("src/javafx/resources/Receipt" + noReceipts + ".txt");
		FileWriter fw = null;
		BufferedWriter bw = null;
		try {
			fw = new FileWriter(file);
			bw = new BufferedWriter(fw);
			bw.write("Tankstelle" + "\n" + "\n" + "Belegnummer: " + noReceipts + "\n" + "Datum: " + date + "\n" + "\n");
			Product current = null;
			if (liter > 0) {
				bw.write(
						simulationModel.getGasKind() + " - " + liter + " Liter - "+ tankModel.readPrice(simulationModel.getGasKind())
								+ " EUR/Liter - " + simulationModel.getReadPriceComp() + " EUR \n");
			}
			if (numWodka > 0) {
				current = salesModel.getProduct("Wodka");
				String type = (numWodka > 1) ? current.getType() + "n" : current.getType();
				float price = (float) Math.round(numWodka * Float.valueOf(salesModel.readPrice("Wodka")) * 100) / 100;
				bw.write("Wodka Jelzin - " + numWodka + " " + type + " " + price / numWodka + " EUR/"
						+ current.getType() + " - " + price + " EUR" + "\n");
			}
			if (numFilip > 0) {
				current = salesModel.getProduct("Filip");
				String type = (numFilip > 1) ? current.getType() + "en" : current.getType();
				float price = (float) Math.round(numFilip * Float.valueOf(salesModel.readPrice("Filip")) * 100) / 100;
				bw.write("Filip Jelzin - " + numFilip + " " + type + " " + price / numFilip + " EUR/"
						+ current.getType() + " - " + price + " EUR" + "\n");
			}
			if (numJupiter > 0) {
				current = salesModel.getProduct("Jupiter");
				float price = (float) Math.round(numJupiter * Float.valueOf(salesModel.readPrice("Jupiter")) * 100)
						/ 100;
				bw.write("Jupiter Jelzin - " + numJupiter + " " + current.getType() + " " + price / numJupiter + " EUR/"
						+ current.getType() + " - " + price + " EUR" + "\n");
			}
			if (numBull > 0) {
				current = salesModel.getProduct("Bull");
				String type = (numBull > 1) ? current.getType() + "n" : current.getType();
				float price = (float) Math.round(numBull * Float.valueOf(salesModel.readPrice("Bull")) * 100) / 100;
				bw.write("Bull Jelzin - " + numBull + " " + type + " " + price / numBull + " EUR/" + current.getType()
						+ " - " + price + " EUR" + "\n");
			}
			if (numPizza > 0) {
				current = salesModel.getProduct("Pizza");
				float price = (float) Math.round(numPizza * Float.valueOf(salesModel.readPrice("Pizza")) * 100) / 100;
				bw.write("Pizza Jelzin - " + numPizza + " " + current.getType() + " " + price / numPizza + " EUR/"
						+ current.getType() + " - " + price + " EUR" + "\n");
			}
			String stringTotal = String.valueOf(total);
			bw.write("Gesamtpreis: " + stringTotal);
			String p = file.getPath();
			Receipt R1 = new Receipt(date, "Einkaufsbeleg", p);
			receipts.add(R1);
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

	public ArrayList<Receipt> getReceipts() {
		return receipts;

	}

	public void getAmount(float liter, int numWodka, int numFilip, int numJupiter, int numBull, int numPizza,
			float total) {
		this.liter = liter;
		this.numWodka = numWodka;
		this.numFilip = numFilip;
		this.numJupiter = numJupiter;
		this.numBull = numBull;
		this.numPizza = numPizza;
		this.total = total;

	}

}
